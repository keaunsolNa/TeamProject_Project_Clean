package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.adminEntity.AdminReason;

public interface ReasonRepository extends JpaRepository<AdminReason, Integer>{

	List<AdminReason> findAllByEmployeeNo(int empNo, Sort sort);

	

	


}
