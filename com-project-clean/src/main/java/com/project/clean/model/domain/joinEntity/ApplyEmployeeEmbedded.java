package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_APPLY_EMPLOYEE")
public class ApplyEmployeeEmbedded implements Serializable{

	private static final long serialVersionUID = 3533485221607093796L;

	@EmbeddedId
	private ApplyEmployeeIdAndApplyReservationNo applyEmployeeIdAndApplyReservationNo;
	
	@Column(name = "APPLY_CANSEL_YN")
	private String applyCanselYn;
	
	@Column(name = "CHECK_EMPLOYEE_YN") 
	private String checkEMployeeYn;

	public ApplyEmployeeEmbedded() {
	}

	public ApplyEmployeeEmbedded(ApplyEmployeeIdAndApplyReservationNo applyEmployeeIdAndApplyReservationNo,
			String applyCanselYn, String checkEMployeeYn) {
		this.applyEmployeeIdAndApplyReservationNo = applyEmployeeIdAndApplyReservationNo;
		this.applyCanselYn = applyCanselYn;
		this.checkEMployeeYn = checkEMployeeYn;
	}

	public ApplyEmployeeIdAndApplyReservationNo getApplyEmployeeIdAndApplyReservationNo() {
		return applyEmployeeIdAndApplyReservationNo;
	}

	public void setApplyEmployeeIdAndApplyReservationNo(
			ApplyEmployeeIdAndApplyReservationNo applyEmployeeIdAndApplyReservationNo) {
		this.applyEmployeeIdAndApplyReservationNo = applyEmployeeIdAndApplyReservationNo;
	}

	public String getApplyCanselYn() {
		return applyCanselYn;
	}

	public void setApplyCanselYn(String applyCanselYn) {
		this.applyCanselYn = applyCanselYn;
	}

	public String getCheckEMployeeYn() {
		return checkEMployeeYn;
	}

	public void setCheckEMployeeYn(String checkEMployeeYn) {
		this.checkEMployeeYn = checkEMployeeYn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ApplyEmployeeEmbedded [applyEmployeeIdAndApplyReservationNo=" + applyEmployeeIdAndApplyReservationNo
				+ ", applyCanselYn=" + applyCanselYn + ", checkEMployeeYn=" + checkEMployeeYn + "]";
	}
	
	
	
	
}
