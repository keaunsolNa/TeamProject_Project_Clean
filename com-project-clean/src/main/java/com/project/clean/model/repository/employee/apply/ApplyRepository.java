package com.project.clean.model.repository.employee.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;

public interface ApplyRepository extends JpaRepository<ApplyEmployee, Integer>{

	List<ApplyEmployee> findByApplyEmployeeNo(Integer employeeNo);

}
