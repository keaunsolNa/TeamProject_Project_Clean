package com.project.clean.model.repository.pay;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.Employee;

public interface EmployeeRepositoryByPay  extends JpaRepository<Employee, Integer> {

	Employee findByEmployeeId(String employeeId);

	int countByEmployeeNo(int employeeNo);


	

}
