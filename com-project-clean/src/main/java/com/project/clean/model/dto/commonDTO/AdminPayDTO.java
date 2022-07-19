package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;

public class AdminPayDTO implements Serializable{

	private static final long serialVersionUID = 823740744663538992L;

	private int payHistoryAdminNo;
	private int payAdminNo;
	private java.sql.Date payAdminDate;
	private int payAdminFinalSalary;
	
	public AdminPayDTO() {
	}

	public AdminPayDTO(int payHistoryAdminNo, int payAdminNo, Date payAdminDate, int payAdminFinalSalary) {
		super();
		this.payHistoryAdminNo = payHistoryAdminNo;
		this.payAdminNo = payAdminNo;
		this.payAdminDate = payAdminDate;
		this.payAdminFinalSalary = payAdminFinalSalary;
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

	public int getPayAdminFinalSalary() {
		return payAdminFinalSalary;
	}

	public void setPayAdminFinalSalary(int payAdminFinalSalary) {
		this.payAdminFinalSalary = payAdminFinalSalary;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminPayDTO [payHistoryAdminNo=" + payHistoryAdminNo + ", payAdminNo=" + payAdminNo + ", payAdminDate="
				+ payAdminDate + ", payAdminFinalSalary=" + payAdminFinalSalary + "]";
	}
	
	
	
	
}
