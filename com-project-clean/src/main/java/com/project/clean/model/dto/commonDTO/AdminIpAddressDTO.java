package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminIpAddressDTO implements Serializable{

	private static final long serialVersionUID = -3465530795709512182L;
	private int adminNo;
	private String ipAddressValue;
	public AdminIpAddressDTO() {
	}
	public AdminIpAddressDTO(int adminNo, String ipAddressValue) {
		this.adminNo = adminNo;
		this.ipAddressValue = ipAddressValue;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getIpAddressValue() {
		return ipAddressValue;
	}
	public void setIpAddressValue(String ipAddressValue) {
		this.ipAddressValue = ipAddressValue;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AdminIpAddressDTO [adminNo=" + adminNo + ", ipAddressValue=" + ipAddressValue + "]";
	}

	
	
}
