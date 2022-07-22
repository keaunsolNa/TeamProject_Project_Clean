package com.project.clean.model.repository.reservation;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.reservation.Reservation;

public interface AdminReservationRepository extends JpaRepository<Reservation, Object>   {

	@Query(value = "SELECT DISTINCT \r\n"
			+ "A.BUSINESS_DATE \r\n"
			+ "FROM TBL_RESERVATION_INFO A \r\n"
			+ "ORDER BY A.BUSINESS_DATE ASC", nativeQuery = true)
	List<String> findDistinctByBusinessDate();

	@Query(value="SELECT\r\n"
			+ "COUNT(*)\r\n"
			+ "FROM TBL_RESERVATION_INFO A\r\n"
			+ "WHERE A.BUSINESS_DATE = :date\r\n"
			+ "AND A.APPLY_END_YN = :end", nativeQuery = true)
	long countByBusinessDate(@Param("date")String date, @Param("end")String string);

	int countByBusinessDate(Date businessDate);

	List<Reservation> findByBusinessDate(Date businessDate, Pageable paging);

	Reservation findByReservationNo(int reservationNo);

}
