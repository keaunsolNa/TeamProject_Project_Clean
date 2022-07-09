package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class VacationCommitDTO implements Serializable{
	private static final long serialVersionUID = 6113234511936178630L;
	
	private int adminNo;
	private int vacationNo;
	private int categoryNo;
	private String returnReason;
	private java.sql.Date confirmDate;
	
	
	
	public VacationCommitDTO() {
	}



	public VacationCommitDTO(int adminNo, int vacationNo, int categoryNo, String returnReason, Date confirmDate) {
		this.adminNo = adminNo;
		this.vacationNo = vacationNo;
		this.categoryNo = categoryNo;
		this.returnReason = returnReason;
		this.confirmDate = confirmDate;
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



	public int getCategoryNo() {
		return categoryNo;
	}



	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}



	public String getReturnReason() {
		return returnReason;
	}



	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}



	public java.sql.Date getConfirmDate() {
		return confirmDate;
	}



	public void setConfirmDate(java.sql.Date confirmDate) {
		this.confirmDate = confirmDate;
	}



	@Override
	public String toString() {
		return "vacationCommit [adminNo=" + adminNo + ", vacationNo=" + vacationNo + ", categoryNo=" + categoryNo
				+ ", returnReason=" + returnReason + ", confirmDate=" + confirmDate + "]";
	}
	
	
	
}
