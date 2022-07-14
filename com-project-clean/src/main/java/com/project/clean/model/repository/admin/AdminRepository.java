package com.project.clean.model.repository.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{

	Admin findByAdminPhone(String userPhone);

	Admin findByAdminId(String adminId);
	
	
	List<Admin> findAdminByAdminRetireYnNotLike(String string);

	Optional<Admin> findByAdminNo(int adminNo);




}
