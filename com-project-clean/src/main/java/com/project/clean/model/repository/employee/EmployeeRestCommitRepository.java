package com.project.clean.model.repository.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.adminEntity.AdminEmployeeRestCommit;

public interface EmployeeRestCommitRepository extends JpaRepository<AdminEmployeeRestCommit, Integer>{


	List<AdminEmployeeRestCommit> findByEmployeeNo(int empNo);


}
