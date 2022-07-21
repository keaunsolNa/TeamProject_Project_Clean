package com.project.clean.model.repository.pay;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.joinEntity.EmployeePayAndApplyEmployee;

public interface EmployeePayRepository  extends JpaRepository<EmployeePayAndApplyEmployee, Integer> {

	@Query(value="SELECT DISTINCT A.PAY_HISTORY_EMPLOYEE_NO, A.PAY_EMPLOYEE_DATE, A.PAY_EMPLOYEE_FINAL_SALARY, A.APPLY_EMPLOYEE_NO, A.APPLY_RESERVATION_NO, C.EMPLOYEE_NAME, C.EMPLOYEE_PHONE, D.USER_ADDRESS, D.USER_DETAIL_ADDRESS, D.BUSINESS_DATE, D.BUSINESS_START_TIME, D.BUSINESS_END_TIME, D.TOTAL_PAYMENT FROM TBL_EMPLOYEE_PAY A JOIN TBL_APPLY_EMPLOYEE B ON(A.APPLY_EMPLOYEE_NO = B.APPLY_EMPLOYEE_NO) JOIN TBL_EMPLOYEE C ON(B.APPLY_EMPLOYEE_NO = C.EMPLOYEE_NO) JOIN TBL_RESERVATION_INFO D ON(B.APPLY_RESERVATION_NO = D.RESERVATION_NO) JOIN TBL_CHECKLIST E ON(D.RESERVATION_NO = E.CHECK_RESERVATION_NO) WHERE E.CHECK_STATUS = 'A'",countQuery= "SELECT DISTINCT A.PAY_HISTORY_EMPLOYEE_NO, A.PAY_EMPLOYEE_DATE, A.PAY_EMPLOYEE_FINAL_SALARY, A.APPLY_EMPLOYEE_NO, A.APPLY_RESERVATION_NO, C.EMPLOYEE_NAME, C.EMPLOYEE_PHONE, D.USER_ADDRESS, D.USER_DETAIL_ADDRESS, D.BUSINESS_DATE, D.BUSINESS_START_TIME, D.BUSINESS_END_TIME, D.TOTAL_PAYMENT FROM TBL_EMPLOYEE_PAY A JOIN TBL_APPLY_EMPLOYEE B ON(A.APPLY_EMPLOYEE_NO = B.APPLY_EMPLOYEE_NO) JOIN TBL_EMPLOYEE C ON(B.APPLY_EMPLOYEE_NO = C.EMPLOYEE_NO) JOIN TBL_RESERVATION_INFO D ON(B.APPLY_RESERVATION_NO = D.RESERVATION_NO) JOIN TBL_CHECKLIST E ON(D.RESERVATION_NO = E.CHECK_RESERVATION_NO) WHERE E.CHECK_STATUS = 'A'", nativeQuery = true)
	List<EmployeePayAndApplyEmployee> findEmployeePayForNative(Pageable paging);

	List<EmployeePayAndApplyEmployee> findByEmployeeNameContaining(String searchValue, Pageable paging);

	List<EmployeePayAndApplyEmployee> findByEmployeePhoneContaining(Integer valueOf, Pageable paging);

	int countByEmployeePhoneContaining(String searchValue);

	int countByEmployeeNameContaining(String searchValue);
	
	
}
