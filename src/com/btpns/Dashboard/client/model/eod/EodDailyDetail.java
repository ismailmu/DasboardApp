package com.btpns.Dashboard.client.model.eod;

import java.math.BigDecimal;
import java.util.Date;

import com.btpns.Dashboard.client.model.IModelClient;

public class EodDailyDetail implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer no;
	private Integer id;
	private Date tgl;
	private String wisma;
	private String wismaName;
	private BigDecimal sumTrans;
	private Integer countFile;

	public EodDailyDetail() {
	}

	public EodDailyDetail(Integer no,Integer id, Date tgl,String wisma, String wismaName,
			BigDecimal sumTrans, Integer countFile) {
		this.no = no;
		this.id = id;
		this.tgl = tgl;
		this.wisma = wisma;
		this.wismaName = wismaName;
		this.countFile = countFile;
		this.sumTrans = sumTrans;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
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

	public void setCountFile(Integer countFile) {
		this.countFile = countFile;
	}
}
