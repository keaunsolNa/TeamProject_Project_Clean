package com.project.clean.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.joinEntity.EmployeeAndAdminMemberAuthority;

public interface CommonEmployeeLoginRepository extends JpaRepository<EmployeeAndAdminMemberAuthority, String>{

	EmployeeAndAdminMemberAuthority findByEmployeeId(String userId);

}
