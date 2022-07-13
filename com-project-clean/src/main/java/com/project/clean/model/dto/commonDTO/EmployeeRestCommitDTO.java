package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class EmployeeRestCommitDTO implements Serializable{

   private static final long serialVersionUID = 3882546217567793334L;

   private int confirmNo;
   private int employeeNo;
   private int adminNo;
   private String returnReason;
   private java.sql.Date employeeLastConfirmDate;
   public EmployeeRestCommitDTO() {
      super();
   }
   public EmployeeRestCommitDTO(int confirmNo, int employeeNo, int adminNo, String returnReason,
         Date employeeLastConfirmDate) {
      super();
      this.confirmNo = confirmNo;
      this.employeeNo = employeeNo;
      this.adminNo = adminNo;
      this.returnReason = returnReason;
      this.employeeLastConfirmDate = employeeLastConfirmDate;
   }
   public int getConfirmNo() {
      return confirmNo;
   }
   public void setConfirmNo(int confirmNo) {
      this.confirmNo = confirmNo;
   }
   public int getEmployeeNo() {
      return employeeNo;
   }
   public void setEmployeeNo(int employeeNo) {
      this.employeeNo = employeeNo;
   }
   public int getAdminNo() {
      return adminNo;
   }
   public void setAdminNo(int adminNo) {
      this.adminNo = adminNo;
   }
   public String getReturnReason() {
      return returnReason;
   }
   public void setReturnReason(String returnReason) {
      this.returnReason = returnReason;
   }
   public java.sql.Date getEmployeeLastConfirmDate() {
      return employeeLastConfirmDate;
   }
   public void setEmployeeLastConfirmDate(java.sql.Date employeeLastConfirmDate) {
      this.employeeLastConfirmDate = employeeLastConfirmDate;
   }
   public static long getSerialversionuid() {
      return serialVersionUID;
   }
   @Override
   public String toString() {
      return "EmployeeRestCommitDTO [confirmNo=" + confirmNo + ", employeeNo=" + employeeNo + ", adminNo=" + adminNo
            + ", returnReason=" + returnReason + ", employeeLastConfirmDate=" + employeeLastConfirmDate + "]";
   }

   
   

}