package com.project.clean.model.service.reservation;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.BookMark;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.Notification;
import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.NotificationDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeRepository;
import com.project.clean.model.repository.reservation.BookMarkRepository;
import com.project.clean.model.repository.reservation.NotificationRepository;
import com.project.clean.model.repository.reservation.ReservationRepository;

@Service
public class EmployeeNotificationService {
	
	private final ReservationRepository reservationRepository;
	private final EmpRepository empRepository;
	private final ApplyEmployeeRepository applyEmployeeRepository;
	private final BookMarkRepository bookMarkRepository;
	private final NotificationRepository notificationRepository;

	private final ModelMapper modelMapper;
	
	@Autowired
	public EmployeeNotificationService(ReservationRepository reservationRepository, EmpRepository empRepository,
			ApplyEmployeeRepository applyEmployeeRepository, BookMarkRepository bookMarkRepository,
			NotificationRepository notificationRepository, ModelMapper modelMapper) {
		super();
		this.reservationRepository = reservationRepository;
		this.empRepository = empRepository;
		this.applyEmployeeRepository = applyEmployeeRepository;
		this.bookMarkRepository = bookMarkRepository;
		this.notificationRepository = notificationRepository;
		this.modelMapper = modelMapper;
	}
	
	
	/* 직원 번호 select */
	public EmployeeDTO findByEmployeeId(String employeeId) {
		Employee emp = empRepository.findByEmployeeId(employeeId);
		return modelMapper.map(emp, EmployeeDTO.class);
	}

	public List<NotificationDTO> findAllByNotificationEmployeeNo(int employeeNo, SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;			
		int count = selectCriteria.getLimit();
		
		Sort.Order Order = Sort.Order.desc("notificationCreateTime");
		Sort sort = Sort.by(Order);
		Pageable paging = PageRequest.of(index, count, sort);
		List<Notification> notificationList = notificationRepository.findAllByNotificationEmployeeNo(employeeNo, paging);
		
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


	public int selectTotalCount(int employeeNo) {
		int count = (int)notificationRepository.countByNotificationEmployeeNo(employeeNo);
		return count;
	}


	public ReservationInfoDTO findReservation(int notificationReservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(notificationReservationNo);
		return modelMapper.map(reservation, ReservationInfoDTO.class);
	}

	public List<NotificationDTO> findAllByNotificationEmployeeNoAndNotificationAdminYn(int employeeNo, String string) {
		
		List<Notification> notificationList = notificationRepository.findAllByNotificationEmployeeNoAndNotificationAdminYn(employeeNo, string);
		return notificationList.stream().map(noti -> modelMapper.map(noti, NotificationDTO.class)).toList();
	}


}
