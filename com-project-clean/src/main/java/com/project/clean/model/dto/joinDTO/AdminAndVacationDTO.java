package com.project.clean.model.dto.joinDTO;

import java.sql.Date;

public class AdminAndVacationDTO {
	
	private int adminNo;
	private String adminName;
	private int vacationNo;
	private java.sql.Date requestDate;
	private String requestAdmin;
	private String vacationName;
	private String drafter;
	private String vacationFirstConfirmYn;
	private String vacationSecondConfirmYn;
	private String vacationLastConfirmYn;
	private java.sql.Date vacationStartDate;
	private java.sql.Date vacationEndDate;
	private String vacationReason;
	private String vacationReturnYn;
	public AdminAndVacationDTO() {
		super();
	}
	public AdminAndVacationDTO(int adminNo, String adminName, int vacationNo, Date requestDate, String requestAdmin,
			String vacationName, String drafter, String vacationFirstConfirmYn, String vacationSecondConfirmYn,
			String vacationLastConfirmYn, Date vacationStartDate, Date vacationEndDate, String vacationReason,
			String vacationReturnYn) {
		super();
		this.adminNo = adminNo;
		this.adminName = adminName;
		this.vacationNo = vacationNo;
		this.requestDate = requestDate;
		this.requestAdmin = requestAdmin;
		this.vacationName = vacationName;
		this.drafter = drafter;
		this.vacationFirstConfirmYn = vacationFirstConfirmYn;
		this.vacationSecondConfirmYn = vacationSecondConfirmYn;
		this.vacationLastConfirmYn = vacationLastConfirmYn;
		this.vacationStartDate = vacationStartDate;
		this.vacationEndDate = vacationEndDate;
		this.vacationReason = vacationReason;
		this.vacationReturnYn = vacationReturnYn;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getVacationNo() {
		return vacationNo;
	}
	public void setVacationNo(int vacationNo) {
		this.vacationNo = vacationNo;
	}
	public java.sql.Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(java.sql.Date requestDate) {
		this.requestDate = requestDate;
	}
	public String getRequestAdmin() {
		return requestAdmin;
	}
	public void setRequestAdmin(String requestAdmin) {
		this.requestAdmin = requestAdmin;
	}
	public String getVacationName() {
		return vacationName;
	}
	public void setVacationName(String vacationName) {
		this.vacationName = vacationName;
	}
	public String getDrafter() {
		return drafter;
	}
	public void setDrafter(String drafter) {
		this.drafter = drafter;
	}
	public String getVacationFirstConfirmYn() {
		return vacationFirstConfirmYn;
	}
	public void setVacationFirstConfirmYn(String vacationFirstConfirmYn) {
		this.vacationFirstConfirmYn = vacationFirstConfirmYn;
	}
	public String getVacationSecondConfirmYn() {
		return vacationSecondConfirmYn;
	}
	public void setVacationSecondConfirmYn(String vacationSecondConfirmYn) {
		this.vacationSecondConfirmYn = vacationSecondConfirmYn;
	}
	public String getVacationLastConfirmYn() {
		return vacationLastConfirmYn;
	}
	public void setVacationLastConfirmYn(String vacationLastConfirmYn) {
		this.vacationLastConfirmYn = vacationLastConfirmYn;
	}
	public java.sql.Date getVacationStartDate() {
		return vacationStartDate;
	}
	public void setVacationStartDate(java.sql.Date vacationStartDate) {
		this.vacationStartDate = vacationStartDate;
	}
	public java.sql.Date getVacationEndDate() {
		return vacationEndDate;
	}
	public void setVacationEndDate(java.sql.Date vacationEndDate) {
		this.vacationEndDate = vacationEndDate;
	}
	public String getVacationReason() {
		return vacationReason;
	}
	public void setVacationReason(String vacationReason) {
		this.vacationReason = vacationReason;
	}
	public String getVacationReturnYn() {
		return vacationReturnYn;
	}
	public void setVacationReturnYn(String vacationReturnYn) {
		this.vacationReturnYn = vacationReturnYn;
	}
	@Override
	public String toString() {
		return "AdminAndVacationDTO [adminNo=" + adminNo + ", adminName=" + adminName + ", vacationNo=" + vacationNo
				+ ", requestDate=" + requestDate + ", requestAdmin=" + requestAdmin + ", vacationName=" + vacationName
				+ ", drafter=" + drafter + ", vacationFirstConfirmYn=" + vacationFirstConfirmYn
				+ ", vacationSecondConfirmYn=" + vacationSecondConfirmYn + ", vacationLastConfirmYn="
				+ vacationLastConfirmYn + ", vacationStartDate=" + vacationStartDate + ", vacationEndDate="
				+ vacationEndDate + ", vacationReason=" + vacationReason + ", vacationReturnYn=" + vacationReturnYn
				+ "]";
	}
	
}
