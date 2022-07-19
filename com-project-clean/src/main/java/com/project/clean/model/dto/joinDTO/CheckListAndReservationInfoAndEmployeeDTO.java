package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;

public class CheckListAndReservationInfoAndEmployeeDTO implements Serializable{

	private static final long serialVersionUID = -7947093037753135106L;

	private Integer checkReservationNo;
	private String checkHTML;
	private String checkStatus;
	private Integer adminNo;
	private String customerName;
	private String employeeName;
	private String adminName;
	public CheckListAndReservationInfoAndEmployeeDTO() {
	}
	public CheckListAndReservationInfoAndEmployeeDTO(Integer checkReservationNo, String checkHTML, String checkStatus,
			Integer adminNo, String customerName, String employeeName, String adminName) {
		this.checkReservationNo = checkReservationNo;
		this.checkHTML = checkHTML;
		this.checkStatus = checkStatus;
		this.adminNo = adminNo;
		this.customerName = customerName;
		this.employeeName = employeeName;
		this.adminName = adminName;
	}
	public Integer getCheckReservationNo() {
		return checkReservationNo;
	}
	public void setCheckReservationNo(Integer checkReservationNo) {
		this.checkReservationNo = checkReservationNo;
	}
	public String getCheckHTML() {
		return checkHTML;
	}
	public void setCheckHTML(String checkHTML) {
		this.checkHTML = checkHTML;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Integer getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckListAndReservationInfoAndEmployeeDTO [checkReservationNo=" + checkReservationNo + ", checkHTML="
				+ checkHTML + ", checkStatus=" + checkStatus + ", adminNo=" + adminNo + ", customerName=" + customerName
				+ ", employeeName=" + employeeName + ", adminName=" + adminName + "]";
	}
	
	
	
	
	
	
	
	
}
