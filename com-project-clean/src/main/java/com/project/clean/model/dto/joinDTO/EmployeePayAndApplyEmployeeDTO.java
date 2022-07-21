package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;

public class EmployeePayAndApplyEmployeeDTO implements Serializable{

	private static final long serialVersionUID = 2287870055904603747L;
	
	private int payHistoryEmployeeNo;
	private java.sql.Date payEmployeeDate; 
	private int payEmployeeFinalSalary;
	private int applyEmployeeNo;
	private int applyReservationNo;
	private String employeeName;
	private String employeePhone;
	private String userAddress;
	private String userDetailAddress;
	private java.sql.Date businessDate;
	private java.sql.Timestamp businessStartTime;
	private java.sql.Timestamp businessEndTime;
	private int totalPayment;
	
	
	public EmployeePayAndApplyEmployeeDTO() {
	}


	public EmployeePayAndApplyEmployeeDTO(int payHistoryEmployeeNo, Date payEmployeeDate, int payEmployeeFinalSalary,
			int applyEmployeeNo, int applyReservationNo, String employeeName, String employeePhone, String userAddress,
			String userDetailAddress, Date businessDate, Timestamp businessStartTime, Timestamp businessEndTime,
			int totalPayment) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
		this.applyEmployeeNo = applyEmployeeNo;
		this.applyReservationNo = applyReservationNo;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.userAddress = userAddress;
		this.userDetailAddress = userDetailAddress;
		this.businessDate = businessDate;
		this.businessStartTime = businessStartTime;
		this.businessEndTime = businessEndTime;
		this.totalPayment = totalPayment;
	}


	public int getPayHistoryEmployeeNo() {
		return payHistoryEmployeeNo;
	}


	public void setPayHistoryEmployeeNo(int payHistoryEmployeeNo) {
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
	}


	public java.sql.Date getPayEmployeeDate() {
		return payEmployeeDate;
	}


	public void setPayEmployeeDate(java.sql.Date payEmployeeDate) {
		this.payEmployeeDate = payEmployeeDate;
	}


	public int getPayEmployeeFinalSalary() {
		return payEmployeeFinalSalary;
	}


	public void setPayEmployeeFinalSalary(int payEmployeeFinalSalary) {
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
	}


	public int getApplyEmployeeNo() {
		return applyEmployeeNo;
	}


	public void setApplyEmployeeNo(int applyEmployeeNo) {
		this.applyEmployeeNo = applyEmployeeNo;
	}


	public int getApplyReservationNo() {
		return applyReservationNo;
	}


	public void setApplyReservationNo(int applyReservationNo) {
		this.applyReservationNo = applyReservationNo;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	public String getEmployeePhone() {
		return employeePhone;
	}


	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public String getUserDetailAddress() {
		return userDetailAddress;
	}


	public void setUserDetailAddress(String userDetailAddress) {
		this.userDetailAddress = userDetailAddress;
	}


	public java.sql.Date getBusinessDate() {
		return businessDate;
	}


	public void setBusinessDate(java.sql.Date businessDate) {
		this.businessDate = businessDate;
	}


	public java.sql.Timestamp getBusinessStartTime() {
		return businessStartTime;
	}


	public void setBusinessStartTime(java.sql.Timestamp businessStartTime) {
		this.businessStartTime = businessStartTime;
	}


	public java.sql.Timestamp getBusinessEndTime() {
		return businessEndTime;
	}


	public void setBusinessEndTime(java.sql.Timestamp businessEndTime) {
		this.businessEndTime = businessEndTime;
	}


	public int getTotalPayment() {
		return totalPayment;
	}


	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "EmployeePayAndApplyEmployeeDTO [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeDate="
				+ payEmployeeDate + ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + ", applyEmployeeNo="
				+ applyEmployeeNo + ", applyReservationNo=" + applyReservationNo + ", employeeName=" + employeeName
				+ ", employeePhone=" + employeePhone + ", userAddress=" + userAddress + ", userDetailAddress="
				+ userDetailAddress + ", businessDate=" + businessDate + ", businessStartTime=" + businessStartTime
				+ ", businessEndTime=" + businessEndTime + ", totalPayment=" + totalPayment + "]";
	}


	
	
	
	
	
}
