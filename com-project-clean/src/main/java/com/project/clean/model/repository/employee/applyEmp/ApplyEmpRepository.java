package com.project.clean.model.repository.employee.applyEmp;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.joinEntity.EmployeeAndApplyEmployee;

public interface ApplyEmpRepository extends JpaRepository<EmployeeAndApplyEmployee, String> {

	EmployeeAndApplyEmployee findByEmployeeId(String employeeId);

}
