package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_RETIRE_EMPLOYEE")
public class RetireEmployee implements Serializable{

	@Id
	@Column(name="RETIRE_EMPLOYEE_NO")
	private int retireEmployeeNo;
	
	@Column(name="EMPLOYEE_NAME")
	private String retireEmployeeName;
	
	@Column(name="RETIRE_EMPLOYEE_ID")
	private String retireEmployeeId;
	
	@Column(name="RETIRE_EMPLOYEE_PWD")
	private String retireEmployeePwd;
	
	@Column(name="RETIRE_EMPLOYEE_BIRTH")
	private java.sql.Date retireEmployeeBirth;
	
	@Column(name="RETIRE_EMPLOYEE_GENDER")
	private String retireEmployeeGender;
	
	@Column(name="RETIRE_EMPLOYEE_PHONE")
	private String retireEmployeePhone;
	
	@Column(name="RETIRE_EMPLOYEE_HIRE_DATE")
	private java.sql.Date retireEmployeeHireDate;
	
	@Column(name="RETIRE_EMPLOYEE_RETIRE_DATE")
	private java.sql.Date retireEmployeeRetireDate;
	
	@Column(name="RETIRE_EMPLOYEE_SUM_COUNT")
	private int retireEmployeeSumCount;
	
	@Column(name="RETIRE_EMPLOYEE_SUM_TIME")
	private int retireEmployeeSumTime;
	
	@Column(name="RETIRE_EMPLOYEE_EMAIL")
	private String retireEmployeeEmail;
	
	@Column(name="RETIRE_EMPLOYEE_RETIRE_YN")
	private String retireEmployeeRetireYn;
	
	@Column(name="EMPLOYEE_LAST_LOGIN_DATE")
	private java.sql.Date retireEmployeeLastLoginDate;

	public RetireEmployee() {
	}

	public RetireEmployee(int retireEmployeeNo, String retireEmployeeName, String retireEmployeeId,
			String retireEmployeePwd, Date retireEmployeeBirth, String retireEmployeeGender, String retireEmployeePhone,
			Date retireEmployeeHireDate, Date retireEmployeeRetireDate, int retireEmployeeSumCount,
			int retireEmployeeSumTime, String retireEmployeeEmail, String retireEmployeeRetireYn,
			Date retireEmployeeLastLoginDate) {
		this.retireEmployeeNo = retireEmployeeNo;
		this.retireEmployeeName = retireEmployeeName;
		this.retireEmployeeId = retireEmployeeId;
		this.retireEmployeePwd = retireEmployeePwd;
		this.retireEmployeeBirth = retireEmployeeBirth;
		this.retireEmployeeGender = retireEmployeeGender;
		this.retireEmployeePhone = retireEmployeePhone;
		this.retireEmployeeHireDate = retireEmployeeHireDate;
		this.retireEmployeeRetireDate = retireEmployeeRetireDate;
		this.retireEmployeeSumCount = retireEmployeeSumCount;
		this.retireEmployeeSumTime = retireEmployeeSumTime;
		this.retireEmployeeEmail = retireEmployeeEmail;
		this.retireEmployeeRetireYn = retireEmployeeRetireYn;
		this.retireEmployeeLastLoginDate = retireEmployeeLastLoginDate;
	}

	public int getRetireEmployeeNo() {
		return retireEmployeeNo;
	}

	public void setRetireEmployeeNo(int retireEmployeeNo) {
		this.retireEmployeeNo = retireEmployeeNo;
	}

	public String getRetireEmployeeName() {
		return retireEmployeeName;
	}

	public void setRetireEmployeeName(String retireEmployeeName) {
		this.retireEmployeeName = retireEmployeeName;
	}

	public String getRetireEmployeeId() {
		return retireEmployeeId;
	}

	public void setRetireEmployeeId(String retireEmployeeId) {
		this.retireEmployeeId = retireEmployeeId;
	}

	public String getRetireEmployeePwd() {
		return retireEmployeePwd;
	}

	public void setRetireEmployeePwd(String retireEmployeePwd) {
		this.retireEmployeePwd = retireEmployeePwd;
	}

	public java.sql.Date getRetireEmployeeBirth() {
		return retireEmployeeBirth;
	}

	public void setRetireEmployeeBirth(java.sql.Date retireEmployeeBirth) {
		this.retireEmployeeBirth = retireEmployeeBirth;
	}

	public String getRetireEmployeeGender() {
		return retireEmployeeGender;
	}

	public void setRetireEmployeeGender(String retireEmployeeGender) {
		this.retireEmployeeGender = retireEmployeeGender;
	}

	public String getRetireEmployeePhone() {
		return retireEmployeePhone;
	}

	public void setRetireEmployeePhone(String retireEmployeePhone) {
		this.retireEmployeePhone = retireEmployeePhone;
	}

	public java.sql.Date getRetireEmployeeHireDate() {
		return retireEmployeeHireDate;
	}

	public void setRetireEmployeeHireDate(java.sql.Date retireEmployeeHireDate) {
		this.retireEmployeeHireDate = retireEmployeeHireDate;
	}

	public java.sql.Date getRetireEmployeeRetireDate() {
		return retireEmployeeRetireDate;
	}

	public void setRetireEmployeeRetireDate(java.sql.Date retireEmployeeRetireDate) {
		this.retireEmployeeRetireDate = retireEmployeeRetireDate;
	}

	public int getRetireEmployeeSumCount() {
		return retireEmployeeSumCount;
	}

	public void setRetireEmployeeSumCount(int retireEmployeeSumCount) {
		this.retireEmployeeSumCount = retireEmployeeSumCount;
	}

	public int getRetireEmployeeSumTime() {
		return retireEmployeeSumTime;
	}

	public void setRetireEmployeeSumTime(int retireEmployeeSumTime) {
		this.retireEmployeeSumTime = retireEmployeeSumTime;
	}

	public String getRetireEmployeeEmail() {
		return retireEmployeeEmail;
	}

	public void setRetireEmployeeEmail(String retireEmployeeEmail) {
		this.retireEmployeeEmail = retireEmployeeEmail;
	}

	public String getRetireEmployeeRetireYn() {
		return retireEmployeeRetireYn;
	}

	public void setRetireEmployeeRetireYn(String retireEmployeeRetireYn) {
		this.retireEmployeeRetireYn = retireEmployeeRetireYn;
	}

	public java.sql.Date getRetireEmployeeLastLoginDate() {
		return retireEmployeeLastLoginDate;
	}

	public void setRetireEmployeeLastLoginDate(java.sql.Date retireEmployeeLastLoginDate) {
		this.retireEmployeeLastLoginDate = retireEmployeeLastLoginDate;
	}

	@Override
	public String toString() {
		return "RetireEmployee [retireEmployeeNo=" + retireEmployeeNo + ", retireEmployeeName=" + retireEmployeeName
				+ ", retireEmployeeId=" + retireEmployeeId + ", retireEmployeePwd=" + retireEmployeePwd
				+ ", retireEmployeeBirth=" + retireEmployeeBirth + ", retireEmployeeGender=" + retireEmployeeGender
				+ ", retireEmployeePhone=" + retireEmployeePhone + ", retireEmployeeHireDate=" + retireEmployeeHireDate
				+ ", retireEmployeeRetireDate=" + retireEmployeeRetireDate + ", retireEmployeeSumCount="
				+ retireEmployeeSumCount + ", retireEmployeeSumTime=" + retireEmployeeSumTime + ", retireEmployeeEmail="
				+ retireEmployeeEmail + ", retireEmployeeRetireYn=" + retireEmployeeRetireYn
				+ ", retireEmployeeLastLoginDate=" + retireEmployeeLastLoginDate + "]";
	}

	
	
}
