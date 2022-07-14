package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_RETIRE_ADMIN")
@SequenceGenerator(
		name = "RETIRE_ADMIN_SEQ_TBL_GENERATOR",
		sequenceName = "SEQ_TBL_RETIRE_ADMIN",
		initialValue = 1,
		allocationSize = 1
)
public class RetireAdmin implements Serializable{

	@Id
	@Column(name="RETIRE_ADMIN_NO")
	@SequenceGenerator(
			name = "RETIRE_ADMIN_SEQ_TBL_GENERATOR",
			sequenceName = "SEQ_TBL_RETIRE_ADMIN",
			initialValue = 1,
			allocationSize = 1
	)
	private int retireAdminNo;
	
	@Column(name="RETIRE_ADMIN_NAME")
	private String retireAdminName;
	
	@Column(name="RETIRE_ADMIN_ID")
	private String retireAdminId;
	
	@Column(name="RETIRE_ADMIN_PWD")
	private String retireAdminPwd;
	
	@Column(name="RETIRE_ADMIN_BIRTH")
	private java.sql.Date retireAdminBrith;
	
	@Column(name="RETIRE_ADMIN_GENDER")
	private String retireAdminGender;
	
	@Column(name="RETIRE_ADMIN_PHONE")
	private String retireAdminPhone;
	
	@Column(name="RETIRE_ADMIN_HIRE_DATE")
	private java.sql.Date retireAdminHireDate;
	
	@Column(name="RETIRE_ADMIN_RETIRE_DATE")
	private java.sql.Date retireAdminRetireDate;
	
	@Column(name="RETIRE_ADMIN_RETIRE_YN")
	private String retireAdminRetireYn;
	
	@Column(name="RETIRE_ADMIN_JOB")
	private String retireAdminJob;
	
	@Column(name="RETIRE_ADMIN_LAST_LOGIN_DATE")
	private java.sql.Date retireAdminLastLoginDate;
	
	@Column(name="RETIRE_ADMIN_SALARY")
	private int retireAdminSalary;
	
	@Column(name="RETIRE_ANNUAL_VACATION_USE")
	private int retireAnnualVacationUse;

	public RetireAdmin() {
	}

	public RetireAdmin(int retireAdminNo, String retireAdminName, String retireAdminId, String retireAdminPwd,
			Date retireAdminBrith, String retireAdminGender, String retireAdminPhone, Date retireAdminHireDate,
			Date retireAdminRetireDate, String retireAdminRetireYn, String retireAdminJob,
			Date retireAdminLastLoginDate, int retireAdminSalary, int retireAnnualVacationUse) {
		this.retireAdminNo = retireAdminNo;
		this.retireAdminName = retireAdminName;
		this.retireAdminId = retireAdminId;
		this.retireAdminPwd = retireAdminPwd;
		this.retireAdminBrith = retireAdminBrith;
		this.retireAdminGender = retireAdminGender;
		this.retireAdminPhone = retireAdminPhone;
		this.retireAdminHireDate = retireAdminHireDate;
		this.retireAdminRetireDate = retireAdminRetireDate;
		this.retireAdminRetireYn = retireAdminRetireYn;
		this.retireAdminJob = retireAdminJob;
		this.retireAdminLastLoginDate = retireAdminLastLoginDate;
		this.retireAdminSalary = retireAdminSalary;
		this.retireAnnualVacationUse = retireAnnualVacationUse;
	}

	public int getRetireAdminNo() {
		return retireAdminNo;
	}

	public void setRetireAdminNo(int retireAdminNo) {
		this.retireAdminNo = retireAdminNo;
	}

	public String getRetireAdminName() {
		return retireAdminName;
	}

	public void setRetireAdminName(String retireAdminName) {
		this.retireAdminName = retireAdminName;
	}

	public String getRetireAdminId() {
		return retireAdminId;
	}

	public void setRetireAdminId(String retireAdminId) {
		this.retireAdminId = retireAdminId;
	}

	public String getRetireAdminPwd() {
		return retireAdminPwd;
	}

	public void setRetireAdminPwd(String retireAdminPwd) {
		this.retireAdminPwd = retireAdminPwd;
	}

	public java.sql.Date getRetireAdminBrith() {
		return retireAdminBrith;
	}

	public void setRetireAdminBrith(java.sql.Date retireAdminBrith) {
		this.retireAdminBrith = retireAdminBrith;
	}

	public String getRetireAdminGender() {
		return retireAdminGender;
	}

	public void setRetireAdminGender(String retireAdminGender) {
		this.retireAdminGender = retireAdminGender;
	}

	public String getRetireAdminPhone() {
		return retireAdminPhone;
	}

	public void setRetireAdminPhone(String retireAdminPhone) {
		this.retireAdminPhone = retireAdminPhone;
	}

	public java.sql.Date getRetireAdminHireDate() {
		return retireAdminHireDate;
	}

	public void setRetireAdminHireDate(java.sql.Date retireAdminHireDate) {
		this.retireAdminHireDate = retireAdminHireDate;
	}

	public java.sql.Date getRetireAdminRetireDate() {
		return retireAdminRetireDate;
	}

	public void setRetireAdminRetireDate(java.sql.Date retireAdminRetireDate) {
		this.retireAdminRetireDate = retireAdminRetireDate;
	}

	public String getRetireAdminRetireYn() {
		return retireAdminRetireYn;
	}

	public void setRetireAdminRetireYn(String retireAdminRetireYn) {
		this.retireAdminRetireYn = retireAdminRetireYn;
	}

	public String getRetireAdminJob() {
		return retireAdminJob;
	}

	public void setRetireAdminJob(String retireAdminJob) {
		this.retireAdminJob = retireAdminJob;
	}

	public java.sql.Date getRetireAdminLastLoginDate() {
		return retireAdminLastLoginDate;
	}

	public void setRetireAdminLastLoginDate(java.sql.Date retireAdminLastLoginDate) {
		this.retireAdminLastLoginDate = retireAdminLastLoginDate;
	}

	public int getRetireAdminSalary() {
		return retireAdminSalary;
	}

	public void setRetireAdminSalary(int retireAdminSalary) {
		this.retireAdminSalary = retireAdminSalary;
	}

	public int getRetireAnnualVacationUse() {
		return retireAnnualVacationUse;
	}

	public void setRetireAnnualVacationUse(int retireAnnualVacationUse) {
		this.retireAnnualVacationUse = retireAnnualVacationUse;
	}

	@Override
	public String toString() {
		return "RetireAdmin [retireAdminNo=" + retireAdminNo + ", retireAdminName=" + retireAdminName
				+ ", retireAdminId=" + retireAdminId + ", retireAdminPwd=" + retireAdminPwd + ", retireAdminBrith="
				+ retireAdminBrith + ", retireAdminGender=" + retireAdminGender + ", retireAdminPhone="
				+ retireAdminPhone + ", retireAdminHireDate=" + retireAdminHireDate + ", retireAdminRetireDate="
				+ retireAdminRetireDate + ", retireAdminRetireYn=" + retireAdminRetireYn + ", retireAdminJob="
				+ retireAdminJob + ", retireAdminLastLoginDate=" + retireAdminLastLoginDate + ", retireAdminSalary="
				+ retireAdminSalary + ", retireAnnualVacationUse=" + retireAnnualVacationUse + "]";
	}
	
	
	
}
