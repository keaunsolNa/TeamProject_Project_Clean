package com.project.clean.model.repository.pay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.BestEmployeePay;

public interface BestEmployeePayRepository  extends JpaRepository<BestEmployeePay, Integer> {
	
	@Modifying
	@Query(value="INSERT INTO TBL_BEST_EMPLOYEE_PAY( BEST_EMPLOYEE_PAY_HISTORY_NO, BEST_EMPLOYEE_PAY_DATE, BEST_EMPLOYEE_NO, BEST_EMPLOYEE_BONUS) VALUES (SEQ_TBL_BEST_EMPLOYEE_PAY.NEXTVAL, TO_DATE(SYSDATE, 'YY-MM-DD'), :bestEmployeeNo, :bestEmployeeBonus)" ,nativeQuery=true)
	void registBestEmployeePay(int bestEmployeeNo, int bestEmployeeBonus);


	

}
