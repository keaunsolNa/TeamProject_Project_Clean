package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByAdminPhone(String userPhone);

	Admin findByAdminId(String adminId);

	@Query(value = "SELECT\n"
				 + "       A.ADMIN_NO\n"
				 + "     , A.ADMIN_NAME\n"
				 + "     , A.ADMIN_ID\n"
				 + "     , A.ADMIN_PWD\n"
				 + "     , A.ADMIN_BIRTH\n"
				 + "     , A.ADMIN_GENDER\n"
				 + "     , A.ADMIN_PHONE\n"
				 + "     , A.ADMIN_HIRE_DATE\n"
				 + "     , A.ADMIN_RETIRE_DATE\n"
				 + "     , A.ADMIN_RETIRE_YN\n"
				 + "     , A.ADMIN_JOB\n"
				 + "     , A.ADMIN_LAST_LOGIN_DATE\n"
				 + "     , A.ADMIN_SIGN\n"
				 + "     , A.ADMIN_SALARY\n"
				 + "     , A.ADMIN_USE_ANNUAL_VACATION\n"
				 + "  FROM TBL_ADMIN A\n"
				 + "  JOIN TBL_REASON B ON (A.ADMIN_NO = B.ADMIN_NO)\n"
				 + " WHERE EMPLOYEE_NO = ?1\n"
				 + " ORDER BY ADMIN_NO DESC", nativeQuery = true)
		
	List<Admin> findByAdminName(int empNo);


}
