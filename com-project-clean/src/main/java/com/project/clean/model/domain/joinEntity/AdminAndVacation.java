package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.project.clean.model.domain.commonEntity.VacationCommit;

public class AdminAndVacation implements Serializable{

	private static final long serialVersionUID = -8852175082903184330L;
	
	@Column(name="ADMIN_NO")
	private int adminNo;

	@Column(name="ADMIN_NAME")
	private String adminName;
	
	@Column(name="VACATION_NO")
	private int vacationNo;
	
	@Column(name="REQUEST_DATE")
	private java.sql.Date requestDate;
	
	@Column(name="REQUEST_ADMIN")
	private String requestAdmin;
	
	@Column(name="VACATION_NAME")
	private String vacationName;
	
	@Column(name="DRAFTER")
	private String drafter;
	
	@Column(name="VACATION_FIRST_CONFIRM_YN")
	private String vacationFirstConfirmYn;
	
	@Column(name="VACATION_SECOND_CONFIRM_YN")
	private String vacationSecondConfirmYn;
	
	@Column(name="VACATION_LAST_CONFIRM_YN")
	private String vacationLastConfirmYn;
	
	@Column(name="VACATION_START_DATE")
	private java.sql.Date vacationStartDate;
	
	@Column(name="VACATION_END_DATE")
	private java.sql.Date vacationEndDate;
	
	@Column(name="VACATION_REASON")
	private String vacationReason;
	
	@Column(name="VACATION_RETURN_YN")
	private String vacationReturnYn;

	public AdminAndVacation() {
		super();
	}

	public AdminAndVacation(int adminNo, String adminName, int vacationNo, Date requestDate, String requestAdmin,
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminAndVacation [adminNo=" + adminNo + ", adminName=" + adminName + ", vacationNo=" + vacationNo
				+ ", requestDate=" + requestDate + ", requestAdmin=" + requestAdmin + ", vacationName=" + vacationName
				+ ", drafter=" + drafter + ", vacationFirstConfirmYn=" + vacationFirstConfirmYn
				+ ", vacationSecondConfirmYn=" + vacationSecondConfirmYn + ", vacationLastConfirmYn="
				+ vacationLastConfirmYn + ", vacationStartDate=" + vacationStartDate + ", vacationEndDate="
				+ vacationEndDate + ", vacationReason=" + vacationReason + ", vacationReturnYn=" + vacationReturnYn
				+ "]";
	}

	

	
	
	

}
