package com.btpns.Dashboard.client.model.snapshot;

import java.math.BigDecimal;

import com.btpns.Dashboard.client.model.IModelClient;

public class SnapshotWismaDetail implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer no;
	private String kcs;
	private String kcsName;
	private String wisma;
	private String wismaName;
	private Integer totalCustomer;
	private Integer totalGroup;
	private Integer totalSentra;
	private Integer totalSaving;
	private BigDecimal amountSaving;
	private Integer totalLoan;
	private BigDecimal amountOs;
	private BigDecimal amountDisburse;
	
	public SnapshotWismaDetail() {}

	public SnapshotWismaDetail(Integer no,Integer id, String kcs,
			String kcsName, String wisma, String wismaName,
			Integer totalCustomer, Integer totalGroup, Integer totalSentra,
			Integer totalSaving, BigDecimal amountSaving, Integer totalLoan,
			BigDecimal amountOs, BigDecimal amountDisburse) {
		super();
		this.id = id;
		this.no = no;
		this.kcs = kcs;
		this.kcsName = kcsName;
		this.wisma = wisma;
		this.wismaName = wismaName;
		this.totalCustomer = totalCustomer;
		this.totalGroup = totalGroup;
		this.totalSentra = totalSentra;
		this.totalSaving = totalSaving;
		this.amountSaving = amountSaving;
		this.totalLoan = totalLoan;
		this.amountOs = amountOs;
		this.amountDisburse = amountDisburse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getKcs() {
		return kcs;
	}

	public void setKcs(String kcs) {
		this.kcs = kcs;
	}

	public String getKcsName() {
		return kcsName;
	}

	public void setKcsName(String kcsName) {
		this.kcsName = kcsName;
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

	public Integer getTotalCustomer() {
		return totalCustomer;
	}

	public void setTotalCustomer(Integer totalCustomer) {
		this.totalCustomer = totalCustomer;
	}

	public Integer getTotalGroup() {
		return totalGroup;
	}

	public void setTotalGroup(Integer totalGroup) {
		this.totalGroup = totalGroup;
	}

	public Integer getTotalSentra() {
		return totalSentra;
	}

	public void setTotalSentra(Integer totalSentra) {
		this.totalSentra = totalSentra;
	}

	public Integer getTotalSaving() {
		return totalSaving;
	}

	public void setTotalSaving(Integer totalSaving) {
		this.totalSaving = totalSaving;
	}

	public BigDecimal getAmountSaving() {
		return amountSaving;
	}

	public void setAmountSaving(BigDecimal amountSaving) {
		this.amountSaving = amountSaving;
	}

	public Integer getTotalLoan() {
		return totalLoan;
	}

	public void setTotalLoan(Integer totalLoan) {
		this.totalLoan = totalLoan;
	}

	public BigDecimal getAmountOs() {
		return amountOs;
	}

	public void setAmountOs(BigDecimal amountOs) {
		this.amountOs = amountOs;
	}

	public BigDecimal getAmountDisburse() {
		return amountDisburse;
	}

	public void setAmountDisburse(BigDecimal amountDisburse) {
		this.amountDisburse = amountDisburse;
	}
}