package com.project.clean.model.repository.Common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.joinEntity.AdminAndAdminMemberAuthority;
import com.project.clean.model.domain.joinEntity.EmployeeAndAdminMemberAuthority;

public interface CommonAdminLoginRepository extends JpaRepository<AdminAndAdminMemberAuthority, String>{

	AdminAndAdminMemberAuthority findByAdminId(String userId);

}
