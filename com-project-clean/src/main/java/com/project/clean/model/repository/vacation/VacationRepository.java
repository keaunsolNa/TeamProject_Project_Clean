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

	/* 승인 전체 조회 */
	Page<Vacation> findByVacationLastConfirmYn(String string, Pageable pageable);
	
	/* 반려 전체 조회 */
	Page<Vacation> findByVacationReturnYn(String string, Pageable pageable);
	
	/* 전체 이름 검색 */
	public Page<Vacation> findByRequestAdminContaining(String categoryValue, Pageable pageable);
	
	/* 승인 이름 검색*/
	public Page<Vacation> findByVacationLastConfirmYnAndRequestAdminContaining(String confirm, String categoryValue, Pageable pageable);
	
	/* 반려 이름 검색*/
	public Page<Vacation> findByVacationReturnYnAndRequestAdminContaining(String returnYn, String categoryValue, Pageable pageable);

	/* 전체 휴가 시작일 검색 */
	@Query(value = " SELECT \r\n"
				  + "       *\r\n"
				  + "  FROM TBL_VACATION\r\n"
				  + "  WHERE VACATION_START_DATE BETWEEN TO_DATE(?1) AND TO_DATE(?2)", nativeQuery = true)
	public Page<Vacation> selectBetweenStartDate(String startDate, String endDate, Pageable pageable);
	/* 전체 휴가 종료일 검색 */
	@Query(value = " SELECT \r\n"
			  + "       *\r\n"
			  + "  FROM TBL_VACATION\r\n"
			  + "  WHERE VACATION_END_DATE BETWEEN TO_DATE(?1) AND TO_DATE(?2)", nativeQuery = true)
	public Page<Vacation> selectBetweenEndDate(String startDate, String endDate, Pageable pageable);
	
	/* 승인 휴가 시작일 검색 */
	@Query(value = "  SELECT \r\n"
			+ "       *\r\n"
			+ "  FROM TBL_VACATION\r\n"
			+ "  WHERE VACATION_START_DATE BETWEEN TO_DATE(?1)AND TO_DATE(?2)\r\n"
			+ "    AND VACATION_LAST_CONFIRM_YN = 'Y'", nativeQuery = true)
	public Page<Vacation> selectConfirmBetweenStartDate(String startDate, String endDate, Pageable pageable);
	
	/* 승인 휴가 종료일 검색 */
	@Query(value = " SELECT \r\n"
			+ "       *\r\n"
			+ "  FROM TBL_VACATION\r\n"
			+ "  WHERE VACATION_END_DATE BETWEEN TO_DATE(?1)AND TO_DATE(?2)\r\n"
			+ "    AND VACATION_LAST_CONFIRM_YN = 'Y'", nativeQuery = true)
	public Page<Vacation> selectConfirmBetweenEndDate(String startDate, String endDate, Pageable pageable);
	
	/* 반려 휴가 시작일 검색 */
	@Query(value = " SELECT \r\n"
			+ "       *\r\n"
			+ "  FROM TBL_VACATION\r\n"
			+ "  WHERE VACATION_START_DATE BETWEEN TO_DATE(?1)AND TO_DATE(?2)\r\n"
			+ "    AND VACATION_RETURN_YN = 'Y'", nativeQuery = true)
	public Page<Vacation> selectReturnBetweenStartDate(String startDate, String endDate, Pageable pageable);
	
	/* 반려 휴가 종료일 검색 */
	@Query(value = " SELECT \r\n"
			+ "       *\r\n"
			+ "  FROM TBL_VACATION\r\n"
			+ "  WHERE VACATION_END_DATE BETWEEN TO_DATE(?1)AND TO_DATE(?2)\r\n"
			+ "    AND VACATION_RETURN_YN = 'Y'", nativeQuery = true)
	public Page<Vacation> selectReturnBetweenEndDate(String startDate, String endDate, Pageable pageable);




	
}

