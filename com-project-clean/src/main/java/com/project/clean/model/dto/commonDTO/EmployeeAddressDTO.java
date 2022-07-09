package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class EmployeeAddressDTO implements Serializable{

	   private static final long serialVersionUID = 8851790007206166773L;
	   
	   private int employeeNo;
	   private int employeeAddressNo;
	   private String employeeAddress;
	   private String employeeDetailAddress;
	   public EmployeeAddressDTO() {
	      super();
	   }
	   public EmployeeAddressDTO(int employeeNo, int employeeAddressNo, String employeeAddress,
	         String employeeDetailAddress) {
	      super();
	      this.employeeNo = employeeNo;
	      this.employeeAddressNo = employeeAddressNo;
	      this.employeeAddress = employeeAddress;
	      this.employeeDetailAddress = employeeDetailAddress;
	   }
	   public int getEmployeeNo() {
	      return employeeNo;
	   }
	   public void setEmployeeNo(int employeeNo) {
	      this.employeeNo = employeeNo;
	   }
	   public int getEmployeeAddressNo() {
	      return employeeAddressNo;
	   }
	   public void setEmployeeAddressNo(int employeeAddressNo) {
	      this.employeeAddressNo = employeeAddressNo;
	   }
	   public String getEmployeeAddress() {
	      return employeeAddress;
	   }
	   public void setEmployeeAddress(String employeeAddress) {
	      this.employeeAddress = employeeAddress;
	   }
	   public String getEmployeeDetailAddress() {
	      return employeeDetailAddress;
	   }
	   public void setEmployeeDetailAddress(String employeeDetailAddress) {
	      this.employeeDetailAddress = employeeDetailAddress;
	   }
	   public static long getSerialversionuid() {
	      return serialVersionUID;
	   }
	   @Override
	   public String toString() {
	      return "EmployeeAddressDTO [employeeNo=" + employeeNo + ", employeeAddressNo=" + employeeAddressNo
	            + ", employeeAddress=" + employeeAddress + ", employeeDetailAddress=" + employeeDetailAddress + "]";
	   }
	   
	
	
	

}
