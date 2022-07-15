package com.project.clean.model.domain.reservation;

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

@Entity(name="Reservation")
@Table(name="TBL_RESERVATION_INFO")
@SequenceGenerator(
		name="RESERVATION_SEQ_GENERATOR",
		sequenceName="SEQ_TBL_RESERVATION",
		initialValue = 1,
		allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
public class Reservation implements Serializable {

	private static final long serialVersionUID = 4615417887893850568L;
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "RESERVATION_SEQ_GENERATOR"
	)
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
	private java.util.Date businessDate;
	
	@Column(name="BUSINESS_START_TIME")
	private java.util.Date businessStartTime;
	
	@Column(name="BUSINESS_END_TIME")
	private java.util.Date businessEndTime;
	
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
	
	@Column(name="GASHOOD_CLEAN_YN")
	private String gashoodCleanYn;
	
	@Column(name="MOLD_CLEAN_YN")
	private String moldCleanYn;
	
	@Column(name="FILTER_CLEAN_YN")
	private String filterCleanYn;
	
	@Column(name="WAREHOUSE_CLEAN_YN")
	private String warehouseCleanYn;
	
	@Column(name="PET_YN")
	private String petYn;
	
	@Column(name="MULTIPLE_LAYER_YN")
	private String multipleLayerYn;

	public Reservation() {
		super();
	}

	public Reservation(int reservationNo, String userName, String userPhoneNo, int userPostalNo, String userAddress,
			String userDetailAddress, int userHouseSize, Date userReservationDate, String userRequirements,
			java.util.Date businessDate, java.util.Date businessStartTime, java.util.Date businessEndTime,
			int businessFixedPeople, int businessApplyPeople, int totalPayment, String applyEndYn,
			String reservationCancelYn, String paymentYn, String gashoodCleanYn, String moldCleanYn,
			String filterCleanYn, String warehouseCleanYn, String petYn, String multipleLayerYn) {
		super();
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
		return "Reservation [reservationNo=" + reservationNo + ", userName=" + userName + ", userPhoneNo=" + userPhoneNo
				+ ", UserPostalNo=" + UserPostalNo + ", userAddress=" + userAddress + ", userDetailAddress="
				+ userDetailAddress + ", userHouseSize=" + userHouseSize + ", userReservationDate="
				+ userReservationDate + ", userRequirements=" + userRequirements + ", businessDate=" + businessDate
				+ ", businessStartTime=" + businessStartTime + ", businessEndTime=" + businessEndTime
				+ ", businessFixedPeople=" + businessFixedPeople + ", businessApplyPeople=" + businessApplyPeople
				+ ", totalPayment=" + totalPayment + ", applyEndYn=" + applyEndYn + ", ReservationCancelYn="
				+ ReservationCancelYn + ", paymentYn=" + paymentYn + ", gashoodCleanYn=" + gashoodCleanYn
				+ ", moldCleanYn=" + moldCleanYn + ", filterCleanYn=" + filterCleanYn + ", warehouseCleanYn="
				+ warehouseCleanYn + ", petYn=" + petYn + ", multipleLayerYn=" + multipleLayerYn + "]";
	}
	
}
