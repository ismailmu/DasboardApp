package com.btpns.Dashboard.client.model.wisma;

import com.btpns.Dashboard.client.model.IModelClient;

public class WismaEmployee implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private Integer id;
	private String wisma;
	private String employeeName;
	private String email = "";
	private String telephone = "";
	private String hp = "";
	private String position;
	
	public WismaEmployee(){}

	public WismaEmployee(Integer no, Integer id, String wisma,
			String employeeName, String email, String telephone, String hp,
			String position) {
		super();
		this.no = no;
		this.id = id;
		this.wisma = wisma;
		this.employeeName = employeeName;
		this.email = email;
		this.telephone = telephone;
		this.hp = hp;
		this.position = position;
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

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
