package com.btpns.Dashboard.client.model.ftp;

import com.btpns.Dashboard.client.model.IModelClient;

public class FtpFailureSummary implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer totalWisma;
	private Integer totalFileReceived;
	private Integer totalRecordReceived;
	private Integer totalHasBeenProcessed;
	private Integer totalFileProcessed;
	private Integer totalRecord;
	private Integer totalRecordSuccess;
	private Integer totalRecordFailure;

	public FtpFailureSummary() {
	}

	public FtpFailureSummary(Integer id, Integer totalWisma,
			Integer totalFileReceived, Integer totalRecordReceived,
			Integer totalHasBeenProcessed, Integer totalFileProcessed,
			Integer totalRecord, Integer totalRecordSuccess,
			Integer totalRecordFailure) {
		super();
		this.id = id;
		this.totalWisma = totalWisma;
		this.totalFileReceived = totalFileReceived;
		this.totalRecordReceived = totalRecordReceived;
		this.totalHasBeenProcessed = totalHasBeenProcessed;
		this.totalFileProcessed = totalFileProcessed;
		this.totalRecord = totalRecord;
		this.totalRecordSuccess = totalRecordSuccess;
		this.totalRecordFailure = totalRecordFailure;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalWisma() {
		return totalWisma;
	}

	public void setTotalWisma(Integer totalWisma) {
		this.totalWisma = totalWisma;
	}

	public Integer getTotalFileReceived() {
		return totalFileReceived;
	}

	public void setTotalFileReceived(Integer totalFileReceived) {
		this.totalFileReceived = totalFileReceived;
	}

	public Integer getTotalRecordReceived() {
		return totalRecordReceived;
	}

	public void setTotalRecordReceived(Integer totalRecordReceived) {
		this.totalRecordReceived = totalRecordReceived;
	}

	public Integer getTotalHasBeenProcessed() {
		return totalHasBeenProcessed;
	}

	public void setTotalHasBeenProcessed(Integer totalHasBennProcessed) {
		this.totalHasBeenProcessed = totalHasBennProcessed;
	}

	public Integer getTotalFileProcessed() {
		return totalFileProcessed;
	}

	public void setTotalFileProcessed(Integer totalFileProcessed) {
		this.totalFileProcessed = totalFileProcessed;
	}

	public Integer getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Integer getTotalRecordSuccess() {
		return totalRecordSuccess;
	}

	public void setTotalRecordSuccess(Integer totalRecordSuccess) {
		this.totalRecordSuccess = totalRecordSuccess;
	}

	public Integer getTotalRecordFailure() {
		return totalRecordFailure;
	}

	public void setTotalRecordFailed(Integer totalRecordFailure) {
		this.totalRecordFailure = totalRecordFailure;
	}
}
