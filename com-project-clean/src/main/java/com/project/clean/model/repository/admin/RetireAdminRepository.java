package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.RetireAdmin;

@Repository
public interface RetireAdminRepository extends JpaRepository<RetireAdmin, Integer>{

	List<RetireAdmin> findRetireAdminByRetireAdminRetireYnNotLike(String string);

	
}
