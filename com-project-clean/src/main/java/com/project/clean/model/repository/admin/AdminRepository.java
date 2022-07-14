package com.project.clean.model.repository.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

	Admin findByAdminPhone(String userPhone);

	Admin findByAdminId(String adminId);
	
	/* 재직 중인 관리자 조회 */
	@Query(value="SELECT * FROM TBL_ADMIN a where a.admin_retire_yn = 'N' ORDER BY a.admin_no", nativeQuery = true)
	List<Admin> findAdminByAdminRetireN();

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
