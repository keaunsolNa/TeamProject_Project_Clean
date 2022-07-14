package com.project.clean.model.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.AdminAddress;

@Repository
public interface AdminAddressRepository extends JpaRepository<AdminAddress, Integer> {

	AdminAddress findByAdminNo(int adminNo);

}
