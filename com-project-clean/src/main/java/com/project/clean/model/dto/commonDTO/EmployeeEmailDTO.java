package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class EmployeeEmailDTO implements Serializable {

	private static final long serialVersionUID = 4538353386139628818L;

	private int employeeNo;
	private String employeeEmail;
	private String EmployeeDomain;
	public EmployeeEmailDTO() {
	}
	public EmployeeEmailDTO(int employeeNo, String employeeEmail, String employeeDomain) {
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
		return "EmployeeEmailDTO [employeeNo=" + employeeNo + ", employeeEmail=" + employeeEmail + ", EmployeeDomain="
				+ EmployeeDomain + "]";
	}
	
	
}
