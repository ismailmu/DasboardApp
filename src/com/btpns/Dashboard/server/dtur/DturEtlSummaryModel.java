package com.btpns.Dashboard.server.dtur;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_dtur_etl_summary")
public class DturEtlSummaryModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="job_name")
	private String jobName;
	@Column(name="start_date")
	private Date maxStartDate;
	@Column(name="end_date")
	private Date maxEndDate;
	@Column(name="count_process")
	private Integer countProcess;
	@Column(name="min_process")
	private Date startEtl;
	@Column(name="max_process")
	private Date endEtl;
	
	public DturEtlSummaryModel() {}

	public DturEtlSummaryModel(Integer id, Date tgl, String jobName,
			Date maxStartDate, Date maxEndDate, Integer countProcess,
			Date startEtl, Date endEtl) {
		super();
		this.id = id;
		this.tgl = tgl;
		this.jobName = jobName;
		this.maxStartDate = maxStartDate;
		this.maxEndDate = maxEndDate;
		this.countProcess = countProcess;
		this.startEtl = startEtl;
		this.endEtl = endEtl;
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Date getMaxStartDate() {
		return maxStartDate;
	}

	public void setMaxStartDate(Date maxStartDate) {
		this.maxStartDate = maxStartDate;
	}

	public Date getMaxEndDate() {
		return maxEndDate;
	}

	public void setMaxEndDate(Date maxEndDate) {
		this.maxEndDate = maxEndDate;
	}

	public Integer getCountProcess() {
		return countProcess;
	}

	public void setCountProcess(Integer countProcess) {
		this.countProcess = countProcess;
	}

	public Date getStartEtl() {
		return startEtl;
	}

	public void setStartEtl(Date startEtl) {
		this.startEtl = startEtl;
	}

	public Date getEndEtl() {
		return endEtl;
	}

	public void setEndEtl(Date endEtl) {
		this.endEtl = endEtl;
	}
}