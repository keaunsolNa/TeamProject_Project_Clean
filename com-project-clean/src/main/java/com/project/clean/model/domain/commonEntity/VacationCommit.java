package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.project.clean.model.domain.adminEntity.AdminVacationCommitPFKId;


@Entity
@Table(name = "TBL_VACATION_COMMIT")
@IdClass(AdminVacationCommitPFKId.class)
public class VacationCommit implements Serializable {

	private static final long serialVersionUID = -8882971212931923117L;
	
	@Id
	@Column(name="ADMIN_NO")
	private int adminNo;

	@Id
	@Column(name="VACATION_NO")
	private int vacationNo;
	
	@Column(name="RETURN_REASON")
	private String confirmReason;
	
	@Column(name="CONFIRM_DATE")
	private java.sql.Date confirmDate;

	public VacationCommit() {
		super();

	}

	public VacationCommit(int adminNo, int vacationNo, String confirmReason, Date confirmDate) {
		this.adminNo = adminNo;
		this.vacationNo = vacationNo;
		this.confirmReason = confirmReason;
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
		return "VacationCommit [adminNo=" + adminNo + ", vacationNo=" + vacationNo + ", confirmReason=" + confirmReason
				+ ", confirmDate=" + confirmDate + "]";
	}

	
	
	
}
