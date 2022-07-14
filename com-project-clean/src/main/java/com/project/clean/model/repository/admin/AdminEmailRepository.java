package com.project.clean.model.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.AdminEmail;

@Repository
public interface AdminEmailRepository extends JpaRepository<AdminEmail, Integer> {

	AdminEmail findByAdminNo(int adminNo);

}
