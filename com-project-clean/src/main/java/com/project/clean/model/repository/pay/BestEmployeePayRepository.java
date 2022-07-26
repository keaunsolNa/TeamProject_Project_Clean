package com.project.clean.model.repository.pay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.BestEmployeePay;
import com.project.clean.model.dto.joinDTO.BestEmployeePayAndEmployeeDTO;

public interface BestEmployeePayRepository  extends JpaRepository<BestEmployeePay, Integer> {
	
	@Modifying
	@Query(value="INSERT INTO TBL_BEST_EMPLOYEE_PAY( BEST_EMPLOYEE_PAY_HISTORY_NO, BEST_EMPLOYEE_PAY_DATE, BEST_EMPLOYEE_NO, BEST_EMPLOYEE_BONUS) VALUES (SEQ_TBL_BEST_EMPLOYEE_PAY.NEXTVAL, TO_DATE(SYSDATE, 'YY-MM-DD'), :bestEmployeeNo, :bestEmployeeBonus)" ,nativeQuery=true)
	void registBestEmployeePay(int bestEmployeeNo, int bestEmployeeBonus);
	
	@Query(value="SELECT\r\n"
			+ "       BEST_EMPLOYEE_PAY_HISTORY_NO\r\n"
			+ "  FROM TBL_BEST_EMPLOYEE_PAY\r\n"
			+ " WHERE EXTRACT(MONTH FROM BEST_EMPLOYEE_PAY_DATE) = EXTRACT(MONTH FROM SYSDATE)\r\n"
			+ "   AND EXTRACT(YEAR FROM BEST_EMPLOYEE_PAY_DATE) = EXTRACT(YEAR FROM SYSDATE)\r\n"
			+ "   AND BEST_EMPLOYEE_NO = :bestEmployeeNo", nativeQuery = true)
	BestEmployeePayAndEmployeeDTO hasBestEmployeePay(int bestEmployeeNo);


	

}
