package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.AdminEmail;

public interface AdminEmailRepository extends JpaRepository<AdminEmail, Integer> {

	AdminEmail findByAdminNo(int adminNo);

}
