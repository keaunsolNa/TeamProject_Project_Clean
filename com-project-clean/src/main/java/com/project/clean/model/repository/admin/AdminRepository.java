package com.project.clean.model.repository.admin;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.model.domain.commonEntity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByAdminPhone(String userPhone);

	Admin findByAdminId(String adminId);
	
	/* 재직 중인 관리자 조회 */
//	@Query(value="SELECT * FROM TBL_ADMIN a where a.admin_retire_yn = 'N' ORDER BY a.admin_no", nativeQuery = true)
//	List<Admin> findAdminByAdminRetireN();

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
	@Query(value="UPDATE tbl_admin a SET  a.admin_retire_yn = 'Y' WHERE admin_no = ?", nativeQuery = true)
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

	/* 퇴사자 조회 */
//	@Query(value="SELECT * FROM TBL_ADMIN a WHERE a.admin_retire_yn = 'Y' ORDER BY a.admin_no", nativeQuery = true)
//	List<Admin> findAdminByAdminRetireY();

	/* 최종 승인된 휴가가 연차일 경우 연차 사용 내역 업데이트 */
	@Query(value="UPDATE tbl_admin a SET a.admin_use_annual_vacation = a.admin_use_annual_vacation + 1 WHERE a.admin_no = ?", nativeQuery = true)
	@Transactional
	@Modifying
	void modifyAnnualVacationUse(int adminNo);

	
	/* ---------------------------------------------------------------------------------- */
	
	/* 재직 관리자 목록 조회 */
	List<Admin> findAllByAdminRetireYn(String string);

	/* 재직 관리자 이름 검색 */
	List<Admin> findAllByAdminRetireYnAndAdminNameContaining(String string, String searchValue, Pageable paging);

	/* 재직 관리자 생년월일 검색 */
	List<Admin> findAllByAdminRetireYnAndAdminBirth(String string, Date adminBirth, Pageable paging);

	/* 재직 관리자 입사일 검색 */
	List<Admin> findAllByAdminRetireYnAndAdminHireDate(String string, Date adminHireDate, Pageable paging);

	/* 재직 관리자 직책명 검색 */
	List<Admin> findAllByAdminRetireYnAndAdminJob(String string, String searchValue, Pageable paging);

	/* 재직 관리자 전화번호 검색 */
	List<Admin> findAllByAdminRetireYnAndAdminPhoneContaining(String string, String searchValue, Pageable paging);

	/* 재직자 이름 검색 페이징 */
	int countByAdminRetireYnAndAdminNameContaining(String string, String searchValue);

	/* 재직자 생년월일 페이징 */
	int countByAdminRetireYnAndAdminBirth(String string, Date adminBirth);

	/* 재직자 입사일 페이징 */
	int countByAdminRetireYnAndAdminHireDate(String string, Date adminHireDate);

	/* 재직자 직책명 페이징 */
	int countByAdminRetireYnAndAdminJob(String string, String searchValue);

	/* 재직자 휴대전화 번호 페이징 */
	int countByAdminRetireYnAndAdminPhoneContaining(String string, String searchValue);

	/* 전체 재직자 페이징 */
	int countByAdminRetireYn(String string);

	/* 퇴사자 퇴사일 검색 */
	List<Admin> findAllByAdminRetireYnAndAdminRetireDate(String string, Date adminRetireDate, Pageable paging);

	/* 퇴사자 페이징 */
	int countByAdminRetireYnAndAdminRetireDate(String string, Date adminRetireDate);


	/* 휴가 승인한 최종 관리자의 정보 불러오기 */
	@Query(value="SELECT * FROM tbl_admin a WHERE a.admin_no = ?", nativeQuery = true)
	Admin findByCommitAdmin(int bossNo);

}
