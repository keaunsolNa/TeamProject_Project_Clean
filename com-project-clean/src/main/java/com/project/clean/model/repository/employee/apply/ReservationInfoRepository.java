package com.project.clean.model.repository.employee.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.ReservationInfo;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfo, Integer>{

	ReservationInfo findByReservationNo(Integer reservationNo);

}
