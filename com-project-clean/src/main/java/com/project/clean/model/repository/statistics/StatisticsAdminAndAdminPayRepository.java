package com.project.clean.model.repository.statistics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.joinEntity.AdminAndAdminPay;

public interface StatisticsAdminAndAdminPayRepository extends JpaRepository<AdminAndAdminPay, Integer> {

	/* 관리자 직급별 상세정보 조회 */
	public List<AdminAndAdminPay> findByAdminJob(String job);
}
