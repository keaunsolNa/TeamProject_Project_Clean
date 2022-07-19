package com.project.clean.model.dto.joinDTO;

import java.sql.Date;
import java.util.List;

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.VacationCommitDTO;


public class AdminVacationCommitDTO {
	
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
	private int adminNo;

	private List<AdminDTO> adminList;
	
	private List<VacationCommitDTO> vacationCommitList;
	
	

	public AdminVacationCommitDTO() {
		super();
	}



	public AdminVacationCommitDTO(int vacationNo, Date requestDate, String requestAdmin, String vacationName,
			String drafter, String vacationFirstConfirmYn, String vacationSecondConfirmYn, String vacationLastConfirmYn,
			Date vacationStartDate, Date vacationEndDate, String vacationReason, String vacationReturnYn, int adminNo,
			List<AdminDTO> adminList, List<VacationCommitDTO> vacationCommitList) {
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
		this.adminNo = adminNo;
		this.adminList = adminList;
		this.vacationCommitList = vacationCommitList;
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



	public int getAdminNo() {
		return adminNo;
	}



	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}



	public List<AdminDTO> getAdminList() {
		return adminList;
	}



	public void setAdminList(List<AdminDTO> adminList) {
		this.adminList = adminList;
	}



	public List<VacationCommitDTO> getVacationCommitList() {
		return vacationCommitList;
	}



	public void setVacationCommitList(List<VacationCommitDTO> vacationCommitList) {
		this.vacationCommitList = vacationCommitList;
	}



	@Override
	public String toString() {
		return "AdminVacationCommitDTO [vacationNo=" + vacationNo + ", requestDate=" + requestDate + ", requestAdmin="
				+ requestAdmin + ", vacationName=" + vacationName + ", drafter=" + drafter + ", vacationFirstConfirmYn="
				+ vacationFirstConfirmYn + ", vacationSecondConfirmYn=" + vacationSecondConfirmYn
				+ ", vacationLastConfirmYn=" + vacationLastConfirmYn + ", vacationStartDate=" + vacationStartDate
				+ ", vacationEndDate=" + vacationEndDate + ", vacationReason=" + vacationReason + ", vacationReturnYn="
				+ vacationReturnYn + ", adminNo=" + adminNo + ", adminList=" + adminList + ", vacationCommitList="
				+ vacationCommitList + "]";
	}



	
	
	
}
