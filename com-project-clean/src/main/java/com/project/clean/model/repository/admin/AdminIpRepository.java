package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.AdminIpAddress;

@Repository
public interface AdminIpRepository extends CrudRepository<AdminIpAddress, Integer> {

	List<AdminIpAddress> findAllByAdminNo(int adminNo);
	



}
