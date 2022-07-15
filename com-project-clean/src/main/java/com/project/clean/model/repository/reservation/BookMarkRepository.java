package com.project.clean.model.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.clean.model.domain.commonEntity.BookMark;

public interface BookMarkRepository extends JpaRepository<BookMark, Object> {

	@Query(value="SELECT\r\n"
			+ "       A.BOOKMARK_NO\r\n"
			+ "     , A.BOOKMARK_CANCEL_YN\r\n"
			+ "     , A.BOOKMARK_EMPLOYEE_NO\r\n"
			+ "     , A.BOOKMARK_RESERVATION_NO\r\n"
			+ "  FROM TBL_BOOKMARK A\r\n"
			+ " WHERE A.BOOKMARK_EMPLOYEE_NO = :employeeNo\r\n"
			+ "   AND A.BOOKMARK_RESERVATION_NO = :reservationNo"
			+ "   AND A.BOOKMARK_CANCEL_YN = 'N'", nativeQuery = true)
	BookMark findByBookmarkEmployeeNoAndBookmarkReservationNo(@Param("employeeNo")int employeeNo, @Param("reservationNo")int reservationNo);

}

