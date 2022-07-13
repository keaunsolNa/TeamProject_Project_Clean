package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class VacationCommitDTO implements Serializable{
	private static final long serialVersionUID = 6113234511936178630L;
	
	private int confirmNo;
	private int adminNo;
	private int vacationNo;
	private String confirmReason;
	private java.sql.Date confirmDate;
	
	
	
	public VacationCommitDTO() {
	}



	public VacationCommitDTO(int confirmNo, int adminNo, int vacationNo, String confirmReason, Date confirmDate) {
		this.confirmNo = confirmNo;
		this.adminNo = adminNo;
		this.vacationNo = vacationNo;
		this.confirmReason = confirmReason;
		this.confirmDate = confirmDate;
	}



	public int getConfirmNo() {
		return confirmNo;
	}



	public void setConfirmNo(int confirmNo) {
		this.confirmNo = confirmNo;
	}



	public int getAdminNo() {
		return adminNo;
	}



	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}



	public int getVacationNo() {
		return vacationNo;
	}



	public void setVacationNo(int vacationNo) {
		this.vacationNo = vacationNo;
	}



	public String getConfirmReason() {
		return confirmReason;
	}



	public void setConfirmReason(String confirmReason) {
		this.confirmReason = confirmReason;
	}



	public java.sql.Date getConfirmDate() {
		return confirmDate;
	}



	public void setConfirmDate(java.sql.Date confirmDate) {
		this.confirmDate = confirmDate;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "VacationCommitDTO [confirmNo=" + confirmNo + ", adminNo=" + adminNo + ", vacationNo=" + vacationNo
				+ ", confirmReason=" + confirmReason + ", confirmDate=" + confirmDate + "]";
	}


	
}
