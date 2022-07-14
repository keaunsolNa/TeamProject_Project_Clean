package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name ="AdminAddress")
@Table(name="TBL_ADMIN_ADDRESS")
public class AdminAddress implements java.io.Serializable{

	private static final long serialVersionUID = 735511455709127142L;

	@Id
	@Column(name="ADMIN_NO")
	private int adminNo;

	@Column(name="ADMIN_ADDRESS_NO")
	private int adminAddressNo;
	
	@Column(name="ADMIN_ADDRESS")
	private String adminAddress;
	
	@Column(name="ADMIN_DETAIL_ADDRESS")
	private String adminDetailAddress;

	public AdminAddress() {
	}

	public AdminAddress(int adminNo, int adminAddressNo, String adminAddress, String adminDetailAddress) {
		this.adminNo = adminNo;
		this.adminAddressNo = adminAddressNo;
		this.adminAddress = adminAddress;
		this.adminDetailAddress = adminDetailAddress;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public int getAdminAddressNo() {
		return adminAddressNo;
	}

	public void setAdminAddressNo(int adminAddressNo) {
		this.adminAddressNo = adminAddressNo;
	}

	public String getAdminAddress() {
		return adminAddress;
	}

	public void setAdminAddress(String adminAddress) {
		this.adminAddress = adminAddress;
	}

	public String getAdminDetailAddress() {
		return adminDetailAddress;
	}

	public void setAdminDetailAddress(String adminDetailAddress) {
		this.adminDetailAddress = adminDetailAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminAddress [adminNo=" + adminNo + ", adminAddressNo=" + adminAddressNo + ", adminAddress="
				+ adminAddress + ", adminDetailAddress=" + adminDetailAddress + "]";
	}

	
	
	
	
}
