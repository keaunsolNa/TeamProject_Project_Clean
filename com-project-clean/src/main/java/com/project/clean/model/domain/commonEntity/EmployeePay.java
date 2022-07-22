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
	
	@Column(name="PAY_EMPLOYEE_DATE")
	private java.sql.Date payEmployeeDate;
	
	@Column(name="APPLY_RESERVATION_NO")
	private int applyReservationNo;

	@Column(name="APPLY_EMPLOYEE_NO")
	private int applyEmployeeNo;
	
	@Column(name="PAY_FINAL_SALARY")
	private int payEmployeeFinalSalary;
	
	public EmployeePay() {
	}

	public EmployeePay(int payHistoryEmployeeNo, Date payEmployeeDate, int applyReservationNo, int applyEmployeeNo,
			int payEmployeeFinalSalary) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.applyReservationNo = applyReservationNo;
		this.applyEmployeeNo = applyEmployeeNo;
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

	public int getApplyReservationNo() {
		return applyReservationNo;
	}

	public void setApplyReservationNo(int applyReservationNo) {
		this.applyReservationNo = applyReservationNo;
	}

	public int getApplyEmployeeNo() {
		return applyEmployeeNo;
	}

	public void setApplyEmployeeNo(int applyEmployeeNo) {
		this.applyEmployeeNo = applyEmployeeNo;
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
		return "EmployeePay [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeDate=" + payEmployeeDate
				+ ", applyReservationNo=" + applyReservationNo + ", applyEmployeeNo=" + applyEmployeeNo
				+ ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + "]";
	}

	
	
	
	
}
