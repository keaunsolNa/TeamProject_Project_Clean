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
@SequenceGenerator(
		name = "ADMIN_PAY_SEQ_TBL_GENERATOR",
		sequenceName = "SEQ_TBL_ADMIN_PAY",
		initialValue = 1,
		allocationSize = 1
)
public class AdminPay implements java.io.Serializable{


	private static final long serialVersionUID = -887967325358696593L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "ADMIN_PAY_SEQ_TBL_GENERATOR"
	)
	@Column(name="PAY_HISTORY_ADMIN_NO")
	private int payHistoryAdminNo;

	@Column(name="PAY_ADMIN_NO")
	private int payAdminNo;
	
	@Column(name="PAY_ADMIN_DATE")
	private java.sql.Date payAdminDate;
	
	@Column(name="PAY_ADMIN_FINAL_SALARY")
	private int payAdminFinalSalary;
	
	public AdminPay() {
	}

	public AdminPay(int payHistoryAdminNo, int payAdminNo, Date payAdminDate, int payAdminFinalSalary) {
		super();
		this.payHistoryAdminNo = payHistoryAdminNo;
		this.payAdminNo = payAdminNo;
		this.payAdminDate = payAdminDate;
		this.payAdminFinalSalary = payAdminFinalSalary;
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

	public int getPayAdminFinalSalary() {
		return payAdminFinalSalary;
	}

	public void setPayAdminFinalSalary(int payAdminFinalSalary) {
		this.payAdminFinalSalary = payAdminFinalSalary;
	}

	@Override
	public String toString() {
		return "AdminPay [payHistoryAdminNo=" + payHistoryAdminNo + ", payAdminNo=" + payAdminNo + ", payAdminDate="
				+ payAdminDate + ", payAdminFinalSalary=" + payAdminFinalSalary + "]";
	}

	
	
	
}
