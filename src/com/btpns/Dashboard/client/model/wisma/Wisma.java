package com.btpns.Dashboard.client.model.wisma;

import com.btpns.Dashboard.client.model.IModelClient;

public class Wisma implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private Integer id;
	private String wisma;
	private String wismaName;
	private String kcs;
	private String kcsName;
	private String address;
	private String rtrw = "";
	private String kelurahan = "";
	private String kecamatan = "";
	private String kabupaten = "";
	private String propinsi = "";
	private String zipcode = "";
	private String telephone = "";
	
	public Wisma(){}
	
	public Wisma(Integer no,Integer id,String wisma, String wismaName, String kcs, String kcsName,String address, String rtrw,
			String kelurahan, String kecamatan, String kabupaten,
			String propinsi, String zipcode, String telephone) {
		super();
		this.no = no;
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
