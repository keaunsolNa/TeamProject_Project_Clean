package com.project.clean.model.repository.pay;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.Admin;

public interface AdminRepositoryByPay  extends JpaRepository<Admin, Integer> {

	Admin findByAdminId(String adminId);


}
