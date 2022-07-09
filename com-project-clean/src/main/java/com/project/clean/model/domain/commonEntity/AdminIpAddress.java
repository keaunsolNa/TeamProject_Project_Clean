package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_ADMIN_IP_ADRESS")
public class AdminIpAddress implements java.io.Serializable{

	private static final long serialVersionUID = 4101364744100557303L;

	@Id
	@Column(name="ADMIN_NO")
	private int adminNo;
	
	@Column(name="ADMIN_IP_ADRESS_VALUE")
	private String ipAddressValue;

	public AdminIpAddress(int adminNo, String ipAddressValue) {
		this.adminNo = adminNo;
		this.ipAddressValue = ipAddressValue;
	}

	public AdminIpAddress() {
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getIpAddressValue() {
		return ipAddressValue;
	}

	public void setIpAddressValue(String ipAddressValue) {
		this.ipAddressValue = ipAddressValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AdminIpAddress [adminNo=" + adminNo + ", ipAddressValue=" + ipAddressValue + "]";
	}

	
	
	
}
