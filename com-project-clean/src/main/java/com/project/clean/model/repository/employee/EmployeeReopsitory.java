package com.project.clean.model.repository.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.adminEntity.AdminEmployee;

public interface EmployeeReopsitory extends JpaRepository<AdminEmployee, Integer>{

	@Query(value = "SELECT MAX(EMPLOYEE_NO) FROM TBL_EMPLOYEE", nativeQuery = true)
	public int getMaxMemberNo();
	
	@Query(value = "SELECT MAX(EMPLOYEE_NO) FROM TBL_EMPLOYEE", nativeQuery = true)
	public java.sql.Date getMamxMemberNo();

	@Query(value = "SELECT SYSDATE FROM DUAL", nativeQuery = true)
	public String sysdate();

	@Query
	public List<AdminEmployee> findAllByEmployeeRetireYn(String employeeRetireYn);

	public List<AdminEmployee> findByEmployeeRetireYn(String string);

	public List<AdminEmployee> findByEmployeeRetireYnAndEmployeeLastConfirmYn(String string, String string2);

	public List<AdminEmployee> findByEmployeeRegistReturnYn(String string);

	public List<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeLastConfirmYn(String string, String string2);

	public List<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeLastConfirmYnAndEmployeeRegistReturnYn(
			String string, String string2, String string3, String string4);

	
}
