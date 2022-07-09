package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminIpAddressDTO implements Serializable{

	private static final long serialVersionUID = -3465530795709512182L;
	private int ipAddressNo;
	private String ipAddressValue;
	
	public AdminIpAddressDTO() {
	}
	
	public AdminIpAddressDTO(int ipAddressNo, String ipAddressValue) {
		this.ipAddressNo = ipAddressNo;
		this.ipAddressValue = ipAddressValue;
	}
	public int getIpAddressNo() {
		return ipAddressNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setIpAddressNo(int ipAddressNo) {
		this.ipAddressNo = ipAddressNo;
	}
	public String getIpAddressValue() {
		return ipAddressValue;
	}
	public void setIpAddressValue(String ipAddressValue) {
		this.ipAddressValue = ipAddressValue;
	}
	@Override
	public String toString() {
		return "AdminIpAddressDTO [ipAddressNo=" + ipAddressNo + ", ipAddressValue=" + ipAddressValue + "]";
	}
	
	
}
