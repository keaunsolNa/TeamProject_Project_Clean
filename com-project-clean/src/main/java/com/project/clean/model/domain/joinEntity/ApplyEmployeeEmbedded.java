package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="TBL_APPLY_EMPLOYEE")
@DynamicUpdate
@DynamicInsert
public class ApplyEmployeeEmbedded implements Serializable{

	private static final long serialVersionUID = 3533485221607093796L;

	@EmbeddedId
	private ApplyEmployeeIdAndApplyReservationNo applyEmployeeIdAndApplyReservationNo;
	
	@Column(name = "APPLY_CANCEL_YN")
	private String applyCancelYn;
	
	@Column(name = "CHECK_EMPLOYEE_YN") 
	private String checkEmployeeYn;

	public ApplyEmployeeEmbedded() {
		super();
	}

	public ApplyEmployeeEmbedded(ApplyEmployeeIdAndApplyReservationNo applyEmployeeIdAndApplyReservationNo,
			String applyCancelYn, String checkEmployeeYn) {
		super();
		this.applyEmployeeIdAndApplyReservationNo = applyEmployeeIdAndApplyReservationNo;
		this.applyCancelYn = applyCancelYn;
		this.checkEmployeeYn = checkEmployeeYn;
	}

	public ApplyEmployeeIdAndApplyReservationNo getApplyEmployeeIdAndApplyReservationNo() {
		return applyEmployeeIdAndApplyReservationNo;
	}

	public void setApplyEmployeeIdAndApplyReservationNo(
			ApplyEmployeeIdAndApplyReservationNo applyEmployeeIdAndApplyReservationNo) {
		this.applyEmployeeIdAndApplyReservationNo = applyEmployeeIdAndApplyReservationNo;
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
		return "ApplyEmployeeEmbedded [applyEmployeeIdAndApplyReservationNo=" + applyEmployeeIdAndApplyReservationNo
				+ ", applyCancelYn=" + applyCancelYn + ", checkEmployeeYn=" + checkEmployeeYn + "]";
	}

}
