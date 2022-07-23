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
	private Integer notificationEmployeeNo;
	private int notificationReservationNo;
	private int notificationAdminNo;
	public NotificationDTO() {
		super();
	}
	NotificationDTO(int notificationNo, String notificationText, Date notificationCreateTime, String notificationReadYn,
			String notificationAdminYn, Integer notificationEmployeeNo, int notificationReservationNo,
			int notificationAdminNo) {
		super();
		this.notificationNo = notificationNo;
		this.notificationText = notificationText;
		this.notificationCreateTime = notificationCreateTime;
		this.notificationReadYn = notificationReadYn;
		this.notificationAdminYn = notificationAdminYn;
		this.notificationEmployeeNo = notificationEmployeeNo;
		this.notificationReservationNo = notificationReservationNo;
		this.notificationAdminNo = notificationAdminNo;
	}
	public int getNotificationNo() {
		return notificationNo;
	}
	public void setNotificationNo(int notificationNo) {
		this.notificationNo = notificationNo;
	}
	public String getNotificationText() {
		return notificationText;
	}
	public void setNotificationText(String notificationText) {
		this.notificationText = notificationText;
	}
	public java.sql.Date getNotificationCreateTime() {
		return notificationCreateTime;
	}
	public void setNotificationCreateTime(java.sql.Date notificationCreateTime) {
		this.notificationCreateTime = notificationCreateTime;
	}
	public String getNotificationReadYn() {
		return notificationReadYn;
	}
	public void setNotificationReadYn(String notificationReadYn) {
		this.notificationReadYn = notificationReadYn;
	}
	public String getNotificationAdminYn() {
		return notificationAdminYn;
	}
	public void setNotificationAdminYn(String notificationAdminYn) {
		this.notificationAdminYn = notificationAdminYn;
	}
	public Integer getNotificationEmployeeNo() {
		return notificationEmployeeNo;
	}
	public void setNotificationEmployeeNo(Integer notificationEmployeeNo) {
		this.notificationEmployeeNo = notificationEmployeeNo;
	}
	public int getNotificationReservationNo() {
		return notificationReservationNo;
	}
	public void setNotificationReservationNo(int notificationReservationNo) {
		this.notificationReservationNo = notificationReservationNo;
	}
	public int getNotificationAdminNo() {
		return notificationAdminNo;
	}
	public void setNotificationAdminNo(int notificationAdminNo) {
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

