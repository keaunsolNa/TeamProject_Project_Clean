package com.project.clean.model.repository.employee.checkList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.CheckList;

public interface CheckListRepository  extends JpaRepository<CheckList, Integer>{

}
