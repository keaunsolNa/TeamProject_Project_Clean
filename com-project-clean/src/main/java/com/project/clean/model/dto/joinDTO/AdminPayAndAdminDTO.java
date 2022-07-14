package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.project.clean.model.dto.commonDTO.AdminDTO;

public class AdminPayAndAdminDTO implements Serializable{

	private static final long serialVersionUID = 823740744663538992L;

	private int payHistoryAdminNo;
	private int payAdminNo;
	private java.sql.Date payAdminDate;
	private AdminDTO admin;
	
	public AdminPayAndAdminDTO() {
	}

	public AdminPayAndAdminDTO(int payHistoryAdminNo, int payAdminNo, Date payAdminDate, AdminDTO admin) {
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

	public AdminDTO getAdmin() {
		return admin;
	}

	public void setAdmin(AdminDTO admin) {
		this.admin = admin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminPayAndAdminDTO [payHistoryAdminNo=" + payHistoryAdminNo + ", payAdminNo=" + payAdminNo
				+ ", payAdminDate=" + payAdminDate + ", admin=" + admin + "]";
	}

	
	
	
}
