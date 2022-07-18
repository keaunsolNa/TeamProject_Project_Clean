package com.project.clean.model.repository.vacation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.adminEntity.AdminVacationCommit;

@Repository
public interface AdminVacationCommitRepository extends JpaRepository<AdminVacationCommit, Integer>{

	/* 휴가 상세 조회 */
	AdminVacationCommit findByVacationNo(int vacationNo);

}
