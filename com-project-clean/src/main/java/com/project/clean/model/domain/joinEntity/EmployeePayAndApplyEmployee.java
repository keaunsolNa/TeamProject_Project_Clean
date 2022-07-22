package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.ReservationInfo;

@Entity
@Table(name = "TBL_EMPLOYEE_PAY")
public class EmployeePayAndApplyEmployee implements Serializable {

	private static final long serialVersionUID = 8545621209731929833L;
	
	@Id
	@Column(name="PAY_HISTORY_EMPLOYEE_NO")
	private int payHistoryEmployeeNo;
	
	@Column(name="PAY_EMPLOYEE_DATE")
	private java.sql.Date payEmployeeDate;
	
	@Column(name="PAY_EMPLOYEE_FINAL_SALARY")
	private int payEmployeeFinalSalary;
	
	@ManyToOne
	@JoinColumn(name = "PAY_APPLY_EMPLOYEE_NO", referencedColumnName="EMPLOYEE_NO")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name= "PAY_APPLY_RESERVATION_NO", referencedColumnName="RESERVATION_NO")
	private ReservationInfo reservationInfo;
	
	
	public EmployeePayAndApplyEmployee() {
		// TODO Auto-generated constructor stub
	}


	public EmployeePayAndApplyEmployee(int payHistoryEmployeeNo, Date payEmployeeDate, int payEmployeeFinalSalary,
			Employee employee, ReservationInfo reservationInfo) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
		this.employee = employee;
		this.reservationInfo = reservationInfo;
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


	public int getPayEmployeeFinalSalary() {
		return payEmployeeFinalSalary;
	}


	public void setPayEmployeeFinalSalary(int payEmployeeFinalSalary) {
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public ReservationInfo getReservationInfo() {
		return reservationInfo;
	}


	public void setReservationInfo(ReservationInfo reservationInfo) {
		this.reservationInfo = reservationInfo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "EmployeePayAndApplyEmployee [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeDate="
				+ payEmployeeDate + ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + ", employee=" + employee
				+ ", reservationInfo=" + reservationInfo + "]";
	}
	
	
	
	

	
	
	
	
}
