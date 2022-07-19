package com.project.clean.model.repository.reservation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.clean.model.domain.joinEntity.BookMarkAndReservationInfo;
import com.project.clean.model.domain.joinEntity.NotificationAndReservationInfo;

public interface NotificationAndReservationInfoRepository extends JpaRepository<NotificationAndReservationInfo, Object> {



}

