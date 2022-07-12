package com.project.clean.model.domain.adminEntity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "TBL_EMPLOYEE_ADDRESS")
@DynamicInsert
public class AdminEmployeeAddress implements Serializable{

	private static final long serialVersionUID = 8851790007206166773L;

	@Id
	@Column(name = "EMPLOYEE_NO")
	private int employeeNo;
	
	@Column(name = "EMPLOYEE_ADDRESS_NO")
	private String employeeAddressNo;
	
	@Column(name = "EMPLOYEE_ADDRESS")
	private String employeeAddress;
	
	@Column(name = "EMPLOYEE_DETAIL_ADDRESS")
	private String employeeDetailAddress;

	public AdminEmployeeAddress() {
		super();
	}

	public AdminEmployeeAddress(int employeeNo, String employeeAddressNo, String employeeAddress,
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
		return "AdminEmployeeAddress [employeeNo=" + employeeNo + ", employeeAddressNo=" + employeeAddressNo
				+ ", employeeAddress=" + employeeAddress + ", employeeDetailAddress=" + employeeDetailAddress + "]";
	}

	
	

	
	

}
