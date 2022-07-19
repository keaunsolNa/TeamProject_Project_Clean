package com.project.clean.model.domain.joinEntity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.project.clean.model.domain.commonEntity.ReservationInfo;
import com.project.clean.model.domain.reservation.IdApplyEmployee2;

@Entity
@Table(name="TBL_APPLY_EMPLOYEE")
@DynamicInsert
@DynamicUpdate
@IdClass(IdApplyEmployee2.class)
public class ApplyEmployeeAndReservationInfo implements Serializable{

	private static final long serialVersionUID = 7126120363900887130L;

	@Id
	@Column(name="APPLY_EMPLOYEE_NO")
	private int applyEmployeeNo;
	
	@Id
	@OneToOne
	@JoinColumn(name="APPLY_RESERVATION_NO")
	private ReservationInfo applyReservationNo;
	
	@Column(name="APPLY_CANCEL_YN")
	private String applyCancelYn;
	
	@Column(name="CHECK_EMPLOYEE_YN")
	private String checkEmployeeYn;

	ApplyEmployeeAndReservationInfo() {
		super();
	}

	ApplyEmployeeAndReservationInfo(int applyEmployeeNo, ReservationInfo applyReservationNo, String applyCancelYn,
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

	public ReservationInfo getApplyReservationNo() {
		return applyReservationNo;
	}

	public void setApplyReservationNo(ReservationInfo applyReservationNo) {
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
		return "ApplyEmployeeAndReservationInfo [applyEmployeeNo=" + applyEmployeeNo + ", applyReservationNo="
				+ applyReservationNo + ", applyCancelYn=" + applyCancelYn + ", checkEmployeeYn=" + checkEmployeeYn
				+ "]";
	}

							
}
