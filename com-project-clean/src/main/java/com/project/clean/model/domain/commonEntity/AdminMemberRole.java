package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_ADMIN_MEMBER_ROLE")
public class AdminMemberRole implements java.io.Serializable{

	private static final long serialVersionUID = -4341617180128696853L;
	
	@Id
	@Column(name="AUTHORITY_CODE")
	private int authorityCode;
	
	@Column(name="EMPLOYEE_NO")
	private int employeeNo;
	
	@Column(name="ADMIN_NO")
	private int adminNo;

	public AdminMemberRole() {
	}

	public AdminMemberRole(int authorityCode, int employeeNo, int adminNo) {
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
		return "AdminMemberRole [authorityCode=" + authorityCode + ", employeeNo=" + employeeNo + ", adminNo=" + adminNo
				+ "]";
	}

	

	

}
