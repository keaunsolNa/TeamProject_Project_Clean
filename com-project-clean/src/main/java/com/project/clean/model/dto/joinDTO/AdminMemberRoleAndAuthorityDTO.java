package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.util.List;

import com.project.clean.model.dto.commonDTO.AuthorityDTO;

public class AdminMemberRoleAndAuthorityDTO implements Serializable{

	private static final long serialVersionUID = 1089154992279977863L;
	private int authorityCode;
	private int employeeNo;
	private int adminNo;
	private List<AuthorityDTO> authorityList;

	public AdminMemberRoleAndAuthorityDTO() {
	}

	public AdminMemberRoleAndAuthorityDTO(int authorityCode, int employeeNo, int adminNo,
			List<AuthorityDTO> authorityList) {
		this.authorityCode = authorityCode;
		this.employeeNo = employeeNo;
		this.adminNo = adminNo;
		this.authorityList = authorityList;
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
	public List<AuthorityDTO> getAuthorityList() {
		return authorityList;
	}
	public void setAuthorityList(List<AuthorityDTO> authorityList) {
		this.authorityList = authorityList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AdminMemberRoleAndAuthorityDTO [authorityCode=" + authorityCode + ", employeeNo=" + employeeNo
				+ ", adminNo=" + adminNo + ", authorityList=" + authorityList + "]";
	}
	
	
	
	
}
