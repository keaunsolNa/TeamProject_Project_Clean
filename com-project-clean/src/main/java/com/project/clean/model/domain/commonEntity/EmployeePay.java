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
	
	@Column(name="PAY_APPLY_RESERVATION_NO")
	private int payApplyReservationNo;

	@Column(name="PAY_APPLY_EMPLOYEE_NO")
	private int payApplyEmployeeNo;
	
	@Column(name="PAY_FINAL_SALARY")
	private int payEmployeeFinalSalary;
	
	public EmployeePay() {
	}

	public EmployeePay(int payHistoryEmployeeNo, Date payEmployeeDate, int payApplyReservationNo,
			int payApplyEmployeeNo, int payEmployeeFinalSalary) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.payApplyReservationNo = payApplyReservationNo;
		this.payApplyEmployeeNo = payApplyEmployeeNo;
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

	public int getPayApplyReservationNo() {
		return payApplyReservationNo;
	}

	public void setPayApplyReservationNo(int payApplyReservationNo) {
		this.payApplyReservationNo = payApplyReservationNo;
	}

	public int getPayApplyEmployeeNo() {
		return payApplyEmployeeNo;
	}

	public void setPayApplyEmployeeNo(int payApplyEmployeeNo) {
		this.payApplyEmployeeNo = payApplyEmployeeNo;
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
				+ ", payApplyReservationNo=" + payApplyReservationNo + ", payApplyEmployeeNo=" + payApplyEmployeeNo
				+ ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + "]";
	}

	
	
	
	
}
