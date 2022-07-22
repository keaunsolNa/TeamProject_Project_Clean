package com.project.clean.model.repository.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;

public interface ApplyEmployeeRepository extends JpaRepository<ApplyEmployee, Object> {

	@Query(value="SELECT * FROM TBL_APPLY_EMPLOYEE A WHERE A.APPLY_RESERVATION_NO = :findByApplyReservationNo AND A.APPLY_CANCEL_YN = 'N'", nativeQuery = true)
	List<ApplyEmployee> findAllByApplyReservationNo(@Param("findByApplyReservationNo")int findByApplyReservationNo);

	@Query(value = "SELECT\r\n"
			+ "       A.APPLY_EMPLOYEE_NO\r\n"
			+ "     , A.APPLY_RESERVATION_NO\r\n"
			+ "     , A.APPLY_CANCEL_YN\r\n"
			+ "     , A.CHECK_EMPLOYEE_YN\r\n"
			+ "  FROM TBL_APPLY_EMPLOYEE A\r\n"
			+ " WHERE A.APPLY_EMPLOYEE_NO = :employeeNo\r\n"
			+ "   AND A.APPLY_RESERVATION_NO = :reservationNo", nativeQuery = true)
	ApplyEmployee findByApplyEmployeeNoAndApplyReservationNo(@Param("reservationNo")int reservationNo, @Param("employeeNo")int employeeNo);

	int countByApplyEmployeeNoAndApplyCancelYn(int employeeNo, String n);

	ApplyEmployee findByApplyReservationNoAndApplyCancelYn(int applyReservationNo, String string);
	
}

