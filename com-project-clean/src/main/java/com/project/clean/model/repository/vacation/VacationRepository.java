package com.project.clean.model.repository.vacation;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Integer>{
	
	/* 휴가 1차 승인 대기 목록 조회 */
	@Query(value="SELECT * FROM tbl_vacation v WHERE v.vacation_first_confirm_yn = 'N'", nativeQuery = true)
	List<Vacation> findVacationByFirstConfrimN();

	/* 휴가 2차 승인 대기 목록 조회 */
	@Query(value="SELECT * FROM tbl_vacation v \r\n"
			+ " WHERE v.vacation_first_confirm_yn = 'Y' \r\n"
			+ "   AND  v.vacation_second_confirm_yn = 'N' \r\n"
			+ "   AND v.vacation_return_yn = 'N'", nativeQuery = true)
	List<Vacation> findVacationBySecondConfirmN();


	public Page<Vacation> findByAdminNo(int adminNo, Pageable pageable);

	public Vacation findByVacationNo(int vacationNo, Sort sort);

	public Page<Vacation> findByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationLastConfirmYnAndVacationReturnYn(
			String firstConfirm, String secondConfirm, String lastConfirm, String returnUn, Pageable pageable);
	
	public Page<Vacation> findByVacationFirstConfirmYnOrVacationSecondConfirmYnOrVacationLastConfirmYnOrVacationReturnYn(
			String firstConfirm, String secondConfirm, String lastConfirm, String returnUn, Pageable pageable);

	@Query(value = "SELECT\r\n"
			+ "       A.ADMIN_NO\r\n"
			+ "      ,A.ADMIN_NAME\r\n"
			+ "      ,B.VACATION_NO\r\n"
			+ "      ,B.REQUEST_DATE\r\n"
			+ "      ,B.REQUEST_ADMIN\r\n"
			+ "      ,B.DRAFTER\r\n"
			+ "      ,B.VACATION_NAME\r\n"
			+ "      ,B.VACATION_FIRST_CONFIRM_YN\r\n"
			+ "      ,B.VACATION_SECOND_CONFIRM_YN\r\n"
			+ "      ,B.VACATION_LAST_CONFIRM_YN\r\n"
			+ "      ,B.VACATION_START_DATE\r\n"
			+ "      ,B.VACATION_END_DATE\r\n"
			+ "      ,B.VACATION_REASON\r\n"
			+ "      ,B.VACATION_RETURN_YN\r\n"
			+ "  FROM TBL_ADMIN A\r\n"
			+ "  JOIN TBL_VACATION B ON (A.ADMIN_NO = B.ADMIN_NO)\r\n"
			+ " WHERE A. ADMIN_NAME LIKE '?1'", nativeQuery = true)
	public Page<Vacation> findByNameContaining(String categoryValue, Pageable pageable);

	@Query(value = "SELECT \r\n"
			    	+ "       *\r\n"
				 + "  FROM TBL_VACATION\r\n"
				 + " WHERE ADMIN_NO = 1\r\n"
				 + "   AND VACATION_START_DATE BETWEEN ?1 AND ?2", nativeQuery = true)
	public Page<Vacation> selectBetweenStartDate(String startDate, String endDate, Pageable pageable);

	@Query(value = "SELECT \r\n"
	    	+ "       *\r\n"
		 + "  FROM TBL_VACATION\r\n"
		 + " WHERE ADMIN_NO = 1\r\n"
		 + "   AND VACATION_END_DATE BETWEEN ?1 AND ?2", nativeQuery = true)
	public Page<Vacation> selectBetweenEndDate(String startDate, String endDate, Pageable pageable);

//	public Page<Vacation> findByAdminNoAndRequestDateBetweeen(int adminNo, Date startDate, Date endDate, Pageable pageable);
//
//	public Page<Vacation> findByAdminNoAndVacationStartDateBetweeen(int adminNo, Date startDate, Date endDate, Pageable pageable);
//
//	public Page<Vacation> findByAdminNoAndVacationEndDateBetweeen(int adminNo, Date startDate, Date endDate, Pageable pageable);


	
}

