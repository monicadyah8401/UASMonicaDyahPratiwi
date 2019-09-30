package uas.momon.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uas.momon.dao.TabunganDao;
import uas.momon.model.TabunganModel;

@RestController
@RequestMapping("/bank")
//tabungan = alamat API
public class TabunganController {
	
	//POST   : create
	//GET    : read
	//PUT    : update
	//DELETE : delete
	
	@Autowired
	TabunganDao tabunganDao;
	
	//create
	@PostMapping("/tabungan")
	//alamat API u save
	public TabunganModel saveTabungan(@Valid @RequestBody TabunganModel tabunganModel) {
		return tabunganDao.save(tabunganModel);
	}
	
	//read all
	@GetMapping("/tabungan")
	public List<TabunganModel> getAll(){
		return tabunganDao.getAll();
	}
	
	//read by id
	@GetMapping("/tabungan/{id}")//{param}
	public ResponseEntity<TabunganModel> getDataById(@PathVariable(value="id")Long id){
		TabunganModel tab = tabunganDao.getDataById(id);
		if(tab == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(tab);
		}
	}
	
	//read by nik
	@GetMapping("/tabunganByNik/{nik}")
	public List<TabunganModel> getDataByNik(@PathVariable(value="nik")String nik){
		return tabunganDao.getDataByNik(nik);
	}
	
	//update
	@PutMapping("/tabungan/{id}")
	public ResponseEntity<TabunganModel> update(@PathVariable(value="id")Long id, @Valid @RequestBody TabunganModel tabunganModel){
		TabunganModel tb1=tabunganDao.getDataById(id);
		//mengambil data sblm di ubah
		if(tb1==null) {
			return ResponseEntity.notFound().build();
		}else {
			tb1.setSaldo(tb1.getSaldo() - tb1.getKredit() + tb1.getDebet());
			//u mengembalikan saldo terakhir
			tb1.setDebet(tabunganModel.getDebet());
			tb1.setKredit(tabunganModel.getKredit());
			TabunganModel Hasil=tabunganDao.update(tb1);
			return ResponseEntity.ok().body(Hasil);
		}

	}
	
	
	//delete
	@DeleteMapping("/tabungan/{id}")
	public ResponseEntity<TabunganModel> delet(@PathVariable(value="id")Long id){
		TabunganModel tab = tabunganDao.getDataById(id);
		if(tab == null) {
			return ResponseEntity.notFound().build();
		}else {
			tabunganDao.delete(id);
			return ResponseEntity.ok().build();
			//knp gk body? karna kalau hapus data sudah tidak ada/hilang
		}
	}
}
