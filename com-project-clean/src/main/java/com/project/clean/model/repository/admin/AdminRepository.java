package com.project.clean.model.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

	Admin findByAdminPhone(String userPhone);

	Admin findByAdminId(String adminId);


}
