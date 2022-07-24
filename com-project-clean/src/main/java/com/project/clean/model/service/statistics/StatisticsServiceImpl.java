package com.project.clean.model.service.statistics;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.joinEntity.AdminAndAdminPay;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.EmployeeStatisticsDTO;
import com.project.clean.model.dto.commonDTO.StatisticsDTO;
import com.project.clean.model.dto.joinDTO.AdminAndAdminPayDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;
import com.project.clean.model.repository.statistics.StatisticsAdminAndAdminPayRepository;
import com.project.clean.model.repository.statistics.StatisticsRepository;

@Service
public class StatisticsServiceImpl implements StatisticsService{
	private final StatisticsRepository statisticsRepository;
	private final StatisticsAdminAndAdminPayRepository statisticsAdminAndAdminPayRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public StatisticsServiceImpl(StatisticsRepository statisticsRepository, StatisticsAdminAndAdminPayRepository statisticsAdminAndAdminPayRepository, ModelMapper modelMapper) {
		this.statisticsRepository = statisticsRepository;
		this.statisticsAdminAndAdminPayRepository = statisticsAdminAndAdminPayRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 관리자 총 평균 급여 조회*/
	@Override
	public int findTotalAvgSalaryWithQuery() {
		int totalAvgSalary = statisticsRepository.findTotalAvgSalaryWithQuery();		
		return totalAvgSalary;
	}

	/* 관리자 직급별 평균 급여 조회 */
	@Override
	public List<StatisticsDTO> findAvgSalaryWithQuery() {
		List<Object[]> resultList = statisticsRepository.findAvgSalaryWithQuery();
		
//		for(Object[] row : resultList) {
//			System.out.println("" + row[0] + '/' + row[1] + '/' + row[2]);
//		}
		return resultList.stream().map(adminSalaryList -> new StatisticsDTO(
					Integer.parseInt(String.valueOf(adminSalaryList[0])),
					(String) adminSalaryList[1],
					String.valueOf(adminSalaryList[2] + " 원")
				)).collect(Collectors.toList());
	}

	/* 관리자 직급별 상세정보 조회 -> entity로 바로 갖고옴*/
	
	
	/* 총 매출 조회 */
	@Override
	public int findTotalSalesyWithQuery() {
		int totalSales = statisticsRepository.findTotalSalesWithQuery();		
		return totalSales;
	}
	
	/* 순 수익 구하기 */
	@Override
	public int findNetProfitWithQuery() {
		int NetProfit = statisticsRepository.findNetProfitWithQuery();
		return NetProfit;
	}
	
	/* 직원 평균 누적근무시간 */
	@Override
	public int findAvgHoursWithQuery() {
		int employeeAvgHours = statisticsRepository.findAvgHoursWithQuery();
		return employeeAvgHours;
	}
	
	/* 직원 총 누적근무시간 */
	@Override
	public int findTotalHoursWithQuery() {
		int employeeTotalHours = statisticsRepository.findTotalHoursWithQuery();
		return employeeTotalHours;
	}
	
	/* 직원 근무시간 조회 */
//	@Override
//	public List<Employee> findByEmployeeNo(int no);
//		int findByEmployeeNo = statisticsEmployeeRepository.findByEmployeeNo();
//		return employeeList;

	/* 이달의 우수직원 조회 */
	@Override
	public List<EmployeeStatisticsDTO> findBestEmployeeWithQuery() {
//		List<Employee> employeeList = statisticsRepository.findBestEmployeeWithQuery();					
			
		List<Object[]> resultList = statisticsRepository.findBestEmployeeWithQuery();
		
		for(Object[] row : resultList) {
			System.out.println("" + row[0] + '/' + row[1] + '/' + row[2]);
		}
		
		return resultList.stream().map(returnList -> new EmployeeStatisticsDTO(
					Integer.parseInt(String.valueOf(returnList[0])),
					(String) returnList[1],
					Integer.parseInt(String.valueOf(returnList[2]))
				)).collect(Collectors.toList());
	}

	@Override
	public List<AdminAndAdminPay> findByAdminJob(String job) {
		return statisticsAdminAndAdminPayRepository.findByAdminJob(job);
	}

	@Override
	public List<Employee> findByEmployeeNo(int no) {
		// TODO Auto-generated method stub
		return null;
	}
}
