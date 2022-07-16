package com.project.clean.model.service.employee.checkList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class CheckListServiceImpl implements CheckListService{

	private EmpRepository empRepository;
	private ApplyRepository applyRepository;
	private ModelMapper modelMapper;
	private ReservationInfoRepository reservationInfoRepository;
	private CheckListRepository checkListRepository;
	private AdminRepository adminRepository;
	
	
	@Autowired
	public CheckListServiceImpl(EmpRepository empRepository,  ApplyRepository applyRepository, ModelMapper modelMapper, ReservationInfoRepository reservationInfoRepository,
			CheckListRepository checkListRepository, AdminRepository adminRepository) {
		this.empRepository = empRepository;
		this.applyRepository = applyRepository;
		this.modelMapper = modelMapper;
		this.reservationInfoRepository = reservationInfoRepository;
		this.checkListRepository = checkListRepository;
		this.adminRepository = adminRepository;
	}
	
	/* 본인 예약 업무 조회 */
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
			
			Integer reservationNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyReservationNo();
			
			System.out.println("예약 번호 : " + reservationNo);

				try {
					
				CheckList checkList = checkListRepository.findById(reservationNo).get();
				
				} catch(java.util.NoSuchElementException e) {
				
					System.out.println("실행 확인");

						ReservationInfo reservationInfo = reservationInfoRepository.findByReservationNo(reservationNo);
						ReservationInfoDTO reservationInfoList = modelMapper.map(reservationInfo, ReservationInfoDTO.class);
						reservationInfoArrayList.add(reservationInfoList);
						System.out.println("해당 직원 예약 목록 : " + reservationInfoArrayList);
						
				}  
		}
		
		return reservationInfoArrayList;
		
	}

	/* 직원 번호 조회 */
	@Override
	public int selectEmployeeNo(String employeeId) {
		
		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		return emplist.getEmployeeNo();
	}

	/* 업무 시작 시 빈 체크리스트 등록 */
	@Override
	public int registNewCheckList(CheckListDTO checkListDTO) {
		
		checkListRepository.save(modelMapper.map(checkListDTO, CheckList.class));
		
		return 0;
	}

	/* 체크리스트 작성 */
	@Override
	public CheckListDTO selectCheckList(String employeeId) {
		
		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		int empNo = emplist.getEmployeeNo();
		
		List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply(empNo);
		
		for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
			
			Integer reservationNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyReservationNo();
				try {
				
					CheckList checkList = checkListRepository.findBycheckReservationNoAndCheckStatus(reservationNo, "N");
					
					return modelMapper.map(checkList, CheckListDTO.class);
					
				} catch(java.util.NoSuchElementException e) {
				
					System.out.println("작성 가능한 체크리스트가 없습니다.");

				} catch(java.lang.IllegalArgumentException f) {
					
					continue;
					
				}
		}
		return null;
		
	}

	/* 체크리스트 수정 */
	@Override
	@Transactional
	public int updateCheckList(CheckListDTO checkListDTO) {
		
		CheckList checkList = checkListRepository.findById(checkListDTO.getCheckReservationNo()).get();
		
		checkList.setCheckReservationNo(checkListDTO.getCheckReservationNo());
		checkList.setCheckHTML(checkListDTO.getCheckHTML());
		checkList.setCheckStatus(checkListDTO.getCheckStatus());	
		
		return 0;
	}

	/* 반려 체크리스트 조회 */
	@Override
	public List<CheckListAndReservationInfoAndEmployeeDTO> selectDenialCheckList(String employeeId, int parameter) {
		
		List<CheckListDTO> check = new ArrayList<>();
		List<CheckListAndReservationInfoAndEmployeeDTO> checkListAndReservationInfoAndEmployeeList = new ArrayList<>();
		
		try {
			List<CheckList> checkList = new ArrayList<>();
			
			if(parameter == 1) {
				checkList = checkListRepository.findAllByCheckStatusOrCheckStatus("D", "R");
			} else if(parameter == 2) {
				checkList = checkListRepository.findAllByCheckStatus("A");
			}
			
			List<CheckListDTO> checkListDTO = checkList.stream().map(returnCheckList -> modelMapper.map(returnCheckList, CheckListDTO.class)).collect(Collectors.toList());

			Employee employee = empRepository.findByEmployeeId(employeeId);
			
			EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			
			Integer employeeNo = employeeDTO.getEmployeeNo();
			
			String employeeName = "없음";
			
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
						
						/* EmployeeDTO에서 employeeName 변수 꺼내오기 */
						employeeName = employeeDTO.getEmployeeName();
						
						/* 전달용 DTO 객체 생성 */
						CheckListAndReservationInfoAndEmployeeDTO checkListAndReservationInfoAndEmployeeDTO = new CheckListAndReservationInfoAndEmployeeDTO();
						
						/* 값 주입 */
						checkListAndReservationInfoAndEmployeeDTO.setEmployeeName(employeeName);
						checkListAndReservationInfoAndEmployeeDTO.setCustomerName(userName);
						checkListAndReservationInfoAndEmployeeDTO.setCheckReservationNo(checkReservationNo);
						
						if(checkStatus.equals("R")) {
							checkStatus = "확인중";
						} else if(checkStatus.equals("D")) {
							checkStatus = "반려";
						}
						
						checkListAndReservationInfoAndEmployeeDTO.setCheckStatus(checkStatus);
						checkListAndReservationInfoAndEmployeeDTO.setCheckHTML(checkHTML);
						
						String adminName = "";
						Integer adminNo = 0;
						
							try {
								Integer checkAdminNo = checkLists.getAdminNo();
								
								Admin admin = adminRepository.findByAdminNo(checkAdminNo).get();
								AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
								
								adminName = adminDTO.getAdminName();
								adminNo = admin.getAdminNo();
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
							} catch(NullPointerException e) {
								adminName = "담당자 없음";
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
							}

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

	/* 반려 체크리스트 상세 조회 */
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

