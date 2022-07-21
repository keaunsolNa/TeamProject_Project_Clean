package com.project.clean.model.repository.pay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.EmployeePay;

public interface EmployeePayRepository  extends JpaRepository<EmployeePay, Integer> {
	
	@Modifying
	@Query(value="INSERT INTO TBL_EMPLOYEE_PAY( PAY_HISTORY_EMPLOYEE_NO, PAY_EMPLOYEE_DATE, PAY_APPLY_RESERVATION_NO, PAY_APPLY_EMPLOYEE_NO, PAY_EMPLOYEE_FINAL_SALARY) VALUES (SEQ_TBL_EMPLOYEE_PAY.NEXTVAL, TO_DATE(SYSDATE,'YY-MM-DD'), :applyReservationNo, :applyEmployeeNo, :payEmployeeFinalSalary)", nativeQuery=true)
	void modifyEmployeePay(int applyReservationNo, int applyEmployeeNo, int payEmployeeFinalSalary);
	
	
}
