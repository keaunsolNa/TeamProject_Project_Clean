package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class CheckListDTO implements Serializable{

	private static final long serialVersionUID = -7366151170662430573L;
	private int checkReservationNo;
	private String checkHTML;
	private String checkStatus;
	private int adminNo;
	
	public CheckListDTO() {
	}
	
	public CheckListDTO(int checkReservationNo, String checkHTML, String checkStatus, int adminNo) {
		this.checkReservationNo = checkReservationNo;
		this.checkHTML = checkHTML;
		this.checkStatus = checkStatus;
		this.adminNo = adminNo;
	}
	public int getCheckReservationNo() {
		return checkReservationNo;
	}
	public void setCheckReservationNo(int checkReservationNo) {
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
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CheckListDTO [checkReservationNo=" + checkReservationNo + ", checkHTML=" + checkHTML + ", checkStatus="
				+ checkStatus + ", adminNo=" + adminNo + "]";
	}
	
	
	
}
