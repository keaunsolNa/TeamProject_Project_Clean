package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Date;

import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;

public class EmployeePayAndApplyEmployeeDTO implements Serializable{

	private static final long serialVersionUID = 2287870055904603747L;
	
	private int payHistoryEmployeeNo;
	private java.sql.Date payEmployeeDate; 
	private int payEmployeeFinalSalary;
	private ApplyEmployeeDTO applyEmployee;
	
	public EmployeePayAndApplyEmployeeDTO(int payHistoryEmployeeNo, Date payEmployeeDate, int payEmployeeFinalSalary,
			ApplyEmployeeDTO applyEmployee) {
		super();
		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
		this.payEmployeeDate = payEmployeeDate;
		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
		this.applyEmployee = applyEmployee;
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
	public ApplyEmployeeDTO getApplyEmployee() {
		return applyEmployee;
	}
	public void setApplyEmployee(ApplyEmployeeDTO applyEmployee) {
		this.applyEmployee = applyEmployee;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EmployeePayAndApplyEmployeeDTO [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeDate="
				+ payEmployeeDate + ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + ", applyEmployee="
				+ applyEmployee + "]";
	}
	
	
	
	
	
}
