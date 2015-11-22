package com.btpns.Dashboard.client.model.ftp;

import com.btpns.Dashboard.client.model.IModelClient;

public class FtpChartTrx implements IModelClient {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private Integer id;
	private String timeReference;
	private Integer countIncoming;
	private Integer countOutgoing;
//	private Integer countDistinctIncoming;
//	private Integer countDistinctOutgoing;

	public FtpChartTrx(){}
	public FtpChartTrx(Integer no,Integer id,String timeReference,Integer countIncoming,Integer countOutgoing) {
		super();
		this.no = no;
		this.id=id;
		this.timeReference = timeReference;
		this.countIncoming = countIncoming;
		this.countOutgoing = countOutgoing;
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
//	public Integer getCountDistinctIncoming() {
//		return countDistinctIncoming;
//	}
//	public void setCountDistinctIncoming(Integer countDistinctIncoming) {
//		this.countDistinctIncoming = countDistinctIncoming;
//	}
//	public Integer getCountDistinctOutgoing() {
//		return countDistinctOutgoing;
//	}
//	public void setCountDistinctOutgoing(Integer countDistinctOutgoing) {
//		this.countDistinctOutgoing = countDistinctOutgoing;
//	}
}
