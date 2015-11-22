package com.btpns.Dashboard.server.helpdesk;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_helpdesk")
public class HelpdeskDailyModel implements IModelPersistence {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="tgl",nullable=false)
	private Date tgl;
	@Column(name="ticket_id",nullable=false)
	private Integer ticketId;
	@Column(name="office_code",nullable=false,length=5)
	private String wisma;
	@Column(name="display_name",nullable=false,length=200)
	private String wismaName;
	@Column(name="status_id")
	private String status;
	@Column(name="priority_id")
	private String priority;
	@Column(name="aging")
	private Integer aging;
	@Column(name="category1")
	private String category1 = "";
	@Column(name="category2")
	private String category2 = "";
	@Column(name="category3")
	private String category3 = "";
	@Column(name="category4")
	private String category4 = "";

	public HelpdeskDailyModel() {
	}

	public HelpdeskDailyModel(Integer id, Integer ticketId, String wisma,
			String wismaName, String status, String priority, Integer aging,
			String category1, String category2, String category3,
			String category4) {
		super();
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
	
	public Date getTgl() {
		return tgl;
	}

	public void setTgl(Date tgl) {
		this.tgl = tgl;
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
