package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class NotificationDTO implements Serializable{

	private static final long serialVersionUID = -8719955820737927037L;
	private int notificationNo;
	private String notificationText;
	private java.sql.Date notificationCreateTime;
	private String notificationReadYn;
	private String notificationAdminYn;
	private int notificationEmployeeNo;
	private int notificationReservationNo;
	private int notificationAdminNo;
	
	public NotificationDTO() {
	}
	
	public NotificationDTO(int notificationNo, String notificationText, Date notificationCreateTime,
			String notificationReadYn, String notificationAdminYn, int notificationEmployeeNo,
			int notificationReservationNo, int notificationAdminNo) {
		this.notificationNo = notificationNo;
		this.notificationText = notificationText;
		this.notificationCreateTime = notificationCreateTime;
		this.notificationReadYn = notificationReadYn;
		this.notificationAdminYn = notificationAdminYn;
		this.notificationEmployeeNo = notificationEmployeeNo;
		this.notificationReservationNo = notificationReservationNo;
		this.notificationAdminNo = notificationAdminNo;
	}
	int getNotificationNo() {
		return notificationNo;
	}
	void setNotificationNo(int notificationNo) {
		this.notificationNo = notificationNo;
	}
	String getNotificationText() {
		return notificationText;
	}
	void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}
	java.sql.Date getNotificationCreateTime() {
		return notificationCreateTime;
	}
	void setNotificationCreateTime(java.sql.Date notificationCreateTime) {
		this.notificationCreateTime = notificationCreateTime;
	}
	String getNotificationReadYn() {
		return notificationReadYn;
	}
	void setNotificationReadYn(String notificationReadYn) {
		this.notificationReadYn = notificationReadYn;
	}
	String getNotificationAdminYn() {
		return notificationAdminYn;
	}
	void setNotificationAdminYn(String notificationAdminYn) {
		this.notificationAdminYn = notificationAdminYn;
	}
	int getNotificationEmployeeNo() {
		return notificationEmployeeNo;
	}
	void setNotificationEmployeeNo(int notificationEmployeeNo) {
		this.notificationEmployeeNo = notificationEmployeeNo;
	}
	int getNotificationReservationNo() {
		return notificationReservationNo;
	}
	void setNotificationReservationNo(int notificationReservationNo) {
		this.notificationReservationNo = notificationReservationNo;
	}
	int getNotificationAdminNo() {
		return notificationAdminNo;
	}
	void setNotificationAdminNo(int notificationAdminNo) {
		this.notificationAdminNo = notificationAdminNo;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "NotificationDTO [notificationNo=" + notificationNo + ", notificationText=" + notificationText
				+ ", notificationCreateTime=" + notificationCreateTime + ", notificationReadYn=" + notificationReadYn
				+ ", notificationAdminYn=" + notificationAdminYn + ", notificationEmployeeNo=" + notificationEmployeeNo
				+ ", notificationReservationNo=" + notificationReservationNo + ", notificationAdminNo="
				+ notificationAdminNo + "]";
	}
	
}

