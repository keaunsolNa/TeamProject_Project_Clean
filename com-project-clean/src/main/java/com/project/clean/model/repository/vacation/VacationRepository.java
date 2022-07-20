package com.project.clean.model.repository.vacation;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Integer>{
	
	/* 휴가 1차 승인 대기 목록 조회 */
	@Query(value="SELECT \r\n"
			+ "        v.VACATION_NO\r\n"
			+ "     , v.REQUEST_DATE\r\n"
			+ "     , v.REQUEST_ADMIN\r\n"
			+ "     , v.DRAFTER\r\n"
			+ "     , v.VACATION_NAME\r\n"
			+ "     , v.VACATION_FIRST_CONFIRM_YN\r\n"
			+ "     , v.VACATION_SECOND_CONFIRM_YN\r\n"
			+ "     , v.VACATION_LAST_CONFIRM_YN\r\n"
			+ "     , v.VACATION_START_DATE\r\n"
			+ "     , v.VACATION_END_DATE\r\n"
			+ "     , v.VACATION_REASON\r\n"
			+ "     , v.VACATION_RETURN_YN\r\n"
			+ "     , v.ADMIN_NO\r\n"
			+ "  FROM tbl_vacation v \r\n"
			+ "  JOIN tbl_admin a ON (a.admin_no = v.admin_no)\r\n"
			+ " WHERE v.vacation_first_confirm_yn = 'N' \r\n"
			+ "   AND v.vacation_return_yn = 'N' \r\n"
			+ "   AND a.admin_retire_yn = 'N'\r\n"
			+ " ORDER BY v.vacation_no", nativeQuery = true)
	List<Vacation> findVacationByFirstConfrimN();

	/* 휴가 2차 승인 대기 목록 조회 */
	@Query(value="SELECT\r\n"
			+ "       v.VACATION_NO\r\n"
			+ "     , v.REQUEST_DATE\r\n"
			+ "     , v.REQUEST_ADMIN\r\n"
			+ "     , v.DRAFTER\r\n"
			+ "     , v.VACATION_NAME\r\n"
			+ "     , v.VACATION_FIRST_CONFIRM_YN\r\n"
			+ "     , v.VACATION_SECOND_CONFIRM_YN\r\n"
			+ "     , v.VACATION_LAST_CONFIRM_YN\r\n"
			+ "     , v.VACATION_START_DATE\r\n"
			+ "     , v.VACATION_END_DATE\r\n"
			+ "     , v.VACATION_REASON\r\n"
			+ "     , v.VACATION_RETURN_YN\r\n"
			+ "     , v.ADMIN_NO\r\n"
			+ "  FROM tbl_vacation v \r\n"
			+ "  JOIN tbl_admin a ON (a.admin_no = v.admin_no)\r\n"
			+ " WHERE v.vacation_first_confirm_yn = 'Y'\r\n"
			+ "   AND v.vacation_second_confirm_yn = 'N'\r\n"
			+ "   AND v.vacation_return_yn = 'N'\r\n"
			+ "   AND a.admin_retire_yn = 'N'\r\n"
			+ " ORDER BY v.vacation_no", nativeQuery = true)
	List<Vacation> findVacationBySecondConfirmN();


	public Page<Vacation> findByAdminNo(int adminNo, Pageable pageable);

	public Vacation findByVacationNo(int vacationNo, Sort sort);

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

	
}

