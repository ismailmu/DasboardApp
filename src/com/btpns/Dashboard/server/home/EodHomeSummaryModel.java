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
@Table(name="rpt_eod_summary")
public class EodHomeSummaryModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="start_time")
	private Date startTime;
	@Column(name="end_time")
	private Date endTime;
	@Column(name="tot_wisma")
	private Integer totWisma;
	@Column(name="sum_trans")
	private BigDecimal sumTrans;
	@Column(name="tot_files")
	private Integer totFiles;
	@Column(name="tot_non_wisma")
	private Integer totNonWisma;
	
	public EodHomeSummaryModel(){}
	
	public EodHomeSummaryModel(Integer id, Date tgl, Integer jam, Integer menit,
			Date startTime, Date endTime, Integer totWisma,
			BigDecimal sumTrans, Integer totFiles, Integer totNonWisma) {
		super();
		this.id = id;
		this.tgl = tgl;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totWisma = totWisma;
		this.sumTrans = sumTrans;
		this.totFiles = totFiles;
		this.totNonWisma = totNonWisma;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTotWisma() {
		return totWisma;
	}

	public void setTotWisma(Integer totWisma) {
		this.totWisma = totWisma;
	}

	public BigDecimal getSumTrans() {
		return sumTrans;
	}

	public void setSumTrans(BigDecimal sumTrans) {
		this.sumTrans = sumTrans;
	}

	public Integer getTotFiles() {
		return totFiles;
	}

	public void setTotFiles(Integer totFiles) {
		this.totFiles = totFiles;
	}

	public Integer getTotNonWisma() {
		return totNonWisma;
	}

	public void setTotNonWisma(Integer totNonWisma) {
		this.totNonWisma = totNonWisma;
	}
}
