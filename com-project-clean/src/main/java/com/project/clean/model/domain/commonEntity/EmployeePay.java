package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_EMPLOYEE_PAY")
public class EmployeePay implements Serializable {

	private static final long serialVersionUID = 8545621209731929833L;

	@Id
	@Column(name="PAY_HISTORY_EMPLOYEE_NO")
	private int payHistoryEmployeeNo;
	
	@Column(name="PAY_EMPLOYEE_NO")
	private int payEmployeeNo;  
	
	@Column(name="PAY_CALENDER_PICTURE")
	private String payCalenderPicture;

	@Column(name="PAY_EMPLOYEE_DATE")
	private java.sql.Date payEmployeeDate;
	
	@Column(name="PAY_APPLY_RESERVATION_NO")
	private int payApplyReservationNo;

	
	public EmployeePay() {
	}


	public EmployeePay(int payHistoryEmployeeNo, int payEmployeeNo, String payCalenderPicture, Date payEmployeeDate,
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
		return "EmployeePay [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeNo=" + payEmployeeNo
				+ ", payCalenderPicture=" + payCalenderPicture + ", payEmployeeDate=" + payEmployeeDate
				+ ", payApplyReservationNo=" + payApplyReservationNo + "]";
	}

	
	
	
}
