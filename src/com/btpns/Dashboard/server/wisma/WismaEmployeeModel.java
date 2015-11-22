package com.btpns.Dashboard.server.wisma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="office_employee")
public class WismaEmployeeModel implements IModelPersistence {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="office_code",length=5,nullable=false)
	private String wisma;
	@Column(name="employee_name",length=5,nullable=false)
	private String employeeName;
	@Column(name="email")
	private String email = "";
	@Column(name="telephone")
	private String telephone = "";
	@Column(name="hp")
	private String hp = "";
	@Column(name="position",length=40,nullable=false)
	private String position;
	@Column(name="ordinal_position")
	private Integer ordinalPosition;
	
	public WismaEmployeeModel(){}

	public WismaEmployeeModel(Integer id, String wisma, String employeeName,
			String email, String telephone, String hp, String position,
			Integer ordinalPosition) {
		super();
		this.id = id;
		this.wisma = wisma;
		this.employeeName = employeeName;
		this.email = email;
		this.telephone = telephone;
		this.hp = hp;
		this.position = position;
		this.ordinalPosition = ordinalPosition;
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

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}
}
