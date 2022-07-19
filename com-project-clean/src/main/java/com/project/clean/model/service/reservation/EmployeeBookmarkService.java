package com.project.clean.model.service.reservation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.BookMark;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.joinEntity.BookMarkAndReservationInfo;
import com.project.clean.model.domain.reservation.Reservation;
import com.project.clean.model.dto.commonDTO.BookMarkDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.BookMarkAndReservationInfoDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.reservation.ApplyEmployeeRepository;
import com.project.clean.model.repository.reservation.BookMarkAndReservationInfoRepository;
import com.project.clean.model.repository.reservation.BookMarkRepository;
import com.project.clean.model.repository.reservation.ReservationRepository;

@Service
public class EmployeeBookmarkService {

	private final ReservationRepository reservationRepository;
	private final EmpRepository empRepository;
	private final ApplyEmployeeRepository applyEmployeeRepository;
	private final BookMarkRepository bookMarkRepository;
	private final BookMarkAndReservationInfoRepository bookMarkAndReservationInfoRepository;

	private final ModelMapper modelMapper;

	@Autowired
	public EmployeeBookmarkService(ReservationRepository reservationRepository, EmpRepository empRepository,
			ApplyEmployeeRepository applyEmployeeRepository, BookMarkRepository bookMarkRepository,
			BookMarkAndReservationInfoRepository bookMarkAndReservationInfoRepository, ModelMapper modelMapper) {
		super();
		this.reservationRepository = reservationRepository;
		this.empRepository = empRepository;
		this.applyEmployeeRepository = applyEmployeeRepository;
		this.bookMarkRepository = bookMarkRepository;
		this.bookMarkAndReservationInfoRepository = bookMarkAndReservationInfoRepository;
		this.modelMapper = modelMapper;
	}

	public EmployeeDTO findByEmployeeId(String employeeId) {
		
		Employee emp = empRepository.findByEmployeeId(employeeId);
		return modelMapper.map(emp, EmployeeDTO.class);
	}
	

	public int selectTotalCountByBookmarkCancelYn(int employeeNo, String n) {
		int count = (int)bookMarkRepository.countByBookmarkEmployeeNoAndBookmarkCancelYn(employeeNo, n);
		return count;
	}
	
	public List<BookMarkAndReservationInfoDTO> findByBookmarkEmployeeNoAndBookmarkCancelYn(int employeeNo, String n,
			SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;			
		int count = selectCriteria.getLimit();
		Sort.Order Order = Sort.Order.desc("reservationInfoDTO.businessStartTime");
		Sort sort = Sort.by(Order);
		Pageable paging = PageRequest.of(index, count, sort);
		
		List<BookMarkAndReservationInfo> bookList = bookMarkAndReservationInfoRepository.findByBookmarkEmployeeNoAndBookmarkCancelYn(employeeNo, n, paging);
		
		return bookList.stream().map(book -> modelMapper.map(book, BookMarkAndReservationInfoDTO.class)).toList();
	}






	
}
