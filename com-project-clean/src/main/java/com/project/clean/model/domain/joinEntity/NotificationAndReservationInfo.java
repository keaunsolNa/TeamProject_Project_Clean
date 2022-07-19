package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.project.clean.model.domain.reservation.Reservation;

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
public class NotificationAndReservationInfo implements Serializable{

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
	
	@Column(name="NOTIFIVATION_EMPLOYEE_NO")
	private int notificationEmployeeNo;
	
	@ManyToOne
	@JoinColumn(name="NOTIFIVATION_RESERVATION_NO")
	private Reservation reservationInfoDTO;

	@Column(name="NOTIFIVATION_ADMIN_NO")
	private int notificationAdminNo;

	public NotificationAndReservationInfo() {
		super();
	}

	public NotificationAndReservationInfo(int notificationNo, String notificationText, Date notificationCreateTime,
			String notificationReadYn, String notificationAdminYn, int notificationEmployeeNo,
			Reservation reservationInfoDTO, int notificationAdminNo) {
		super();
		this.notificationNo = notificationNo;
		this.notificationText = notificationText;
		this.notificationCreateTime = notificationCreateTime;
		this.notificationReadYn = notificationReadYn;
		this.notificationAdminYn = notificationAdminYn;
		this.notificationEmployeeNo = notificationEmployeeNo;
		this.reservationInfoDTO = reservationInfoDTO;
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

	public Reservation getReservationInfoDTO() {
		return reservationInfoDTO;
	}

	public void setReservationInfoDTO(Reservation reservationInfoDTO) {
		this.reservationInfoDTO = reservationInfoDTO;
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
		return "NotificationAndReservationInfo [notificationNo=" + notificationNo + ", notificationText="
				+ notificationText + ", notificationCreateTime=" + notificationCreateTime + ", notificationReadYn="
				+ notificationReadYn + ", notificationAdminYn=" + notificationAdminYn + ", notificationEmployeeNo="
				+ notificationEmployeeNo + ", reservationInfoDTO=" + reservationInfoDTO + ", notificationAdminNo="
				+ notificationAdminNo + "]";
	}

		
}
