package com.project.clean.model.service.admin.checkList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	/* CheckList 전체 조회 */
	@Override
	public List<CheckListAndReservationInfoAndEmployeeDTO> selectAllStandCheckList(String adminId, int parameter) {

		List<CheckListDTO> check = new ArrayList<>();
		List<CheckListAndReservationInfoAndEmployeeDTO> checkListAndReservationInfoAndEmployeeList = new ArrayList<>();
		System.out.println("확인 : " + parameter);
		System.out.println("확인 : " + parameter);
		System.out.println("확인 : " + parameter);
		System.out.println("확인 : " + parameter);
		try {
			List<CheckList> checkList = new ArrayList<>();
			/* 파라미터 값으로 모든 체크리스트 조회 */
			if(parameter == 1) {
				checkList = checkListRepository.findAllByCheckStatus("R");
			} else if(parameter == 2) {
				checkList = checkListRepository.findAllByCheckStatus("D");
			}
			
			/* 조회한 Entity List<CheckListDTO>로 전환 */
			List<CheckListDTO> checkListDTO = checkList.stream().map(returnCheckList -> modelMapper.map(returnCheckList, CheckListDTO.class)).collect(Collectors.toList());

			/* 매개변수로 adminEntity 조회 */
			Admin admin = adminRepository.findByAdminId(adminId);
			
			/* AdminDTO로 전환 */
			AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
			
			/* AdminNo Integer 변수에 담기 */
			Integer adminNo = adminDTO.getAdminNo();
			
			/* 전달할 AdminName 변수에 담기 */
			String adminName = "없음";
			
			/* for-each문 시작 */
			for (CheckListDTO checkLists : checkListDTO) {
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
						String employeeName = employeeDTO.getEmployeeName();
						
						/* 전달용 DTO 객체 생성 */
						CheckListAndReservationInfoAndEmployeeDTO checkListAndReservationInfoAndEmployeeDTO = new CheckListAndReservationInfoAndEmployeeDTO();
						
						/* 값 주입 */
						checkListAndReservationInfoAndEmployeeDTO.setEmployeeName(employeeName);
						checkListAndReservationInfoAndEmployeeDTO.setCustomerName(userName);
						checkListAndReservationInfoAndEmployeeDTO.setCheckReservationNo(checkReservationNo);
						checkListAndReservationInfoAndEmployeeDTO.setCheckStatus(checkStatus);
						checkListAndReservationInfoAndEmployeeDTO.setCheckHTML(checkHTML);
						
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
				return checkListAndReservationInfoAndEmployeeList;
			
		} catch(java.util.NoSuchElementException e) {
			
			System.out.println("쌓인 체크리스트 없음");
			
			return checkListAndReservationInfoAndEmployeeList;
		} 
		
	}

	/* CheckList 상세 조회 */
	@Override
	public CheckListDTO selectStandCheckListDetails(String adminId, int checkReservationNo) {
		
		try {
			
			CheckList checkList = checkListRepository.findByCheckReservationNo(checkReservationNo);
			
			Admin admin = adminRepository.findByAdminId(adminId);
			
			int adminNo = admin.getAdminNo();
			
			checkList.setAdminNo(adminNo);
			
			checkList.setCheckStatus("R");
			
			return modelMapper.map(checkList, CheckListDTO.class);
			
		} catch (java.util.NoSuchElementException e){
			
			CheckListDTO checkListDTO = new CheckListDTO();
			
			System.out.println("일치하는 체크리스트 없음.");
			
			return checkListDTO;
		} 
	}

	/* CheckList 거절 */
	@Override
	public int modifyCheckListDenial(CheckListDTO checkList) {
		
		CheckList checkListEntity = checkListRepository.findByCheckReservationNo(checkList.getCheckReservationNo()); 
		
		checkListEntity.setCheckHTML(checkList.getCheckHTML());
		checkListEntity.setCheckStatus("D");
		
		return 0;
	}

	/* 상세 조회 */
	@Override
	public CheckListDTO selectDenialCheckListDetails(int reservationNo) {
		
		try{
			CheckList checkList =  checkListRepository.findByCheckReservationNo(reservationNo);
			
			return modelMapper.map(checkList, CheckListDTO.class);
			
		} catch(java.util.NoSuchElementException e) {
			
			CheckListDTO checkListDTO = new CheckListDTO();
			
			System.out.println("일치 항목 없음");
			
			return checkListDTO;
		}
	}
}


