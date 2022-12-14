package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ApplyEmployeeIdAndApplyReservationNo implements Serializable{

	private static final long serialVersionUID = -9091848399449270299L;

	@Column(name = "APPLY_EMPLOYEE_NO")
	private int applyEmployeeNo;
	
	@Column(name = "APPLY_RESERVATION_NO")
	private int applyReservationNo;

	
	
	public ApplyEmployeeIdAndApplyReservationNo() {
	}



	public ApplyEmployeeIdAndApplyReservationNo(int applyEmployeeNo, int applyReservationNo) {
		this.applyEmployeeNo = applyEmployeeNo;
		this.applyReservationNo = applyReservationNo;
	}



	public int getApplyEmployeeNo() {
		return applyEmployeeNo;
	}



	public void setApplyEmployeeNo(int applyEmployeeNo) {
		this.applyEmployeeNo = applyEmployeeNo;
	}



	public int getApplyReservationNo() {
		return applyReservationNo;
	}



	public void setApplyReservationNo(int applyReservationNo) {
		this.applyReservationNo = applyReservationNo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	@Override
	public String toString() {
		return "ApplyEmployeeIdAndApplyReservationNo [applyEmployeeNo=" + applyEmployeeNo + ", applyReservationNo="
				+ applyReservationNo + "]";
	}



	
	
	
	
}
