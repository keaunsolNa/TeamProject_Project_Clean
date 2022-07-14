package com.project.clean.model.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.adminEntity.AdminReason;

@Repository
public interface ReasonRepository extends JpaRepository<AdminReason, Integer>{


	List<AdminReason> findByEmployeeNo(int empNo);

	List<AdminReason> findById(int empNo);
	

	


}
