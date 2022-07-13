package com.project.clean.model.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.adminEntity.AdminEmployeeEmail;

public interface EmployeeEmailRepository extends JpaRepository<AdminEmployeeEmail, Integer>{

}
