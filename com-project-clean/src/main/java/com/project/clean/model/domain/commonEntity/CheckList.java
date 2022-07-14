package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CHECKLIST")
public class CheckList {

	@Id
	@Column(name = "CHECK_RESERVATION_NO")
	private int checkReservationNo;
	
	@Column(name="CHECK_HTML")
	@Lob
	private String checkHTML;
	
	@Column(name="CHECK_STATUS")
	private String checkStatus;
	
	@Column(name="CHECK_ADMIN_NO")
	private int adminNo;

	public CheckList() {
	}

	public CheckList(int checkReservationNo, String checkHTML, String checkStatus, int adminNo) {
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

	@Override
	public String toString() {
		return "CheckList [checkReservationNo=" + checkReservationNo + ", checkHTML=" + checkHTML + ", checkStatus="
				+ checkStatus + ", adminNo=" + adminNo + "]";
	}
	
	
}
