package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class VacationDTO implements Serializable{
	private static final long serialVersionUID = -1541909810077995530L;
	
	private int vacationNo;
	private int categoryNo;
	private java.sql.Date requestDate;
	private String drafter;
	private String vacationFirstConfirmYn;
	private String vacationSecondConfirmYn;
	private java.sql.Date vacationStartDate;
	private java.sql.Date vacationEndDate;
	private String vacationReason;
	private String vacationReturnYn;
	private int adminNo;
	
	
	
	public VacationDTO() {
	}



	public VacationDTO(int vacationNo, int categoryNo, Date requestDate, String drafter, String vacationFirstConfirmYn,
			String vacationSecondConfirmYn, Date vacationStartDate, Date vacationEndDate, String vacationReason,
			String vacationReturnYn, int adminNo) {
		this.vacationNo = vacationNo;
		this.categoryNo = categoryNo;
		this.requestDate = requestDate;
		this.drafter = drafter;
		this.vacationFirstConfirmYn = vacationFirstConfirmYn;
		this.vacationSecondConfirmYn = vacationSecondConfirmYn;
		this.vacationStartDate = vacationStartDate;
		this.vacationEndDate = vacationEndDate;
		this.vacationReason = vacationReason;
		this.vacationReturnYn = vacationReturnYn;
		this.adminNo = adminNo;
	}



	public int getVacationNo() {
		return vacationNo;
	}



	public void setVacationNo(int vacationNo) {
		this.vacationNo = vacationNo;
	}



	public int getCategoryNo() {
		return categoryNo;
	}



	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}



	public java.sql.Date getRequestDate() {
		return requestDate;
	}



	public void setRequestDate(java.sql.Date requestDate) {
		this.requestDate = requestDate;
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
		return "VacationDTO [vacationNo=" + vacationNo + ", categoryNo=" + categoryNo + ", requestDate=" + requestDate
				+ ", drafter=" + drafter + ", vacationFirstConfirmYn=" + vacationFirstConfirmYn
				+ ", vacationSecondConfirmYn=" + vacationSecondConfirmYn + ", vacationStartDate=" + vacationStartDate
				+ ", vacationEndDate=" + vacationEndDate + ", vacationReason=" + vacationReason + ", vacationReturnYn="
				+ vacationReturnYn + ", adminNo=" + adminNo + "]";
	}


	
	

}
