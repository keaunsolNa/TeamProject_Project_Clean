package com.project.clean.model.repository.vacation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.VacationCommit;


public interface VacationCommitRepository extends JpaRepository<VacationCommit, Integer>{

	/* 관리자 승인 조회 */
//	VacationCommit findByVacationNo(int vacationNo);

	
}
