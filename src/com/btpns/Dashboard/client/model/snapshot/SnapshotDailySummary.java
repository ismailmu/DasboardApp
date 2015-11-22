package com.btpns.Dashboard.client.model.snapshot;

import java.math.BigDecimal;

import com.btpns.Dashboard.client.model.IModelClient;

public class SnapshotDailySummary implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer totalWisma;
	private Integer totalCountNewSaving;
	private Integer totalCountDeposit;
	private BigDecimal totalSumDeposit;
	private Integer totalCountWithdraw;
	private BigDecimal totalSumWithdraw;
	private Integer totalCountNewLoan;
	private Integer totalCountDisburse;
	private BigDecimal totalSumDisburse;
	private Integer totalCountRepayment;
	private BigDecimal totalSumRepayment;
	private Integer totalCountEarlyPayment;
	private BigDecimal totalSumEarlyPayment;
	private Integer totalCountCc;
	private Integer totalCountCoverIn;
	private BigDecimal totalSumCoverIn;
	private Integer totalCountCoverOut;
	private BigDecimal totalSumCoverOut;
	
	public SnapshotDailySummary(){}
	
	public SnapshotDailySummary(Integer id,Integer totalWisma,
			Integer totalCountNewSaving, Integer totalCountDeposit,
			BigDecimal totalSumDeposit, Integer totalCountWithdraw,
			BigDecimal totalSumWithdraw, Integer totalCountNewLoan,
			Integer totalCountDisburse, BigDecimal totalSumDisburse,
			Integer totalCountRepayment, BigDecimal totalSumRepayment,
			Integer totalCountEarlyPayment, BigDecimal totalSumEarlyPayment,
			Integer totalCountCc, Integer totalCountCoverIn,
			BigDecimal totalSumCoverIn, Integer totalCountCoverOut,
			BigDecimal totalSumCoverOut) {
		
		super();
		this.id = id;
		this.totalWisma = totalWisma;
		this.totalCountNewSaving = totalCountNewSaving;
		this.totalCountDeposit = totalCountDeposit;
		this.totalSumDeposit = totalSumDeposit;
		this.totalCountWithdraw = totalCountWithdraw;
		this.totalSumWithdraw = totalSumWithdraw;
		this.totalCountNewLoan = totalCountNewLoan;
		this.totalCountDisburse = totalCountDisburse;
		this.totalSumDisburse = totalSumDisburse;
		this.totalCountRepayment = totalCountRepayment;
		this.totalSumRepayment = totalSumRepayment;
		this.totalCountEarlyPayment = totalCountEarlyPayment;
		this.totalSumEarlyPayment = totalSumEarlyPayment;
		this.totalCountCc = totalCountCc;
		this.totalCountCoverIn = totalCountCoverIn;
		this.totalSumCoverIn = totalSumCoverIn;
		this.totalCountCoverOut = totalCountCoverOut;
		this.totalSumCoverOut = totalSumCoverOut;
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

	public Integer getTotalCountNewSaving() {
		return totalCountNewSaving;
	}

	public void setTotalCountNewSaving(Integer totalCountNewSaving) {
		this.totalCountNewSaving = totalCountNewSaving;
	}

	public Integer getTotalCountDeposit() {
		return totalCountDeposit;
	}

	public void setTotalCountDeposit(Integer totalCountDeposit) {
		this.totalCountDeposit = totalCountDeposit;
	}

	public BigDecimal getTotalSumDeposit() {
		return totalSumDeposit;
	}

	public void setTotalSumDeposit(BigDecimal totalSumDeposit) {
		this.totalSumDeposit = totalSumDeposit;
	}

	public Integer getTotalCountWithdraw() {
		return totalCountWithdraw;
	}

	public void setTotalCountWithdraw(Integer totalCountWithdraw) {
		this.totalCountWithdraw = totalCountWithdraw;
	}

	public BigDecimal getTotalSumWithdraw() {
		return totalSumWithdraw;
	}

	public void setTotalSumWithdraw(BigDecimal totalSumWithdraw) {
		this.totalSumWithdraw = totalSumWithdraw;
	}

	public Integer getTotalCountNewLoan() {
		return totalCountNewLoan;
	}

	public void setTotalCountNewLoan(Integer totalCountNewLoan) {
		this.totalCountNewLoan = totalCountNewLoan;
	}

	public Integer getTotalCountDisburse() {
		return totalCountDisburse;
	}

	public void setTotalCountDisburse(Integer totalCountDisburse) {
		this.totalCountDisburse = totalCountDisburse;
	}

	public BigDecimal getTotalSumDisburse() {
		return totalSumDisburse;
	}

	public void setTotalSumDisburse(BigDecimal totalSumDisburse) {
		this.totalSumDisburse = totalSumDisburse;
	}

	public Integer getTotalCountRepayment() {
		return totalCountRepayment;
	}

	public void setTotalCountRepayment(Integer totalCountRepayment) {
		this.totalCountRepayment = totalCountRepayment;
	}

	public BigDecimal getTotalSumRepayment() {
		return totalSumRepayment;
	}

	public void setTotalSumRepayment(BigDecimal totalSumRepayment) {
		this.totalSumRepayment = totalSumRepayment;
	}

	public Integer getTotalCountEarlyPayment() {
		return totalCountEarlyPayment;
	}

	public void setTotalCountEarlyPayment(Integer totalCountEarlyPayment) {
		this.totalCountEarlyPayment = totalCountEarlyPayment;
	}

	public BigDecimal getTotalSumEarlyPayment() {
		return totalSumEarlyPayment;
	}

	public void setTotalSumEarlyPayment(BigDecimal totalSumEarlyPayment) {
		this.totalSumEarlyPayment = totalSumEarlyPayment;
	}

	public Integer getTotalCountCc() {
		return totalCountCc;
	}

	public void setTotalCountCc(Integer totalCountCc) {
		this.totalCountCc = totalCountCc;
	}

	public Integer getTotalCountCoverIn() {
		return totalCountCoverIn;
	}

	public void setTotalCountCoverIn(Integer totalCountCoverIn) {
		this.totalCountCoverIn = totalCountCoverIn;
	}

	public BigDecimal getTotalSumCoverIn() {
		return totalSumCoverIn;
	}

	public void setTotalSumCoverIn(BigDecimal totalSumCoverIn) {
		this.totalSumCoverIn = totalSumCoverIn;
	}

	public Integer getTotalCountCoverOut() {
		return totalCountCoverOut;
	}

	public void setTotalCountCoverOut(Integer totalCountCoverOut) {
		this.totalCountCoverOut = totalCountCoverOut;
	}

	public BigDecimal getTotalSumCoverOut() {
		return totalSumCoverOut;
	}

	public void setTotalSumCoverOut(BigDecimal totalSumCoverOut) {
		this.totalSumCoverOut = totalSumCoverOut;
	}
}
