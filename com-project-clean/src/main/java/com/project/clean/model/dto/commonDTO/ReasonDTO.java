package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class ReasonDTO implements Serializable{

   private static final long serialVersionUID = 3882546217567793334L;

   private int employeeNo;
   private int adminNo;
   private String Reason;
   private java.sql.Date employeeRegistDate;
   private AdminDTO adminDTO;
public ReasonDTO() {
	super();
}
public ReasonDTO(int employeeNo, int adminNo, String reason, Date employeeRegistDate, AdminDTO adminDTO) {
	super();
	this.employeeNo = employeeNo;
	this.adminNo = adminNo;
	Reason = reason;
	this.employeeRegistDate = employeeRegistDate;
	this.adminDTO = adminDTO;
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
public String getReason() {
	return Reason;
}
public void setReason(String reason) {
	Reason = reason;
}
public java.sql.Date getEmployeeRegistDate() {
	return employeeRegistDate;
}
public void setEmployeeRegistDate(java.sql.Date employeeRegistDate) {
	this.employeeRegistDate = employeeRegistDate;
}
public AdminDTO getAdminDTO() {
	return adminDTO;
}
public void setAdminDTO(AdminDTO adminDTO) {
	this.adminDTO = adminDTO;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
@Override
public String toString() {
	return "ReasonDTO [employeeNo=" + employeeNo + ", adminNo=" + adminNo + ", Reason=" + Reason
			+ ", employeeRegistDate=" + employeeRegistDate + ", adminDTO=" + adminDTO + "]";
}



   
   

}