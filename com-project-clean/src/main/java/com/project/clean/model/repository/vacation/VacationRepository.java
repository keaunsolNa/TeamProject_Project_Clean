package com.project.clean.model.repository.vacation;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	
}

