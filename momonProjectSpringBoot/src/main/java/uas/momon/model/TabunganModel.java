package uas.momon.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
//pilih yg javax.persistence

@Table(name="tabungan_tbl")
//value dr name=nama table
//pilih yg javax.persistence

@EntityListeners(AuditingEntityListener.class)
//pilih yg javax.persistence

public class TabunganModel {
	@Id
	//pilih yg javax.presistence
	@GeneratedValue(strategy=GenerationType.AUTO)
	//u/ auto increment
	private Long id;
	
	@NotBlank
	private String nik, nama;
	
	private Integer saldo;
	
	private Integer kredit, debet;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	//pilih yg javax.presistence
	private Date createTime;
	//Date pilih yg java.util

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public Integer getKredit() {
		return kredit;
	}

	public void setKredit(Integer kredit) {
		this.kredit = kredit;
	}

	public Integer getDebet() {
		return debet;
	}

	public void setDebet(Integer debet) {
		this.debet = debet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
