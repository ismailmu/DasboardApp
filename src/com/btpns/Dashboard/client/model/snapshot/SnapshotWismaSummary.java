package com.btpns.Dashboard.client.model.snapshot;

import java.math.BigDecimal;

import com.btpns.Dashboard.client.model.IModelClient;

public class SnapshotWismaSummary implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer totalWisma;
	private Integer totalCustomer;
	private Integer totalGroup;
	private Integer totalSentra;
	private Integer totalSaving;
	private BigDecimal totalAmountSaving;
	private Integer totalLoan;
	private BigDecimal totalAmountOs;
	private BigDecimal totalAmountDisburse;
	
	public SnapshotWismaSummary(){}
	
	public SnapshotWismaSummary(Integer id,Integer totalWisma, Integer totalCustomer,Integer totalGroup,
			Integer totalSentra, Integer totalSaving, BigDecimal totalAmountSaving,
			Integer totalLoan, BigDecimal totalAmountOs, BigDecimal totalAmountDisburse) {
		super();
		this.id = id;
		this.totalWisma = totalWisma;
		this.totalCustomer = totalCustomer;
		this.totalGroup = totalGroup;
		this.totalSentra = totalSentra;
		this.totalSaving = totalSaving;
		this.totalAmountSaving = totalAmountSaving;
		this.totalLoan = totalLoan;
		this.totalAmountOs = totalAmountOs;
		this.totalAmountDisburse = totalAmountDisburse;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalWisma() {
		return totalWisma;
	}

	public void setTotalWisma(Integer totalWisma) {
		this.totalWisma = totalWisma;
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

	public BigDecimal getTotalAmountSaving() {
		return totalAmountSaving;
	}

	public void setTotalAmountSaving(BigDecimal totalAmountSaving) {
		this.totalAmountSaving = totalAmountSaving;
	}

	public Integer getTotalLoan() {
		return totalLoan;
	}

	public void setTotalLoan(Integer totalLoan) {
		this.totalLoan = totalLoan;
	}

	public BigDecimal getTotalAmountOs() {
		return totalAmountOs;
	}

	public void setTotalAmountOs(BigDecimal totalAmountOs) {
		this.totalAmountOs = totalAmountOs;
	}

	public BigDecimal getTotalAmountDisburse() {
		return totalAmountDisburse;
	}

	public void setTotalAmountDisburse(BigDecimal totalAmountDisburse) {
		this.totalAmountDisburse = totalAmountDisburse;
	}
	
}
