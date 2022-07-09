package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_ADMIN_EMAIL")
public class AdminEmail implements java.io.Serializable{

	@Id
	@Column(name="ADMIN_NO")
	private int no;

	@Column(name="ADMIN_EMAIL")
	private String email;
	
	@Column(name="ADMIN_DOMAIN")
	private String domain;

	public AdminEmail() {
	}

	public AdminEmail(int no, String email, String domain) {
		this.no = no;
		this.email = email;
		this.domain = domain;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
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

	@Override
	public String toString() {
		return "AdminEmail [no=" + no + ", email=" + email + ", domain=" + domain + "]";
	}
	
	
}
