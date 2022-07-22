package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Date;

import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;

public class EmployeePayAndApplyEmployeeDTO implements Serializable{

	private static final long serialVersionUID = 2287870055904603747L;
	
	private int payHistoryEmployeeNo;
	private java.sql.Date payEmployeeDate; 
	private int payEmployeeFinalSalary;
	private int payApplyEmployeeNo;
	private int payApplyReservationNo;
	private EmployeeDTO employee;
	private ReservationInfoDTO reservationInfo;
	
	
	public EmployeePayAndApplyEmployeeDTO() {
	}


	public EmployeePayAndApplyEmployeeDTO(int payHistoryEmployeeNo, Date payEmployeeDate, int payEmployeeFinalSalary,
			int payApplyEmployeeNo, int payApplyReservationNo, EmployeeDTO employee,
			ReservationInfoDTO reservationInfo) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
		this.payApplyEmployeeNo = payApplyEmployeeNo;
		this.payApplyReservationNo = payApplyReservationNo;
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


	public EmployeeDTO getEmployee() {
		return employee;
	}


	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}


	public ReservationInfoDTO getReservationInfo() {
		return reservationInfo;
	}


	public void setReservationInfo(ReservationInfoDTO reservationInfo) {
		this.reservationInfo = reservationInfo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "EmployeePayAndApplyEmployeeDTO [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeDate="
				+ payEmployeeDate + ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + ", payApplyEmployeeNo="
				+ payApplyEmployeeNo + ", payApplyReservationNo=" + payApplyReservationNo + ", employee=" + employee
				+ ", reservationInfo=" + reservationInfo + "]";
	}


	
	
	

	
	
	
}
