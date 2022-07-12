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
	private java.util.Date businessDate;
	private java.util.Date businessStartTime;
	private java.util.Date businessEndTime;
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
			String userRequirements, java.util.Date businessDate, java.util.Date businessStartTime,
			java.util.Date businessEndTime, int businessFixedPeople, int businessApplyPeople, int totalPayment,
			String applyEndYn, String reservationCancelYn, String paymentYn) {
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
	public int getReservationNo() {
		return reservationNo;
	}
	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhoneNo() {
		return userPhoneNo;
	}
	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}
	public int getUserPostalNo() {
		return UserPostalNo;
	}
	public void setUserPostalNo(int userPostalNo) {
		UserPostalNo = userPostalNo;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserDetailAddress() {
		return userDetailAddress;
	}
	public void setUserDetailAddress(String userDetailAddress) {
		this.userDetailAddress = userDetailAddress;
	}
	public int getUserHouseSize() {
		return userHouseSize;
	}
	public void setUserHouseSize(int userHouseSize) {
		this.userHouseSize = userHouseSize;
	}
	public java.sql.Date getUserReservationDate() {
		return userReservationDate;
	}
	public void setUserReservationDate(java.sql.Date userReservationDate) {
		this.userReservationDate = userReservationDate;
	}
	public String getUserRequirements() {
		return userRequirements;
	}
	public void setUserRequirements(String userRequirements) {
		this.userRequirements = userRequirements;
	}
	public java.util.Date getBusinessDate() {
		return businessDate;
	}
	public void setBusinessDate(java.util.Date businessDate) {
		this.businessDate = businessDate;
	}
	public java.util.Date getBusinessStartTime() {
		return businessStartTime;
	}
	public void setBusinessStartTime(java.util.Date businessStartTime) {
		this.businessStartTime = businessStartTime;
	}
	public java.util.Date getBusinessEndTime() {
		return businessEndTime;
	}
	public void setBusinessEndTime(java.util.Date businessEndTime) {
		this.businessEndTime = businessEndTime;
	}
	public int getBusinessFixedPeople() {
		return businessFixedPeople;
	}
	public void setBusinessFixedPeople(int businessFixedPeople) {
		this.businessFixedPeople = businessFixedPeople;
	}
	public int getBusinessApplyPeople() {
		return businessApplyPeople;
	}
	public void setBusinessApplyPeople(int businessApplyPeople) {
		this.businessApplyPeople = businessApplyPeople;
	}
	public int getTotalPayment() {
		return totalPayment;
	}
	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}
	public String getApplyEndYn() {
		return applyEndYn;
	}
	public void setApplyEndYn(String applyEndYn) {
		this.applyEndYn = applyEndYn;
	}
	public String getReservationCancelYn() {
		return ReservationCancelYn;
	}
	public void setReservationCancelYn(String reservationCancelYn) {
		ReservationCancelYn = reservationCancelYn;
	}
	public String getPaymentYn() {
		return paymentYn;
	}
	public void setPaymentYn(String paymentYn) {
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
