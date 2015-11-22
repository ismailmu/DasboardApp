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
@Table(name="rpt_portfolio")
public class SnapshotWismaDetailModel implements IModelPersistence {

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
	@Column(name="office_code",length=5,nullable=false)
	private String wisma;
	@Column(name="display_name",length=200,nullable=false)
	private String wismaName;
	@Column(name="count_customer")
	private Integer totalCustomer;
	@Column(name="count_group")
	private Integer totalGroup;
	@Column(name="count_sentra")
	private Integer totalSentra;
	@Column(name="count_saving_wisma")
	private Integer totalSaving;
	@Column(name="sum_saving_wisma")
	private BigDecimal amountSaving;
	@Column(name="count_loan_wisma")
	private Integer totalLoan;
	@Column(name="sum_loan_os_wisma")
	private BigDecimal amountOs;
	@Column(name="sum_loan_disburse_wisma")
	private BigDecimal amountDisburse;
	
	public SnapshotWismaDetailModel() {}
	
	public SnapshotWismaDetailModel(Integer id, String kcs,String kcsName,String wisma, String wismaName,
			Integer totalCustomer,Integer totalGroup,Integer totalSentra, Integer totalSaving, BigDecimal amountSaving,
			Integer totalLoan, BigDecimal amountOs, BigDecimal amountDisburse) {
		super();
		this.id = id;
		this.kcs=kcs;
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