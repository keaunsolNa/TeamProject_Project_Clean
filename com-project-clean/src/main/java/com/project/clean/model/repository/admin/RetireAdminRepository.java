package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.RetireAdmin;

@Repository
public interface RetireAdminRepository extends JpaRepository<RetireAdmin, Integer>{

	@Query(value="SELECT * FROM TBL_RETIRE_ADMIN a ORDER BY retire_admin_no", nativeQuery = true)
	List<RetireAdmin> findRetireAdminList();
}
