package com.project.clean.model.repository.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.SupplementService;

public interface SupplementServiceRepository extends JpaRepository<SupplementService, Integer>   {

	SupplementService findSupplementServiceByServiceReservationNo(int serviceReservationNo);

}
