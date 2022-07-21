package com.project.clean.model.repository.pay;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;

public interface ApplyEmployeeRepositoryByPay  extends JpaRepository<ApplyEmployee, Integer> {


	List<ApplyEmployee> findByApplyReservationNo(Integer valueOf);


}
