package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class BookMarkDTO implements Serializable{

	private static final long serialVersionUID = 3576945236851160745L;

	private int bookmarkNo;
	private String bookmarkCancelYn;
	private int bookmarkEmployeeNo;
	private int bookmarkReservationNo;
	public BookMarkDTO() {
		super();
	}
	public BookMarkDTO(int bookmarkNo, String bookmarkCancelYn, int bookmarkEmployeeNo, int bookmarkReservationNo) {
		super();
		this.bookmarkNo = bookmarkNo;
		this.bookmarkCancelYn = bookmarkCancelYn;
		this.bookmarkEmployeeNo = bookmarkEmployeeNo;
		this.bookmarkReservationNo = bookmarkReservationNo;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BookMarkDTO [bookmarkNo=" + bookmarkNo + ", bookmarkCancelYn=" + bookmarkCancelYn
				+ ", bookmarkEmployeeNo=" + bookmarkEmployeeNo + ", bookmarkReservationNo=" + bookmarkReservationNo
				+ "]";
	}
	
	
}

