package com.btpns.Dashboard.server.eod;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name = "rpt_duration")
public class EodDurationModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "start_time", nullable = false)
	private Date startTime;
	@Column(name = "end_time", nullable = false)
	private Date endTime;
	@Column(name = "status", nullable = false, length = 7)
	private String status = "Success";
	@Column(name = "fail_task", nullable = true)
	private String failTask = "";

	public EodDurationModel() {
	}

	public EodDurationModel(Date startTime, Date endTime, String status, String failTask) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.failTask = failTask;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
