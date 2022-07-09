package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_EMPLOYEE_REST_COMMIT")
public class EmployeeRestCommit implements Serializable{
	private static final long serialVersionUID = 1227252943717788373L;

	@Id
	@Column(name="EMPLOYEE_NO")
	private int employeeNo;
	
	@Column(name="ADMIN_NO")
	private int adminNo;
	
	@Column(name="REASON")
	private String reason;
	
	@Column(name="EMPLOYEE_LAST_CONFIRM_DATE")
	private java.sql.Date employeeLastConfirmDate;

	public EmployeeRestCommit() {
	}

	public EmployeeRestCommit(int employeeNo, int adminNo, String reason, Date employeeLastConfirmDate) {
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
		return "EmployeeRestCommit [employeeNo=" + employeeNo + ", adminNo=" + adminNo + ", reason=" + reason
				+ ", employeeLastConfirmDate=" + employeeLastConfirmDate + "]";
	}

	

}
