package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminAddressDTO implements Serializable {

	private static final long serialVersionUID = -5497004233970787129L;
	private int adminNo;
	private int addressNo;
	private String address;
	private String detailAddress;
	
	public AdminAddressDTO() {
	}

	public AdminAddressDTO(int adminNo, int addressNo, String address, String detailAddress) {
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
		return "AdminAddressDTO [adminNo=" + adminNo + ", addressNo=" + addressNo + ", address=" + address
				+ ", detailAddress=" + detailAddress + "]";
	}
	
	
	
}
