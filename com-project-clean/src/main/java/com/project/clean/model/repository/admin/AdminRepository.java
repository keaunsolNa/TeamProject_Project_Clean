package com.project.clean.model.repository.admin;

import org.springframework.data.repository.CrudRepository;

import com.project.clean.model.domain.commonEntity.Admin;

public interface AdminRepository extends CrudRepository<Admin, String>{

	Admin findByAdminPhone(String userPhone);


}
