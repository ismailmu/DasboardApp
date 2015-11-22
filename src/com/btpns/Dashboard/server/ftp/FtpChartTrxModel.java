package com.btpns.Dashboard.server.ftp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.client.model.IModelClient;

@Entity
@Table(name="rpt_ftp_graph_daily")
public class FtpChartTrxModel implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="tgl")
	private Date tgl;
	@Column(name="time_reference",length=5,nullable=false)
	private String timeReference;
	@Column(name="count_incoming")
	private Integer countIncoming;
	@Column(name="count_outgoing")
	private Integer countOutgoing;

	public FtpChartTrxModel(){}
	public FtpChartTrxModel(Integer id,String timeReference,Integer countIncoming,Integer countOutgoing) {
		super();
		this.id = id;
		this.timeReference = timeReference;
		this.countIncoming = countIncoming;
		this.countOutgoing = countOutgoing;
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
	public String getTimeReference() {
		return timeReference;
	}
	public void setTimeReference(String timeReference) {
		this.timeReference = timeReference;
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
}
