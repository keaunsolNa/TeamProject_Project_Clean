package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ReservationInfoDTO implements Serializable{
	
	private static final long serialVersionUID = 5574907059587424790L;
	
	private int reservationNo;
	private String userName;
	private String userPhoneNo;
	private int userPostalNo;
	private String userAddress;
	private String userDetailAddress;
	private int userHouseSize;
	private java.sql.Date userReservationDate;
	private String userRequirements;
	private java.sql.Date businessDate;
	private java.sql.Timestamp businessStartTime;
	private java.sql.Timestamp businessEndTime;
	private int businessFixedPeople;
	private int businessApplyPeople;
	private int totalPayment;
	private String applyEndYn;
	private String reservationCancelYn;
	private String paymentYn;
	private String gashoodCleanYn;
	private String moldCleanYn;
	private String filterCleanYn;
	private String warehouseCleanYn;
	private String petYn;
	private String multipleLayerYn;
	public ReservationInfoDTO() {
		super();
	}
	public ReservationInfoDTO(int reservationNo, String userName, String userPhoneNo, int userPostalNo,
			String userAddress, String userDetailAddress, int userHouseSize, Date userReservationDate,
			String userRequirements, Date businessDate, Timestamp businessStartTime, Timestamp businessEndTime,
			int businessFixedPeople, int businessApplyPeople, int totalPayment, String applyEndYn,
			String reservationCancelYn, String paymentYn, String gashoodCleanYn, String moldCleanYn,
			String filterCleanYn, String warehouseCleanYn, String petYn, String multipleLayerYn) {
		super();
		this.reservationNo = reservationNo;
		this.userName = userName;
		this.userPhoneNo = userPhoneNo;
		this.userPostalNo = userPostalNo;
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
		this.reservationCancelYn = reservationCancelYn;
		this.paymentYn = paymentYn;
		this.gashoodCleanYn = gashoodCleanYn;
		this.moldCleanYn = moldCleanYn;
		this.filterCleanYn = filterCleanYn;
		this.warehouseCleanYn = warehouseCleanYn;
		this.petYn = petYn;
		this.multipleLayerYn = multipleLayerYn;
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
		return userPostalNo;
	}
	public void setUserPostalNo(int userPostalNo) {
		this.userPostalNo = userPostalNo;
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
	public java.sql.Timestamp getBusinessStartTime() {
		return businessStartTime;
	}
	public void setBusinessStartTime(java.sql.Timestamp businessStartTime) {
		this.businessStartTime = businessStartTime;
	}
	public java.sql.Timestamp getBusinessEndTime() {
		return businessEndTime;
	}
	public void setBusinessEndTime(java.sql.Timestamp businessEndTime) {
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
		return reservationCancelYn;
	}
	public void setReservationCancelYn(String reservationCancelYn) {
		this.reservationCancelYn = reservationCancelYn;
	}
	public String getPaymentYn() {
		return paymentYn;
	}
	public void setPaymentYn(String paymentYn) {
		this.paymentYn = paymentYn;
	}
	public String getGashoodCleanYn() {
		return gashoodCleanYn;
	}
	public void setGashoodCleanYn(String gashoodCleanYn) {
		this.gashoodCleanYn = gashoodCleanYn;
	}
	public String getMoldCleanYn() {
		return moldCleanYn;
	}
	public void setMoldCleanYn(String moldCleanYn) {
		this.moldCleanYn = moldCleanYn;
	}
	public String getFilterCleanYn() {
		return filterCleanYn;
	}
	public void setFilterCleanYn(String filterCleanYn) {
		this.filterCleanYn = filterCleanYn;
	}
	public String getWarehouseCleanYn() {
		return warehouseCleanYn;
	}
	public void setWarehouseCleanYn(String warehouseCleanYn) {
		this.warehouseCleanYn = warehouseCleanYn;
	}
	public String getPetYn() {
		return petYn;
	}
	public void setPetYn(String petYn) {
		this.petYn = petYn;
	}
	public String getMultipleLayerYn() {
		return multipleLayerYn;
	}
	public void setMultipleLayerYn(String multipleLayerYn) {
		this.multipleLayerYn = multipleLayerYn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ReservationInfoDTO [reservationNo=" + reservationNo + ", userName=" + userName + ", userPhoneNo="
				+ userPhoneNo + ", userPostalNo=" + userPostalNo + ", userAddress=" + userAddress
				+ ", userDetailAddress=" + userDetailAddress + ", userHouseSize=" + userHouseSize
				+ ", userReservationDate=" + userReservationDate + ", userRequirements=" + userRequirements
				+ ", businessDate=" + businessDate + ", businessStartTime=" + businessStartTime + ", businessEndTime="
				+ businessEndTime + ", businessFixedPeople=" + businessFixedPeople + ", businessApplyPeople="
				+ businessApplyPeople + ", totalPayment=" + totalPayment + ", applyEndYn=" + applyEndYn
				+ ", reservationCancelYn=" + reservationCancelYn + ", paymentYn=" + paymentYn + ", gashoodCleanYn="
				+ gashoodCleanYn + ", moldCleanYn=" + moldCleanYn + ", filterCleanYn=" + filterCleanYn
				+ ", warehouseCleanYn=" + warehouseCleanYn + ", petYn=" + petYn + ", multipleLayerYn=" + multipleLayerYn
				+ "]";
	}
	
	
}
