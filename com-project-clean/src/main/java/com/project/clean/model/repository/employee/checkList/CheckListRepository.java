package com.project.clean.model.repository.employee.checkList;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.CheckList;

public interface CheckListRepository  extends JpaRepository<CheckList, Integer>{

	CheckList findBycheckReservationNoAndCheckStatus(Integer reservationNo, String string);

	List<CheckList> findAllByCheckStatus(String string);
	

}
