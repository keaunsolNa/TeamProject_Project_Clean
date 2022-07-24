package com.project.clean.model.repository.statistics;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.Employee;

public interface StatisticsEmployeeRepository extends JpaRepository<Employee, Integer>{

	/* 직원 근무시간 조회 */
//	public List<Employee> findAll();
	
	public List<Employee> findByEmployeeRetireYn(String yn);
	public List<Employee> findByEmployeeRetireYnOrderByEmployeeSumTimeDesc(String yn);

	
	
}
