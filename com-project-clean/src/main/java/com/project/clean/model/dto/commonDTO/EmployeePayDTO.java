package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class EmployeePayDTO implements Serializable{

	private static final long serialVersionUID = 5781554671268417687L;
	
	private int payHistoryEmployeeNo;
	private int payEmployeeNo;  
	private String payCalenderPicture;
	private java.sql.Date payEmployeeDate;
	private int payApplyReservationNo;
	public EmployeePayDTO() {
	}
	public EmployeePayDTO(int payHistoryEmployeeNo, int payEmployeeNo, String payCalenderPicture, Date payEmployeeDate,
			int payApplyReservationNo) {
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeNo = payEmployeeNo;
		this.payCalenderPicture = payCalenderPicture;
		this.payEmployeeDate = payEmployeeDate;
		this.payApplyReservationNo = payApplyReservationNo;
	}
	public int getPayHistoryEmployeeNo() {
		return payHistoryEmployeeNo;
	}
	public void setPayHistoryEmployeeNo(int payHistoryEmployeeNo) {
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
	}
	public int getPayEmployeeNo() {
		return payEmployeeNo;
	}
	public void setPayEmployeeNo(int payEmployeeNo) {
		this.payEmployeeNo = payEmployeeNo;
	}
	public String getPayCalenderPicture() {
		return payCalenderPicture;
	}
	public void setPayCalenderPicture(String payCalenderPicture) {
		this.payCalenderPicture = payCalenderPicture;
	}
	public java.sql.Date getPayEmployeeDate() {
		return payEmployeeDate;
	}
	public void setPayEmployeeDate(java.sql.Date payEmployeeDate) {
		this.payEmployeeDate = payEmployeeDate;
	}
	public int getPayApplyReservationNo() {
		return payApplyReservationNo;
	}
	public void setPayApplyReservationNo(int payApplyReservationNo) {
		this.payApplyReservationNo = payApplyReservationNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EmployeePayDTO [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeNo=" + payEmployeeNo
				+ ", payCalenderPicture=" + payCalenderPicture + ", payEmployeeDate=" + payEmployeeDate
				+ ", payApplyReservationNo=" + payApplyReservationNo + "]";
	}
	
	
}
