package com.project.clean.model.domain.commonEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TBL_ADMIN_PAY")

public class AdminPay implements java.io.Serializable{
	
	@Id
	@Column(name="PAY_HISTORY_ADMIN_NO")
	private int payHistoryAdminNo;

	@Column(name="PAY_ADMIN_NO")
	private int payAdminNo;
	
	@Column(name="PAY_ADMIN_DATE")
	private java.sql.Date payAdminDate;

	public AdminPay() {
	}

	public AdminPay(int payHistoryAdminNo, int payAdminNo, Date payAdminDate) {
		this.payHistoryAdminNo = payHistoryAdminNo;
		this.payAdminNo = payAdminNo;
		this.payAdminDate = payAdminDate;
	}

	public int getPayHistoryAdminNo() {
		return payHistoryAdminNo;
	}

	public void setPayHistoryAdminNo(int payHistoryAdminNo) {
		this.payHistoryAdminNo = payHistoryAdminNo;
	}

	public int getPayAdminNo() {
		return payAdminNo;
	}

	public void setPayAdminNo(int payAdminNo) {
		this.payAdminNo = payAdminNo;
	}

	public java.sql.Date getPayAdminDate() {
		return payAdminDate;
	}

	public void setPayAdminDate(java.sql.Date payAdminDate) {
		this.payAdminDate = payAdminDate;
	}

	@Override
	public String toString() {
		return "AdminPay [payHistoryAdminNo=" + payHistoryAdminNo + ", payAdminNo=" + payAdminNo + ", payAdminDate="
				+ payAdminDate + "]";
	}
	
	
}
