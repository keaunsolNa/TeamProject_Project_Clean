package com.project.clean.model.repository.pay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.AdminPay;

public interface AdminPayRepository extends JpaRepository<AdminPay, Integer>{
	
	// 관리자 급여 계산(insert)
	@Modifying
	@Query(value="INSERT INTO TBL_ADMIN_PAY( PAY_HISTORY_ADMIN_NO, PAY_ADMIN_NO, PAY_ADMIN_DATE, PAY_ADMIN_FINAL_SALARY) VALUES (SEQ_TBL_ADMIN_PAY.NEXTVAL, :payAdminNo, TO_DATE(SYSDATE,'YY-MM-DD'), :payAdminFinalSalary)", nativeQuery=true)
	void modifyAdminPay(int payAdminNo, int payAdminFinalSalary);

	
}
