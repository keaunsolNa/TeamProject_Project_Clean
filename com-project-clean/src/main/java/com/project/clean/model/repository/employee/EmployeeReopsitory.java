package com.project.clean.model.repository.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.adminEntity.AdminEmployee;

public interface EmployeeReopsitory extends JpaRepository<AdminEmployee, Integer>{

	@Query(value = "SELECT MAX(EMPLOYEE_NO) FROM TBL_EMPLOYEE", nativeQuery = true)
	public int getMaxMemberNo();
	
	@Query(value = "SELECT MAX(EMPLOYEE_NO) FROM TBL_EMPLOYEE", nativeQuery = true)
	public java.sql.Date getMamxMemberNo();

	@Query
	public List<AdminEmployee> findAllByEmployeeRetireYn(String employeeRetireYn);

	public List<AdminEmployee> findByEmployeeRetireYn(String string);

	public Page<AdminEmployee> findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYn(String retire, String confirm, String black, Pageable page);

//	public List<AdminEmployee> findByEmployeeRegistReturnYnAndEmployeeBlackListYn(String returnYn, String blackYn);

	public List<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeLastConfirmYn(String string, String string2);

	public List<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeLastConfirmYnAndEmployeeRegistReturnYn(
			String string, String string2, String string3, String string4);

	public Page<AdminEmployee> findByEmployeeRegistReturnYnAndEmployeeBlackListYn(String string, String string2,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeBlackListYn(String string, Pageable pageable);

	
}
