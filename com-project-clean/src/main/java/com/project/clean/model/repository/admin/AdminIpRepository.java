package com.project.clean.model.repository.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.AdminIpAddress;

@Repository
public interface AdminIpRepository extends CrudRepository<AdminIpAddress, String> {
	



}
