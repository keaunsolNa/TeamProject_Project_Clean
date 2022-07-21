package com.project.clean.model.service.employee.checkList;

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
	
	/* KS. 본인 예약 업무 조회 */
	@Override
	@Transactional
	public List<ReservationInfoDTO> selectReservationListByEmployeeId(String employeeId) {

		/* employeeId로 Employee Entity 조회 */
		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		
		/* Employee Entity EmployeeDTO로 Mapping */
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		/* EmployeeDTO에서 employeeNo 가져오기 */
		Integer employeeNo = emplist.getEmployeeNo();
		
		/* employeeNo로 복합 Entity ApplyEmployeeEmbedded List Entity 조회 */
		List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply(employeeNo);

		/* ReservationInfoDTO List 빈 객체 생성 */
		List<ReservationInfoDTO> reservationInfoArrayList = new ArrayList<>();
		
		/* for문 시작 */
		for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
			
			/* reservationNo 가져오기 */
			Integer reservationNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyReservationNo();
			
				/* 예외처리 */
				try {
					
				/* reservationNo로 checkList Entity 조회 */
				CheckList checkList = checkListRepository.findById(reservationNo).get();
				
				System.out.println("TEST : " + checkList);
				
				continue;
				
				} catch(java.util.NoSuchElementException e) {

						/* reservationNo로 ReservationInfo Entity 조회 */
						ReservationInfo reservationInfo = reservationInfoRepository.findByReservationNo(reservationNo);
						
						/* ReservationInfo Entity ReservationInfoDTO로 Mapping */
						ReservationInfoDTO reservationInfoList = modelMapper.map(reservationInfo, ReservationInfoDTO.class);
						
						/* 기존에 생성한 reservationInfoArrayList에 값 추가 */
						reservationInfoArrayList.add(reservationInfoList);
						
						continue;
				}  
		}
		
		/* 값 반환 */
		return reservationInfoArrayList;
		
	}

	/* KS. 직원 번호 조회 */
	@Override
	public int selectEmployeeNo(String employeeId) {
		
		/*  employeeId 매개변수로 Employee Entity 조회 */
		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		
		/* Employee Entity EmployeeDTO로 Mapping */
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		/* 값 반환 */
		return emplist.getEmployeeNo();
	}

	/* 업무 시작 시 빈 체크리스트 등록 */
	@Override
	public int registNewCheckList(CheckListDTO checkListDTO) {
		
		/* Controller에서 가져온 CheckListDTO Insert */
		checkListRepository.save(modelMapper.map(checkListDTO, CheckList.class));
	
		/* 값 반환 */
		return 0;
	}

	/* 체크리스트 작성 */
	@Override
	public CheckListDTO InsertCheckList(String employeeId) {
		
		/*  employeeId로 Employee Entity 조회 */
		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		
		/* Employee Entity로 EmployeeDTO Mapping */
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		/* EmployeeDTO에서 EmpployeeNo 가져오기 */
		int empNo = emplist.getEmployeeNo();
		
		/* employeeNo로 복합 Entity List<ApplyEmployeeEmbedded> 객체 조회 */
		List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply(empNo);
		
		/* for문 시행 */
		for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
			
			/* Entity에서 reservationNo 조회 */
			Integer reservationNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyReservationNo();
			
			/* 예외 처리 */
			try {
				
					/* reservationNo로 아직 작성하지 않은 CheckList Entity 조회 */
					CheckList checkList = checkListRepository.findBycheckReservationNoAndCheckStatus(reservationNo, "N");
					
					/* 값 반환 */
					return modelMapper.map(checkList, CheckListDTO.class);
					
					/* 체크리스트가 없을 때 */
				} catch(java.util.NoSuchElementException e) {
				
					System.out.println("작성 가능한 체크리스트가 없습니다.");
					
					/* for문에서 해당하는 항목이 없을 때 다음 reservationInfo로 시행 */
				} catch(java.lang.IllegalArgumentException f) {
					
					continue;
					
				}
		}
		
		/* 값 반환 */
		return null;
		
	}

	/* 체크리스트 수정 */
	@Override
	@Transactional
	public int updateCheckList(CheckListDTO checkListDTO) {
		
		/* ChekListDTO의 reservationNo로 CheckList Entity 조회 */
		CheckList checkList = checkListRepository.findById(checkListDTO.getCheckReservationNo()).get();
		
		/* 조회한 Entity에 Update */
		checkList.setCheckReservationNo(checkListDTO.getCheckReservationNo());
		checkList.setCheckHTML(checkListDTO.getCheckHTML());
		checkList.setCheckStatus(checkListDTO.getCheckStatus());	
		
		/* 값 반환 */
		return 0;
	}

	/* 체크리스트 조회 */
	@Override
	public List<CheckListAndReservationInfoAndEmployeeDTO> selectCheckList(String employeeId, int parameter) {
		
		/* 빈 CheckListDTO ArrayList 객체 생성 */
		List<CheckListDTO> check = new ArrayList<>();
		
		/* 빈 CheckListAndReservationInfoAndEmployeeDTO ArrayList 객체 생성(반환할 값 담기 위한 인조 DTO) */
		List<CheckListAndReservationInfoAndEmployeeDTO> checkListAndReservationInfoAndEmployeeList = new ArrayList<>();
		
		/* 예외 처리 */
		try {
			
			/* 빈 CheckList Entity ArrayList 객체 생성 */
			List<CheckList> checkList = new ArrayList<>();
			
			/* parameter 인자값으로 다른 CheckList Entity 객체 조회 */
			if(parameter == 1) {
				checkList = checkListRepository.findAllByCheckStatusOrCheckStatus("D", "R");
			} else if(parameter == 2) {
				checkList = checkListRepository.findAllByCheckStatus("A");
			} else if(parameter == 3) {
				checkList = checkListRepository.findAllByCheckStatus("B");
			} 
			
			/* CheckListDTO ArrayList 반환받은 CheckListDTO로 Mapping */
			List<CheckListDTO> checkListDTO = checkList.stream().map(returnCheckList -> modelMapper.map(returnCheckList, CheckListDTO.class)).collect(Collectors.toList());

			/* 매개변수로 Employee Entity 객체 조회 */
			Employee employee = empRepository.findByEmployeeId(employeeId);
			
			/* Employee Entity EmployeeDTO로 Mapping */
			EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
			
			/* EmployeeDTO에서 employeeNo 가져오기 */
			Integer employeeNo = employeeDTO.getEmployeeNo();
			
			/* employeeName 변수 초기값 설정 */
			String employeeName = "없음";
			
			/* for-each문 시작 */
			for (CheckListDTO checkLists : checkListDTO) {
								
					/* CheckListDTO에서 매개변수로 던질 값 가져오기 */
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
					List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply3(reservationNo, employeeNo);
					
					/* 빈 List<EmployeeDTO> 객체 생성 */
					List<EmployeeDTO> employeeArrayList = new ArrayList<>();
					
					/* 2중 for-each문 시작 */
					for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
						
						/* EmployeeDTO에서 employeeName 변수 꺼내오기 */
						employeeName = employeeDTO.getEmployeeName();
						
						/* List 처리를 위한  DTO 객체 생성 */
						CheckListAndReservationInfoAndEmployeeDTO checkListAndReservationInfoAndEmployeeDTO = new CheckListAndReservationInfoAndEmployeeDTO();
						
						/* 값 주입 */
						checkListAndReservationInfoAndEmployeeDTO.setEmployeeName(employeeName);
						checkListAndReservationInfoAndEmployeeDTO.setCustomerName(userName);
						checkListAndReservationInfoAndEmployeeDTO.setCheckReservationNo(checkReservationNo);
						
						/* View에서 뿌려줄 변수값 변환 */
						if(checkStatus.equals("R")) {
							checkStatus = "확인중";
						} else if(checkStatus.equals("D")) {
							checkStatus = "반려";
						} else if(checkStatus.equals("A")) {
							checkStatus = "통과";
						} else if(checkStatus.equals("B")) {
							checkStatus = "경고";
						}
						
						/* 값 주입 */
						checkListAndReservationInfoAndEmployeeDTO.setCheckStatus(checkStatus);
						checkListAndReservationInfoAndEmployeeDTO.setCheckHTML(checkHTML);
						
						/* 매개변수 초기화 */
						String adminName = "";
						Integer adminNo = 0;
						
							/* 예외 처리 */
							try {
								
								/* checkList에서 adminNo 가져오기 */
								Integer checkAdminNo = checkLists.getAdminNo();
								
								/* adminNo로 Admin Entity 객체 조회 */
								Admin admin = adminRepository.findByAdminNo(checkAdminNo).get();
								
								/* Admin Entity AdminDTO로 Mapping */
								AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
								
								/* 필요한 매개변수 가져오기 */
								adminName = adminDTO.getAdminName();
								adminNo = admin.getAdminNo();
								
								/* 값 주입 */
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
								
								checkListAndReservationInfoAndEmployeeList.add(checkListAndReservationInfoAndEmployeeDTO);
								
								continue;
//								return checkListAndReservationInfoAndEmployeeList;
								/* 아직 담당자 없을 때를 위한 예외처리 */
							} catch(NullPointerException e) {
								
								/* 변수 설정 */
								adminName = "담당자 없음";
								
								/* 값 주입 */
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
								
								/* List<CheckListAndReservationAndEmployee> 객체에 값 주입 */
								checkListAndReservationInfoAndEmployeeList.add(checkListAndReservationInfoAndEmployeeDTO);
								
								continue;
//								return checkListAndReservationInfoAndEmployeeList;
							}
							
						}
				}
			
				/* 체크리스트가 없을 때 */
		} catch(java.util.NoSuchElementException e) {
			
			/* 값 반환 */
			return checkListAndReservationInfoAndEmployeeList;
		}
		return checkListAndReservationInfoAndEmployeeList; 
		
	}

	/* KS. 체크리스트 상세 조회 */
	@Override
	public CheckListDTO selectCheckListDetails(int reservationNo) {
		
		/* 예외처리 */
		try{
			
			/* 매개변수 reservationNo로 CheckList Entity 객체 조회 */
			CheckList checkList =  checkListRepository.findByCheckReservationNo(reservationNo);
			
			/* 값 반환 */
			return modelMapper.map(checkList, CheckListDTO.class);
			
			/* 체크리스트 없을 떄 */
		} catch(java.util.NoSuchElementException e) {
			
			/* 빈 객체 생성 */
			CheckListDTO checkListDTO = new CheckListDTO();
			
			return checkListDTO;
		}
	}

	/* 작성 해야하는 체크리스트 유무 확인 */
	@Override
	public int selectCheckListIsNotNull() {
		
		String checkStatus = "N";
		
		/* 아직 작성하지 않은 체크리스트 유무 탐색 */
		CheckList checkList =  checkListRepository.findByCheckStatus(checkStatus);
		
		/* 결과값 초기화 */
		int result = 0;
		
		/* 예외처리 */
		try {
			
			/* checkList가 있다면 */
			if(!(checkList.equals(null))) {
				
				/* 결과값 선언 */
				result = 1;
			}
			
			/* checkList가 없다면 */
		} catch(java.lang.NullPointerException e) {
			
			/* 결과값 선언 */
			result = 0;
		}
			
		/* 값 반환 */
		return result;
		
	}
}

