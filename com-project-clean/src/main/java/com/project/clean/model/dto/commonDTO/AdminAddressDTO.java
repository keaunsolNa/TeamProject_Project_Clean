package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminAddressDTO implements Serializable {

	private static final long serialVersionUID = -5497004233970787129L;
	private int adminNo;
	private int adminAddressNo;
	private String adminAddress;
	private String adminDetailAddress;
	
	public AdminAddressDTO() {
	}

	public AdminAddressDTO(int adminNo, int adminAddressNo, String adminAddress, String adminDetailAddress) {
		this.adminNo = adminNo;
		this.adminAddressNo = adminAddressNo;
		this.adminAddress = adminAddress;
		this.adminDetailAddress = adminDetailAddress;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public int getAdminAddressNo() {
		return adminAddressNo;
	}

	public void setAdminAddressNo(int adminAddressNo) {
		this.adminAddressNo = adminAddressNo;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public String getAdminDetailAddress() {
		return adminDetailAddress;
	}

	public void setAdminDetailAddress(String adminDetailAddress) {
		this.adminDetailAddress = adminDetailAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminAddressDTO [adminNo=" + adminNo + ", adminAddressNo=" + adminAddressNo + ", adminAddress="
				+ adminAddress + ", adminDetailAddress=" + adminDetailAddress + "]";
	}

	
	
	
}
