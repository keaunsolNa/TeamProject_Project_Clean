package com.project.clean.model.repository.common;

import org.springframework.data.repository.CrudRepository;

import com.project.clean.model.domain.joinEntity.EmployeeAndAdminMemberAuthority;

public interface CommonEmployeeLoginRepository extends CrudRepository<EmployeeAndAdminMemberAuthority, String>{

	EmployeeAndAdminMemberAuthority findByEmployeeIdAndEmployeeRetireYn(String userId, String RetireYn);

}
