package com.project.clean.model.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.AdminAddress;


public interface AdminAddressRepository extends JpaRepository<AdminAddress, Integer> {

	AdminAddress findByAdminNo(int adminNo);

}
