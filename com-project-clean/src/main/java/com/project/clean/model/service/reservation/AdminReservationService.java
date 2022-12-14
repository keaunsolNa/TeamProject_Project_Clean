package com.project.clean.model.service.reservation;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.Notification;
import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.NotificationDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.reservation.AdminReservationRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeRepository;
import com.project.clean.model.repository.reservation.BookMarkRepository;
import com.project.clean.model.repository.reservation.NotificationRepository;

@Service
public class AdminReservationService {
	

	private final AdminReservationRepository adminReservationRepository;
	private final ApplyEmployeeRepository applyEmployeeRepository;
	private final BookMarkRepository bookMarkRepository;
	private final EmpRepository empRepository;
	private final AdminRepository adminRepository;
	private final NotificationRepository notificationRepository;

	private final ModelMapper modelMapper;
	
	@Autowired
	AdminReservationService(AdminReservationRepository adminReservationRepository,
			ApplyEmployeeRepository applyEmployeeRepository, BookMarkRepository bookMarkRepository,
			EmpRepository empRepository, AdminRepository adminRepository, NotificationRepository notificationRepository,
			ModelMapper modelMapper) {
		super();
		this.adminReservationRepository = adminReservationRepository;
		this.applyEmployeeRepository = applyEmployeeRepository;
		this.bookMarkRepository = bookMarkRepository;
		this.empRepository = empRepository;
		this.adminRepository = adminRepository;
		this.notificationRepository = notificationRepository;
		this.modelMapper = modelMapper;
	}
	
	public List<String> findDistinctByBusinessDate() {
		
		List<String> date = adminReservationRepository.findDistinctByBusinessDate();
		return date;
	}


	public int selectAbleCount(String date) {
		long count = adminReservationRepository.countByBusinessDate(date, "N");
		return (int) count;
	}

	public int selectUnableCount(String date) {
		long count = adminReservationRepository.countByBusinessDate(date, "Y");
		return (int) count;
	}

	public int selectTotalCount(Date businessDate) {
		int count = (int)adminReservationRepository.countByBusinessDate(businessDate);
		return count;
	}

	public List<ReservationInfoDTO> findReservationByBusinessDate(Date businessDate, SelectCriteria selectCriteria) {
		int index = selectCriteria.getPageNo() - 1;			// Pageble????????? ????????? ???????????? 0?????? ??????(1???????????? 0)
		int count = selectCriteria.getLimit();
		Pageable paging = PageRequest.of(index, count, Sort.by("businessStartTime"));	
		List<Reservation> reservationList = adminReservationRepository.findByBusinessDate(businessDate, paging);
		return reservationList.stream().map(reservation -> modelMapper.map(reservation, ReservationInfoDTO.class)).toList();
	}

	public ReservationInfoDTO findByReservationNo(int reservationNo) {
		Reservation reservation = adminReservationRepository.findByReservationNo(reservationNo);
		return modelMapper.map(reservation, ReservationInfoDTO.class);
	}

	public List<ApplyEmployeeDTO> findAllByApplyReservationNo(int reservationNo) {
		List<ApplyEmployee> applyEmployeeList = applyEmployeeRepository.findAllByApplyReservationNo(reservationNo);
		return applyEmployeeList.stream().map(app -> modelMapper.map(app, ApplyEmployeeDTO.class)).toList(); 
	}

	public EmployeeDTO findByEmployeeNo(int employeeNo) {
		Employee emp = empRepository.findByEmployeeNo(employeeNo);
		if(emp == null) {
			Employee emp2 = new Employee();
			return modelMapper.map(emp2, EmployeeDTO.class);
		}
		return modelMapper.map(emp, EmployeeDTO.class);
	}

	/* ?????? ?????? ?????? ????????? */
	@Transactional
	public void modifyApplyEmployeeCancel(int employeeNo, int reservationNo) {
		ApplyEmployee app = applyEmployeeRepository.findByApplyEmployeeNoAndApplyReservationNo(reservationNo, employeeNo);
		app.setApplyCancelYn("Y");
		app.setCheckEmployeeYn("N");
	}

	/* ?????? ????????? ?????? */
	@Transactional
	public void modifyEmployeePenalty(int employeeNo) {
		Employee emp = empRepository.findByEmployeeNo(employeeNo);
		if(emp.getEmployeeSumCount() == 4) {
			emp.setEmployeeSumCount(emp.getEmployeeSumCount() + 1);
			emp.setEmployeeBlackListYn("Y");
		} else {
			emp.setEmployeeSumCount(emp.getEmployeeSumCount() + 1);
		}
	}

	/* ???????????? -1 , ???????????? ?????? "N"?????? ?????? */
	@Transactional
	public void modifyApplyPeopleAndApplyEndYn(int reservationNo) {
		Reservation reservation = adminReservationRepository.findByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() - 1);
		reservation.setApplyEndYn("N");
	}

	/* ?????? ?????? ????????? */
	@Transactional
	public void insertNewApply(ApplyEmployeeDTO newApply) {
		applyEmployeeRepository.save(modelMapper.map(newApply, ApplyEmployee.class));		
	}

	/* ????????? ???????????? +1 */
	@Transactional
	public void modifyReservationApplyPeoplePlus(int reservationNo) {
		Reservation reservation = adminReservationRepository.findByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() + 1);
	}

	/* ?????? = ???????????? ??? ???????????? update */
	@Transactional
	public void modifyReservationApplyEndYnByY(int reservationNo) {
		Reservation reservation = adminReservationRepository.findByReservationNo(reservationNo);
		reservation.setApplyEndYn("Y");		
	}

	/* ????????? ?????? ???????????? */
	public AdminDTO findByAdminId(String adminId) {
		Admin admin = adminRepository.findByAdminId(adminId);
		
		return modelMapper.map(admin, AdminDTO.class);
	}

	/* ??????????????? ??????/?????? ??? ?????? ???????????? ?????? ?????? */
	@Transactional
	public void applyNewNotificationMessage(NotificationDTO newNotification) {
		notificationRepository.save(modelMapper.map(newNotification, Notification.class));		
	}

	public ApplyEmployeeDTO findByApplyReservationNoAndCancelYn(int reservationNo, String string) {
		ApplyEmployee app = applyEmployeeRepository.findByApplyReservationNoAndApplyCancelYn(reservationNo, string);
		if(app == null) {
			ApplyEmployee app2 = new ApplyEmployee();
			return modelMapper.map(app2, ApplyEmployeeDTO.class);
		}
		return modelMapper.map(app, ApplyEmployeeDTO.class);
	}

	public ApplyEmployeeDTO findByApplyReservationNoAndApplyCancelYn(int reservationNo, String string) {
		ApplyEmployee app = applyEmployeeRepository.findByApplyReservationNoAndApplyCancelYn(reservationNo, string);
		return modelMapper.map(app, ApplyEmployeeDTO.class);
	}

	
}
