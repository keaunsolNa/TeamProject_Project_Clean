package com.project.clean.model.domain.adminEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TBL_REASON")
@IdClass(AdminReasonPFKId.class)
@DynamicInsert
@DynamicUpdate
public class AdminReason implements Serializable{
	
	private static final long serialVersionUID = 4792075326909462293L;

	@Id
	@Column(name = "EMPLOYEE_NO")
	private int employeeNo;
	
	@Id
	@Column(name = "ADMIN_NO")
	private int adminNo;
	
	@Column(name = "REASON", nullable = true)
	private String Reason;
	
	@Column(name = "REGIST_REASON_DATE")
	private java.sql.Date employeeRegistDate;

	public AdminReason() {
		super();
	}

	public AdminReason(int employeeNo, int adminNo, String reason, Date employeeRegistDate) {
		super();
		this.employeeNo = employeeNo;
		this.adminNo = adminNo;
		Reason = reason;
		this.employeeRegistDate = employeeRegistDate;
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
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}

	public java.sql.Date getEmployeeRegistDate() {
		return employeeRegistDate;
	}

	public void setEmployeeRegistDate(java.sql.Date employeeRegistDate) {
		this.employeeRegistDate = employeeRegistDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminReason [employeeNo=" + employeeNo + ", adminNo=" + adminNo + ", Reason=" + Reason
				+ ", employeeRegistDate=" + employeeRegistDate + "]";
	}

	

	
	
	
}














