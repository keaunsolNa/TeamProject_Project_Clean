package com.project.clean.model.repository.Common;

import org.springframework.data.repository.CrudRepository;

import com.project.clean.model.domain.commonEntity.AdminIpAddress;
import com.project.clean.model.domain.joinEntity.AdminAndAdminMemberAuthority;

public interface CommonAdminLoginRepository extends CrudRepository<AdminAndAdminMemberAuthority, String>{

	AdminAndAdminMemberAuthority findByAdminIdAndAdminRetireYn(String userId, String string);

	AdminIpAddress findAllByAdminNo(int adminNo);

}
	