//package com.project.clean.model.domain.joinEntity;
//
//import java.io.Serializable;
//import java.sql.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinColumns;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.project.clean.model.domain.commonEntity.ApplyEmployee;
//
//@Entity
//@Table(name="TBL_EMPLOYEE_PAY")
//public class EmployeePayAndApplyEmployee implements Serializable {
//
//	private static final long serialVersionUID = 8545621209731929833L;
//
//	@Id
//	@Column(name="PAY_HISTORY_EMPLOYEE_NO")
//	private int payHistoryEmployeeNo;
//	
//	@Column(name="PAY_EMPLOYEE_DATE")
//	private java.sql.Date payEmployeeDate;
//	
//	@Column(name="PAY_EMPLOYEE_FINAL_SALARY")
//	private int payEmployeeFinalSalary;
//	
//	@OneToOne
//	@JoinColumns({
//		@JoinColumn(name = "APPLY_EMPLOYEE_NO"),
//		@JoinColumn(name = "APPLY_RESERVATION_NO")
//	})
//	private ApplyEmployee applyEmployee;
//	
//	public EmployeePayAndApplyEmployee() {
//	}
//
//	public EmployeePayAndApplyEmployee(int payHistoryEmployeeNo, Date payEmployeeDate, int payEmployeeFinalSalary,
//			ApplyEmployee applyEmployee) {
//		super();
//		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
//		this.payEmployeeDate = payEmployeeDate;
//		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
//		this.applyEmployee = applyEmployee;
//	}
//
//	public int getPayHistoryEmployeeNo() {
//		return payHistoryEmployeeNo;
//	}
//
//	public void setPayHistoryEmployeeNo(int payHistoryEmployeeNo) {
//		this.payHistoryEmployeeNo = payHistoryEmployeeNo;
//	}
//
//	public java.sql.Date getPayEmployeeDate() {
//		return payEmployeeDate;
//	}
//
//	public void setPayEmployeeDate(java.sql.Date payEmployeeDate) {
//		this.payEmployeeDate = payEmployeeDate;
//	}
//
//	public int getPayEmployeeFinalSalary() {
//		return payEmployeeFinalSalary;
//	}
//
//	public void setPayEmployeeFinalSalary(int payEmployeeFinalSalary) {
//		this.payEmployeeFinalSalary = payEmployeeFinalSalary;
//	}
//
//	public ApplyEmployee getApplyEmployee() {
//		return applyEmployee;
//	}
//
//	public void setApplyEmployee(ApplyEmployee applyEmployee) {
//		this.applyEmployee = applyEmployee;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	@Override
//	public String toString() {
//		return "EmployeePayAndApplyEmployee [payHistoryEmployeeNo=" + payHistoryEmployeeNo + ", payEmployeeDate="
//				+ payEmployeeDate + ", payEmployeeFinalSalary=" + payEmployeeFinalSalary + ", applyEmployee="
//				+ applyEmployee + "]";
//	}
//
//	
//
//	
//
//	
//	
//}
