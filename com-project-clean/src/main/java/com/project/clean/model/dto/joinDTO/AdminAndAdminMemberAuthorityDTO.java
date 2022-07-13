package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.util.List;

public class AdminAndAdminMemberAuthorityDTO implements Serializable{

	private static final long serialVersionUID = 5351444132765759589L;
	
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
	private int annualVacationUse;
	private List<AdminMemberRoleAndAuthorityDTO> adminMemberRoleAndAuthorityList;
	
	public AdminAndAdminMemberAuthorityDTO() {
	}
	
	public AdminAndAdminMemberAuthorityDTO(int adminNo, String adminName, String adminId, String adminPwd,
			Date adminBirth, String adminGender, String adminPhone, Date adminHireDate, Date adminRetireDate,
			String adminRetireYn, String adminJob, Blob adminSign, Date adminLastLoginDate, int adminSalary,
			int annualVacationUse, List<AdminMemberRoleAndAuthorityDTO> adminMemberRoleAndAuthorityList) {
		this.adminNo = adminNo;
		this.adminName = adminName;
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.adminBirth = adminBirth;
		this.adminGender = adminGender;
		this.adminPhone = adminPhone;
		this.adminHireDate = adminHireDate;
		this.adminRetireDate = adminRetireDate;
		this.adminRetireYn = adminRetireYn;
		this.adminJob = adminJob;
		this.adminLastLoginDate = adminLastLoginDate;
		this.adminSalary = adminSalary;
		this.annualVacationUse = annualVacationUse;
		this.adminMemberRoleAndAuthorityList = adminMemberRoleAndAuthorityList;
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
	public void setAdminJob(String adminJob) {
		this.adminJob = adminJob;
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
	public int getAnnualVacationUse() {
		return annualVacationUse;
	}
	public void setAnnualVacationUse(int annualVacationUse) {
		this.annualVacationUse = annualVacationUse;
	}
	public List<AdminMemberRoleAndAuthorityDTO> getAdminMemberRoleAndAuthorityList() {
		return adminMemberRoleAndAuthorityList;
	}
	public void setAdminMemberRoleAndAuthorityList(List<AdminMemberRoleAndAuthorityDTO> adminMemberRoleAndAuthorityList) {
		this.adminMemberRoleAndAuthorityList = adminMemberRoleAndAuthorityList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminAndAdminMemberAuthorityDTO [adminNo=" + adminNo + ", adminName=" + adminName + ", adminId="
				+ adminId + ", adminPwd=" + adminPwd + ", adminBirth=" + adminBirth + ", adminGender=" + adminGender
				+ ", adminPhone=" + adminPhone + ", adminHireDate=" + adminHireDate + ", adminRetireDate="
				+ adminRetireDate + ", adminRetireYn=" + adminRetireYn + ", adminJob=" + adminJob
				+ ", adminLastLoginDate=" + adminLastLoginDate + ", adminSalary=" + adminSalary + ", annualVacationUse="
				+ annualVacationUse + ", adminMemberRoleAndAuthorityList=" + adminMemberRoleAndAuthorityList + "]";
	}

	
	

}
