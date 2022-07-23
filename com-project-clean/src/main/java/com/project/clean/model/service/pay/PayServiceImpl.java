package com.project.clean.model.service.pay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.ReservationInfo;
import com.project.clean.model.domain.commonEntity.Surcharge;
import com.project.clean.model.domain.joinEntity.AdminAndAdminPay;
import com.project.clean.model.domain.joinEntity.AdminPayAndAdmin;
import com.project.clean.model.domain.joinEntity.BestEmployeePayAndEmployee;
import com.project.clean.model.domain.joinEntity.EmployeePayAndApplyEmployee;
//import com.project.clean.model.domain.joinEntity.EmployeePayAndApplyEmployee;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminAndAdminPayDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;
import com.project.clean.model.dto.joinDTO.BestEmployeePayAndEmployeeDTO;
import com.project.clean.model.dto.joinDTO.EmployeePayAndApplyEmployeeDTO;
import com.project.clean.model.repository.pay.AdminAndAdminPayRepository;
import com.project.clean.model.repository.pay.AdminPayAndAdminRepository;
import com.project.clean.model.repository.pay.AdminPayRepository;
import com.project.clean.model.repository.pay.AdminRepositoryByPay;
import com.project.clean.model.repository.pay.ApplyEmployeeRepositoryByPay;
import com.project.clean.model.repository.pay.BestEmployeePayAndEmployeeRepository;
import com.project.clean.model.repository.pay.BestEmployeePayRepository;
import com.project.clean.model.repository.pay.EmployeePayAndEmployeeRepository;
import com.project.clean.model.repository.pay.EmployeePayRepository;
import com.project.clean.model.repository.pay.EmployeeRepositoryByPay;
import com.project.clean.model.repository.pay.ReservationInfoRepositoryByPay;
import com.project.clean.model.repository.pay.SurchargeRepository;

@Service
public class PayServiceImpl implements PayService{
	
	private final SurchargeRepository surchargeRepository;
	private final AdminPayAndAdminRepository adminPayAndAdminRepository;
	private final AdminAndAdminPayRepository adminAndAdminPayRepository;
	private final AdminRepositoryByPay adminRepository;
	private final AdminPayRepository adminPayRepository;
	private final EmployeePayRepository employeePayRepository;
	private final EmployeePayAndEmployeeRepository employeePayAndEmployeeRepository;
	private final ApplyEmployeeRepositoryByPay applyEmployeeRepository;
	private final ReservationInfoRepositoryByPay reservationInfoRepository;
	private final EmployeeRepositoryByPay employeeRepository;
	private final BestEmployeePayRepository bestEmployeePayRepository;
	private final BestEmployeePayAndEmployeeRepository bestEmployeePayAndEmployeeRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	

	public PayServiceImpl(SurchargeRepository surchargeRepository,
			AdminPayAndAdminRepository adminPayAndAdminRepository,
			AdminAndAdminPayRepository adminAndAdminPayRepository, AdminRepositoryByPay adminRepository,
			AdminPayRepository adminPayRepository, EmployeePayRepository employeePayRepository,
			EmployeePayAndEmployeeRepository employeePayAndEmployeeRepository,
			ApplyEmployeeRepositoryByPay applyEmployeeRepository,
			ReservationInfoRepositoryByPay reservationInfoRepository, EmployeeRepositoryByPay employeeRepository,
			BestEmployeePayRepository bestEmployeePayRepository,
			BestEmployeePayAndEmployeeRepository bestEmployeePayAndEmployeeRepository, ModelMapper modelMapper) {
		super();
		this.surchargeRepository = surchargeRepository;
		this.adminPayAndAdminRepository = adminPayAndAdminRepository;
		this.adminAndAdminPayRepository = adminAndAdminPayRepository;
		this.adminRepository = adminRepository;
		this.adminPayRepository = adminPayRepository;
		this.employeePayRepository = employeePayRepository;
		this.employeePayAndEmployeeRepository = employeePayAndEmployeeRepository;
		this.applyEmployeeRepository = applyEmployeeRepository;
		this.reservationInfoRepository = reservationInfoRepository;
		this.employeeRepository = employeeRepository;
		this.bestEmployeePayRepository = bestEmployeePayRepository;
		this.bestEmployeePayAndEmployeeRepository = bestEmployeePayAndEmployeeRepository;
		this.modelMapper = modelMapper;
	}











	// 부가요금 ------------------------------------------------------------------------------------------------
	
	/* 부가요금 페이지(조회) */
	@Override
	public List<SurchargeDTO> findSurchargeList() {
		List<Surcharge> surchargeList = surchargeRepository.findAll();				
		System.out.println("잘 되지?" + surchargeList );

		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return surchargeList.stream().map(surcharge -> modelMapper.map(surcharge, SurchargeDTO.class)).collect(Collectors.toList());
	}
	
	



	





	/* 부가요금 수정 */
	@Transactional
	@Override
	public void modifySurcharge(SurchargeDTO surcharge) {
		System.out.println("서비스임플");
		
		Surcharge foundSurcharge = surchargeRepository.findById(surcharge.getSurchargeInsurance()).get();
		Integer Commission = surcharge.getSurchargeCommission();
		Integer Bonus = surcharge.getSurchargeBonus();
		if(Commission != 0 && Bonus != 0) {
			foundSurcharge.setSurchargeCommission(surcharge.getSurchargeCommission());
			foundSurcharge.setSurchargeBonus(surcharge.getSurchargeBonus());
		} else if(Commission != 0 && Bonus == 0){
			foundSurcharge.setSurchargeCommission(surcharge.getSurchargeCommission());
		} else if(Commission == 0 && Bonus != 0) {
			foundSurcharge.setSurchargeBonus(surcharge.getSurchargeBonus());
		} else {
		  System.out.println("오류 발생");	
		}
		
		
		surchargeRepository.save(foundSurcharge);
		
	}
	
	
	// 관리자 급여 ---------------------------------------------------------------------------------------------
	
	/* 관리자 급여 페이징처리 카운트 */
	public int selectAdminPayTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("adminName".equals(searchCondition)) {
				count = adminPayAndAdminRepository.countByAdminAdminNameContaining(searchValue);
			}

			if("adminjob".equals(searchCondition)) {
				count = adminPayAndAdminRepository.countByAdminAdminJobContaining(searchValue);
			}
			
			if("adminPhone".equals(searchCondition)) {
				count = adminPayAndAdminRepository.countByAdminAdminPhoneContaining(searchValue);
			}
				
		} else {
			count = (int)adminPayRepository.count();
		}

		return count;
	}
	
	/* 관리자 급여 검색 */
	public List<AdminPayAndAdminDTO> adminPaySearch(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("payHistoryAdminNo").descending());	// Pageable은 org.springframework.data.domain패키지로 import

		List<AdminPayAndAdmin> adminPayList = new ArrayList<AdminPayAndAdmin>();
		if(searchValue != null) {

			/* 관리자 이름 검색일 경우 */
			if("adminName".equals(selectCriteria.getSearchCondition())) {
				adminPayList = adminPayAndAdminRepository.findByAdminAdminNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
			/* 관리자 직급 검색일 경우 */
			if("adminJob".equals(selectCriteria.getSearchCondition())) {
				adminPayList = adminPayAndAdminRepository.findByAdminAdminJobContaining(selectCriteria.getSearchValue(), paging);
			}
			
			/* 관리자 전화번호 검색일 경우 */
			if("adminPhone".equals(selectCriteria.getSearchCondition())) {
				adminPayList = adminPayAndAdminRepository.findByAdminAdminPhoneContaining(Integer.valueOf(selectCriteria.getSearchValue()), paging);
			}
			
			
		} else {
			adminPayList = adminPayAndAdminRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return adminPayList.stream().map(pay -> modelMapper.map(pay,AdminPayAndAdminDTO.class)).collect(Collectors.toList());
	}
	
	/* 관리자 급여 상세 조회 */
	public AdminPayAndAdminDTO findAdminPayByPayHistoryAdminNo(int payHistoryNo) {

		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		AdminPayAndAdmin pay = adminPayAndAdminRepository.findById(payHistoryNo).get();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(pay, AdminPayAndAdminDTO.class);
	}

	
	/* 급여를 한번도 받지 않은 신입 관리자 조회  */
	public List<AdminAndAdminPayDTO> findNullAdmin() {
		
		List<AdminAndAdminPay> adminList = adminAndAdminPayRepository.findNullAdminNativeQuery();				
		System.out.println("--------- DTO에서 adminPay 여부 확인 ----------");
		for (AdminAndAdminPay adminAndAdminPay : adminList) {
			System.out.println(adminAndAdminPay.getAdminPay());
		}
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return adminList.stream().map(admin -> modelMapper.map(admin,AdminAndAdminPayDTO.class)).collect(Collectors.toList());
		
	}
	
	/* 이번달 급여를 받은 관리자 조회  */
	public List<AdminAndAdminPayDTO> findPaidAdmin() {
		
		List<AdminAndAdminPay> adminList2 = adminAndAdminPayRepository.findPaidAdminNativeQuery();				

		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return adminList2.stream().map(admin -> modelMapper.map(admin,AdminAndAdminPayDTO.class)).collect(Collectors.toList());
		
	}

	/* 모든 관리자 조회 */
	@Override
	public List<AdminAndAdminPayDTO> findAllAdmin() {
		
		List<AdminAndAdminPay> adminList3 = adminAndAdminPayRepository.findAll(Sort.by("adminName"));				

		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return adminList3.stream().map(admin -> modelMapper.map(admin,AdminAndAdminPayDTO.class)).collect(Collectors.toList());
	}

	
	/* 관리자 한명의 정보만 반환 */
	@Override
	public AdminDTO findAdminByAdminNo(int adminNo) {
		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		Admin admin = adminRepository.findById(adminNo).get();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(admin, AdminDTO.class);
	}
	
	
	/* 관리자 급여 지급 */
	@Override
	@Transactional
	public void registAdminPay(int payAdminNo, int salary, int insurance) {
		
		// 최종 급여 계산
		int payAdminFinalSalary = salary - (salary * insurance / 100);
		
		System.out.println("최종 급여 확인 : " + payAdminFinalSalary);
		System.out.println("최종 급여 확인 : " + payAdminFinalSalary);
		System.out.println("최종 급여 확인 : " + payAdminFinalSalary);
		
		adminPayRepository.modifyAdminPay(payAdminNo,payAdminFinalSalary);
		
		
	}




	@Override
	public int selectEmployeePayTotalCount(String searchCondition, String searchValue) {
		int count = 0;
		if(searchValue != null) {
			if("employeeName".equals(searchCondition)) {
				count = employeePayAndEmployeeRepository.countByEmployeeEmployeeNameContaining(searchValue);
			}
			
			if("employeePhone".equals(searchCondition)) {
				count = employeePayAndEmployeeRepository.countByEmployeeEmployeePhoneContaining(searchValue);
			}
				
		} else {
			count = (int)employeePayAndEmployeeRepository.count();
		}

		return count;
	}


	// 직원 급여 전체 조회
	@Override
	public List<EmployeePayAndApplyEmployeeDTO> employeePaySearch(SelectCriteria selectCriteria) {
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("payHistoryEmployeeNo").descending());	// Pageable은 org.springframework.data.domain패키지로 import
		/* 페이징 처리와 정렬을 위한 객체 생성 */
		List<EmployeePayAndApplyEmployee> employeePayList = new ArrayList<EmployeePayAndApplyEmployee>();
		if(searchValue != null) {

			/* 직원 이름 검색일 경우 */
			if("employeeName".equals(selectCriteria.getSearchCondition())) {
				employeePayList = employeePayAndEmployeeRepository.findByEmployeeEmployeeNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
			
			/* 직원 전화번호 검색일 경우 */
			if("employeePhone".equals(selectCriteria.getSearchCondition())) {
				employeePayList = employeePayAndEmployeeRepository.findByEmployeeEmployeePhoneContaining(Integer.valueOf(selectCriteria.getSearchValue()), paging);
			}
			

			
		} else {
			employeePayList = employeePayAndEmployeeRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return employeePayList.stream().map(pay -> modelMapper.map(pay,EmployeePayAndApplyEmployeeDTO.class)).collect(Collectors.toList());
	}


	
	// 예약번호로 예약별직원 찾기
	@Override
	public List<ApplyEmployeeDTO> findByApplyReservationNo(int reservationNo) {
		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		List<ApplyEmployee> applyEmployeeList = applyEmployeeRepository.findByApplyReservationNo(Integer.valueOf(reservationNo));
		
		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return applyEmployeeList.stream().map(applyEmployee -> modelMapper.map(applyEmployee,ApplyEmployeeDTO.class)).collect(Collectors.toList());
	}

	// 예약번호로 그 예약의 원래 급여 찾기
	@Override
	public ReservationInfoDTO findByTotalPaymentByReservationNo(int reservationNo) {
		ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationNo).get();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(reservationInfo, ReservationInfoDTO.class);
	}


	// 직원 급여 지급
	@Override
	@Transactional
	public void registEmployeePay(int applyReservationNo, int applyEmployeeNo, int payEmployeeFinalSalary) {
		System.out.println("registEmployeePay 모두 확인 : " + applyReservationNo + applyEmployeeNo + payEmployeeFinalSalary );
				
		employeePayRepository.modifyEmployeePay(applyReservationNo,applyEmployeeNo,payEmployeeFinalSalary);
		
	}



	@Override
	public List<EmployeeDTO> findAllEmployee() {
		List<Employee> employeeList = employeeRepository.findAll(Sort.by("employeeName"));				

		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return employeeList.stream().map(employee -> modelMapper.map(employee,EmployeeDTO.class)).collect(Collectors.toList());
	}



	@Override
	@Transactional
	public void registBestEmployeePay(int bestEmployeeNo, int bestEmployeeBonus) {
		bestEmployeePayRepository.registBestEmployeePay(bestEmployeeNo,bestEmployeeBonus);
		
	}


	// 이달의 우수사원 전체 조회 카운트
	@Override
	public int selectBestEmployeePayTotalCount(String searchCondition, String searchValue) {
		int count = 0;
		if(searchValue != null) {
			if("employeeName".equals(searchCondition)) {
				count = bestEmployeePayAndEmployeeRepository.countByEmployeeEmployeeNameContaining(searchValue);
			}

			if("employeePhone".equals(searchCondition)) {
				count = bestEmployeePayAndEmployeeRepository.countByEmployeeEmployeePhoneContaining(searchValue);
			}
			
				
		} else {
			count = (int)bestEmployeePayAndEmployeeRepository.count();
		}

		return count;
	}


	// 이달의 우수사원 전체 조회 
	@Override
	public List<BestEmployeePayAndEmployeeDTO> bestEmployeePaySearch(SelectCriteria selectCriteria) {
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("bestEmployeePayHistoryNo").descending());	// Pageable은 org.springframework.data.domain패키지로 import

		List<BestEmployeePayAndEmployee> bestEmployeePayList = new ArrayList<BestEmployeePayAndEmployee>();
		if(searchValue != null) {

			/* 직원 이름 검색일 경우 */
			if("employeeName".equals(selectCriteria.getSearchCondition())) {
				bestEmployeePayList = bestEmployeePayAndEmployeeRepository.findByEmployeeEmployeeNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
			/* 직원 휴대폰번호 검색일 경우 */
			if("employeePhone".equals(selectCriteria.getSearchCondition())) {
				bestEmployeePayList = bestEmployeePayAndEmployeeRepository.findByEmployeeEmployeePhoneContaining(selectCriteria.getSearchValue(), paging);
			}

			
			
		} else {
			bestEmployeePayList = bestEmployeePayAndEmployeeRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return bestEmployeePayList.stream().map(pay -> modelMapper.map(pay,BestEmployeePayAndEmployeeDTO.class)).collect(Collectors.toList());
	}



	// 직원 급여 상세조회
	@Override
	public EmployeePayAndApplyEmployeeDTO findEmployeePayByPayHistoryEmployeeNo(int payHistoryNo) {
		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		EmployeePayAndApplyEmployee pay = employeePayAndEmployeeRepository.findById(payHistoryNo).get();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(pay, EmployeePayAndApplyEmployeeDTO.class);
	}





	// 관리자아이디로 관리자찾기
	@Override
	public AdminDTO findAdminByAdminId(String adminId) {
		
		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		Admin admin = adminRepository.findByAdminId(adminId);
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(admin, AdminDTO.class);
	}

	
	@Override
	public int selectMyPayForAdminTotalCount(int adminNo) {
		int count = (int)adminRepository.count();
		System.out.println(count + "입니다");
		return count;
	}

	@Override
	public List<AdminPayAndAdminDTO> myPayForAdmin(int adminNo, SelectCriteria selectCriteria) {
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("payHistoryAdminNo").descending());	// Pageable은 org.springframework.data.domain패키지로 import

		List<AdminPayAndAdmin> adminPayList = new ArrayList<AdminPayAndAdmin>();
		adminPayList = adminPayAndAdminRepository.findAllByAdminAdminNo(adminNo,paging);

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return adminPayList.stream().map(pay -> modelMapper.map(pay,AdminPayAndAdminDTO.class)).toList();
	}











	@Override
	public EmployeeDTO findEmployeeByEmployeeId(String employeeId) {
		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(employee, EmployeeDTO.class);
	}




	@Override
	public int selectMyPayForEmployeeTotalCount(int employeeNo) {
		int count = (int)employeeRepository.count();
		System.out.println(count + "입니다");
		return count;
	}




	@Override
	public List<EmployeePayAndApplyEmployeeDTO> myPayForEmployee(int employeeNo, SelectCriteria selectCriteria) {
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("payHistoryEmployeeNo").descending());	// Pageable은 org.springframework.data.domain패키지로 import

		List<EmployeePayAndApplyEmployee> employeePayList = new ArrayList<EmployeePayAndApplyEmployee>();
		employeePayList = employeePayAndEmployeeRepository.findAllByEmployeeEmployeeNo(employeeNo,paging);

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return employeePayList.stream().map(pay -> modelMapper.map(pay,EmployeePayAndApplyEmployeeDTO.class)).toList();
	}













	

	



}
