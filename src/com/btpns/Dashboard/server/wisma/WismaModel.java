package com.btpns.Dashboard.server.wisma;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="rpt_office")
public class WismaModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(name="office_code",length=5,nullable=false)
	private String wisma;
	@Column(name="display_name",length=200,nullable=false)
	private String wismaName;
	@Column(name="kcs",length=4,nullable=false)
	private String kcs;
	@Column(name="kcs_name",length=200,nullable=false)
	private String kcsName;
	@Column(name="address_1",length=200)
	private String address;
	@Column(name="address_2",length=200)
	private String rtrw = "";
	@Column(name="address_3",length=200)
	private String kelurahan = "";
	@Column(name="city",length=100)
	private String kecamatan = "";
	@Column(name="state",length=100)
	private String kabupaten = "";
	@Column(name="country",length=100)
	private String propinsi = "";
	@Column(name="zip",length=20)
	private String zipcode = "";
	@Column(name="telephone",length=20)
	private String telephone = "";
	
	public WismaModel(){}

	public WismaModel(Integer id,String wisma, String wismaName, String kcs,
			String kcsName, String address, String rtrw, String kelurahan,
			String kecamatan, String kabupaten, String propinsi,
			String zipcode, String telephone) {
		super();
		this.id = id;
		this.wisma = wisma;
		this.wismaName = wismaName;
		this.kcs = kcs;
		this.kcsName = kcsName;
		this.address = address;
		this.rtrw = rtrw;
		this.kelurahan = kelurahan;
		this.kecamatan = kecamatan;
		this.kabupaten = kabupaten;
		this.propinsi = propinsi;
		this.zipcode = zipcode;
		this.telephone = telephone;
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

	public String getKcs() {
		return kcs;
	}

	public void setKcs(String kcs) {
		this.kcs = kcs;
	}

	public String getKcsName() {
		return kcsName;
	}

	public void setKcsName(String kcsName) {
		this.kcsName = kcsName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRtrw() {
		return rtrw;
	}

	public void setRtrw(String rtrw) {
		this.rtrw = rtrw;
	}

	public String getKelurahan() {
		return kelurahan;
	}

	public void setKelurahan(String kelurahan) {
		this.kelurahan = kelurahan;
	}

	public String getKecamatan() {
		return kecamatan;
	}

	public void setKecamatan(String kecamatan) {
		this.kecamatan = kecamatan;
	}

	public String getKabupaten() {
		return kabupaten;
	}

	public void setKabupaten(String kabupaten) {
		this.kabupaten = kabupaten;
	}

	public String getPropinsi() {
		return propinsi;
	}

	public void setPropinsi(String propinsi) {
		this.propinsi = propinsi;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
