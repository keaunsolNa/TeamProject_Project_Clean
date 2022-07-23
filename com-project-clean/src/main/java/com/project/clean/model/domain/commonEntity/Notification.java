package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TBL_NOTIFICATION")
@SequenceGenerator(
		name="NOTIFICATION_SEQ_GENERATOR",
		sequenceName="SEQ_TBL_NOTIFICATION",
		initialValue = 1,
		allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
public class Notification implements Serializable{

	private static final long serialVersionUID = -5571635687854580599L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "NOTIFICATION_SEQ_GENERATOR"
	)
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
	
	@Column(name="NOTIFIVATION_EMPLOYEE_NO", nullable=true)
	private Integer notificationEmployeeNo;
	
	@Column(name="NOTIFIVATION_RESERVATION_NO")
	private int notificationReservationNo;

	@Column(name="NOTIFIVATION_ADMIN_NO")
	private int notificationAdminNo;

	Notification() {
		super();
	}

	Notification(int notificationNo, String notificationText, Date notificationCreateTime, String notificationReadYn,
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
		return "Notification [notificationNo=" + notificationNo + ", notificationText=" + notificationText
				+ ", notificationCreateTime=" + notificationCreateTime + ", notificationReadYn=" + notificationReadYn
				+ ", notificationAdminYn=" + notificationAdminYn + ", notificationEmployeeNo=" + notificationEmployeeNo
				+ ", notificationReservationNo=" + notificationReservationNo + ", notificationAdminNo="
				+ notificationAdminNo + "]";
	}

}
