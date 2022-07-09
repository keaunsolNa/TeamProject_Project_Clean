package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name ="AdminAddress")
@Table(name="TBL_ADMIN_ADDRESS")
public class AdminAddress implements java.io.Serializable{

	private static final long serialVersionUID = 735511455709127142L;

	@Id
	@Column(name="ADMIN_NO")
	private int no;

	@Column(name="ADMIN_ADDRESS_NO")
	private int addressNo;
	
	@Column(name="ADMIN_ADDRESS")
	private String address;
	
	@Column(name="ADMIN_DETAIL_ADDRESS")
	private String detailAddress;

	public AdminAddress() {
	}

	public AdminAddress(int no, int addressNo, String address, String detailAddress) {
		this.no = no;
		this.addressNo = addressNo;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(int addressNo) {
		this.addressNo = addressNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminAddress [no=" + no + ", addressNo=" + addressNo + ", address=" + address + ", detailAddress="
				+ detailAddress + "]";
	}

	
	
	
}
