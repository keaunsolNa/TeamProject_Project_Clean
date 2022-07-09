package com.project.clean.model.domain.commonEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_ADMIN_IP_ADDRESS")
public class AdminIpAddress implements java.io.Serializable{

	@Id
	@Column(name="ADMIN_IP_ADRESS_NO")
	private int ipAddressNo;
	
	@Column(name="ADMIN_IP_ADRESS_VALUE")
	private String ipAddressValue;

	public AdminIpAddress() {
	}

	public AdminIpAddress(int ipAddressNo, String ipAddressValue) {
		this.ipAddressNo = ipAddressNo;
		this.ipAddressValue = ipAddressValue;
	}

	public int getIpAddressNo() {
		return ipAddressNo;
	}

	public void setIpAddressNo(int ipAddressNo) {
		this.ipAddressNo = ipAddressNo;
	}

	public String getIpAddressValue() {
		return ipAddressValue;
	}

	public void setIpAddressValue(String ipAddressValue) {
		this.ipAddressValue = ipAddressValue;
	}

	@Override
	public String toString() {
		return "AdminIpAddress [ipAddressNo=" + ipAddressNo + ", ipAddressValue=" + ipAddressValue + "]";
	}
	
	
	
}
