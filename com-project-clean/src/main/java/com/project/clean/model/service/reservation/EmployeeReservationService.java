package com.project.clean.model.service.reservation;

import java.sql.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.BookMark;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.Notification;
import com.project.clean.model.domain.joinEntity.ApplyEmployeeAndReservationInfo;
import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.BookMarkDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.NotificationDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.ApplyEmployeeAndReservationInfoDTO;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeAndReservationInfoRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeRepository;
import com.project.clean.model.repository.reservation.BookMarkRepository;
import com.project.clean.model.repository.reservation.NotificationRepository;
import com.project.clean.model.repository.reservation.ReservationRepository;



@Service
public class EmployeeReservationService {

	private final ReservationRepository reservationRepository;
	private final EmpRepository empRepository;
	private final ApplyEmployeeRepository applyEmployeeRepository;
	private final BookMarkRepository bookMarkRepository;
	private final ApplyEmployeeAndReservationInfoRepository applyEmployeeAndReservationInfoRepository;
	private final NotificationRepository notificationRepository;
	private final AdminRepository adminRepository;

	private final ModelMapper modelMapper;

	@Autowired
	EmployeeReservationService(ReservationRepository reservationRepository, EmpRepository empRepository,
			ApplyEmployeeRepository applyEmployeeRepository, BookMarkRepository bookMarkRepository,
			ApplyEmployeeAndReservationInfoRepository applyEmployeeAndReservationInfoRepository,
			NotificationRepository notificationRepository, AdminRepository adminRepository, ModelMapper modelMapper) {
		super();
		this.reservationRepository = reservationRepository;
		this.empRepository = empRepository;
		this.applyEmployeeRepository = applyEmployeeRepository;
		this.bookMarkRepository = bookMarkRepository;
		this.applyEmployeeAndReservationInfoRepository = applyEmployeeAndReservationInfoRepository;
		this.notificationRepository = notificationRepository;
		this.adminRepository = adminRepository;
		this.modelMapper = modelMapper;
	}

	/* ?????? ?????? ?????? */
	@Transactional
	public void insertNewReservation(ReservationInfoDTO newReservation) {
		reservationRepository.save(modelMapper.map(newReservation, Reservation.class));
	}

	public List<String> findDistinctByBusinessDate() {
		/* ????????? 00??? ????????? select ???????????? ??? */
		List<String> date = reservationRepository.findDistinctByBusinessDate();
		return date;
	}

	public int selectAbleCount(String date) {
		long count = reservationRepository.countByBusinessDate(date, "N");
		return (int) count;
	}

	public int selectUnableCount(String date) {
		long count = reservationRepository.countByBusinessDate(date, "Y");
		return (int) count;
	}

	/* ??????????????? ?????? ??? ?????? ?????? ????????? ????????? ?????? */
	public List<ReservationInfoDTO> findReservationByBusinessDate(Date businessDate, SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;			// Pageble????????? ????????? ???????????? 0?????? ??????(1???????????? 0)
		int count = selectCriteria.getLimit();
		Pageable paging = PageRequest.of(index, count, Sort.by("businessStartTime"));	
		List<Reservation> reservationList = reservationRepository.findByBusinessDate(businessDate, paging);
		return reservationList.stream().map(reservation -> modelMapper.map(reservation, ReservationInfoDTO.class)).toList();
	}

	/* ?????? ?????? ?????? select */
	public ReservationInfoDTO findReservationByReservationNo(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		return modelMapper.map(reservation, ReservationInfoDTO.class);
	}

	/* ?????? ????????? ???????????? ?????? ?????? ?????? */
	public EmployeeDTO findByEmployeeId(String employeeId) {
		Employee emp = empRepository.findByEmployeeId(employeeId);
		return modelMapper.map(emp, EmployeeDTO.class);
	}

	/* ?????? ?????? insert */
	@Transactional
	public void insertNewApply(ApplyEmployeeDTO newApply) {
		applyEmployeeRepository.save(modelMapper.map(newApply, ApplyEmployee.class));
	}

	/* ????????? ???????????? +1 */
	@Transactional
	public void modifyReservationApplyPeoplePlus(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() + 1);
	}

	/* ?????? = ???????????? ??? ???????????? update */
	@Transactional
	public void modifyReservationApplyEndYnByY(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setApplyEndYn("Y");
	}

	public List<ApplyEmployeeDTO> findAllByApplyReservationNo(int reservationNo) { 
		List<ApplyEmployee> applyEmployeeList = applyEmployeeRepository.findAllByApplyReservationNo(reservationNo);
		return applyEmployeeList.stream().map(app -> modelMapper.map(app, ApplyEmployeeDTO.class)).toList(); 
	}

	/* ?????? ??????????????? ??????????????? update */
	@Transactional
	public void modifyApplyEmployeeApplyCancelYn(int reservationNo, int employeeNo) {
		ApplyEmployee app = applyEmployeeRepository.findByApplyEmployeeNoAndApplyReservationNo(reservationNo, employeeNo);
		app.setApplyCancelYn("Y");
		app.setCheckEmployeeYn("N");
	}
	 
	 /* ??????????????? ???????????? -1 */
	@Transactional
	public void modifyReservationApplyPeopleMinus(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() - 1);
	}
	
	/* ?????? ????????? ?????? ?????? N?????? ?????? */
	@Transactional
	public void modifyReservationApplyEndYnByN(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setApplyEndYn("N");
	}

	/* ???????????? ????????? ???????????? ????????? select */
	public BookMarkDTO findByBookmarkEmployeeNoAndBookmarkReservationNo(int employeeNo, int reservationNo) {
		BookMark book = bookMarkRepository.findByBookmarkEmployeeNoAndBookmarkReservationNo(employeeNo, reservationNo);
		if(book == null) {
			BookMark book2 = new BookMark();
			return modelMapper.map(book2, BookMarkDTO.class);
		}
		return modelMapper.map(book, BookMarkDTO.class);
	}

	/* ???????????? ?????? */
	@Transactional
	public void insertNewBookmark(BookMarkDTO newBookmark) {
		bookMarkRepository.save(modelMapper.map(newBookmark, BookMark.class));
	}

	/* ???????????? ?????? */
	@Transactional
	public void modifyBookmarkCancelByY(int employeeNo, int reservationNo) {
		BookMark book = bookMarkRepository.findByBookmarkEmployeeNoAndBookmarkReservationNo(employeeNo, reservationNo);
		book.setBookmarkCancelYn("Y");
	}
	/* ???????????? ????????? */
	@Transactional
	public void modifyBookmarkCancelByN(int employeeNo, int reservationNo) {
		BookMark book = bookMarkRepository.findByBookmarkEmployeeNoAndBookmarkReservationNo(employeeNo, reservationNo);
		book.setBookmarkCancelYn("N");
	}

	/* ?????? ?????? */
	@Transactional
	public void insertNewNotificationMessage(NotificationDTO newNotification) {
		notificationRepository.save(modelMapper.map(newNotification, Notification.class));
	}

	public int selectTotalCount(Date businessDate) {
		int count = (int)reservationRepository.countByBusinessDate(businessDate);
		return count;
	}

	public int selectTotalCount(int employeeNo) {
		String n = "N";
		int count = (int)applyEmployeeRepository.countByApplyEmployeeNoAndApplyCancelYn(employeeNo, n);
		return count;
	}

	public List<ApplyEmployeeAndReservationInfoDTO> findByApplyEmployeeNo(int employeeNo,
			SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;			
		int count = selectCriteria.getLimit();
		
		Sort.Order Order = Sort.Order.desc("applyReservationNo.businessStartTime");
		Sort sort = Sort.by(Order);
		Pageable paging = PageRequest.of(index, count, sort);
		
		List<ApplyEmployeeAndReservationInfo> reservationList = applyEmployeeAndReservationInfoRepository.findAllByApplyEmployeeNoAndApplyCancelYn(employeeNo, "N", paging);
		System.out.println("reservationList" + reservationList);
		
		return reservationList.stream().map(reservation -> modelMapper.map(reservation, ApplyEmployeeAndReservationInfoDTO.class)).toList();
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

	/* ????????? ?????? ?????? */
	public List<AdminDTO> findAllAdmin() {

		List<Admin> adminList = adminRepository.findAll();
		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).toList();
	}

	public ApplyEmployeeDTO findByApplyReservationNoAndApplyCancelYn(int applyReservationNo, String string) {
		ApplyEmployee app = applyEmployeeRepository.findByApplyReservationNoAndApplyCancelYn(applyReservationNo, string);
		return modelMapper.map(app, ApplyEmployeeDTO.class);
	}
	
}
