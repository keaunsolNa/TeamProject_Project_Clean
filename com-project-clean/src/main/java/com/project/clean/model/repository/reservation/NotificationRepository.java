package com.project.clean.model.repository.reservation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.clean.model.domain.commonEntity.Notification;
import com.project.clean.model.dto.joinDTO.NotificationAndResercationInfoDTO;

public interface NotificationRepository extends JpaRepository<Notification, Object>{

	Notification findByNotificationNo(int notificationNo);

	int countByNotificationEmployeeNo(int employeeNo);

	List<Notification> findAllByNotificationEmployeeNo(int employeeNo, Pageable paging);

	int countByNotificationAdminNoAndNotificationAdminYn(int adminNo, String string);

	List<Notification> findAllByNotificationAdminNoAndNotificationAdminYn(int adminNo, String y, Pageable paging);

	List<Notification> findAllByNotificationAdminNoAndNotificationAdminYn(int adminNo, String string);

	List<Notification> findAllByNotificationEmployeeNoAndNotificationAdminYn(int employeeNo, String string);


}
