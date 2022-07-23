package com.project.clean.model.service.admin.checkList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.CheckList;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.ReservationInfo;
import com.project.clean.model.domain.joinEntity.ApplyEmployeeEmbedded;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;
import com.project.clean.model.repository.admin.AdminRepository;
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
	private AdminRepository adminRepository;
	
	@Autowired
	public AdminCheckListServiceImpl(CheckListRepository checkListRepository, ModelMapper modelMapper,
			EmpRepository empRepository, ReservationInfoRepository reservationInfoRepository, ApplyRepository applyRepository,
			AdminRepository adminRepository) {
		this.checkListRepository = checkListRepository;
		this.modelMapper = modelMapper;
		this.empRepository = empRepository;
		this.reservationInfoRepository = reservationInfoRepository;
		this.applyRepository = applyRepository;
		this.adminRepository = adminRepository;
	}
	

	/* KS. CheckList 상세 조회 */
	@Override
	public CheckListDTO selectCheckListDetails(String adminId, int checkReservationNo, int parameter) {
		
		/* 빈 객체 생성 */
		CheckList checkList = new CheckList();
		
		try {
			
			/* 예약번호로 체크리스트 조회 */
			checkList = checkListRepository.findByCheckReservationNo(checkReservationNo);
			
			/* 아직 아무도 보지 않은 체크리스트인 경우 담당자 배정 */
			if(parameter == 1) {
				
				Admin admin = adminRepository.findByAdminId(adminId);
				
				int adminNo = admin.getAdminNo();
				
				checkList.setAdminNo(adminNo);
				
				checkList.setCheckStatus("R");
				
			} 
			
			/* 체크리스트가 없을 때 에외처리 */
		} catch (java.util.NoSuchElementException e) {
			
			CheckListDTO checkListDTO = new CheckListDTO();
			
			System.out.println("일치하는 체크리스트 없음.");
			
			return checkListDTO;
		}
		
		/* 결과값 반환 */
		return modelMapper.map(checkList, CheckListDTO.class);
	}

	/* KS. CheckList 승인 및 반려 */
	@Override
	public int modifyCheckList(CheckListDTO checkList) {
		
		/* 예약번호로 체크리스트 조회 */
		CheckList checkListEntity = checkListRepository.findByCheckReservationNo(checkList.getCheckReservationNo()); 
		
		/* 체크리스트 HTML 값 주입 */
		checkListEntity.setCheckHTML(checkList.getCheckHTML());

		/* parameter로 넘어온 값으로 각각의 체크리스트 상태 변경*/
		if(checkList.getCheckStatus().equals("D")) {
			
			checkListEntity.setCheckStatus("D");
			
		} else if(checkList.getCheckStatus().equals("A")) {
			
			checkListEntity.setCheckStatus("A");
			
		} else if(checkList.getCheckStatus().equals("B")) {
			
			/* 반려 시 추가 비지니스 로직 수행 */
			checkListEntity.setCheckStatus("B");
			
			/* 체크리스트에서 예약 번호 조회 */
			int reservationNo = checkListEntity.getCheckReservationNo();
			
			/* 예약번호로 예약 리스트 조회 */
			ReservationInfo reservationInfo = reservationInfoRepository.findByReservationNo(reservationNo);
			
			/* Entity DTO로 매핑 */
			ReservationInfoDTO reservationInfoDTO = modelMapper.map(reservationInfo, ReservationInfoDTO.class);
			
			/* 예약번호로 List<ApplyEmployeeEmbedded> 조회 */
			List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply2(reservationNo);
			
			/* List<ApplyEmployeeEmbedded> Entity DTO로 매핑 */
			List<EmployeeDTO> employeeArrayList = new ArrayList<>();
			
			/* for문 시행 */
			for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
				
				/* 일치하는 직원 번호 조회 */
				Integer employeeNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyEmployeeNo();
				
				/* 직원번호로 직원 Entity 조회 */
				Employee employee = empRepository.findByEmployeeNo(employeeNo);
				
				/* 블랙리스트 현재 누적 횟수 + 1 변수에 담기*/
				int sumCount = employee.getEmployeeSumCount()+1;
					if(sumCount >= 5) {
						employee.setEmployeeBlackListYn("Y");
					}
				/* 블랙리스트 횟수 추가 */
				employee.setEmployeeSumCount(sumCount);
			}
		}
		
		return 0;
	}

	/* 체크리스트 조회 및 페이징 */
	@Override
	public Map<String, Object> selectCheckList(String adminId, int parameter, String category, String categoryValue,
			Pageable pageable) {
		
			Page<CheckList> paging;
			List<CheckListDTO> checkList = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			String status = "";
			
			if(parameter == 1) {
				status = "R";
			} else if(parameter == 2) {
				status = "D";
			} else if(parameter == 3) {
				status = "A";
			} else if(parameter == 4) {
				status = "B";
			}
				
			List<CheckListAndReservationInfoAndEmployeeDTO> checkListAndReservationInfoAndEmployeeList = new ArrayList<>();
			
			try {
				
				/* 파라미터 값으로 모든 체크리스트 조회 */
				paging =  checkListRepository.findAllByCheckStatus(status, pageable);
				
				List<CheckList> checkArrayList = paging.getContent();
				
				checkList = checkArrayList.stream().map(list -> modelMapper.map(list, CheckListDTO.class)).toList();
				
				/* 페이징 처리 */
				int currentPage = paging.getNumber();
				int maxPage = paging.getTotalPages();
				int startPage = (int)(currentPage / 5) * 5;
				int endPage = (int)(currentPage / 5) * 5 + 5;
				
				while (endPage > maxPage) {
					endPage -=1;
				}
				
				map.put("maxPage", maxPage);
				map.put("startPage", startPage);
				map.put("endPage", endPage);
				map.put("currentPage", currentPage);
				map.put("category", category);
				map.put("categoryValue", categoryValue);
				
				/* 매개변수로 adminEntity 조회 */
				Admin admin = adminRepository.findByAdminId(adminId);
				
				/* AdminDTO로 전환 */
				AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
				
				/* AdminNo Integer 변수에 담기 */
				Integer adminNo = adminDTO.getAdminNo();
				
				/* 전달할 AdminName 변수에 담기 */
				String adminName = "";
				
				/* for-each문 시작 */
				for (CheckListDTO checkLists : checkList) {
					
					if((checkLists.getAdminNo() == null || checkLists.getAdminNo() == adminNo)) {
						
						Integer checkAdminNo = checkLists.getAdminNo();
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
							String employeeId = employeeDTO.getEmployeeId();
							
							/* 전달용 DTO 객체 생성 */
							CheckListAndReservationInfoAndEmployeeDTO checkListAndReservationInfoAndEmployeeDTO = new CheckListAndReservationInfoAndEmployeeDTO();
							
							/* 값 주입 */
							checkListAndReservationInfoAndEmployeeDTO.setEmployeeName(employeeId);
							checkListAndReservationInfoAndEmployeeDTO.setCustomerName(userName);
							checkListAndReservationInfoAndEmployeeDTO.setCheckReservationNo(checkReservationNo);
							
							/* View단에서 보여질 checkList 상태값 설정 */
							if(checkStatus.equals("R")) {
								checkStatus = "미처리";
							} else if(checkStatus.equals("D")) {
								checkStatus = "반려";
							} else if(checkStatus.equals("A")) {
								checkStatus = "완료";
							} else if(checkStatus.equals("B")) {
								checkStatus = "경고";
							}
							
							/* 값 주입 */
							checkListAndReservationInfoAndEmployeeDTO.setCheckStatus(checkStatus);
							checkListAndReservationInfoAndEmployeeDTO.setCheckHTML(checkHTML);
							
							/* 담당자 있는지 판별 */
							if(checkLists.getAdminNo() == adminNo) {
								adminName = adminDTO.getAdminName();
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
							} else {
								adminName = "담당자 없음";
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
							}
							
							/*  List<CheckListAndReservationAndEmployee> 객체에 값 주입 */
							checkListAndReservationInfoAndEmployeeList.add(checkListAndReservationInfoAndEmployeeDTO);
							
						}
					}
					
					}
				
					map.put("checkList", checkListAndReservationInfoAndEmployeeList);
					return map;
				
			} catch(java.util.NoSuchElementException e) {
				
				System.out.println("쌓인 체크리스트 없음");
				
				return map;
			}
			
		}

}


