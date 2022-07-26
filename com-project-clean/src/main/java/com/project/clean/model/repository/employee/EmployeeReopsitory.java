package com.project.clean.model.repository.employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.Vacation;

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

	public Page<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYn(String string, String string1, String string2, Pageable pageable);

	public List<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeLastConfirmYnAndEmployeeRegistReturnYn(
			String string, String string2, String string3, String string4);

	public Page<AdminEmployee> findByEmployeeRegistReturnYnAndEmployeeBlackListYn(String string, String string2, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeBlackListYn(String string, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeNo(
			String string, String string2, String string3, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeNameContaining(
			String string, String string2, String string3, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeePhoneContaining(
			String string, String string2, String string3, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeAddressContaining(
			String string, String string2, String string3, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeeNoContaining(
			String string, String string1, String string2, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeeAddressContaining(
			String string, String string1, String string2, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeePhoneContaining(
			String string, String string1, String string2, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeeNameContaining(
			String string, String string1, String string2, String categoryValue, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRegistReturnYn(String string, Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRegistReturnYnAndEmployeeNoContaining(String string, String categoryValue,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRegistReturnYnAndEmployeeNameContaining(String string, String categoryValue,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRegistReturnYnAndEmployeeAddressContaining(String string, String categoryValue,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeRegistReturnYnAndEmployeePhoneContaining(String string, String categoryValue,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeBlackListYnAndEmployeeNoContaining(String string, String categoryValue,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeBlackListYnAndEmployeeNameContaining(String string, String categoryValue,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeBlackListYnAndEmployeePhoneContaining(String string, String categoryValue,
			Pageable pageable);

	public Page<AdminEmployee> findByEmployeeBlackListYnAndEmployeeAddressContaining(String string,
			String categoryValue, Pageable pageable);

	public List<AdminEmployee> findByEmployeeBlackListYnNot(String string);

	@Query(value = "SELECT SEQ_EMP_NO.NEXTVAL FROM DUAL", nativeQuery = true)
	public int findMaxSequenceNo();

//	public Page<Vacation> findByNameContaining(String categoryValue, Pageable pageable);
//
//	public Page<Vacation> selectBetweenStartDate(String startDate, String endDate);
//
//	public Page<Vacation> selectBetweenEndDate(String startDate, String endDate);


	
}
