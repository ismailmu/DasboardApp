package com.btpns.Dashboard.server.dtur;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_dtur_etl")
public class DturEtlDetailModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_job")
	private Integer idJob;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="job_name")
	private String jobName;
	@Column(name="status")
	private String status;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	private Date endDate;
	
	public DturEtlDetailModel() {}

	public DturEtlDetailModel(Integer idJob, Date tgl, String jobName,
			String status, Date startDate, Date endDate) {
		super();
		this.idJob = idJob;
		this.tgl = tgl;
		this.jobName = jobName;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getIdJob() {
		return idJob;
	}

	public void setIdJob(Integer idJob) {
		this.idJob = idJob;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
