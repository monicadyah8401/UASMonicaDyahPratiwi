package uas.momon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uas.momon.model.TabunganModel;

public interface TabunganRepository extends JpaRepository<TabunganModel, Long>{
//JpaRepository<namaClassModel, TipeData>
	
	@Query(value="select *from tabungan_tbl where nik =:nik", nativeQuery = true)
	List<TabunganModel> getDataByNik(@Param("nik")String nik);
	
	@Query(value="SELECT *FROM tabungan_tbl WHERE nik =:nik ORDER BY id DESC LIMIT 1", nativeQuery = true)
	TabunganModel getLastSaldo(@Param("nik")String nik);
	//no List karna yang terselect cuman 1 baris data
	
	
}
