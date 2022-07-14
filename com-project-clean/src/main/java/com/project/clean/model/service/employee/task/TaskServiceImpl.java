package com.project.clean.model.service.employee.task;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.ReservationInfo;
import com.project.clean.model.domain.joinEntity.ApplyEmployeeEmbedded;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.employee.apply.ApplyRepository;
import com.project.clean.model.repository.employee.apply.ReservationInfoRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

	private final EmpRepository empRepository;
	private final ApplyRepository applyRepository;
	private final ModelMapper modelMapper;
	private final ReservationInfoRepository reservationInfoRepository;
	
	@Autowired
	public TaskServiceImpl(EmpRepository empRepository,  ApplyRepository applyRepository, ModelMapper modelMapper, ReservationInfoRepository reservationInfoRepository) {
		this.empRepository = empRepository;
		this.applyRepository = applyRepository;
		this.modelMapper = modelMapper;
		this.reservationInfoRepository = reservationInfoRepository;
	}
	
	@Override
	@Transactional
	public List<ReservationInfoDTO> selectReservationListByEmployeeId(String employeeId) {

		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		Integer employeeNo = emplist.getEmployeeNo();
		System.out.println("조회한 직원 번호 : " + employeeNo);
		
		List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply(employeeNo);

		System.out.println("해당 직원의 예약 지원 직원 리스트 : " + applyEmployeeList);

		List<ReservationInfoDTO> reservationInfoArrayList = new ArrayList<>();
		
		for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
			
			System.out.println("예약 번호 : " + applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyReservationNo());
			
			Integer reservationNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyReservationNo();
			
			ReservationInfo reservationInfo = reservationInfoRepository.findByReservationNo(reservationNo);
			
			ReservationInfoDTO reservationInfoList = modelMapper.map(reservationInfo, ReservationInfoDTO.class);
			
			reservationInfoArrayList.add(reservationInfoList);
			
		}
		
		
		System.out.println("해당 직원 예약 목록 : " + reservationInfoArrayList);
		
		
		return reservationInfoArrayList;  
		   
	}

	
}
