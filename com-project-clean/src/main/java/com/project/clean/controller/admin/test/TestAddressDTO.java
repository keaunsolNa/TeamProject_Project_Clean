package com.project.clean.controller.admin.test;

import java.io.Serializable;

public class TestAddressDTO implements Serializable {

	private static final long serialVersionUID = -2041062046663889399L;

	private int employeeNo;
	private String employeeAddressNo;
	private String employeeAddress;
	private String employeeDetailAddress;
	
	public TestAddressDTO() {
		super();
	}
	public TestAddressDTO(int employeeNo, String employeeAddressNo, String employeeAddress,
			String employeeDetailAddress) {
		super();
		this.employeeNo = employeeNo;
		this.employeeAddressNo = employeeAddressNo;
		this.employeeAddress = employeeAddress;
		this.employeeDetailAddress = employeeDetailAddress;
	}
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeAddressNo() {
		return employeeAddressNo;
	}
	public void setEmployeeAddressNo(String employeeAddressNo) {
		this.employeeAddressNo = employeeAddressNo;
	}
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public String getEmployeeDetailAddress() {
		return employeeDetailAddress;
	}
	public void setEmployeeDetailAddress(String employeeDetailAddress) {
		this.employeeDetailAddress = employeeDetailAddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TestAddressDTO [employeeNo=" + employeeNo + ", employeeAddressNo=" + employeeAddressNo
				+ ", employeeAddress=" + employeeAddress + ", employeeDetailAddress=" + employeeDetailAddress + "]";
	}
	
	
}
