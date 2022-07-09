package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;

public class ReservationInfoDTO implements Serializable{
	
	private static final long serialVersionUID = 5574907059587424790L;
	private int reservationNo;
	private String userName;
	private String userPhoneNo;
	private int UserPostalNo;
	private String userAddress;
	private String userDetailAddress;
	private int userHouseSize;
	private java.sql.Date userReservationDate;
	private String userRequirements;
	private java.sql.Date businessDate;
	private java.sql.Date businessStartTime;
	private java.sql.Date businessEndTime;
	private int businessFixedPeople;
	private int businessApplyPeople;
	private int totalPayment;
	private String applyEndYn;
	private String ReservationCancelYn;
	private String paymentYn;
	public ReservationInfoDTO() {
	}
	public ReservationInfoDTO(int reservationNo, String userName, String userPhoneNo, int userPostalNo,
			String userAddress, String userDetailAddress, int userHouseSize, Date userReservationDate,
			String userRequirements, Date businessDate, Date businessStartTime, Date businessEndTime,
			int businessFixedPeople, int businessApplyPeople, int totalPayment, String applyEndYn,
			String reservationCancelYn, String paymentYn) {
		this.reservationNo = reservationNo;
		this.userName = userName;
		this.userPhoneNo = userPhoneNo;
		UserPostalNo = userPostalNo;
		this.userAddress = userAddress;
		this.userDetailAddress = userDetailAddress;
		this.userHouseSize = userHouseSize;
		this.userReservationDate = userReservationDate;
		this.userRequirements = userRequirements;
		this.businessDate = businessDate;
		this.businessStartTime = businessStartTime;
		this.businessEndTime = businessEndTime;
		this.businessFixedPeople = businessFixedPeople;
		this.businessApplyPeople = businessApplyPeople;
		this.totalPayment = totalPayment;
		this.applyEndYn = applyEndYn;
		ReservationCancelYn = reservationCancelYn;
		this.paymentYn = paymentYn;
	}
	int getReservationNo() {
		return reservationNo;
	}
	void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	String getUserName() {
		return userName;
	}
	void setUserName(String userName) {
		this.userName = userName;
	}
	String getUserPhoneNo() {
		return userPhoneNo;
	}
	void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	int getUserPostalNo() {
		return UserPostalNo;
	}
	void setUserPostalNo(int userPostalNo) {
		UserPostalNo = userPostalNo;
	}
	String getUserAddress() {
		return userAddress;
	}
	void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	String getUserDetailAddress() {
		return userDetailAddress;
	}
	void setUserDetailAddress(String userDetailAddress) {
		this.userDetailAddress = userDetailAddress;
	}
	int getUserHouseSize() {
		return userHouseSize;
	}
	void setUserHouseSize(int userHouseSize) {
		this.userHouseSize = userHouseSize;
	}
	java.sql.Date getUserReservationDate() {
		return userReservationDate;
	}
	void setUserReservationDate(java.sql.Date userReservationDate) {
		this.userReservationDate = userReservationDate;
	}
	String getUserRequirements() {
		return userRequirements;
	}
	void setUserRequirements(String userRequirements) {
		this.userRequirements = userRequirements;
	}
	java.sql.Date getBusinessDate() {
		return businessDate;
	}
	void setBusinessDate(java.sql.Date businessDate) {
		this.businessDate = businessDate;
	}
	java.sql.Date getBusinessStartTime() {
		return businessStartTime;
	}
	void setBusinessStartTime(java.sql.Date businessStartTime) {
		this.businessStartTime = businessStartTime;
	}
	java.sql.Date getBusinessEndTime() {
		return businessEndTime;
	}
	void setBusinessEndTime(java.sql.Date businessEndTime) {
		this.businessEndTime = businessEndTime;
	}
	int getBusinessFixedPeople() {
		return businessFixedPeople;
	}
	void setBusinessFixedPeople(int businessFixedPeople) {
		this.businessFixedPeople = businessFixedPeople;
	}
	int getBusinessApplyPeople() {
		return businessApplyPeople;
	}
	void setBusinessApplyPeople(int businessApplyPeople) {
		this.businessApplyPeople = businessApplyPeople;
	}
	int getTotalPayment() {
		return totalPayment;
	}
	void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}
	String getApplyEndYn() {
		return applyEndYn;
	}
	void setApplyEndYn(String applyEndYn) {
		this.applyEndYn = applyEndYn;
	}
	String getReservationCancelYn() {
		return ReservationCancelYn;
	}
	void setReservationCancelYn(String reservationCancelYn) {
		ReservationCancelYn = reservationCancelYn;
	}
	String getPaymentYn() {
		return paymentYn;
	}
	void setPaymentYn(String paymentYn) {
		this.paymentYn = paymentYn;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ReservationInfoDTO [reservationNo=" + reservationNo + ", userName=" + userName + ", userPhoneNo="
				+ userPhoneNo + ", UserPostalNo=" + UserPostalNo + ", userAddress=" + userAddress
				+ ", userDetailAddress=" + userDetailAddress + ", userHouseSize=" + userHouseSize
				+ ", userReservationDate=" + userReservationDate + ", userRequirements=" + userRequirements
				+ ", businessDate=" + businessDate + ", businessStartTime=" + businessStartTime + ", businessEndTime="
				+ businessEndTime + ", businessFixedPeople=" + businessFixedPeople + ", businessApplyPeople="
				+ businessApplyPeople + ", totalPayment=" + totalPayment + ", applyEndYn=" + applyEndYn
				+ ", ReservationCancelYn=" + ReservationCancelYn + ", paymentYn=" + paymentYn + "]";
	}
	
}
