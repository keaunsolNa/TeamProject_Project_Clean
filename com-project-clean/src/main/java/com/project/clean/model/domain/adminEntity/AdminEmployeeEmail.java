package com.project.clean.model.domain.adminEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "TBL_EMPLOYEE_EMAIL")
@DynamicInsert
public class AdminEmployeeEmail {
	
	@Id
	@Column(name = "EMPLOYEE_NO")
	private int employeeNo;
	
	@Column(name = "EMPLOYEE_EMAIL")
	private String employeeEmail;
	
	@Column(name = "EMPLOYEE_DOMAIN")
	private String EmployeeDomain;

	public AdminEmployeeEmail() {
		super();
	}

	public AdminEmployeeEmail(int employeeNo, String employeeEmail, String employeeDomain) {
		super();
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

	@Override
	public String toString() {
		return "EmployeeEmail [employeeNo=" + employeeNo + ", employeeEmail=" + employeeEmail + ", EmployeeDomain="
				+ EmployeeDomain + "]";
	}
	
	
}
