package com.project.clean.model.repository.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByAdminPhone(String userPhone);

	Admin findByAdminId(String adminId);
	
	/* 재직 중인 관리자 조회 */
	@Query(value="SELECT * FROM TBL_ADMIN a where a.admin_retire_yn = 'N' ORDER BY a.admin_no", nativeQuery = true)
	List<Admin> findAdminByAdminRetireN();

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
	
	/* 관리자 상세 조회 */
	Optional<Admin> findByAdminNo(int adminNo);

	/* 신규 관리자 등록을 위한 기본 정보(신규 관리자 번호) 조회 */
	@Query(value="SELECT MAX(ADMIN_NO) FROM TBL_ADMIN", nativeQuery = true)
	int findMaxAdmin();

	/* 신규 관리자 등록을 위해 저장된 관리자 번호 조회 */
	@Query(value="SELECT MAX(ADMIN_NO) FROM TBL_ADMIN", nativeQuery = true)
	int findNewAdminNo();

	/* 관리자 퇴사 처리(RetireYN N -> Y) */
	@Query(value="UPDATE tbl_admin a SET  a.admin_retire_yn = 'Y' WHERE admin_no = 3", nativeQuery = true)
	void modifyAdminRetire(Admin map);

	/* 재직자 Ajax 조회 */
	@Query(value="SELECT * FROM TBL_ADMIN a where a.admin_retire_yn = 'N' ORDER BY a.admin_no", nativeQuery = true)
	List<Admin> findAdminList();

	
	

}