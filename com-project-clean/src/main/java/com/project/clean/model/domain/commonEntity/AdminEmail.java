package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="AdminEmail")
@Table(name="TBL_ADMIN_EMAIL")
public class AdminEmail implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ADMIN_NO")
	private int adminNo;

	@Column(name="ADMIN_EMAIL")
	private String email;
	
	@Column(name="ADMIN_DOMAIN")
	private String domain;

	public AdminEmail() {
	}

	public AdminEmail(int adminNo, String email, String domain) {
		this.adminNo = adminNo;
		this.email = email;
		this.domain = domain;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminEmail [adminNo=" + adminNo + ", email=" + email + ", domain=" + domain + "]";
	}

	

	
	
}
