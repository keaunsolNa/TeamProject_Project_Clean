package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

import com.project.clean.model.dto.commonDTO.AuthorityDTO;

public class AdminMemberRoleDTO implements Serializable {

	private static final long serialVersionUID = -3773502393164485302L;
	
	private int authorityCode;
	private int employeeNo;
	private int adminNo;
	public AdminMemberRoleDTO() {
	}
	public AdminMemberRoleDTO(int authorityCode, int employeeNo, int adminNo) {
		this.authorityCode = authorityCode;
		this.employeeNo = employeeNo;
		this.adminNo = adminNo;
	}
	public int getAuthorityCode() {
		return authorityCode;
	}
	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AdminMemberRoleDTO [authorityCode=" + authorityCode + ", employeeNo=" + employeeNo + ", adminNo="
				+ adminNo + "]";
	}

	
	
	
}
