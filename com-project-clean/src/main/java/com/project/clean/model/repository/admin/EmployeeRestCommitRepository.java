package com.project.clean.model.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.adminEntity.AdminEmployeeRestCommit;

public interface EmployeeRestCommitRepository extends JpaRepository<AdminEmployeeRestCommit, Integer>{

	AdminEmployeeRestCommit findByEmployeeNo(int employeeNo);


}
