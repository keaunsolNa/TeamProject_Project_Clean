package com.project.clean.model.repository.pay;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.joinEntity.EmployeePayAndApplyEmployee;
import com.project.clean.model.dto.joinDTO.EmployeePayAndApplyEmployeeDTO;

public interface EmployeePayAndEmployeeRepository  extends JpaRepository<EmployeePayAndApplyEmployee, Integer> {


	int countByEmployeeEmployeeNameContaining(String searchValue);

	int countByEmployeeEmployeePhoneContaining(String searchValue);

	List<EmployeePayAndApplyEmployee> findByEmployeeEmployeeNameContaining(String searchValue, Pageable paging);

	List<EmployeePayAndApplyEmployee> findByEmployeeEmployeePhoneContaining(Integer valueOf, Pageable paging);

	List<EmployeePayAndApplyEmployee> findAllByEmployeeEmployeeNo(int employeeNo, Pageable paging);

	
	
	
}
