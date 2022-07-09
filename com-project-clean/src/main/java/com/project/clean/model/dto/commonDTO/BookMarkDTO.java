package com.project.clean.model.dto.commonDTO;

import java.io.Serializable;

public class BookMarkDTO implements Serializable{

	private static final long serialVersionUID = 3576945236851160745L;

	private int bookmarkNo;
	private String bookmarkCanselYn;
	private int bookmarkEmployeeNo;
	private int bookmarkReservationNo;
	public BookMarkDTO() {
	}
	public BookMarkDTO(int bookmarkNo, String bookmarkCanselYn, int bookmarkEmployeeNo, int bookmarkReservationNo) {
		this.bookmarkNo = bookmarkNo;
		this.bookmarkCanselYn = bookmarkCanselYn;
		this.bookmarkEmployeeNo = bookmarkEmployeeNo;
		this.bookmarkReservationNo = bookmarkReservationNo;
	}
	int getBookmarkNo() {
		return bookmarkNo;
	}
	void setBookmarkNo(int bookmarkNo) {
		this.bookmarkNo = bookmarkNo;
	}
	String getBookmarkCanselYn() {
		return bookmarkCanselYn;
	}
	void setBookmarkCanselYn(String bookmarkCanselYn) {
		this.bookmarkCanselYn = bookmarkCanselYn;
	}
	int getBookmarkEmployeeNo() {
		return bookmarkEmployeeNo;
	}
	void setBookmarkEmployeeNo(int bookmarkEmployeeNo) {
		this.bookmarkEmployeeNo = bookmarkEmployeeNo;
	}
	int getBookmarkReservationNo() {
		return bookmarkReservationNo;
	}
	void setBookmarkReservationNo(int bookmarkReservationNo) {
		this.bookmarkReservationNo = bookmarkReservationNo;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BookMarkDTO [bookmarkNo=" + bookmarkNo + ", bookmarkCanselYn=" + bookmarkCanselYn
				+ ", bookmarkEmployeeNo=" + bookmarkEmployeeNo + ", bookmarkReservationNo=" + bookmarkReservationNo
				+ "]";
	}
	
	
}

