package com.project.clean.model.service.admin.checkList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.employee.apply.ApplyRepository;
import com.project.clean.model.repository.employee.apply.ReservationInfoRepository;
import com.project.clean.model.repository.employee.checkList.CheckListRepository;

@Service
@Transactional
public class AdminCheckListServiceImpl implements AdminCheckListService {

	private CheckListRepository checkListRepository;
	private ModelMapper modelMapper;
	private EmpRepository empRepository;
	private ReservationInfoRepository reservationInfoRepository;
	private ApplyRepository applyRepository;
	
	@Autowired
	public AdminCheckListServiceImpl(CheckListRepository checkListRepository, ModelMapper modelMapper,
			EmpRepository empRepository, ReservationInfoRepository reservationInfoRepository, ApplyRepository applyRepository) {
		this.checkListRepository = checkListRepository;
		this.modelMapper = modelMapper;
		this.empRepository = empRepository;
		this.reservationInfoRepository = reservationInfoRepository;
		this.applyRepository = applyRepository;
	}
	
	/* CheckList 전체 조회 */
	@Override
	public List<CheckListAndReservationInfoAndEmployeeDTO> selectAllStandCheckList() {

		List<CheckListDTO> check = new ArrayList<>();
		List<CheckListAndReservationInfoAndEmployeeDTO> checkListAndReservationInfoAndEmployeeList = new ArrayList<>();
		
		try {
			
			/* 모든 체크리스트 조회 */
			List<CheckList> checkList = checkListRepository.findAllByCheckStatus("R");
			
			System.out.println(checkList);
			
			/* 조회한 Entity List<CheckListDTO>로 전환 */
			List<CheckListDTO> checkListDTO = checkList.stream().map(returnCheckList -> modelMapper.map(returnCheckList, CheckListDTO.class)).collect(Collectors.toList());

			
			/* for-each문 시작 */
			for (CheckListDTO checkLists : checkListDTO) {
				
				String checkHTML = checkLists.getCheckHTML();
				String checkStatus = checkLists.getCheckStatus();
				Integer checkReservationNo = checkLists.getCheckReservationNo();
				
				/* checkList에서 reservationNo 조회 */
				Integer reservationNo = checkLists.getCheckReservationNo();
				
				/* reservationNo로 ReservationInfo Entity 조회 */
				ReservationInfo reservationInfo = reservationInfoRepository.findByReservationNo(reservationNo);
				
				/* ReservationInfoEntity DTO로 전환 */
				ReservationInfoDTO reservationInfoDTO = modelMapper.map(reservationInfo, ReservationInfoDTO.class);
				
				/* ReservationInfoDTO 에서 고객 이름 뽑아오기 */
				String userName = reservationInfoDTO.getUserName();
				
				/* DTO의 reservationNo로 List<ApplyEmployeeEmbedded> 조회 */
				List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply2(reservationNo);
				
				/* 빈 List<EmployeeDTO> 객체 생성 */
				List<EmployeeDTO> employeeArrayList = new ArrayList<>();
				
				/* 2중 for-each문 시작 */
				for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
					
					/* 조회한 ApplyEmployeeEmbeddedEntity로 employeeNo 조회 */
					Integer employeeNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyEmployeeNo();
					
					/* 조회한 employeeNo로 EmployeeEntity 조회 */
					Employee employee = empRepository.findByEmployeeNo(employeeNo);
					
					/* EmployeeEntityEmployeeDTO로 변환 */
					EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
							
					/* EmployeeDTO에서 employeeName 변수 꺼내오기 */
					String employeeName = employeeDTO.getEmployeeName();
					
					/* 전달용 DTO 객체 생성 */
					CheckListAndReservationInfoAndEmployeeDTO checkListAndReservationInfoAndEmployeeDTO = new CheckListAndReservationInfoAndEmployeeDTO();
					
					/* 값 주입 */
					checkListAndReservationInfoAndEmployeeDTO.setEmployeeName(employeeName);
					checkListAndReservationInfoAndEmployeeDTO.setCustomerName(userName);
					checkListAndReservationInfoAndEmployeeDTO.setCheckReservationNo(checkReservationNo);
					checkListAndReservationInfoAndEmployeeDTO.setCheckStatus(checkStatus);
					checkListAndReservationInfoAndEmployeeDTO.setCheckHTML(checkHTML);
					
					/*  List<CheckListAndReservationAndEmployee> 객체에 값 주입 */
					checkListAndReservationInfoAndEmployeeList.add(checkListAndReservationInfoAndEmployeeDTO);
					
				}
				
			}
				
			return checkListAndReservationInfoAndEmployeeList;
			
		} catch(java.util.NoSuchElementException e) {
			
			System.out.println("쌓인 체크리스트 없음");
			
			return checkListAndReservationInfoAndEmployeeList;
		}
		
	}
}


