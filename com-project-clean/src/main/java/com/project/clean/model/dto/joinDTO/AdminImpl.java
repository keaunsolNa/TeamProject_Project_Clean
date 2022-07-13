package com.project.clean.model.dto.joinDTO;

import java.sql.Blob;
import java.sql.Date;
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
	private java.sql.Date adminLastLoginDate;
	private int adminSalary;
	private int adminUseAnnualVacation;
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
		this.adminLastLoginDate = admin.getAdminLastLoginDate();
		this.adminSalary = admin.getAdminSalary();
		this.adminUseAnnualVacation = admin.getAdminUseAnnualVacation();
		this.adminMemberRoleAndAuthorityList = admin.getAdminMemberRoleAndAuthorityList();
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public java.sql.Date getAdminBirth() {
		return adminBirth;
	}

	public void setAdminBirth(java.sql.Date adminBirth) {
		this.adminBirth = adminBirth;
	}

	public String getAdminGender() {
		return adminGender;
	}

	public void setAdminGender(String adminGender) {
		this.adminGender = adminGender;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public java.sql.Date getAdminHireDate() {
		return adminHireDate;
	}

	public void setAdminHireDate(java.sql.Date adminHireDate) {
		this.adminHireDate = adminHireDate;
	}

	public java.sql.Date getAdminRetireDate() {
		return adminRetireDate;
	}

	public void setAdminRetireDate(java.sql.Date adminRetireDate) {
		this.adminRetireDate = adminRetireDate;
	}

	public String getAdminRetireYn() {
		return adminRetireYn;
	}

	public void setAdminRetireYn(String adminRetireYn) {
		this.adminRetireYn = adminRetireYn;
	}

	public String getAdminJob() {
		return adminJob;
	}

	public java.sql.Date getAdminLastLoginDate() {
		return adminLastLoginDate;
	}

	public void setAdminLastLoginDate(java.sql.Date adminLastLoginDate) {
		this.adminLastLoginDate = adminLastLoginDate;
	}

	public int getAdminSalary() {
		return adminSalary;
	}

	public void setAdminSalary(int adminSalary) {
		this.adminSalary = adminSalary;
	}

	public int getAdminUseAnnualVacation() {
		return adminUseAnnualVacation;
	}

	public void setAdminUseAnnualVacation(int adminUseAnnualVacation) {
		this.adminUseAnnualVacation = adminUseAnnualVacation;
	}

	public List<AdminMemberRoleAndAuthorityDTO> getAdminMemberRoleAndAuthorityList() {
		return adminMemberRoleAndAuthorityList;
	}



	@Override
	public String toString() {
		return "AdminImpl [adminNo=" + adminNo + ", adminName=" + adminName + ", adminId=" + adminId + ", adminPwd="
				+ adminPwd + ", adminBirth=" + adminBirth + ", adminGender=" + adminGender + ", adminPhone="
				+ adminPhone + ", adminHireDate=" + adminHireDate + ", adminRetireDate=" + adminRetireDate
				+ ", adminRetireYn=" + adminRetireYn + ", adminJob=" + adminJob + ", adminLastLoginDate="
				+ adminLastLoginDate + ", adminSalary=" + adminSalary + ", adminUseAnnualVacation=" + adminUseAnnualVacation
				+ ", adminMemberRoleAndAuthorityList=" + adminMemberRoleAndAuthorityList + "]";
	}

	
	
	
	

}
