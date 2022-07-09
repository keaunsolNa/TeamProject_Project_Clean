package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class RetireEmployeeDTO implements Serializable{
	
	private static final long serialVersionUID = -3212781007325137662L;
	
	private int retireEmployeeNo;
	private String retireEmployeeName;
	private String retireEmployeeId;
	private String retireEmployeePwd;
	private java.sql.Date retireEmployeeBirth;
	private String retireEmployeeGender;
	private String retireEmployeePhone;
	private java.sql.Date retireEmployeeHireDate;
	private java.sql.Date retireEmployeeRetireDate;
	private int retireEmployeeSumCount;
	private int retireEmployeeSumTime;
	private String retireEmployeeEmail;
	private String retireEmployeeRetireYn;
	private java.sql.Date retireEmployeeLastLoginDate;
	public RetireEmployeeDTO() {
	}
	public RetireEmployeeDTO(int retireEmployeeNo, String retireEmployeeName, String retireEmployeeId,
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "RetireEmployeeDTO [retireEmployeeNo=" + retireEmployeeNo + ", retireEmployeeName=" + retireEmployeeName
				+ ", retireEmployeeId=" + retireEmployeeId + ", retireEmployeePwd=" + retireEmployeePwd
				+ ", retireEmployeeBirth=" + retireEmployeeBirth + ", retireEmployeeGender=" + retireEmployeeGender
				+ ", retireEmployeePhone=" + retireEmployeePhone + ", retireEmployeeHireDate=" + retireEmployeeHireDate
				+ ", retireEmployeeRetireDate=" + retireEmployeeRetireDate + ", retireEmployeeSumCount="
				+ retireEmployeeSumCount + ", retireEmployeeSumTime=" + retireEmployeeSumTime + ", retireEmployeeEmail="
				+ retireEmployeeEmail + ", retireEmployeeRetireYn=" + retireEmployeeRetireYn
				+ ", retireEmployeeLastLoginDate=" + retireEmployeeLastLoginDate + "]";
	}
	
	

}
