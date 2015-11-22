package com.btpns.Dashboard.server.home;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_portfolio_summary")
public class PortfolioHomeSummaryModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="count_non_booking")
	private Integer countNonBooking;
	@Column(name="count_cif")
	private Integer countCif;
	@Column(name="count_saving")
	private Integer countSaving;
	@Column(name="count_loan")
	private Integer countLoan;
	@Column(name="sum_saving")
	private BigDecimal sumSaving;
	@Column(name="sum_loan")
	private BigDecimal sumLoan;
	
	public PortfolioHomeSummaryModel(){}

	public PortfolioHomeSummaryModel(Integer id, Date tgl, Integer countNonBooking,
			Integer countCif, Integer countSaving, Integer countLoan,
			BigDecimal sumSaving, BigDecimal sumLoan) {
		super();
		this.id = id;
		this.tgl = tgl;
		this.countNonBooking = countNonBooking;
		this.countCif = countCif;
		this.countSaving = countSaving;
		this.countLoan = countLoan;
		this.sumSaving = sumSaving;
		this.sumLoan = sumLoan;
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

	public Integer getCountNonBooking() {
		return countNonBooking;
	}

	public void setCountNonBooking(Integer countNonBooking) {
		this.countNonBooking = countNonBooking;
	}

	public Integer getCountCif() {
		return countCif;
	}

	public void setCountCif(Integer countCif) {
		this.countCif = countCif;
	}

	public Integer getCountSaving() {
		return countSaving;
	}

	public void setCountSaving(Integer countSaving) {
		this.countSaving = countSaving;
	}

	public Integer getCountLoan() {
		return countLoan;
	}

	public void setCountLoan(Integer countLoan) {
		this.countLoan = countLoan;
	}

	public BigDecimal getSumSaving() {
		return sumSaving;
	}

	public void setSumSaving(BigDecimal sumSaving) {
		this.sumSaving = sumSaving;
	}

	public BigDecimal getSumLoan() {
		return sumLoan;
	}

	public void setSumLoan(BigDecimal sumLoan) {
		this.sumLoan = sumLoan;
	}
}
