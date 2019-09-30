package uas.momon.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uas.momon.model.TabunganModel;
import uas.momon.repository.TabunganRepository;

@Service
public class TabunganDao {
	@Autowired
	TabunganRepository tabunganRepository;

	// create
	public TabunganModel save(TabunganModel tabunganModel) {
		TabunganModel tb1 = tabunganModel;
		TabunganModel tb2 = tabunganRepository.getLastSaldo(tabunganModel.getNik());
		// tb1 = data yg kita inputkan
		// tb2 = data terakhir d db berdasarkan nik yg sama
		if (tb2 == null) {
			tb1.setSaldo(0 + tb1.getKredit() - tb1.getDebet());
			return tabunganRepository.save(tb1);
		} else {
			tb1.setSaldo(tb2.getSaldo() + tb1.getKredit() - tb1.getDebet());
			return tabunganRepository.save(tb1);
		}
	}

	// read all
	public List<TabunganModel> getAll() {
		return tabunganRepository.findAll();
	}

	// read by id
	public TabunganModel getDataById(long id) {
		return tabunganRepository.findOne(id);
	}

	// read by nik
	public List<TabunganModel> getDataByNik(String nik) {
		return tabunganRepository.getDataByNik(nik);
	}

	// update
	public TabunganModel update(TabunganModel tabunganModel) {
		TabunganModel tb1 = tabunganModel;
		TabunganModel tb2 = tabunganRepository.findOne(tb1.getId());
		// tb1 = data inputan kita
		// tb2 = data yg akan di ubah dan yg nantinya akan d simpan db
		tb2.setSaldo(tb2.getSaldo() + tb1.getKredit() - tb1.getDebet());
		//u mengubah salo sesuai inputan yg baru
		tb2.setDebet(tb1.getDebet());
		tb2.setKredit(tb1.getKredit());
		Integer tampSaldo = tb2.getSaldo();
		List<TabunganModel> tampData = tabunganRepository.getDataByNik(tb2.getNik());
		for(TabunganModel data : tampData) {
			if(data.getId()>tb2.getId()) {
				TabunganModel tb3 = tabunganRepository.findOne(data.getId());
				tb3.setSaldo(tampSaldo + tb3.getKredit() - tb3.getDebet());
				tabunganRepository.save(tb3);
				tampSaldo=tb3.getSaldo();
			}
		}
		return tabunganRepository.save(tb2);
	}

	// delete
	public void delete(long id) {
		TabunganModel tb1 = tabunganRepository.findOne(id);
		//u menampung data yg akan di hapus
		int tampSaldo = tb1.getSaldo()-tb1.getKredit()+tb1.getDebet();
		//menampung saldo awal saat data akan dihapus
		List<TabunganModel> tampData = tabunganRepository.getDataByNik(tb1.getNik());
		//knp getDataByNik? karna dikelompokan berdasarkan nik
		for(TabunganModel data : tampData) {
			if(data.getId() > tb1.getId()) {
				TabunganModel tb2 = tabunganRepository.findOne(data.getId());
				//u menampung data dibawah baris data yg kita hapus
				tb2.setSaldo(tampSaldo+tb2.getKredit()-tb2.getDebet());
				//ubah saldo pd data dibawah baris data yg kita hapus
				tabunganRepository.save(tb2);
				//menyimpan data yg sdh d ganti
				tampSaldo = tb2.getSaldo();
				//mengambil saldo data dibawah baris data yg kita ubah
			}
		}
		tabunganRepository.delete(id);
	}

}
