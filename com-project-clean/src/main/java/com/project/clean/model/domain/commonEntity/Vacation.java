package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TBL_VACATION")
@SequenceGenerator(
      name = "VACATION_SEQ_TBL_GENERATOR",
      sequenceName = "SEQ_TBL_VACATION",
      initialValue = 1,
      allocationSize = 1
)
@DynamicInsert 
public class Vacation implements Serializable{
	private static final long serialVersionUID = 530219979947287867L;
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "VACATION_SEQ_TBL_GENERATOR"
	)
	@Column(name="VACATION_NO")
	private int vacationNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="VACATION_START_DATE")
	private java.sql.Date vacationStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="VACATION_END_DATE")
	private java.sql.Date vacationEndDate;
	
	@Column(name="VACATION_REASON")
	private String vacationReason;
	
	@Column(name="VACATION_RETURN_YN")
	private String vacationReturnYn;
	
	@Column(name="ADMIN_NO")
	private int adminNo;
	
	@OneToMany
	@JoinColumn(name = "VACATION_NO")
	private List<VacationCommit> vacationCommitList;

	public Vacation() {
		super();
	}

	public Vacation(int vacationNo, Date requestDate, String requestAdmin, String vacationName, String drafter,
			String vacationFirstConfirmYn, String vacationSecondConfirmYn, String vacationLastConfirmYn,
			Date vacationStartDate, Date vacationEndDate, String vacationReason, String vacationReturnYn, int adminNo,
			List<VacationCommit> vacationCommitList) {
		super();
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

	public List<VacationCommit> getVacationCommitList() {
		return vacationCommitList;
	}

	public void setVacationCommitList(List<VacationCommit> vacationCommitList) {
		this.vacationCommitList = vacationCommitList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Vacation [vacationNo=" + vacationNo + ", requestDate=" + requestDate + ", requestAdmin=" + requestAdmin
				+ ", vacationName=" + vacationName + ", drafter=" + drafter + ", vacationFirstConfirmYn="
				+ vacationFirstConfirmYn + ", vacationSecondConfirmYn=" + vacationSecondConfirmYn
				+ ", vacationLastConfirmYn=" + vacationLastConfirmYn + ", vacationStartDate=" + vacationStartDate
				+ ", vacationEndDate=" + vacationEndDate + ", vacationReason=" + vacationReason + ", vacationReturnYn="
				+ vacationReturnYn + ", adminNo=" + adminNo + ", vacationCommitList=" + vacationCommitList + "]";
	}
	
	

}
