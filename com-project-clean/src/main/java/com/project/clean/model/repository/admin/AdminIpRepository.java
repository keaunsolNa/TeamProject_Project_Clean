package com.project.clean.model.repository.admin;

import org.springframework.data.repository.CrudRepository;

import com.project.clean.model.domain.commonEntity.AdminIpAddress;

public interface AdminIpRepository extends CrudRepository<AdminIpAddress, String> {

}
