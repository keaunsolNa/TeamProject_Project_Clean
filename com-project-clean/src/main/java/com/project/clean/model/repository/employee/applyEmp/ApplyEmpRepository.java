package com.project.clean.model.repository.employee.applyEmp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.joinEntity.EmployeeAndApplyEmployee;

public interface ApplyEmpRepository extends JpaRepository<EmployeeAndApplyEmployee, String> {

	List<EmployeeAndApplyEmployee> findByEmployeeId(String employeeId);


}
