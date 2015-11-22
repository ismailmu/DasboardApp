package com.btpns.Dashboard.client.model.helpdesk;

import com.btpns.Dashboard.client.model.IModelClient;

public class HelpdeskDaily implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer no;
	private Integer ticketId;
	private String wisma;
	private String wismaName;
	private String status;
	private String priority;
	private Integer aging;
	private String category1 = "";
	private String category2 = "";
	private String category3 = "";
	private String category4 = "";

	public HelpdeskDaily() {
	}

	public HelpdeskDaily(Integer no,Integer id, Integer ticketId,
			String wisma, String wismaName, String status, String priority,
			Integer aging, String category1, String category2,
			String category3, String category4) {
		super();
		this.no = no;
		this.id = id;
		this.ticketId = ticketId;
		this.wisma = wisma;
		this.wismaName = wismaName;
		this.status = status;
		this.priority = priority;
		this.aging = aging;
		this.category1 = category1;
		this.category2 = category2;
		this.category3 = category3;
		this.category4 = category4;
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

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Integer getAging() {
		return aging;
	}

	public void setAging(Integer aging) {
		this.aging = aging;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getCategory4() {
		return category4;
	}

	public void setCategory4(String category4) {
		this.category4 = category4;
	}
}