package com.project.clean.model.repository.vacation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.joinEntity.AdminVacation;

public interface AdminVacationRepository extends JpaRepository<AdminVacation, Integer>{

//	List<AdminVacation> findByAdminNoAndVacationFirstConfirmYnAndVacationReturnYnAndAdminRetireYn(String string,
//			String string2, String string3);

}
