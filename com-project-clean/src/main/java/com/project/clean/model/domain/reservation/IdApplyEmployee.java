package com.project.clean.model.domain.reservation;

import java.io.Serializable;

public class IdApplyEmployee implements Serializable {

	private static final long serialVersionUID = 3957038657020012026L;
	
	private int applyEmployeeNo;
	private int applyReservationNo;
	
	public IdApplyEmployee() {
	}
	
	public IdApplyEmployee(int applyEmployeeNo, int applyReservationNo) {
		super();
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
		return "IdApplyEmployee [applyEmployeeNo=" + applyEmployeeNo + ", applyReservationNo=" + applyReservationNo
				+ "]";
	}
	
	
	
	
}
