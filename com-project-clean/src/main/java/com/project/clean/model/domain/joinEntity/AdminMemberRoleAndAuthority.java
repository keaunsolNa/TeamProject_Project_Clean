package com.project.clean.model.domain.joinEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.clean.model.domain.commonEntity.Authority;

@Entity
@Table(name="TBL_ADMIN_MEMBER_ROLE")
public class AdminMemberRoleAndAuthority implements java.io.Serializable {

	private static final long serialVersionUID = 830297073917396340L;

	@Id
	@Column(name="AUTHORITY_CODE")
	private int authorityCode;
	
	@Column(name="EMPLOYEE_NO")
	private Integer employeeNo;
	
	@Column(name="ADMIN_NO")
	private Integer adminNo;
	
	@ManyToOne
	@JoinColumn(name = "AUTHORITY_CODE" , insertable = false, updatable = false)
	private Authority authority;

	public AdminMemberRoleAndAuthority() {
	}
	

	public AdminMemberRoleAndAuthority(int authorityCode, Integer employeeNo, Integer adminNo, Authority authority) {
		this.authorityCode = authorityCode;
		this.employeeNo = employeeNo;
		this.adminNo = adminNo;
		this.authority = authority;
	}


	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public Integer getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(Integer employeeNo) {
		this.employeeNo = employeeNo;
	}

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "AdminMemberRoleAndAuthority [authorityCode=" + authorityCode + ", employeeNo=" + employeeNo
				+ ", adminNo=" + adminNo + ", authority=" + authority + "]";
	}


	
	
	
	
}
