package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;

public class EmployeePayDTO implements Serializable{

	private static final long serialVersionUID = 5781554671268417687L;
	
	private int payHistoryEmployeeNo;
	private java.sql.Date payEmployeeDate;
	private int payApplyEmployeeNo;  
	private int payApplyReservationNo;
	private int payEmployeeFinalSalary;
	
	public EmployeePayDTO() {
	}

	public EmployeePayDTO(int payHistoryEmployeeNo, Date payEmployeeDate, int payApplyEmployeeNo,
			int payApplyReservationNo, int payEmployeeFinalSalary) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.payApplyEmployeeNo = payApplyEmployeeNo;
		this.payApplyReservationNo = payApplyReservationNo;
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
	}

	public int getPayHistoryEmployeeNo() {
		return payHistoryEmployeeNo;
	}

	public void setPayHistoryEmployeeNo(int payHistoryEmployeeNo) {
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
	}

	public java.sql.Date getPayEmployeeDate() {
		return payEmployeeDate;
	}

	public void setPayEmployeeDate(java.sql.Date payEmployeeDate) {
		this.payEmployeeDate = payEmployeeDate;
	}

	public int getPayApplyEmployeeNo() {
		return payApplyEmployeeNo;
	}

	public void setPayApplyEmployeeNo(int payApplyEmployeeNo) {
		this.payApplyEmployeeNo = payApplyEmployeeNo;
	}

	public int getPayApplyReservationNo() {
		return payApplyReservationNo;
	}

	public void setPayApplyReservationNo(int payApplyReservationNo) {
		this.payApplyReservationNo = payApplyReservationNo;
	}

	public int getPayEmployeeFinalSalary() {
		return payEmployeeFinalSalary;
	}

	public void setPayEmployeeFinalSalary(int payEmployeeFinalSalary) {
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "EmployeePayDTO [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeDate=" + payEmployeeDate
				+ ", payApplyEmployeeNo=" + payApplyEmployeeNo + ", payApplyReservationNo=" + payApplyReservationNo
				+ ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + "]";
	}

	
	
	
	
	
}
