package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_ADMIN_EMAIL")
public class AdminEmail implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ADMIN_NO")
	private int adminNo;

	@Column(name="ADMIN_EMAIL")
	private String adminEmail;
	
	@Column(name="ADMIN_DOMAIN")
	private String adminDomain;

	public AdminEmail() {
	}

	public AdminEmail(int adminNo, String adminEmail, String adminDomain) {
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
		return "AdminEmail [adminNo=" + adminNo + ", adminEmail=" + adminEmail + ", adminDomain=" + adminDomain + "]";
	}

	

	
	
}
