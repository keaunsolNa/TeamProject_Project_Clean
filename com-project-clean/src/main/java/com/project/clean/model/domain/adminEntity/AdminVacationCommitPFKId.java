package com.project.clean.model.domain.adminEntity;

import java.io.Serializable;

public class AdminVacationCommitPFKId implements Serializable{
	private static final long serialVersionUID = 3940241343311197126L;
	
	
	public int adminNo;
	public int vacationNo;
	
	
	
	
	public AdminVacationCommitPFKId() {
		super();
	}




	public AdminVacationCommitPFKId(int adminNo, int vacationNo) {
		super();
		this.adminNo = adminNo;
		this.vacationNo = vacationNo;
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




	@Override
	public String toString() {
		return "AdminVacationCommitPFKId [adminNo=" + adminNo + ", vacationNo=" + vacationNo + "]";
	}
	
	

}
