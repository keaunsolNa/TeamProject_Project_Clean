package com.project.clean.model.dto.joinDTO;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.project.clean.model.dto.commonDTO.AdminDTO;

public class AdminImpl extends User{
	
	private static final long serialVersionUID = -8418537379278429510L;
	private int adminNo;
	private String adminName;
	private String adminId;
	private String adminPwd;
	private java.sql.Date adminBirth;
	private String adminGender;
	private String adminPhone;
	private java.sql.Date adminHireDate;
	private java.sql.Date adminRetireDate;
	private String adminRetireYn;
	private String adminJob;
	private java.sql.Blob adminSign;
	private java.sql.Date adminLastLoginDate;
	private int adminSalary;
	private int annualVacationUse;
	private List<AdminMemberRoleAndAuthorityDTO> adminMemberRoleAndAuthorityList;
	
	public AdminImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public void SetDetailsAdmin(AdminAndAdminMemberAuthorityDTO admin) {
		this.adminNo = admin.getAdminNo();
		this.adminName = admin.getAdminName();
		this.adminId = admin.getAdminId();
		this.adminPwd = admin.getAdminPwd();
		this.adminBirth = admin.getAdminBirth();
		this.adminGender = admin.getAdminGender();
		this.adminPhone = admin.getAdminPhone();
		this.adminHireDate = admin.getAdminHireDate();
		this.adminRetireDate = admin.getAdminRetireDate();
		this.adminRetireYn = admin.getAdminRetireYn();
		this.adminJob = admin.getAdminJob();
		this.adminSign = admin.getAdminSign();
		this.adminLastLoginDate = admin.getAdminLastLoginDate();
		this.adminSalary = admin.getAdminSalary();
		this.annualVacationUse = admin.getAnnualVacationUse();
		this.adminMemberRoleAndAuthorityList = admin.getAdminMemberRoleAndAuthorityList();
	}

	public int getAdminNo() {
		return adminNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public String getAdminId() {
		return adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public java.sql.Date getAdminBirth() {
		return adminBirth;
	}

	public String getAdminGender() {
		return adminGender;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public java.sql.Date getAdminHireDate() {
		return adminHireDate;
	}

	public java.sql.Date getAdminRetireDate() {
		return adminRetireDate;
	}

	public String getAdminRetireYn() {
		return adminRetireYn;
	}

	public String getAdminJob() {
		return adminJob;
	}

	public java.sql.Blob getAdminSign() {
		return adminSign;
	}

	public java.sql.Date getAdminLastLoginDate() {
		return adminLastLoginDate;
	}

	public int getAdminSalary() {
		return adminSalary;
	}

	public int getAnnualVacationUse() {
		return annualVacationUse;
	}

	public List<AdminMemberRoleAndAuthorityDTO> getAdminMemberRoleAndAuthorityList() {
		return adminMemberRoleAndAuthorityList;
	}

	@Override
	public String toString() {
		return "AdminImpl [adminNo=" + adminNo + ", adminName=" + adminName + ", adminId=" + adminId + ", adminPwd="
				+ adminPwd + ", adminBirth=" + adminBirth + ", adminGender=" + adminGender + ", adminPhone="
				+ adminPhone + ", adminHireDate=" + adminHireDate + ", adminRetireDate=" + adminRetireDate
				+ ", adminRetireYn=" + adminRetireYn + ", adminJob=" + adminJob + ", adminSign=" + adminSign
				+ ", adminLastLoginDate=" + adminLastLoginDate + ", adminSalary=" + adminSalary + ", annualVacationUse="
				+ annualVacationUse + ", adminMemberRoleAndAuthorityList=" + adminMemberRoleAndAuthorityList + "]";
	}
	
	
	
	
	

	
}
