package com.project.clean.model.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.Employee;

public interface EmpRepository extends JpaRepository<Employee, String> {

	Employee findByEmployeePhone(String userPhone);

	Employee findByEmployeeId(String employeeId);


}
