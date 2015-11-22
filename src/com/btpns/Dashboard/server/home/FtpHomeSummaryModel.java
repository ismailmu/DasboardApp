package com.btpns.Dashboard.server.home;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_ftp_summary")
public class FtpHomeSummaryModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="count_incoming")
	private Integer countIncoming;
	@Column(name="count_outgoing")
	private Integer countOutgoing;
	@Column(name="count_wisma_incoming")
	private Integer countWismaIncoming;
	@Column(name="count_wisma_outgoing")
	private Integer countWismaOutgoing;
	@Column(name="count_wisma_operational")
	private Integer countWismaOperational;
	
	public FtpHomeSummaryModel() {}

	public FtpHomeSummaryModel(Integer id, Date tgl,Integer countIncoming,
			Integer countOutgoing, Integer countWismaIncoming,
			Integer countWismaOutgoing, Integer countWismaOperational) {
		super();
		this.id = id;
		this.tgl = tgl;
		this.countIncoming = countIncoming;
		this.countOutgoing = countOutgoing;
		this.countWismaIncoming = countWismaIncoming;
		this.countWismaOutgoing = countWismaOutgoing;
		this.countWismaOperational = countWismaOperational;
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

	public Integer getCountIncoming() {
		return countIncoming;
	}

	public void setCountIncoming(Integer countIncoming) {
		this.countIncoming = countIncoming;
	}

	public Integer getCountOutgoing() {
		return countOutgoing;
	}

	public void setCountOutgoing(Integer countOutgoing) {
		this.countOutgoing = countOutgoing;
	}

	public Integer getCountWismaIncoming() {
		return countWismaIncoming;
	}

	public void setCountWismaIncoming(Integer countWismaIncoming) {
		this.countWismaIncoming = countWismaIncoming;
	}

	public Integer getCountWismaOutgoing() {
		return countWismaOutgoing;
	}

	public void setCountWismaOutgoing(Integer countWismaOutgoing) {
		this.countWismaOutgoing = countWismaOutgoing;
	}

	public Integer getCountWismaOperational() {
		return countWismaOperational;
	}

	public void setCountWismaOperational(Integer countWismaOperational) {
		this.countWismaOperational = countWismaOperational;
	}
}
