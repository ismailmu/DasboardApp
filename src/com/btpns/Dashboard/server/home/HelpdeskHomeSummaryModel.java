package com.btpns.Dashboard.server.home;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_helpdesk_summary")
public class HelpdeskHomeSummaryModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="count_wisma")
	private Integer countWisma;
	@Column(name="status_id")
	private String statusId;
	@Column(name="count_status")
	private Integer countStatus;
	
	public HelpdeskHomeSummaryModel(){}

	public HelpdeskHomeSummaryModel(Integer id, Date tgl, Integer countWisma,
			String statusId, Integer countStatus) {
		super();
		this.id = id;
		this.tgl = tgl;
		this.countWisma = countWisma;
		this.statusId = statusId;
		this.countStatus = countStatus;
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

	public Integer getCountWisma() {
		return countWisma;
	}

	public void setCountWisma(Integer countWisma) {
		this.countWisma = countWisma;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public Integer getCountStatus() {
		return countStatus;
	}

	public void setCountStatus(Integer countStatus) {
		this.countStatus = countStatus;
	}
}
