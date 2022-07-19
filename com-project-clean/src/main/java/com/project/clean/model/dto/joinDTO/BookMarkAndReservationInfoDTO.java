package com.project.clean.model.dto.joinDTO;

import java.io.Serializable;

import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;

public class BookMarkAndReservationInfoDTO implements Serializable{

	private static final long serialVersionUID = 3576945236851160745L;

	private int bookmarkNo;
	private String bookmarkCancelYn;
	private int bookmarkEmployeeNo;
	private int bookmarkReservationNo;
	private ReservationInfoDTO reservationInfoDTO;
	public BookMarkAndReservationInfoDTO() {
		super();
	}
	public BookMarkAndReservationInfoDTO(int bookmarkNo, String bookmarkCancelYn, int bookmarkEmployeeNo,
			int bookmarkReservationNo, ReservationInfoDTO reservationInfoDTO) {
		super();
		this.bookmarkNo = bookmarkNo;
		this.bookmarkCancelYn = bookmarkCancelYn;
		this.bookmarkEmployeeNo = bookmarkEmployeeNo;
		this.bookmarkReservationNo = bookmarkReservationNo;
		this.reservationInfoDTO = reservationInfoDTO;
	}
	public int getBookmarkNo() {
		return bookmarkNo;
	}
	public void setBookmarkNo(int bookmarkNo) {
		this.bookmarkNo = bookmarkNo;
	}
	public String getBookmarkCancelYn() {
		return bookmarkCancelYn;
	}
	public void setBookmarkCancelYn(String bookmarkCancelYn) {
		this.bookmarkCancelYn = bookmarkCancelYn;
	}
	public int getBookmarkEmployeeNo() {
		return bookmarkEmployeeNo;
	}
	public void setBookmarkEmployeeNo(int bookmarkEmployeeNo) {
		this.bookmarkEmployeeNo = bookmarkEmployeeNo;
	}
	public int getBookmarkReservationNo() {
		return bookmarkReservationNo;
	}
	public void setBookmarkReservationNo(int bookmarkReservationNo) {
		this.bookmarkReservationNo = bookmarkReservationNo;
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
		return "BookMarkAndReservationInfoDTO [bookmarkNo=" + bookmarkNo + ", bookmarkCancelYn=" + bookmarkCancelYn
				+ ", bookmarkEmployeeNo=" + bookmarkEmployeeNo + ", bookmarkReservationNo=" + bookmarkReservationNo
				+ ", reservationInfoDTO=" + reservationInfoDTO + "]";
	}
	
		
}

