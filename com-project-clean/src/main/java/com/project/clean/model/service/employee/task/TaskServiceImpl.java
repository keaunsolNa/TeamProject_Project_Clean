package com.project.clean.model.service.employee.task;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.CheckList;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.ReservationInfo;
import com.project.clean.model.domain.joinEntity.ApplyEmployeeEmbedded;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.employee.apply.ApplyRepository;
import com.project.clean.model.repository.employee.apply.ReservationInfoRepository;
import com.project.clean.model.repository.employee.checkList.CheckListRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

	private EmpRepository empRepository;
	private ApplyRepository applyRepository;
	private ModelMapper modelMapper;
	private ReservationInfoRepository reservationInfoRepository;
	private CheckListRepository checkListRepository;
	
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
		
		return reservationInfoArrayList;
		
	}

//	@Override
//	public int selectEmployeeNo(String employeeId) {
//		
//		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
//		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
//		
//		System.out.println("해당 직원 예약 목록 : " + reservationInfoArrayList);
//		
//		
//		return reservationInfoArrayList;  
//		   
//	}

	@Override
	public CheckListDTO selectScheckList(String employeeId) {
		
		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		int empNo = emplist.getEmployeeNo();
		
		List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply(empNo);
		
		List<ReservationInfoDTO> reservationInfoArrayList = new ArrayList<>();
		
		for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
			
			Integer reservationNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyReservationNo();
				System.out.println("예약 번호 : " + reservationNo);
				System.out.println("예약 번호 : " + reservationNo);
				System.out.println("예약 번호 : " + reservationNo);
				try {
				
					CheckList checkList = checkListRepository.findBycheckReservationNoAndCheckStatus(reservationNo, "N");
					System.out.println(checkList);
					
					return modelMapper.map(checkList, CheckListDTO.class);
					
				} catch(java.util.NoSuchElementException e) {
				
					System.out.println("작성 가능한 체크리스트가 없습니다.");

				} catch(java.lang.IllegalArgumentException f) {
					
					continue;
					
				}
		}
		return null;
		
	}

	@Override
	@Transactional
	public int updateCheckList(CheckListDTO checkListDTO) {
		
		CheckList checkList = checkListRepository.findById(checkListDTO.getCheckReservationNo()).get();

		checkList.setCheckHTML(checkListDTO.getCheckHTML());
		
		return 0;
	}

	@Override
	public int selectEmployeeNo(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int registNewCheckList(CheckListDTO checkListDTO) {
		// TODO Auto-generated method stub
		return 0;
	}
}
