package com.project.clean.model.repository.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.model.domain.commonEntity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByAdminPhone(String userPhone);

	Admin findByAdminId(String adminId);
	
	/* 재직 중인 관리자 조회 */
	@Query(value="SELECT * FROM TBL_ADMIN a where a.admin_retire_yn = 'N' ORDER BY a.admin_no", nativeQuery = true)
	List<Admin> findAdminByAdminRetireN();

	@Query(value = "SELECT\r\n"
				 + "       A.*\r\n"
				 + "  FROM TBL_ADMIN A\r\n"
				 + "  JOIN TBL_REASON B ON (A.ADMIN_NO = B.ADMIN_NO)\r\n"
				 + " WHERE B.EMPLOYEE_NO = ?1\r\n"
				 + " ORDER BY A.ADMIN_NO DESC", nativeQuery = true)
		
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

	/* 관리자 수정을 위한 조회 */
	@Query(value="SELECT * FROM tbl_admin a WHERE admin_no = ?", nativeQuery = true)
	Admin modifyAdmin(int adminNo);

	/* 관리자 테이블 재직여부를 Y로 변경 */
	@Transactional
	@Modifying
	@Query(value="UPDATE TBL_ADMIN a SET a.admin_retire_yn = 'Y' WHERE a.admin_no = ?", nativeQuery = true)
	Integer findByAdminRetire(int adminNo);

	/* 관리자 테이블 퇴사일 sysdate로 지정 */
	@Transactional
	@Modifying
	@Query(value="UPDATE TBL_ADMIN a SET admin_retire_date = sysdate WHERE admin_no = ?", nativeQuery = true)
	Integer findByAdminRetireDate(int adminNo);

}
