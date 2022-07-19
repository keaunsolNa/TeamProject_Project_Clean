package com.project.clean.model.domain.joinEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;

import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;

@Entity
@Table(name="TBL_BOOKMARK")
@SequenceGenerator(
		name="BOOKMARK_SEQ_GENERATOR",
		sequenceName="SEQ_TBL_BOOKMARK",
		initialValue = 1,
		allocationSize = 1
)
@DynamicInsert
@DynamicUpdate
public class BookMarkAndReservationInfo implements Serializable{

	private static final long serialVersionUID = -3095654268238559940L;
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "BOOKMARK_SEQ_GENERATOR"
	)
	@Column(name = "BOOKMARK_NO")
	private int bookmarkNo;
	
	@Column(name="BOOKMARK_CANCEL_YN")
	private String bookmarkCancelYn;
	
	@Column(name="BOOKMARK_EMPLOYEE_NO")
	private int bookmarkEmployeeNo;
	
	@ManyToOne
	@JoinColumn(name="BOOKMARK_RESERVATION_NO")
	private Reservation reservationInfoDTO;

	public BookMarkAndReservationInfo() {
		super();
	}

	public BookMarkAndReservationInfo(int bookmarkNo, String bookmarkCancelYn, int bookmarkEmployeeNo,
			Reservation reservationInfoDTO) {
		super();
		this.bookmarkNo = bookmarkNo;
		this.bookmarkCancelYn = bookmarkCancelYn;
		this.bookmarkEmployeeNo = bookmarkEmployeeNo;
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

	public Reservation getReservationInfoDTO() {
		return reservationInfoDTO;
	}

	public void setReservationInfoDTO(Reservation reservationInfoDTO) {
		this.reservationInfoDTO = reservationInfoDTO;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "BookMarkAndReservationInfo [bookmarkNo=" + bookmarkNo + ", bookmarkCancelYn=" + bookmarkCancelYn
				+ ", bookmarkEmployeeNo=" + bookmarkEmployeeNo + ", reservationInfoDTO=" + reservationInfoDTO + "]";
	}
	
	

		
}
