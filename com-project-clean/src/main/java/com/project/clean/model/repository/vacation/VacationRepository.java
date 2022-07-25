package com.project.clean.model.repository.vacation;


import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Integer>{
	
	/* 휴가 1차 승인 대기 목록 조회 */
//	@Query(value="SELECT \r\n"
//			+ "        v.VACATION_NO\r\n"
//			+ "     , v.REQUEST_DATE\r\n"
//			+ "     , v.REQUEST_ADMIN\r\n"
//			+ "     , v.DRAFTER\r\n"
//			+ "     , v.VACATION_NAME\r\n"
//			+ "     , v.VACATION_FIRST_CONFIRM_YN\r\n"
//			+ "     , v.VACATION_SECOND_CONFIRM_YN\r\n"
//			+ "     , v.VACATION_LAST_CONFIRM_YN\r\n"
//			+ "     , v.VACATION_START_DATE\r\n"
//			+ "     , v.VACATION_END_DATE\r\n"
//			+ "     , v.VACATION_REASON\r\n"
//			+ "     , v.VACATION_RETURN_YN\r\n"
//			+ "     , v.ADMIN_NO\r\n"
//			+ "  FROM tbl_vacation v \r\n"
//			+ "  JOIN tbl_admin a ON (a.admin_no = v.admin_no)\r\n"
//			+ " WHERE v.vacation_first_confirm_yn = 'N' \r\n"
//			+ "   AND v.vacation_return_yn = 'N' \r\n"
//			+ "   AND a.admin_retire_yn = 'N'\r\n"
//			+ " ORDER BY v.vacation_no DESC", nativeQuery = true)
//	List<Vacation> findVacationByFirstConfrimN();
//
//	/* 휴가 2차 승인 대기 목록 조회 */
//	@Query(value="SELECT\r\n"
//			+ "       v.VACATION_NO\r\n"
//			+ "     , v.REQUEST_DATE\r\n"
//			+ "     , v.REQUEST_ADMIN\r\n"
//			+ "     , v.DRAFTER\r\n"
//			+ "     , v.VACATION_NAME\r\n"
//			+ "     , v.VACATION_FIRST_CONFIRM_YN\r\n"
//			+ "     , v.VACATION_SECOND_CONFIRM_YN\r\n"
//			+ "     , v.VACATION_LAST_CONFIRM_YN\r\n"
//			+ "     , v.VACATION_START_DATE\r\n"
//			+ "     , v.VACATION_END_DATE\r\n"
//			+ "     , v.VACATION_REASON\r\n"
//			+ "     , v.VACATION_RETURN_YN\r\n"
//			+ "     , v.ADMIN_NO\r\n"
//			+ "  FROM tbl_vacation v \r\n"
//			+ "  JOIN tbl_admin a ON (a.admin_no = v.admin_no)\r\n"
//			+ " WHERE v.vacation_first_confirm_yn = 'Y'\r\n"
//			+ "   AND v.vacation_second_confirm_yn = 'N'\r\n"
//			+ "   AND v.vacation_return_yn = 'N'\r\n"
//			+ "   AND a.admin_retire_yn = 'N'\r\n"
//			+ " ORDER BY v.vacation_no DESC", nativeQuery = true)
//	List<Vacation> findVacationBySecondConfirmN();


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

	/* 1차 승인 - 1차 승인여부 변경 */
	@Modifying
	@Query(value="UPDATE tbl_vacation v SET v.vacation_first_confirm_yn = 'Y' WHERE v.vacation_no = ?", nativeQuery = true)
	void findByVacationFirstConfirm(int vacationNo);

	/* 2차 승인 - 2차 승인 여부 및 최종 승인 여부 변경 */
	@Modifying
	@Query(value="UPDATE tbl_vacation v SET v.vacation_second_confirm_yn = 'Y', v.vacation_last_confirm_yn ='Y' WHERE v.vacation_no = ?", nativeQuery = true)
	void findByVacationSecondConfirm(int vacationNo);

	/* 반려 - 반려 여부 변경 */
	@Modifying
	@Query(value="UPDATE tbl_vacation v SET v.vacation_return_yn = 'Y' WHERE v.vacation_no = ?", nativeQuery = true)
	void findByVacationReturnYn(int vacationNo);

	/* 대표 승인을 위한 조회 */
	Vacation findByVacationNo(int vacationNo);


//	/* 휴가 대기 목록 신청자명으로 검색 */
//	int countByRequestAdminContaining(String searchValue);
//
//	/* 휴가 대기 목록 휴가명으로 검색 */
//	int countByVacationNameContaining(String searchValue);
//
//	/* 휴가 번호로 검색 */
//	int countByVacationNo(Integer valueOf);
//
//	/* 휴가 요청일로 검색 */
//	int countByRequestDate(Date valueOf);
//
//	/* 휴가 시작일 검색 */
//	int countByVacationStartDate(Date valueOf);
//
//	/* 휴가 종료일 검색 */
//	int countByVacationEndDate(Date valueOf);
	
	/* -------------------------------------------------------------------------------------------------------------------- */

	/* 휴가 1차 대기목록 전체 조회 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationReturnYn(String firstConfrim, String returnYN, Pageable paging);

	/* 휴가 2차 대기 목록 전체 조회 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYn(String string,
			String string2, String string3, Pageable paging);

	/* 휴가 1차 대기목록 신청자 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationReturnYnAndRequestAdminContaining(String string,
			String string2, String searchValue, Pageable paging);

	/* 휴가 1차 대기 목록 휴가 시작일 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationReturnYnAndVacationStartDate(String string,
			String string2, Date vacationStartDate, Pageable paging);

	/* 휴가 1차 대기 목록 휴가 마지막일 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationReturnYnAndVacationEndDate(String string,
			String string2, Date vacationEndDate, Pageable paging);

	/* 휴가 1차 대기 목록 휴가명 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationReturnYnAndVacationNameContaining(String string,
			String string2, String searchValue, Pageable paging);

	/* 휴가 요청자 페이징 */
	int countByVacationFirstConfirmYnAndVacationReturnYnAndRequestAdminContaining(String string, String string2,
			String searchValue);

	/* 휴가 시작일 페이징 */
	int countByVacationFirstConfirmYnAndVacationReturnYnAndVacationStartDate(String string, String string2,
			Date vacationStartDate);
	
	/* 휴가 종료일 페이징 */
	int countByVacationFirstConfirmYnAndVacationReturnYnAndVacationEndDate(String string, String string2,
			Date vacationEndDate);

	/* 휴가명 페이징 */
	int countByVacationFirstConfirmYnAndVacationReturnYnAndVacationNameContaining(String string, String string2,
			String searchValue);

	/* 대기 전체 휴가 페이징 */
	int countByVacationFirstConfirmYnAndVacationReturnYn(String string, String string2);

	List<Vacation> findAlldByVacationFirstConfirmYnAndVacationReturnYn(String string, String string2,
			Pageable paging);

/*-------------------------------------------------------------------------------------------------------------------------- */
	
	/* 2차 대기목록 관리자 이름 페이징 */
	int countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndRequestAdminContaining(
			String string, String string2, String string3, String searchValue);
	
	/* 2차 대기목록 휴가명 페이징 */
	int countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationNameContaining(
			String string, String string2, String string3, String searchValue);

	/* 2차 대기 목록 휴가 시작일 페이징 */
	int countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationStartDate(
			String string, String string2, String string3, Date vacationStartDate);

	/* 2차 대기 목록 휴가 종료일 페이징 */
	int countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationEndDate(
			String string, String string2, String string3, Date vacationEndDate);

	/* 2차 대기 목록 전체 페이징 */
	int countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYn(String string, String string2,
			String string3);

	/* 2차 대기 목록 관리자 이름 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndRequestAdminContaining(
			String string, String string2, String string3, String searchValue, Pageable paging);

	/* 2차 대기 목록 휴가 시작일 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationStartDate(
			String string, String string2, String string3, Date vacationStartDate, Pageable paging);

	/* 2차 대기 목록 휴가 종료일 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationEndDate(
			String string, String string2, String string3, Date vacationEndDate, Pageable paging);

	/* 2차 대기 목록 휴가명 검색 */
	List<Vacation> findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationNameContaining(
			String string, String string2, String string3, String searchValue, Pageable paging);

	
}

