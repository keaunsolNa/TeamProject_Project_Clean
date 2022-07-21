package com.project.clean.model.repository.pay;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.ReservationInfo;

public interface ReservationInfoRepositoryByPay extends JpaRepository<ReservationInfo, Integer>{
	

	
}
