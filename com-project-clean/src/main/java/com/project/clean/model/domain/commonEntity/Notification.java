package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_NOTIFICATION")
public class Notification implements Serializable{

	private static final long serialVersionUID = -5571635687854580599L;

	@Id
	@Column(name="NOTIFICATION_NO")
	private int notificationNo;

	@Column(name="NOTIFIVATION_TEXT")
	private String notificationText;
	
	@Column(name="NOTIFIVATION_CREATE_TIME")
	private java.sql.Date notificationCreateTime;
	
	@Column(name="NOTIFIVATION_READ_YN")
	private String notificationReadYn;
	
	@Column(name="NOTIFIVATION_ADMIN_YN")
	private String notificationAdminYn;
	
	@Column(name="NOTIFIVATION_EMPLOYEE_NO")
	private int notificationEmployeeNo;
	
	@Column(name="NOTIFIVATION_RESERVATION_NO")
	private int notificationReservationNo;

	@Column(name="NOTIFIVATION_ADMIN_NO")
	private int notificationAdminNo;

	public Notification() {
	}

	public Notification(int notificationNo, String notificationText, Date notificationCreateTime,
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

	public int getNotificationEmployeeNo() {
		return notificationEmployeeNo;
	}

	public void setNotificationEmployeeNo(int notificationEmployeeNo) {
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
		return "Notification [notificationNo=" + notificationNo + ", notificationText=" + notificationText
				+ ", notificationCreateTime=" + notificationCreateTime + ", notificationReadYn=" + notificationReadYn
				+ ", notificationAdminYn=" + notificationAdminYn + ", notificationEmployeeNo=" + notificationEmployeeNo
				+ ", notificationReservationNo=" + notificationReservationNo + ", notificationAdminNo="
				+ notificationAdminNo + "]";
	}
	
	
	
}
