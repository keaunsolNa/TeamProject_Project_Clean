package com.project.clean.model.repository.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.adminEntity.AdminEmployeePicture;

public interface EmployeePictureRepository extends JpaRepository<AdminEmployeePicture, Integer>{

}