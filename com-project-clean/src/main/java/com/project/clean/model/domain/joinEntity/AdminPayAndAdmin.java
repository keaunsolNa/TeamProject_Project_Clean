package com.project.clean.model.domain.joinEntity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.clean.model.domain.commonEntity.Admin;

@Entity
@Table(name="TBL_ADMIN_PAY")
public class AdminPayAndAdmin implements java.io.Serializable{

	private static final long serialVersionUID = 6199703563922919312L;

	@Id
	@Column(name="PAY_HISTORY_ADMIN_NO")
	private int payHistoryAdminNo;

	@Column(name="PAY_ADMIN_NO")
	private int payAdminNo;
	
	@Column(name="PAY_ADMIN_DATE")
	private java.sql.Date payAdminDate;
	
	@ManyToOne
	@JoinColumn(name = "PAY_ADMIN_NO", insertable = false, updatable = false)
	private Admin admin;
	
	public AdminPayAndAdmin() {
	}

	public AdminPayAndAdmin(int payHistoryAdminNo, int payAdminNo, Date payAdminDate, Admin admin) {
		super();
		this.payHistoryAdminNo = payHistoryAdminNo;
		this.payAdminNo = payAdminNo;
		this.payAdminDate = payAdminDate;
		this.admin = admin;
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

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "AdminPayAndAdmin [payHistoryAdminNo=" + payHistoryAdminNo + ", payAdminNo=" + payAdminNo
				+ ", payAdminDate=" + payAdminDate + ", admin=" + admin + "]";
	}
	
	
	
	
}
