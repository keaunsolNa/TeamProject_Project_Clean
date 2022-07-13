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
	private int adminNo;

	@Column(name="ADMIN_ADDRESS_NO")
	private int addressNo;
	
	@Column(name="ADMIN_ADDRESS")
	private String address;
	
	@Column(name="ADMIN_DETAIL_ADDRESS")
	private String detailAddress;

	public AdminAddress() {
	}

	public AdminAddress(int adminNo, int addressNo, String address, String detailAddress) {
		this.adminNo = adminNo;
		this.addressNo = addressNo;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
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
		return "AdminAddress [adminNo=" + adminNo + ", addressNo=" + addressNo + ", address=" + address
				+ ", detailAddress=" + detailAddress + "]";
	}

	
	
	
	
}
