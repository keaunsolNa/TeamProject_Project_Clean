package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class StatisticsDTO implements Serializable{

	private static final long serialVersionUID = 3448241504852358438L;
	
	private java.sql.Date businessDate;
	private java.sql.Date businessStartTime;
	private java.sql.Date businessEndTime;
	private int totalPayment;
	private String paymentYn;
	private String adminJob;
	private int adminSalary;
	private int payHistoryAdminNo;
	private java.sql.Date payAdminDate;
	private java.sql.Date employeeSumTime;
	private String employeeName;
	
	public StatisticsDTO() {}

	public StatisticsDTO(Date businessDate, Date businessStartTime, Date businessEndTime, int totalPayment,
			String paymentYn, String adminJob, int adminSalary, int payHistoryAdminNo, Date payAdminDate,
			Date employeeSumTime, String employeeName) {
		super();
		this.businessDate = businessDate;
		this.businessStartTime = businessStartTime;
		this.businessEndTime = businessEndTime;
		this.totalPayment = totalPayment;
		this.paymentYn = paymentYn;
		this.adminJob = adminJob;
		this.adminSalary = adminSalary;
		this.payHistoryAdminNo = payHistoryAdminNo;
		this.payAdminDate = payAdminDate;
		this.employeeSumTime = employeeSumTime;
		this.employeeName = employeeName;
	}

	public java.sql.Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(java.sql.Date businessDate) {
		this.businessDate = businessDate;
	}

	public java.sql.Date getBusinessStartTime() {
		return businessStartTime;
	}

	public void setBusinessStartTime(java.sql.Date businessStartTime) {
		this.businessStartTime = businessStartTime;
	}

	public java.sql.Date getBusinessEndTime() {
		return businessEndTime;
	}

	public void setBusinessEndTime(java.sql.Date businessEndTime) {
		this.businessEndTime = businessEndTime;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	public String getPaymentYn() {
		return paymentYn;
	}

	public void setPaymentYn(String paymentYn) {
		this.paymentYn = paymentYn;
	}

	public String getAdminJob() {
		return adminJob;
	}

	public void setAdminJob(String adminJob) {
		this.adminJob = adminJob;
	}

	public int getAdminSalary() {
		return adminSalary;
	}

	public void setAdminSalary(int adminSalary) {
		this.adminSalary = adminSalary;
	}

	public int getPayHistoryAdminNo() {
		return payHistoryAdminNo;
	}

	public void setPayHistoryAdminNo(int payHistoryAdminNo) {
		this.payHistoryAdminNo = payHistoryAdminNo;
	}

	public java.sql.Date getPayAdminDate() {
		return payAdminDate;
	}

	public void setPayAdminDate(java.sql.Date payAdminDate) {
		this.payAdminDate = payAdminDate;
	}

	public java.sql.Date getEmployeeSumTime() {
		return employeeSumTime;
	}

	public void setEmployeeSumTime(java.sql.Date employeeSumTime) {
		this.employeeSumTime = employeeSumTime;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	@Override
	public String toString() {
		return "StatisticsDTO [businessDate=" + businessDate + ", businessStartTime=" + businessStartTime
				+ ", businessEndTime=" + businessEndTime + ", totalPayment=" + totalPayment + ", paymentYn=" + paymentYn
				+ ", adminJob=" + adminJob + ", adminSalary=" + adminSalary + ", payHistoryAdminNo=" + payHistoryAdminNo
				+ ", payAdminDate=" + payAdminDate + ", employeeSumTime=" + employeeSumTime + ", employeeName="
				+ employeeName + "]";
	}
}