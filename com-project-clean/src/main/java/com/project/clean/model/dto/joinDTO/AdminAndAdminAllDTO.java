package com.project.clean.model.dto.joinDTO;

import java.sql.Date;

import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
import com.project.clean.model.dto.commonDTO.AdminPictureDTO;

public class AdminAndAdminAllDTO {
	
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
	private AdminEmailDTO adminEmailDTO;
	private AdminAddressDTO adminAddressDTO;
	private AdminPictureDTO adminPictureDTO;
	
	
	
	
	public AdminAndAdminAllDTO() {
	}




	public AdminAndAdminAllDTO(int adminNo, String adminName, String adminId, String adminPwd, Date adminBirth,
			String adminGender, String adminPhone, Date adminHireDate, Date adminRetireDate, String adminRetireYn,
			String adminJob, Date adminLastLoginDate, int adminSalary, int adminUseAnnualVacation,
			AdminEmailDTO adminEmailDTO, AdminAddressDTO adminAddressDTO, AdminPictureDTO adminPictureDTO) {
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
		this.adminUseAnnualVacation = adminUseAnnualVacation;
		this.adminEmailDTO = adminEmailDTO;
		this.adminAddressDTO = adminAddressDTO;
		this.adminPictureDTO = adminPictureDTO;
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




	public int getAdminUseAnnualVacation() {
		return adminUseAnnualVacation;
	}




	public void setAdminUseAnnualVacation(int adminUseAnnualVacation) {
		this.adminUseAnnualVacation = adminUseAnnualVacation;
	}




	public AdminEmailDTO getAdminEmailDTO() {
		return adminEmailDTO;
	}




	public void setAdminEmailDTO(AdminEmailDTO adminEmailDTO) {
		this.adminEmailDTO = adminEmailDTO;
	}




	public AdminAddressDTO getAdminAddressDTO() {
		return adminAddressDTO;
	}




	public void setAdminAddressDTO(AdminAddressDTO adminAddressDTO) {
		this.adminAddressDTO = adminAddressDTO;
	}




	public AdminPictureDTO getAdminPictureDTO() {
		return adminPictureDTO;
	}




	public void setAdminPictureDTO(AdminPictureDTO adminPictureDTO) {
		this.adminPictureDTO = adminPictureDTO;
	}




	@Override
	public String toString() {
		return "AdminAndAdminAllDTO [adminNo=" + adminNo + ", adminName=" + adminName + ", adminId=" + adminId
				+ ", adminPwd=" + adminPwd + ", adminBirth=" + adminBirth + ", adminGender=" + adminGender
				+ ", adminPhone=" + adminPhone + ", adminHireDate=" + adminHireDate + ", adminRetireDate="
				+ adminRetireDate + ", adminRetireYn=" + adminRetireYn + ", adminJob=" + adminJob
				+ ", adminLastLoginDate=" + adminLastLoginDate + ", adminSalary=" + adminSalary
				+ ", adminUseAnnualVacation=" + adminUseAnnualVacation + ", adminEmailDTO=" + adminEmailDTO
				+ ", adminAddressDTO=" + adminAddressDTO + ", adminPictureDTO=" + adminPictureDTO + "]";
	}
	
	

}
