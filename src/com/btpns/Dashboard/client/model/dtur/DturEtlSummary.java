package com.btpns.Dashboard.client.model.dtur;

import java.util.Date;

import com.btpns.Dashboard.client.model.IModelClient;

public class DturEtlSummary implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String jobName;
	private Date tgl;
	private Integer countProcess;
	private String maxDuration;
	private Date startEtl;
	private Date endEtl;
	private String etlDuration;
	
	
	public DturEtlSummary() {}


	public DturEtlSummary(Integer id, String jobName, Date tgl, Integer countProcess,
			String maxDuration, Date startEtl, Date endEtl, String etlDuration) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.tgl = tgl;
		this.countProcess = countProcess;
		this.maxDuration = maxDuration;
		this.startEtl = startEtl;
		this.endEtl = endEtl;
		this.etlDuration = etlDuration;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public Date getTgl() {
		return tgl;
	}


	public void setTgl(Date tgl) {
		this.tgl = tgl;
	}

	public Integer getCountProcess() {
		return countProcess;
	}


	public void setCountProcess(Integer countProcess) {
		this.countProcess = countProcess;
	}


	public String getMaxDuration() {
		return maxDuration;
	}


	public void setMaxDuration(String maxDuration) {
		this.maxDuration = maxDuration;
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


	public String getEtlDuration() {
		return etlDuration;
	}


	public void setEtlDuration(String etlDuration) {
		this.etlDuration = etlDuration;
	}

}
