package com.project.clean.model.domain.commonEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "TBL_EMPLOYEE_REST_COMMIT")
@DynamicInsert
public class EmployeeRestCommit {
   
   @Id
   @Column(name = "CONFIRM_NO")
   private int confirmNo;

   @Column(name = "EMPLOYEE_NO")
   private int employeeNo;
   
   @Column(name = "ADMIN_NO")
   private int adminNo;
   
   @Column(name = "RETURN_REASON")
   private String returnReason;
   
   @Column(name = "EMPLOYEE_LAST_CONFIRM_DATE")
   private java.sql.Date employeeLastConfirmDate;

   public EmployeeRestCommit() {
      super();
   }

   public EmployeeRestCommit(int confirmNo, int employeeNo, int adminNo, String returnReason,
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

   @Override
   public String toString() {
      return "EmployeeRestCommit [confirmNo=" + confirmNo + ", employeeNo=" + employeeNo + ", adminNo=" + adminNo
            + ", returnReason=" + returnReason + ", employeeLastConfirmDate=" + employeeLastConfirmDate + "]";
   }
   
   
}