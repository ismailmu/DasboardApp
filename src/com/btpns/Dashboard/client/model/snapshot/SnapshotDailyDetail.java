package com.btpns.Dashboard.client.model.snapshot;

import java.math.BigDecimal;

import com.btpns.Dashboard.client.model.IModelClient;

public class SnapshotDailyDetail implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer no;
	private Integer id;
	private String kcs;
	private String kcsName;
	private String wisma;
	private String wismaName;
	private Integer countNewSaving;
	private Integer countDeposit;
	private BigDecimal sumDeposit;
	private Integer countWithdraw;
	private BigDecimal sumWithdraw;
	private Integer countNewLoan;
	private Integer countDisburse;
	private BigDecimal sumDisburse;
	private Integer countRepayment;
	private BigDecimal sumRepayment;
	private Integer countEarlyPayment;
	private BigDecimal sumEarlyPayment;
	private Integer countCc;
	private Integer countCoverIn;
	private BigDecimal sumCoverIn;
	private Integer countCoverOut;
	private BigDecimal sumCoverOut;
	
	public SnapshotDailyDetail() {}

	public SnapshotDailyDetail(Integer no, Integer id, String kcs,String kcsName,
			String wisma, String wismaName, Integer countNewSaving,
			Integer countDeposit, BigDecimal sumDeposit, Integer countWithdraw,
			BigDecimal sumWithdraw, Integer countNewLoan,
			Integer countDisburse, BigDecimal sumDisburse,
			Integer countRepayment, BigDecimal sumRepayment,
			Integer countEarlyPayment, BigDecimal sumEarlyPayment,
			Integer countCc, Integer countCoverIn, BigDecimal sumCoverIn,
			Integer countCoverOut, BigDecimal sumCoverOut) {
		super();
		this.no = no;
		this.id = id;
		this.kcs = kcs;
		this.kcsName = kcsName;
		this.wisma = wisma;
		this.wismaName = wismaName;
		this.countNewSaving = countNewSaving;
		this.countDeposit = countDeposit;
		this.sumDeposit = sumDeposit;
		this.countWithdraw = countWithdraw;
		this.sumWithdraw = sumWithdraw;
		this.countNewLoan = countNewLoan;
		this.countDisburse = countDisburse;
		this.sumDisburse = sumDisburse;
		this.countRepayment = countRepayment;
		this.sumRepayment = sumRepayment;
		this.countEarlyPayment = countEarlyPayment;
		this.sumEarlyPayment = sumEarlyPayment;
		this.countCc = countCc;
		this.countCoverIn = countCoverIn;
		this.sumCoverIn = sumCoverIn;
		this.countCoverOut = countCoverOut;
		this.sumCoverOut = sumCoverOut;
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

	public Integer getCountNewSaving() {
		return countNewSaving;
	}

	public void setCountNewSaving(Integer countNewSaving) {
		this.countNewSaving = countNewSaving;
	}

	public Integer getCountDeposit() {
		return countDeposit;
	}

	public void setCountDeposit(Integer countDeposit) {
		this.countDeposit = countDeposit;
	}

	public BigDecimal getSumDeposit() {
		return sumDeposit;
	}

	public void setSumDeposit(BigDecimal sumDeposit) {
		this.sumDeposit = sumDeposit;
	}

	public Integer getCountWithdraw() {
		return countWithdraw;
	}

	public void setCountWithdraw(Integer countWithdraw) {
		this.countWithdraw = countWithdraw;
	}

	public BigDecimal getSumWithdraw() {
		return sumWithdraw;
	}

	public void setSumWithdraw(BigDecimal sumWithdraw) {
		this.sumWithdraw = sumWithdraw;
	}

	public Integer getCountNewLoan() {
		return countNewLoan;
	}

	public void setCountNewLoan(Integer countNewLoan) {
		this.countNewLoan = countNewLoan;
	}

	public Integer getCountDisburse() {
		return countDisburse;
	}

	public void setCountDisburse(Integer countDisburse) {
		this.countDisburse = countDisburse;
	}

	public BigDecimal getSumDisburse() {
		return sumDisburse;
	}

	public void setSumDisburse(BigDecimal sumDisburse) {
		this.sumDisburse = sumDisburse;
	}

	public Integer getCountRepayment() {
		return countRepayment;
	}

	public void setCountRepayment(Integer countRepayment) {
		this.countRepayment = countRepayment;
	}

	public BigDecimal getSumRepayment() {
		return sumRepayment;
	}

	public void setSumRepayment(BigDecimal sumRepayment) {
		this.sumRepayment = sumRepayment;
	}

	public Integer getCountEarlyPayment() {
		return countEarlyPayment;
	}

	public void setCountEarlyPayment(Integer countEarlyPayment) {
		this.countEarlyPayment = countEarlyPayment;
	}

	public BigDecimal getSumEarlyPayment() {
		return sumEarlyPayment;
	}

	public void setSumEarlyPayment(BigDecimal sumEarlyPayment) {
		this.sumEarlyPayment = sumEarlyPayment;
	}

	public Integer getCountCc() {
		return countCc;
	}

	public void setCountCc(Integer countCc) {
		this.countCc = countCc;
	}

	public Integer getCountCoverIn() {
		return countCoverIn;
	}

	public void setCountCoverIn(Integer countCoverIn) {
		this.countCoverIn = countCoverIn;
	}

	public BigDecimal getSumCoverIn() {
		return sumCoverIn;
	}

	public void setSumCoverIn(BigDecimal sumCoverIn) {
		this.sumCoverIn = sumCoverIn;
	}

	public Integer getCountCoverOut() {
		return countCoverOut;
	}

	public void setCountCoverOut(Integer countCoverOut) {
		this.countCoverOut = countCoverOut;
	}

	public BigDecimal getSumCoverOut() {
		return sumCoverOut;
	}

	public void setSumCoverOut(BigDecimal sumCoverOut) {
		this.sumCoverOut = sumCoverOut;
	}
}
