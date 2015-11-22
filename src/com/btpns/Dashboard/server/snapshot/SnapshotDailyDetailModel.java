package com.btpns.Dashboard.server.snapshot;

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
@Table(name="rpt_portfolio_daily")
public class SnapshotDailyDetailModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="kcs",length=4)
	private String kcs;
	@Column(name="kcs_name",length=200)
	private String kcsName;
	@Column(name="office_code",length=5)
	private String wisma;
	@Column(name="display_name",length=200)
	private String wismaName;
	@Column(name="count_new_saving")
	private Integer countNewSaving;
	@Column(name="count_deposit")
	private Integer countDeposit;
	@Column(name="sum_deposit")
	private BigDecimal sumDeposit;
	@Column(name="count_withdraw")
	private Integer countWithdraw;
	@Column(name="sum_withdraw")
	private BigDecimal sumWithdraw;
	@Column(name="count_new_loan")
	private Integer countNewLoan;
	@Column(name="count_disburse")
	private Integer countDisburse;
	@Column(name="sum_disburse")
	private BigDecimal sumDisburse;
	@Column(name="count_repayment")
	private Integer countRepayment;
	@Column(name="sum_repayment")
	private BigDecimal sumRepayment;
	@Column(name="count_earlypayment")
	private Integer countEarlyPayment;
	@Column(name="sum_earlypayment")
	private BigDecimal sumEarlyPayment;
	@Column(name="count_cc")
	private Integer countCc;
	@Column(name="count_coverin")
	private Integer countCoverIn;
	@Column(name="sum_coverin")
	private BigDecimal sumCoverIn;
	@Column(name="count_coverout")
	private Integer countCoverOut;
	@Column(name="sum_coverout")
	private BigDecimal sumCoverOut;
	
	public SnapshotDailyDetailModel() {}

	public SnapshotDailyDetailModel(Integer id, Date tgl, String kcs,
			String kcsName, String wisma, String wismaName,
			Integer countNewSaving, Integer countDeposit, BigDecimal sumDeposit,
			Integer countWithdraw, BigDecimal sumWithdraw, Integer countNewLoan,
			Integer countDisburse, BigDecimal sumDisburse, Integer countRepayment,
			BigDecimal sumRepayment, Integer countEarlyPayment,
			BigDecimal sumEarlyPayment, Integer countCc, Integer countCoverIn,
			BigDecimal sumCoverIn, Integer countCoverOut, BigDecimal sumCoverOut) {
		super();
		this.id = id;
		this.tgl = tgl;
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
