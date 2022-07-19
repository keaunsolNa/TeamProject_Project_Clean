package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.util.List;

import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;

public class ApplyEmployeeAndReservationInfoDTO implements Serializable{

	private static final long serialVersionUID = 3475731161767980832L;
	private int applyEmployeeNo;
	private ReservationInfoDTO applyReservationNo;
	private String applyCancelYn;
	private String checkEmployeeYn;
	ApplyEmployeeAndReservationInfoDTO() {
		super();
	}
	ApplyEmployeeAndReservationInfoDTO(int applyEmployeeNo, ReservationInfoDTO applyReservationNo, String applyCancelYn,
			String checkEmployeeYn) {
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
	public ReservationInfoDTO getApplyReservationNo() {
		return applyReservationNo;
	}
	public void setApplyReservationNo(ReservationInfoDTO applyReservationNo) {
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
		return "ApplyEmployeeAndReservationInfoDTO [applyEmployeeNo=" + applyEmployeeNo + ", applyReservationNo="
				+ applyReservationNo + ", applyCancelYn=" + applyCancelYn + ", checkEmployeeYn=" + checkEmployeeYn
				+ "]";
	}
				
}
