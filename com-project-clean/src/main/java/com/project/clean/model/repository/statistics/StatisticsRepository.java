package com.project.clean.model.repository.statistics;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.joinEntity.AdminAndAdminPay;

public interface StatisticsRepository extends JpaRepository<Admin, Integer>{

	/* 관리자 총 평균 급여 조회*/
	@Query(value = "SELECT ROUND(AVG(ADMIN_SALARY)) ADMIN_SALARY FROM TBL_ADMIN", nativeQuery = true)
	public int findTotalAvgSalaryWithQuery();

	/* 관리자 직급별 평균 급여 조회 */
	@Query(value = "SELECT ROWNUM AS job_code, job, avg_salary FROM \r\n"
			+ "(SELECT a.ADMIN_JOB as job, AVG(b.pay_admin_final_salary) AS AVG_SALARY\r\n"
			+ "FROM TBL_ADMIN A, TBL_ADMIN_PAY B\r\n"
			+ "WHERE a.admin_no = b.pay_admin_no\r\n"
			+ "GROUP BY ADMIN_JOB)", nativeQuery = true)
	public List<Object[]> findAvgSalaryWithQuery();
//	@Query("select c from TBL_ADMIN_PAY c join fetch c.admin")
//	List<Admin> findAll();

	/* 총 매출 조회 */
	@Query(value = "SELECT SUM(BUSINESS_APPLY_PEOPLE)*ROUND(AVG(TOTAL_PAYMENT)) \r\n"
			+ "	FROM TBL_RESERVATION_INFO where business_date BETWEEN '22/01/01' and '22/12/31'",
			nativeQuery = true)
	public int findTotalSalesWithQuery();

	
	/* 순수익 구하기 */
	@Query(value = "select sum((case when idx=1 then 1 else -1 end) * (sales)) as totalSales \r\n"
			+ "from(\r\n"
			+ "SELECT 1 idx, SUM(BUSINESS_APPLY_PEOPLE)*ROUND(AVG(TOTAL_PAYMENT)) as sales FROM TBL_RESERVATION_INFO\r\n"
			+ "union select 2 idx, SUM(pay_employee_final_salary) from tbl_employee_pay\r\n"
			+ "union select 3 idx, SUM(pay_admin_final_salary) from tbl_admin_pay\r\n"
			+ "union select 4 idx, SUM(surcharge_bonus) from tbl_surcharge\r\n"
			+ ")" , 
			nativeQuery = true)
	public int findNetProfitWithQuery();
	
	/* 직원 평균 누적근무시간 */
	@Query(value = "SELECT ROUND(AVG(EMPLOYEE_SUM_TIME)) EMPLOYEE_SUM_TIME FROM TBL_EMPLOYEE", nativeQuery = true)
	public int findAvgHoursWithQuery();
	
	/* 직원 총 누적근무시간 */
	@Query(value = "SELECT ROUND(SUM(EMPLOYEE_SUM_TIME)) EMPLOYEE_SUM_TIME FROM TBL_EMPLOYEE", nativeQuery = true)
	public int findTotalHoursWithQuery();
	
	/* 직원 근무시간 조회 */
//	@Query(value = "SELECT EMPLOYEE_NO, EMPLOYEE_NAME, EMPLOYEE_ID, EMPLOYEE_HIRE_DATE, EMPLOYEE_SUM_TIME \r\n"
//			+ "  FROM TBL_EMPLOYEE; ")
//	public int findByEmployeeNo();
	
	/* 이달의 우수직원 조회 */
	@Query(value = "select * FROM\r\n"
			+ "(SELECT * FROM TBL_EMPLOYEE ORDER BY EMPLOYEE_SUM_TIME DESC)\r\n"
			+ "where rownum between 1 and 5", nativeQuery = true)
	public List<Employee> findBestEmployeeWithQuery();
}
