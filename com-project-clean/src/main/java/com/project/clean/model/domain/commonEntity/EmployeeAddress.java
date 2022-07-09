package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_EMPLOYEE_ADDRESS")
public class EmployeeAddress implements Serializable{

	private static final long serialVersionUID = 8858807642977947599L;

	@Id
	@Column(name="EMPLOYEE_NO")
	private int employeeNo;
	
	@Column(name="EMPLOYEE_ADDRESS_NO")
	private int employeeAddressNo;
	
	@Column(name="EMPLOYEE_ADDRESS")
	private String employeeAddress;
	
	@Column(name="EMPLOYEE_DETAIL_ADDRESS")
	private String employeeDetailAddress;

	public EmployeeAddress() {
	}

	public EmployeeAddress(int employeeNo, int employeeAddressNo, String employeeaddress,
			String employeeDetailAddress) {
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

	public int getEmployeeAddressNo() {
		return employeeAddressNo;
	}

	public void setEmployeeAddressNo(int employeeAddressNo) {
		this.employeeAddressNo = employeeAddressNo;
	}

	public String getEmployeeaddress() {
		return employeeAddress;
	}

	public void setEmployeeaddress(String employeeAddress) {
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
		return "EmployeeAddress [employeeNo=" + employeeNo + ", employeeAddressNo=" + employeeAddressNo
				+ ", employeeAddress=" + employeeAddress + ", employeeDetailAddress=" + employeeDetailAddress + "]";
	}
	
	
}
