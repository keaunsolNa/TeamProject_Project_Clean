package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class AdminEmailDTO implements Serializable{

	private static final long serialVersionUID = -2240846264327040686L;
	private int no;
	private String email;
	private String domain;
	
	public AdminEmailDTO() {
	}
	
	public AdminEmailDTO(int no, String email, String domain) {
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
	public static long getSerialversionuid() {
		return serialVersionUID;
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
		return "AdminEmailDTO [no=" + no + ", email=" + email + ", domain=" + domain + "]";
	}
	
	
	
}
