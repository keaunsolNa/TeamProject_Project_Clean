package com.project.clean.model.service.statistics;

import java.util.List;

import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.joinEntity.AdminAndAdminPay;
import com.project.clean.model.dto.commonDTO.EmployeeStatisticsDTO;
import com.project.clean.model.dto.commonDTO.StatisticsDTO;

public interface StatisticsService {
	
	/* 관리자 총 평균 급여 조회*/
	public int findTotalAvgSalaryWithQuery();
	
	/* 관리자 직급별 평균 급여 조회 */
	public List<StatisticsDTO> findAvgSalaryWithQuery();
//	public List<AdminDTO> countAvgSalaryWithQuery();
	
	/* 관리자 직급별 상세정보 조회 */
	public List<AdminAndAdminPay> findByAdminJob(String job); 
	
	/* 총 매출 조회 */
	public int findTotalSalesyWithQuery();

	/* 순수익 구하기 */
	public int findNetProfitWithQuery();
	

	/* 직원 평균 누적근무시간 */
	int findAvgHoursWithQuery();

	/* 직원 총 누적근무시간 */
	int findTotalHoursWithQuery();

	/* 이달의 우수직원 조회 */
	List<EmployeeStatisticsDTO> findBestEmployeeWithQuery();

	/* 직원 근무시간 조회 */
	List<Employee> findByEmployeeNo(int no);
}
