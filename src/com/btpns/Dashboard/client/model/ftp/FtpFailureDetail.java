package com.btpns.Dashboard.client.model.ftp;

import com.btpns.Dashboard.client.model.IModelClient;

public class FtpFailureDetail implements IModelClient {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer no;
	private Integer id;
	private String wisma;
	private String wismaName;
	private Integer fileReceived;
	private Integer recordReceived;
	private Integer hasBeenProcessed;
	private Integer fileProcessed;
	private Integer totalRecord;
	private Integer totalRecordSuccess;
	private Integer totalRecordFailure;

	public FtpFailureDetail() {
	}

	public FtpFailureDetail(Integer no, Integer id, String wisma,
			String wismaName, Integer fileReceived, Integer recordReceived,
			Integer hasBeenProcessed, Integer fileProcessed,
			Integer totalRecord, Integer totalRecordSuccess,
			Integer totalRecordFailure) {
		super();
		this.no = no;
		this.id = id;
		this.wisma = wisma;
		this.wismaName = wismaName;
		this.fileReceived = fileReceived;
		this.recordReceived = recordReceived;
		this.hasBeenProcessed = hasBeenProcessed;
		this.fileProcessed = fileProcessed;
		this.totalRecord = totalRecord;
		this.totalRecordSuccess = totalRecordSuccess;
		this.totalRecordFailure = totalRecordFailure;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getFileReceived() {
		return fileReceived;
	}

	public void setFileReceived(Integer fileReceived) {
		this.fileReceived = fileReceived;
	}

	public Integer getRecordReceived() {
		return recordReceived;
	}

	public void setRecordReceived(Integer recordReceived) {
		this.recordReceived = recordReceived;
	}

	public Integer getHasBeenProcessed() {
		return hasBeenProcessed;
	}

	public void setHasBeenProcessed(Integer hasBeenProcessed) {
		this.hasBeenProcessed = hasBeenProcessed;
	}

	public Integer getFileProcessed() {
		return fileProcessed;
	}

	public void setFileProcessed(Integer fileProcessed) {
		this.fileProcessed = fileProcessed;
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

	public void setTotalRecordFailure(Integer totalRecordFailure) {
		this.totalRecordFailure = totalRecordFailure;
	}
}
