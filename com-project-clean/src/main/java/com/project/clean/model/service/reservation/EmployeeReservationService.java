package com.project.clean.model.service.reservation;

import java.sql.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.BookMark;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.BookMarkDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeRepository;
import com.project.clean.model.repository.reservation.BookMarkRepository;
import com.project.clean.model.repository.reservation.ReservationRepository;



@Service
public class EmployeeReservationService {

	private final ReservationRepository reservationRepository;
	private final EmpRepository empRepository;
	private final ApplyEmployeeRepository applyEmployeeRepository;
	private final BookMarkRepository bookMarkRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public EmployeeReservationService(ReservationRepository reservationRepository, EmpRepository empRepository,
			ApplyEmployeeRepository applyEmployeeRepository, BookMarkRepository bookMarkRepository,
			ModelMapper modelMapper) {
		super();
		this.reservationRepository = reservationRepository;
		this.empRepository = empRepository;
		this.applyEmployeeRepository = applyEmployeeRepository;
		this.bookMarkRepository = bookMarkRepository;
		this.modelMapper = modelMapper;
	}

	/* 고객 신규 예약 */
	@Transactional
	public void insertNewReservation(ReservationInfoDTO newReservation) {
		reservationRepository.save(modelMapper.map(newReservation, Reservation.class));
	}


	public List<String> findDistinctByBusinessDate() {
		/* 다음날 00시 이후만 select 가능하게 함 */
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

	/* 날짜이벤트 클릭 시 해당 날짜 예약건 리스트 요청 */
	public List<ReservationInfoDTO> findReservationByBusinessDate(Date businessDate) {
		List<Reservation> reservationList = reservationRepository
				.findByBusinessDateOrderByBusinessStartTime(businessDate);
		return reservationList.stream().map(reservation -> modelMapper.map(reservation, ReservationInfoDTO.class))
				.toList();
	}

	/* 예약 상세 정보 select */
	public ReservationInfoDTO findReservationByReservationNo(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		return modelMapper.map(reservation, ReservationInfoDTO.class);
	}

	/* 직원 예약시 아이디로 직원 번호 조회 */
	public EmployeeDTO findByEmployeeId(String employeeId) {
		Employee emp = empRepository.findByEmployeeId(employeeId);
		return modelMapper.map(emp, EmployeeDTO.class);
	}

	/* 직원 지원 insert */
	@Transactional
	public void insertNewApply(ApplyEmployeeDTO newApply) {
		newApply.setApplyCancelYn("N");
		newApply.setCheckEmployeeYn("N");
		applyEmployeeRepository.save(modelMapper.map(newApply, ApplyEmployee.class));
	}

	/* 지원시 지원인원 +1 */
	@Transactional
	public void modifyReservationApplyPeoplePlus(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() + 1);
	}

	/* 정원 = 지원인원 시 지원마감 update */
	@Transactional
	public void modifyReservationApplyEndYnByY(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setApplyEndYn("Y");
	}

	public List<ApplyEmployeeDTO> findAllByApplyReservationNo(int reservationNo) { 
		List<ApplyEmployee> applyEmployeeList = applyEmployeeRepository.findAllByApplyReservationNo(reservationNo);
		return applyEmployeeList.stream().map(app -> modelMapper.map(app, ApplyEmployeeDTO.class)).toList(); 
	}

	/* 예약 지원테이블 지원취소로 update */
	@Transactional
	public void modifyApplyEmployeeApplyCancelYn(int reservationNo, int employeeNo) {
		ApplyEmployee app = applyEmployeeRepository.findByApplyEmployeeNoAndApplyReservationNo(reservationNo, employeeNo);
		app.setApplyCancelYn("Y");
	}
	 
	 /* 예약테이블 지원인원 -1 */
	@Transactional
	public void modifyReservationApplyPeopleMinus(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() - 1);
	}
	
	/* 지원 테이블 마감 여부 N으로 수정 */
	@Transactional
	public void modifyReservationApplyEndYnByN(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setApplyEndYn("N");
	}

	/* 예약건를 본인이 즐겨찾기 했는지 select */
	public BookMarkDTO findByBookmarkEmployeeNoAndBookmarkReservationNo(int employeeNo, int reservationNo) {
		BookMark book = bookMarkRepository.findByBookmarkEmployeeNoAndBookmarkReservationNo(employeeNo, reservationNo);
		if(book == null) {
			BookMark book2 = new BookMark();
			return modelMapper.map(book2, BookMarkDTO.class);
		}
		return modelMapper.map(book, BookMarkDTO.class);
	}

	/* 즐겨찾기 등록 */
	@Transactional
	public void insertNewBookmark(BookMarkDTO newBookmark) {
		bookMarkRepository.save(modelMapper.map(newBookmark, BookMark.class));
	}
	
}