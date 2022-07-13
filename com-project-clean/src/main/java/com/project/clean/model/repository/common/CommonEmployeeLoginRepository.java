package com.project.clean.model.repository.Common;

import org.springframework.data.repository.CrudRepository;

import com.project.clean.model.domain.joinEntity.EmployeeAndAdminMemberAuthority;

public interface CommonEmployeeLoginRepository extends CrudRepository<EmployeeAndAdminMemberAuthority, String>{

	EmployeeAndAdminMemberAuthority findByEmployeeIdAndEmployeeRetireYn(String userId, String string);

}
