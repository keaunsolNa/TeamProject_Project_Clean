package com.project.clean.model.service.reservation;

import java.sql.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.SupplementService;
import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.commonDTO.SupplementServiceDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeRepository;
import com.project.clean.model.repository.reservation.ReservationRepository;
import com.project.clean.model.repository.reservation.SupplementServiceRepository;



@Service
public class EmployeeReservationService {

	private final ReservationRepository reservationRepository;
	private final SupplementServiceRepository supplementServiceRepository;
	private final EmpRepository empRepository;
	private final ApplyEmployeeRepository applyEmployeeRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public EmployeeReservationService(ReservationRepository reservationRepository,
			SupplementServiceRepository supplementServiceRepository, EmpRepository empRepository,
			ApplyEmployeeRepository applyEmployeeRepository, ModelMapper modelMapper) {
		super();
		this.reservationRepository = reservationRepository;
		this.supplementServiceRepository = supplementServiceRepository;
		this.empRepository = empRepository;
		this.applyEmployeeRepository = applyEmployeeRepository;
		this.modelMapper = modelMapper;
	}

	/* 고객 신규 예약 */
	@Transactional
	public void insertNewReservation(ReservationInfoDTO newReservation) {
		reservationRepository.save(modelMapper.map(newReservation, Reservation.class));
	}

	/* 신규 예약의 부가서비스 */
	@Transactional
	public void insertNewSupplementService(SupplementServiceDTO newSupplementService) {
		int lastSequence = reservationRepository.findLastSequence();
		newSupplementService.setServiceReservationNo(lastSequence);
		supplementServiceRepository.save(modelMapper.map(newSupplementService, SupplementService.class));
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

	/* 예약 상세정보에 관련된 부가서비스 조회 select */
	public SupplementServiceDTO findSupplementServiceByServiceReservationNo(int serviceReservationNo) {
		SupplementService service = supplementServiceRepository
				.findSupplementServiceByServiceReservationNo(serviceReservationNo);
		return modelMapper.map(service, SupplementServiceDTO.class);
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
	public void modifyReservationApplyPeople(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setBusinessApplyPeople(reservation.getBusinessApplyPeople() + 1);
	}

	/* 정원 = 지원인원 시 지원마감 update */
	@Transactional
	public void modifyReservationApplyEndYn(int reservationNo) {
		Reservation reservation = reservationRepository.findReservationByReservationNo(reservationNo);
		reservation.setApplyEndYn("Y");
	}

	public List<ApplyEmployeeDTO> findAllByApplyReservationNo(int reservationNo) { 
		List<ApplyEmployee> applyEmployeeList = applyEmployeeRepository.findAllByApplyReservationNo(reservationNo);
		return applyEmployeeList.stream().map(app -> modelMapper.map(app, ApplyEmployeeDTO.class)).toList(); 
	}

	public void modifyApplyEmployeeApplyCancelYn(int employeeNo) {
		// TODO Auto-generated method stub
		
	}
	
}
