package com.btpns.Dashboard.client.model.dtur;

import java.util.Date;

import com.btpns.Dashboard.client.model.IModelClient;

public class DturEtlDetail implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer no;
	private Integer idJob;
	private Date tgl;
	private String jobName;
	private String status;
	private Date startDate=null;
	private Date endDate=null;
	private String duration;
	
	public DturEtlDetail(){}

	public DturEtlDetail(Integer no, Integer idJob, Date tgl, String jobName,
			String status, Date startDate, Date endDate, String duration) {
		super();
		this.no = no;
		this.idJob = idJob;
		this.tgl = tgl;
		this.jobName = jobName;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.duration = duration;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}
