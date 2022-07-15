package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_CHECKLIST")
public class CheckList implements Serializable{

	private static final long serialVersionUID = -8801043489502988320L;

	@Id
	@Column(name = "CHECK_RESERVATION_NO")
	private int checkReservationNo;
	
	@Column(name="CHECK_HTML")
	@Lob
	private String checkHTML;
	
	@Column(name="CHECK_STATUS")
	private String checkStatus;
	
	@Column(name="CHECK_ADMIN_NO", nullable = true)
	private Integer adminNo;

	public CheckList() {
	}

	public CheckList(int checkReservationNo, String checkHTML, String checkStatus, Integer adminNo) {
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

	public Integer getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(Integer adminNo) {
		this.adminNo = adminNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CheckList [checkReservationNo=" + checkReservationNo + ", checkHTML=" + checkHTML + ", checkStatus="
				+ checkStatus + ", adminNo=" + adminNo + "]";
	}

	
	
	
}
