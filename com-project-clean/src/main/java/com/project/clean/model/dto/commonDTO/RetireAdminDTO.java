package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class RetireAdminDTO implements Serializable{
	private static final long serialVersionUID = -3655567958779617346L;
	
	private int retireAdminNo;
	private String retireAdminName;
	private String retireAdminId;
	private String retireAdminPwd;
	private java.sql.Date retireAdminBrith;
	private String retireAdminGender;
	private String retireAdminPhone;
	private java.sql.Date retireAdminHireDate;
	private java.sql.Date retireAdminRetireDate;
	private String retireAdminRetireYn;
	private String retireAdminJob;
	private java.sql.Date retireAdminLastLoginDate;
	private int retireAdminSalary;
	private int retireAnnualVacationUse;
	
	
	
	public RetireAdminDTO() {
	}



	public RetireAdminDTO(int retireAdminNo, String retireAdminName, String retireAdminId, String retireAdminPwd,
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



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "RetireAdminDTO [retireAdminNo=" + retireAdminNo + ", retireAdminName=" + retireAdminName
				+ ", retireAdminId=" + retireAdminId + ", retireAdminPwd=" + retireAdminPwd + ", retireAdminBrith="
				+ retireAdminBrith + ", retireAdminGender=" + retireAdminGender + ", retireAdminPhone="
				+ retireAdminPhone + ", retireAdminHireDate=" + retireAdminHireDate + ", retireAdminRetireDate="
				+ retireAdminRetireDate + ", retireAdminRetireYn=" + retireAdminRetireYn + ", retireAdminJob="
				+ retireAdminJob + ", retireAdminLastLoginDate=" + retireAdminLastLoginDate + ", retireAdminSalary="
				+ retireAdminSalary + ", retireAnnualVacationUse=" + retireAnnualVacationUse + "]";
	}


}
