package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class AdminPayDTO implements Serializable{

	private static final long serialVersionUID = 823740744663538992L;

	private int payHistoryAdminNo;
	private int payAdminNo;
	private java.sql.Date payAdminDate;
	
	public AdminPayDTO() {
	}
	
	public AdminPayDTO(int payHistoryAdminNo, int payAdminNo, Date payAdminDate) {
		this.payHistoryAdminNo = payHistoryAdminNo;
		this.payAdminNo = payAdminNo;
		this.payAdminDate = payAdminDate;
	}
	public int getPayHistoryAdminNo() {
		return payHistoryAdminNo;
	}
	public void setPayHistoryAdminNo(int payHistoryAdminNo) {
		this.payHistoryAdminNo = payHistoryAdminNo;
	}
	public int getPayAdminNo() {
		return payAdminNo;
	}
	public void setPayAdminNo(int payAdminNo) {
		this.payAdminNo = payAdminNo;
	}
	public java.sql.Date getPayAdminDate() {
		return payAdminDate;
	}
	public void setPayAdminDate(java.sql.Date payAdminDate) {
		this.payAdminDate = payAdminDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AdminPayDTO [payHistoryAdminNo=" + payHistoryAdminNo + ", payAdminNo=" + payAdminNo + ", payAdminDate="
				+ payAdminDate + "]";
	}
	
	
}
