package com.project.clean.model.service.reservation;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Notification;
import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.NotificationDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeRepository;
import com.project.clean.model.repository.reservation.BookMarkRepository;
import com.project.clean.model.repository.reservation.NotificationRepository;
import com.project.clean.model.repository.reservation.ReservationRepository;

@Service
public class AdminNotificationService {
	
	private final ReservationRepository reservationRepository;
	private final EmpRepository empRepository;
	private final NotificationRepository notificationRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	AdminNotificationService(ReservationRepository reservationRepository, EmpRepository empRepository,
			NotificationRepository notificationRepository, ModelMapper modelMapper) {
		super();
		this.reservationRepository = reservationRepository;
		this.empRepository = empRepository;
		this.notificationRepository = notificationRepository;
		this.modelMapper = modelMapper;
	}
	

	public int selectTotalCount(int adminNo) {
		
		int count = (int)notificationRepository.countByNotificationAdminNoAndNotificationAdminYn(adminNo, "Y");
		return count;
	}


	public List<NotificationDTO> findAllByNotificationAdminNoAndNotificationAdminYn(int adminNo, String y,
			SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;			
		int count = selectCriteria.getLimit();
		
		Sort.Order Order = Sort.Order.desc("notificationCreateTime");
		Sort sort = Sort.by(Order);
		Pageable paging = PageRequest.of(index, count, sort);
		List<Notification> notificationList = notificationRepository.findAllByNotificationAdminNoAndNotificationAdminYn(adminNo, y, paging);
		
		return notificationList.stream().map(noti -> modelMapper.map(noti, NotificationDTO.class)).toList();
	}


	public NotificationDTO findByNotificationNo(int notificationNo) {
		Notification noti = notificationRepository.findByNotificationNo(notificationNo);
		return modelMapper.map(noti, NotificationDTO.class);
	}

	@Transactional
	public void modifyNotificationReadYn(int notificationNo, String string) {
		Notification noti = notificationRepository.findByNotificationNo(notificationNo);
		noti.setNotificationReadYn(string);
	}


	public ReservationInfoDTO findReservation(int notificationReservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(notificationReservationNo);
		return modelMapper.map(reservation, ReservationInfoDTO.class);
	}


	public List<NotificationDTO> findAllByNotificationAdminNoAndNotificationAdminYn(int adminNo, String string) {
		
		List<Notification> notificationList = notificationRepository.findAllByNotificationAdminNoAndNotificationAdminYn(adminNo, string);
		return notificationList.stream().map(noti -> modelMapper.map(noti, NotificationDTO.class)).toList();
	}

	

}
