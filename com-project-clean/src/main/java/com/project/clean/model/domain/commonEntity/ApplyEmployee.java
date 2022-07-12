package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_APPLY_EMPLOYEE")
public class ApplyEmployee implements java.io.Serializable {

	private static final long serialVersionUID = -2123150406876662534L;
	
	@Id
	@Column(name="APPLY_EMPLOYEE_NO")
	private int applyEmployeeNo;
	
	@Column(name="APPLY_RESERVATION_NO")
	private int reservationNo;
	
	@Column(name="APPLY_CANSEL_YN")
	private String canselYn;
	
	@Column(name="CHECK_EMPLOYEE_YN")
	private String checkEmployeeYn;

	public ApplyEmployee() {
	}

	public ApplyEmployee(int applyEmployeeNo, int reservationNo, String canselYn, String checkEmployeeYn) {
		this.applyEmployeeNo = applyEmployeeNo;
		this.reservationNo = reservationNo;
		this.canselYn = canselYn;
		this.checkEmployeeYn = checkEmployeeYn;
	}

	public int getEmployeeNo() {
		return applyEmployeeNo;
	}

	public void setEmployeeNo(int applyEmployeeNo) {
		this.applyEmployeeNo = applyEmployeeNo;
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
		return "ApplyEmployee [applyEmployeeNo=" + applyEmployeeNo + ", reservationNo=" + reservationNo + ", canselYn=" + canselYn
				+ ", checkEmployeeYn=" + checkEmployeeYn + "]";
	}
	
	

}
