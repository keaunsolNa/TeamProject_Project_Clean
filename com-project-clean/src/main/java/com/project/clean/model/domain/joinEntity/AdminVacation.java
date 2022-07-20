package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.clean.model.domain.commonEntity.Vacation;

@Entity
@Table(name = "TBL_ADMIN")
public class AdminVacation implements Serializable {
	private static final long serialVersionUID = -8297139289361809233L;

	@Id
	@Column(name="ADMIN_NO")
	private int adminNo;

	@Column(name="ADMIN_NAME")
	private String adminName;

	@Column(name="ADMIN_ID")
	private String adminId;
	
	@Column(name="ADMIN_PWD")
	private String adminPwd;
	
	@Column(name="ADMIN_BIRTH")
	private java.sql.Date adminBirth;
	
	@Column(name="ADMIN_GENDER")
	private String adminGender;
	
	@Column(name="ADMIN_PHONE")
	private String adminPhone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="ADMIN_HIRE_DATE")
	private java.sql.Date adminHireDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="ADMIN_RETIRE_DATE", nullable=true)
	private java.sql.Date adminRetireDate;
	
	@Column(name="ADMIN_RETIRE_YN")
	private String adminRetireYn;
	
	@Column(name="ADMIN_JOB")
	private String adminJob;
	
	@Column(name="ADMIN_LAST_LOGIN_DATE", nullable=true)
	private java.sql.Date adminLastLoginDate;
	
	@Column(name="ADMIN_SALARY")
	private int adminSalary;
	
	@Column(name="ADMIN_USE_ANNUAL_VACATION", nullable=true)
	private int annualVacationUse;
	
	
	@Column(name="ADMIN_EMAIL")
	private String adminEmail;

	@Column(name="ADMIN_ADDRESS")
	private String adminAddress;
	
	@Column(name="ADMIN_PICTURE_SAVE_NAME", nullable=true)
	private String adminPictureSaveName;
	
	@Column(name="ADMIN_PICTURE_SAVE_ROOT", nullable=true)
	private String adminPictureSaveRoot;
	
	@Column(name="ADMIN_PICTURE_THUMBNAIL_NAME", nullable=true)
	private String adminPictureThumbnailName;

	@OneToMany
	@JoinColumn(name = "ADMIN_NO" , insertable = false, updatable = false)
	private List<Vacation> vacationList;
	
	
	

	public AdminVacation() {
	}




	public AdminVacation(int adminNo, String adminName, String adminId, String adminPwd, Date adminBirth,
			String adminGender, String adminPhone, Date adminHireDate, Date adminRetireDate, String adminRetireYn,
			String adminJob, Date adminLastLoginDate, int adminSalary, int annualVacationUse, String adminEmail,
			String adminAddress, String adminPictureSaveName, String adminPictureSaveRoot,
			String adminPictureThumbnailName, List<Vacation> vacationList) {
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
		this.adminEmail = adminEmail;
		this.adminAddress = adminAddress;
		this.adminPictureSaveName = adminPictureSaveName;
		this.adminPictureSaveRoot = adminPictureSaveRoot;
		this.adminPictureThumbnailName = adminPictureThumbnailName;
		this.vacationList = vacationList;
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




	public String getAdminEmail() {
		return adminEmail;
	}




	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}




	public String getAdminAddress() {
		return adminAddress;
	}




	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}




	public String getAdminPictureSaveName() {
		return adminPictureSaveName;
	}




	public void setAdminPictureSaveName(String adminPictureSaveName) {
		this.adminPictureSaveName = adminPictureSaveName;
	}




	public String getAdminPictureSaveRoot() {
		return adminPictureSaveRoot;
	}




	public void setAdminPictureSaveRoot(String adminPictureSaveRoot) {
		this.adminPictureSaveRoot = adminPictureSaveRoot;
	}




	public String getAdminPictureThumbnailName() {
		return adminPictureThumbnailName;
	}




	public void setAdminPictureThumbnailName(String adminPictureThumbnailName) {
		this.adminPictureThumbnailName = adminPictureThumbnailName;
	}




	public List<Vacation> getVacationList() {
		return vacationList;
	}




	public void setVacationList(List<Vacation> vacationList) {
		this.vacationList = vacationList;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	@Override
	public String toString() {
		return "AdminVacation [adminNo=" + adminNo + ", adminName=" + adminName + ", adminId=" + adminId + ", adminPwd="
				+ adminPwd + ", adminBirth=" + adminBirth + ", adminGender=" + adminGender + ", adminPhone="
				+ adminPhone + ", adminHireDate=" + adminHireDate + ", adminRetireDate=" + adminRetireDate
				+ ", adminRetireYn=" + adminRetireYn + ", adminJob=" + adminJob + ", adminLastLoginDate="
				+ adminLastLoginDate + ", adminSalary=" + adminSalary + ", annualVacationUse=" + annualVacationUse
				+ ", adminEmail=" + adminEmail + ", adminAddress=" + adminAddress + ", adminPictureSaveName="
				+ adminPictureSaveName + ", adminPictureSaveRoot=" + adminPictureSaveRoot
				+ ", adminPictureThumbnailName=" + adminPictureThumbnailName + ", vacationList=" + vacationList + "]";
	}
	
	
	
}
