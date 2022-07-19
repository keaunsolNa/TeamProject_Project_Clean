package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;
import java.sql.Date;

import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;

public class NotificationAndResercationInfoDTO implements Serializable{

	private static final long serialVersionUID = -8719955820737927037L;
	private int notificationNo;
	private String notificationText;
	private java.sql.Date notificationCreateTime;
	private String notificationReadYn;
	private String notificationAdminYn;
	private int notificationEmployeeNo;
	private int notificationReservationNo;
	private int notificationAdminNo;
	private ReservationInfoDTO reservationInfoDTO;
	public NotificationAndResercationInfoDTO() {
		super();
	}
	public NotificationAndResercationInfoDTO(int notificationNo, String notificationText, Date notificationCreateTime,
			String notificationReadYn, String notificationAdminYn, int notificationEmployeeNo,
			int notificationReservationNo, int notificationAdminNo, ReservationInfoDTO reservationInfoDTO) {
		super();
		this.notificationNo = notificationNo;
		this.notificationText = notificationText;
		this.notificationCreateTime = notificationCreateTime;
		this.notificationReadYn = notificationReadYn;
		this.notificationAdminYn = notificationAdminYn;
		this.notificationEmployeeNo = notificationEmployeeNo;
		this.notificationReservationNo = notificationReservationNo;
		this.notificationAdminNo = notificationAdminNo;
		this.reservationInfoDTO = reservationInfoDTO;
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
	public ReservationInfoDTO getReservationInfoDTO() {
		return reservationInfoDTO;
	}
	public void setReservationInfoDTO(ReservationInfoDTO reservationInfoDTO) {
		this.reservationInfoDTO = reservationInfoDTO;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "NotificationAndResercationInfoDTO [notificationNo=" + notificationNo + ", notificationText="
				+ notificationText + ", notificationCreateTime=" + notificationCreateTime + ", notificationReadYn="
				+ notificationReadYn + ", notificationAdminYn=" + notificationAdminYn + ", notificationEmployeeNo="
				+ notificationEmployeeNo + ", notificationReservationNo=" + notificationReservationNo
				+ ", notificationAdminNo=" + notificationAdminNo + ", reservationInfoDTO=" + reservationInfoDTO + "]";
	}
	
		
}

