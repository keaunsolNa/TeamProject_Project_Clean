package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminEmailDTO implements Serializable{

	private static final long serialVersionUID = -2240846264327040686L;
	private int adminNo;
	private String adminEmail;
	private String adminDomain;
	
	public AdminEmailDTO() {
	}

	public AdminEmailDTO(int adminNo, String adminEmail, String adminDomain) {
		this.adminNo = adminNo;
		this.adminEmail = adminEmail;
		this.adminDomain = adminDomain;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminDomain() {
		return adminDomain;
	}

	public void setAdminDomain(String adminDomain) {
		this.adminDomain = adminDomain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminEmailDTO [adminNo=" + adminNo + ", adminEmail=" + adminEmail + ", adminDomain=" + adminDomain
				+ "]";
	}
	

	
	
}
