package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ApplyEmployeeDTO implements Serializable{

	private static final long serialVersionUID = 3475731161767980832L;
	private int applyEmployeeNo;
	private int applyReservationNo;
	private String applyCancelYn;
	private String checkEmployeeYn;
	public ApplyEmployeeDTO() {
		super();
	}
	public ApplyEmployeeDTO(int applyEmployeeNo, int applyReservationNo, String applyCancelYn, String checkEmployeeYn) {
		super();
		this.applyEmployeeNo = applyEmployeeNo;
		this.applyReservationNo = applyReservationNo;
		this.applyCancelYn = applyCancelYn;
		this.checkEmployeeYn = checkEmployeeYn;
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
	public String getApplyCancelYn() {
		return applyCancelYn;
	}
	public void setApplyCancelYn(String applyCancelYn) {
		this.applyCancelYn = applyCancelYn;
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
		return "ApplyEmployeeDTO [applyEmployeeNo=" + applyEmployeeNo + ", applyReservationNo=" + applyReservationNo
				+ ", applyCancelYn=" + applyCancelYn + ", checkEmployeeYn=" + checkEmployeeYn + "]";
	}
		
}
