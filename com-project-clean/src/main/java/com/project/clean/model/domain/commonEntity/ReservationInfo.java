package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_RESERVATION_INFO")
public class ReservationInfo implements Serializable {

	@Id
	@Column(name="RESERVATION_NO")
	private int reservationNo;

	@Column(name="USER_NAME")
	private String userName;
	
	@Column(name="USER_PHONE_NO")
	private String userPhoneNo;
	
	@Column(name="USER_POSTAL_NO")
	private int UserPostalNo;
	
	@Column(name="USER_ADDRESS")
	private String userAddress;
	
	@Column(name="USER_DETAIL_ADDRESS")
	private String userDetailAddress;
	
	@Column(name="USER_HOUSE_SIZE")
	private int userHouseSize;
	
	@Column(name="USER_RESERVATION_DATE")
	private java.sql.Date userReservationDate;
	
	@Column(name="USER_REQUIREMENTS")
	private String userRequirements;

	@Column(name="BUSINESS_DATE")
	private java.sql.Date businessDate;
	
	@Column(name="BUSINESS_START_TIME")
	private java.sql.Date businessStartTime;
	
	@Column(name="BUSINESS_END_TIME")
	private java.sql.Date businessEndTime;
	
	@Column(name="BUSINESS_FIXED_PEOPLE")
	private int businessFixedPeople;
	
	@Column(name="BUSINESS_APPLY_PEOPLE")
	private int businessApplyPeople;
	
	@Column(name="TOTAL_PAYMENT")
	private int totalPayment;
	
	@Column(name="APPLY_END_YN")
	private String applyEndYn;
	
	@Column(name="RESERVATION_CANCEL_YN")
	private String ReservationCancelYn;
	
	@Column(name="PAYMENT_YN")
	private String paymentYn;

	public ReservationInfo() {
	}

	public ReservationInfo(int reservationNo, String userName, String userPhoneNo, int userPostalNo, String userAddress,
			String userDetailAddress, int userHouseSize, Date userReservationDate, String userRequirements,
			Date businessDate, Date businessStartTime, Date businessEndTime, int businessFixedPeople,
			int businessApplyPeople, int totalPayment, String applyEndYn, String reservationCancelYn,
			String paymentYn) {
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

	public java.sql.Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(java.sql.Date businessDate) {
		this.businessDate = businessDate;
	}

	public java.sql.Date getBusinessStartTime() {
		return businessStartTime;
	}

	public void setBusinessStartTime(java.sql.Date businessStartTime) {
		this.businessStartTime = businessStartTime;
	}

	public java.sql.Date getBusinessEndTime() {
		return businessEndTime;
	}

	public void setBusinessEndTime(java.sql.Date businessEndTime) {
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

	@Override
	public String toString() {
		return "ReservationInfo [reservationNo=" + reservationNo + ", userName=" + userName + ", userPhoneNo="
				+ userPhoneNo + ", UserPostalNo=" + UserPostalNo + ", userAddress=" + userAddress
				+ ", userDetailAddress=" + userDetailAddress + ", userHouseSize=" + userHouseSize
				+ ", userReservationDate=" + userReservationDate + ", userRequirements=" + userRequirements
				+ ", businessDate=" + businessDate + ", businessStartTime=" + businessStartTime + ", businessEndTime="
				+ businessEndTime + ", businessFixedPeople=" + businessFixedPeople + ", businessApplyPeople="
				+ businessApplyPeople + ", totalPayment=" + totalPayment + ", applyEndYn=" + applyEndYn
				+ ", ReservationCancelYn=" + ReservationCancelYn + ", paymentYn=" + paymentYn + "]";
	}
	
	
	
}
