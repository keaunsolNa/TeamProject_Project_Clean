package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminAddressDTO implements Serializable {

	private static final long serialVersionUID = -5497004233970787129L;
	private int no;
	private int addressNo;
	private String address;
	private String detailAddress;
	public AdminAddressDTO() {
	}
	public AdminAddressDTO(int no, int addressNo, String address, String detailAddress) {
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
		return "AdminAddressDTO [no=" + no + ", addressNo=" + addressNo + ", address=" + address + ", detailAddress="
				+ detailAddress + "]";
	}
	
	
	
}
