package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.adminEntity.AdminReason;
import com.project.clean.model.dto.commonDTO.ReasonDTO;

public interface ReasonRepository extends JpaRepository<AdminReason, Integer>{


	List<AdminReason> findByEmployeeNo(int empNo);

	List<AdminReason> findById(int empNo);
	

	


}
