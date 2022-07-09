package com.project.clean.model.domain.commonEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_BOOK_MARK")
public class BookMark implements Serializable{

	private static final long serialVersionUID = -3095654268238559940L;
	
	@Id
	@Column(name = "BOOKMARK_NO")
	private int bookmarkNo;
	
	@Column(name="BOOKMARK_CANSEL_YN")
	private String bookmarkCanselYn;
	
	@Column(name="BOOKMARK_EMPLOYEE_NO")
	private int bookmarkEmployeeNo;
	
	@Column(name="BOOKMARK_RESERVATION_NO")
	private int bookmarkReservationNo;

	public BookMark() {
	}

	public BookMark(int bookmarkNo, String bookmarkCanselYn, int bookmarkEmployeeNo, int bookmarkReservationNo) {
		this.bookmarkNo = bookmarkNo;
		this.bookmarkCanselYn = bookmarkCanselYn;
		this.bookmarkEmployeeNo = bookmarkEmployeeNo;
		this.bookmarkReservationNo = bookmarkReservationNo;
	}

	public int getBookmarkNo() {
		return bookmarkNo;
	}

	public void setBookmarkNo(int bookmarkNo) {
		this.bookmarkNo = bookmarkNo;
	}

	public String getBookmarkCanselYn() {
		return bookmarkCanselYn;
	}

	public void setBookmarkCanselYn(String bookmarkCanselYn) {
		this.bookmarkCanselYn = bookmarkCanselYn;
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
		return "BookMark [bookmarkNo=" + bookmarkNo + ", bookmarkCanselYn=" + bookmarkCanselYn + ", bookmarkEmployeeNo="
				+ bookmarkEmployeeNo + ", bookmarkReservationNo=" + bookmarkReservationNo + "]";
	}
	
	

}
