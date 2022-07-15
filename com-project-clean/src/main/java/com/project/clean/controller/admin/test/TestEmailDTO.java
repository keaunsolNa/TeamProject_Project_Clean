package com.project.clean.controller.admin.test;

import java.io.Serializable;

public class TestEmailDTO implements Serializable{

	private static final long serialVersionUID = 5161902415475968855L;
	
	private int employeeNo;
	private String employeeEmail;
	private String EmployeeDomain;
	
	public TestEmailDTO() {
		super();
	}
	public TestEmailDTO(int employeeNo, String employeeEmail, String employeeDomain) {
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
		return "TestEmailDTO [employeeNo=" + employeeNo + ", employeeEmail=" + employeeEmail + ", EmployeeDomain="
				+ EmployeeDomain + "]";
	}
	
	
}
