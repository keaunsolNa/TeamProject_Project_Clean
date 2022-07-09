package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class ApplyEmployeeDTO implements Serializable{

	private static final long serialVersionUID = 3475731161767980832L;
	private int employeeNo;
	private int reservationNo;
	private String canselYn;
	private String checkEmployeeYn;
	public ApplyEmployeeDTO() {
	}
	public ApplyEmployeeDTO(int employeeNo, int reservationNo, String canselYn, String checkEmployeeYn) {
		this.employeeNo = employeeNo;
		this.reservationNo = reservationNo;
		this.canselYn = canselYn;
		this.checkEmployeeYn = checkEmployeeYn;
	}
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	public int getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	public String getCanselYn() {
		return canselYn;
	}
	public void setCanselYn(String canselYn) {
		this.canselYn = canselYn;
	}
	public String getCheckEmployeeYn() {
		return checkEmployeeYn;
	}
	public void setCheckEmployeeYn(String checkEmployeeYn) {
		this.checkEmployeeYn = checkEmployeeYn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ApplyEmployee [employeeNo=" + employeeNo + ", reservationNo=" + reservationNo + ", canselYn=" + canselYn
				+ ", checkEmployeeYn=" + checkEmployeeYn + "]";
	}
	
	
	
}
