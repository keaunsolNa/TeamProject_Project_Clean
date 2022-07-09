package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeRestCommitDTO implements Serializable{

	private static final long serialVersionUID = 3882546217567793334L;
	
	private int employeeNo;
	private int adminNo;
	private String reason;
	private java.sql.Date employeeLastConfirmDate;
	public EmployeeRestCommitDTO() {
	}
	public EmployeeRestCommitDTO(int employeeNo, int adminNo, String reason, Date employeeLastConfirmDate) {
		this.employeeNo = employeeNo;
		this.adminNo = adminNo;
		this.reason = reason;
		this.employeeLastConfirmDate = employeeLastConfirmDate;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public java.sql.Date getEmployeeLastConfirmDate() {
		return employeeLastConfirmDate;
	}
	public void setEmployeeLastConfirmDate(java.sql.Date employeeLastConfirmDate) {
		this.employeeLastConfirmDate = employeeLastConfirmDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "EmployeeRestCommitDTO [employeeNo=" + employeeNo + ", adminNo=" + adminNo + ", reason=" + reason
				+ ", employeeLastConfirmDate=" + employeeLastConfirmDate + "]";
	}
	
	

}
