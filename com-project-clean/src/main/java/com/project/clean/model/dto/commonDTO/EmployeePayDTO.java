package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class EmployeePayDTO implements Serializable{

	private static final long serialVersionUID = 5781554671268417687L;
	
	private int payHistoryEmployeeNo;
	private java.sql.Date payEmployeeDate;
	private int applyEmployeeNo;  
	private int applyReservationNo;
	private int payEmployeeFinalSalary;
	
	public EmployeePayDTO() {
	}

	public EmployeePayDTO(int payHistoryEmployeeNo, Date payEmployeeDate, int applyEmployeeNo, int applyReservationNo,
			int payEmployeeFinalSalary) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.applyEmployeeNo = applyEmployeeNo;
		this.applyReservationNo = applyReservationNo;
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

	public int getApplyEmployeeNo() {
		return applyEmployeeNo;
	}

	public void setApplyEmployeeNo(int applyEmployeeNo) {
		this.applyEmployeeNo = applyEmployeeNo;
	}

	public int getApplyReservationNo() {
		return applyReservationNo;
	}

	public void setApplyReservationNo(int applyReservationNo) {
		this.applyReservationNo = applyReservationNo;
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
				+ ", applyEmployeeNo=" + applyEmployeeNo + ", applyReservationNo=" + applyReservationNo
				+ ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + "]";
	}

	
	
	
}
