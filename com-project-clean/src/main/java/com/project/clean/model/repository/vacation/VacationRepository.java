package com.project.clean.model.repository.vacation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.Vacation;

public interface VacationRepository extends JpaRepository<Vacation, Integer>{

	public Page<Vacation> findByAdminNo(int adminNo, Pageable pageable);

	public Vacation findByVacationNo(int vacationNo, Sort sort);

	public Page<Vacation> findByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationLastConfirmYnAndVacationReturnYn(
			String firstConfirm, String secondConfirm, String lastConfirm, String returnUn, Pageable pageable);
	
	public Page<Vacation> findByVacationFirstConfirmYnOrVacationSecondConfirmYnOrVacationLastConfirmYnOrVacationReturnYn(
			String firstConfirm, String secondConfirm, String lastConfirm, String returnUn, Pageable pageable);
	
	
}

