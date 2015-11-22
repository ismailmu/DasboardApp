package com.btpns.Dashboard.server.ftp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_eod_failure")
public class FtpFailureDetailModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="tgl",nullable=false)
	private Date tgl;
	@Column(name="office_code",nullable=false,length=5)
	private String wisma;
	@Column(name="display_name",nullable=false,length=200)
	private String wismaName;
	@Column(name="file_received")
	private Integer fileReceived;
	@Column(name="record_received")
	private Integer recordReceived;
	@Column(name="has_been_processed")
	private Integer hasBeenProcessed;
	@Column(name="file_process")
	private Integer fileProcessed;
	@Column(name="total_record")
	private Integer totalRecord;
	@Column(name="success_record")
	private Integer totalRecordSuccess;
	@Column(name="failure_record")
	private Integer totalRecordFailure;

	public FtpFailureDetailModel() {
	}

	public FtpFailureDetailModel(Integer id, String wisma, String wismaName,
			Integer fileReceived, Integer recordReceived,
			Integer hasBeenProcessed, Integer fileProcessed,
			Integer totalRecord, Integer totalRecordSuccess,
			Integer totalRecordFailure) {
		super();
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
