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
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
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

	/* 직원 지원 취소 시키기 */
	@Transactional
	public void modifyApplyEmployeeCancel(int employeeNo, int reservationNo) {
		ApplyEmployee app = applyEmployeeRepository.findByApplyEmployeeNoAndApplyReservationNo(reservationNo, employeeNo);
		app.setApplyCancelYn("Y");
		app.setCheckEmployeeYn("N");
	}

	/* 직원 패널티 부여 */
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

	/* 지원인원 -1 , 지원마감 여부 "N"으로 변경 */
	@Transactional
	public void modifyApplyPeopleAndApplyEndYn(int reservationNo) {
		Reservation reservation = adminReservationRepository.findByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() - 1);
		reservation.setApplyEndYn("N");
	}

	/* 직원 추가 시키기 */
	@Transactional
	public void insertNewApply(ApplyEmployeeDTO newApply) {
		applyEmployeeRepository.save(modelMapper.map(newApply, ApplyEmployee.class));		
	}

	/* 지원시 지원인원 +1 */
	@Transactional
	public void modifyReservationApplyPeoplePlus(int reservationNo) {
		Reservation reservation = adminReservationRepository.findByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() + 1);
	}

	/* 정원 = 지원인원 시 지원마감 update */
	@Transactional
	public void modifyReservationApplyEndYnByY(int reservationNo) {
		Reservation reservation = adminReservationRepository.findByReservationNo(reservationNo);
		reservation.setApplyEndYn("Y");		
	}

	/* 관리자 번호 가져오기 */
	public AdminDTO findByAdminId(String adminId) {
		Admin admin = adminRepository.findByAdminId(adminId);
		
		return modelMapper.map(admin, AdminDTO.class);
	}

	/* 예약일정에 추가/삭제 된 대한 직원에게 알림 전송 */
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
