package com.btpns.Dashboard.client.model.eod;

import java.util.Date;

import com.btpns.Dashboard.client.model.IModelClient;

public class EodDuration implements IModelClient {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer no;
	private Date startTime;
	private Date endTime;
	private String durationString;
	private String status = "Success";
	private String failTask = "";

	public EodDuration(){}

	
	public EodDuration(Integer no, Integer id, Date startTime, Date endTime,String durationString, String status
			,String failTask) {
		this.no = no;
		this.id= id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.durationString = durationString;
		this.status = status;
		this.failTask = failTask;
	}
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getNo() {
		return no;
	}


	public void setNo(Integer no) {
		this.no = no;
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

	public void setDurationString(String durationString) {
		this.durationString = durationString;
	}

	public String getDurationString() {
		return durationString;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFailTask() {
		return failTask;
	}

	public void setFailTask(String failTask) {
		this.failTask = failTask;
	}
}
