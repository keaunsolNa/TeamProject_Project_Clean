package com.project.clean.model.repository.vacation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.adminEntity.AdminVacationCommit;
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

	/* 휴가 정보 상세 조회 */
//	Vacation findByVacationNo(int vacationNo);

	/* 휴가 번호에 따른 승인자 리스트 조회 */
//	@Query(value="SELECT a.admin_no FROM tbl_vacation_commit a WHERE a.vacation_no = ?", nativeQuery = true)
//	List<Vacation> findVacationCommitAdminList(int vacationNo);

	/* 휴가 상세 조회 */
//	Vacation findByVacationNo(int vacationNo);
	
}

