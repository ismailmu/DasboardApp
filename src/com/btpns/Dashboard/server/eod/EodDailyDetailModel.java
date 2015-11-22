package com.btpns.Dashboard.server.eod;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_eod")
public class EodDailyDetailModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="tgl",nullable=false)
	private Date tgl;
	@Column(name="office_code",nullable=false,length=5)
	private String wisma;
	@Column(name="display_name",nullable=false,length=200)
	private String wismaName;
	@Column(name="sum_transaction",nullable=false)
	private BigDecimal sumTrans;
	@Column(name="count_file",nullable=false)
	private Integer countFile;

	public EodDailyDetailModel() {
	}

	public EodDailyDetailModel(String wisma, String wismaName, BigDecimal sumTrans,
			Integer countFile) {
		this.wisma = wisma;
		this.wismaName = wismaName;
		this.countFile = countFile;
		this.sumTrans = sumTrans;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getTgl() {
		return tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}
	

	public String getWisma() {
		return wisma;
	}

	public void setWisma(String wisma) {
		this.wisma = wisma;
	}

	public String getWismaName() {
		return wismaName;
	}

	public void setWismaName(String wismaName) {
		this.wismaName = wismaName;
	}

	public BigDecimal getSumTrans() {
		return sumTrans;
	}

	public void setSumTrans(BigDecimal sumTrans) {
		this.sumTrans = sumTrans;
	}

	public Integer getCountFile() {
		return countFile;
	}

	public void setSumFile(Integer countFile) {
		this.countFile = countFile;
	}
}
