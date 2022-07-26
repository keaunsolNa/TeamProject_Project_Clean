package com.project.clean.model.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.RetireEmployee;

@Repository
public interface RetireEmployeeRepository extends JpaRepository<RetireEmployee, Integer>{



}
