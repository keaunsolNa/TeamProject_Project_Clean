package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_EMPLOYEE_EMAIL")
public class EmployeeEmail implements Serializable{

	private static final long serialVersionUID = -4802468707767782111L;
	
	@Id
	@Column(name="EMPLOYEE_NO")
	private int employeeNo;
	
	@Column(name="EMPLOYEE_EMAIL")
	private String employeeEmail;
	
	@Column(name="EMPLOYEE_DOMAIN")
	private String EmployeeDomain;

	public EmployeeEmail() {
	}

	public EmployeeEmail(int employeeNo, String employeeEmail, String employeeDomain) {
		this.employeeNo = employeeNo;
		this.employeeEmail = employeeEmail;
		EmployeeDomain = employeeDomain;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeeDomain() {
		return EmployeeDomain;
	}

	public void setEmployeeDomain(String employeeDomain) {
		EmployeeDomain = employeeDomain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmployeeEmail [employeeNo=" + employeeNo + ", employeeEmail=" + employeeEmail + ", EmployeeDomain="
				+ EmployeeDomain + "]";
	}
	
	
}
