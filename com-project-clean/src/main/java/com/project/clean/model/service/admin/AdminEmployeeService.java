package com.project.clean.model.service.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.domain.adminEntity.AdminReason;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.Vacation;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ReasonDTO;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.admin.ReasonRepository;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.employee.EmployeeReopsitory;
import com.project.clean.model.repository.vacation.VacationRepository;

@Service
public class AdminEmployeeService {

	private final ModelMapper modelMapper; // modelMapper 빈을 선언
	private final EmployeeReopsitory employeeRepository;
	private final ReasonRepository reasonRepository;
	private final AdminRepository adminRepository;
	private final VacationRepository vacationRepository;
	private final int selectEmployeeLineCount = 1;

	@Autowired
	public AdminEmployeeService(EmployeeReopsitory employeeReopsitory, ModelMapper modelMapper,
			ReasonRepository reasonRepository, AdminRepository adminRepository, VacationRepository vacationRepository) {
		this.employeeRepository = employeeReopsitory;
		this.modelMapper = modelMapper;
		this.reasonRepository = reasonRepository;
		this.adminRepository = adminRepository;
		this.vacationRepository = vacationRepository;
	}

//	/* 전체 직원 조회(재직자) */
//	@Transactional
//	public List<EmployeeAndAllDTO> selectRetireNEmployee(Pageable page) {
//		
//		List<AdminEmployee> startPageList = employeeRepository
//				.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYn("N", "Y", "N", page);
//		
//		return startPageList.stream().map(start -> modelMapper.map(start, EmployeeAndAllDTO.class)).toList();
//		
//	}
//

	@Transactional
	public EmployeeAndAllDTO selectOneEmployee(EmployeeAndAllDTO employeeDTO) {
		AdminEmployee employee = employeeRepository.findById(employeeDTO.getEmployeeNo()).get();

		employee.setEmployeePhone(employeeDTO.getEmployeePhone());
		employee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
		employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
		employee.setEmployeeRetireYn(employeeDTO.getEmployeeRetireYn());

		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}

	public EmployeeAndAllDTO selectOneEmployee(int empNo) {

		AdminEmployee employee = employeeRepository.findById(empNo).get();
		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}

//	@Transactional
//	public EmployeePictureDTO selectOneEmployeePicture(int empNo) {
//		AdminEmployeePicture employeePicture = employeePictureRepository.findById(empNo).get();
//
//		return modelMapper.map(employeePicture, EmployeePictureDTO.class);
//	}

	@Transactional
	public void modifyEmployee(int empNo, String entireYn) {
		AdminEmployee employee = employeeRepository.findById(empNo).get();
		employee.setEmployeeRetireYn(entireYn);
	}

	@Transactional
	public int getMaxMemberNo() {
		int maxMemberNo = employeeRepository.getMaxMemberNo();
		return maxMemberNo;
	}

	@Transactional
	public void registEmployee(EmployeeAndAllDTO employeeDTO) {
		employeeRepository.save(modelMapper.map(employeeDTO, AdminEmployee.class));

	}

	@Transactional
	public void registEmployeeCommit(ReasonDTO ReasonDTO) {
		reasonRepository.save(modelMapper.map(ReasonDTO, AdminReason.class));

	}

	@Transactional
	public void modifyEmployee(EmployeeAndAllDTO employeeDTO) {
		AdminEmployee employee = employeeRepository.findById(employeeDTO.getEmployeeNo()).get();

		employee.setEmployeePictureSaveRoot(employeeDTO.getEmployeePictureSaveRoot());
		employee.setEmployeePictureSaveName(employeeDTO.getEmployeePictureSaveName());
		employee.setEmployeePhone(employeeDTO.getEmployeePhone());
		employee.setEmployeeRetireYn(employeeDTO.getEmployeeRetireYn());
		employee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
		employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());

	}

	@Transactional
	public Map<String, Object> selectWaitingEmployeeListHr(String category, String categoryValue, Pageable pageable) {
		Page<AdminEmployee> paging;
		List<EmployeeAndAllDTO> empDTOList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYn("N", "N", "N", pageable);
		} else if ("name".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeeNameContaining("N", "N", "N", categoryValue, pageable);
		} else if ("address".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeeAddressContaining("N", "N", "N", categoryValue, pageable);
		} else if ("phone".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeePhoneContaining("N", "N", "N", categoryValue, pageable);
		} else {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYn("N", "N", "N", pageable);
		}
		List<AdminEmployee> employeeList = paging.getContent();

		empDTOList = employeeList.stream().map(name -> modelMapper.map(name, EmployeeAndAllDTO.class)).toList();

		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}

		map.put("waitingList", empDTOList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);

		return map;
	}
	

	@Transactional
	public Map<String, Object> selectWaitingEmployeeListBoss(String category, String categoryValue, Pageable pageable) {
		Page<AdminEmployee> paging;
		List<EmployeeAndAllDTO> empDTOList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYn("Y", "N", "N", pageable);
		} else if ("name".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeeNameContaining("Y", "N", "N", categoryValue, pageable);
		} else if ("address".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeeAddressContaining("Y", "N", "N", categoryValue, pageable);
		} else if ("phone".equals(category)) {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYnAndEmployeePhoneContaining("Y", "N", "N", categoryValue, pageable);
		} else {
			paging = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeRegistReturnYn("Y", "N", "N", pageable);
		}
		List<AdminEmployee> employeeList = paging.getContent();

		empDTOList = employeeList.stream().map(name -> modelMapper.map(name, EmployeeAndAllDTO.class)).toList();

		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}

		map.put("waitingList", empDTOList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);

		return map;
	}

//	@Transactional
//	public List<EmployeeAndAllDTO> selectReturnEmployeeList() {
//		
//		List<AdminEmployee> selectReturnEmployeeList = employeeRepository.findByEmployeeRegistReturnYnAndEmployeeBlackListYn("Y", "N");
//
//		return selectReturnEmployeeList.stream().map(waiting -> modelMapper.map(waiting, EmployeeAndAllDTO.class))
//				.toList();
//	}

//	@Transactional
//	public Page<EmployeeAndAllDTO> selectRetireNEmployee(int startAt) {
//
//		Pageable pageable = PageRequest.of(startAt, selectEmployeeLineCount);
//		Page<AdminEmployee> selectRetureNEmployee = employeeRepository
//				.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYn("N", "Y", "N", pageable);
//
//		return modelMapper.map(selectRetureNEmployee, Page.class);
//	}
	
	@Transactional
	public Map<String, Object> selectRetireNEmployee(String category, String categoryValue, Pageable pageable) {
		Page<AdminEmployee> paging;
		List<EmployeeAndAllDTO> empDTOList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYn("N", "Y", "N", pageable);
		} else if ("no".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeNo("N", "Y", "N", categoryValue, pageable);
		} else if ("name".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeNameContaining("N", "Y", "N", categoryValue, pageable);
		} else if ("address".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeAddressContaining("N", "Y", "N", categoryValue, pageable);
		} else if ("phone".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeePhoneContaining("N", "Y", "N", categoryValue, pageable);
		} else {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYn("N", "Y", "N", pageable);
		}
		List<AdminEmployee> employeeList = paging.getContent();

		empDTOList = employeeList.stream().map(name -> modelMapper.map(name, EmployeeAndAllDTO.class)).toList();

		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}

		map.put("retireNEmployee", empDTOList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);
		map.put("mainTain", "N");

		return map;
	}

	public Map<String, Object> selectRetireYEmployee(String category, String categoryValue, Pageable pageable) {
		Page<AdminEmployee> paging;
		List<EmployeeAndAllDTO> empDTOList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYn("Y", "Y", "N", pageable);
		} else if ("no".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeNo("Y", "Y", "N", categoryValue, pageable);
		} else if ("name".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeNameContaining("Y", "Y", "N", categoryValue, pageable);
		} else if ("address".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeeAddressContaining("Y", "Y", "N", categoryValue, pageable);
		} else if ("phone".equals(category)) {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYnAndEmployeePhoneContaining("Y", "Y", "N", categoryValue, pageable);
		} else {
			paging = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYnAndEmployeeBlackListYn("Y", "Y", "N", pageable);
		}
		List<AdminEmployee> employeeList = paging.getContent();

		empDTOList = employeeList.stream().map(name -> modelMapper.map(name, EmployeeAndAllDTO.class)).toList();

		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}

		map.put("retireYEmployee", empDTOList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);
		map.put("mainTain", "Y");

		return map;
	}

	@Transactional
	public EmployeeAndAllDTO waitingEmployee(int empNo) {
		AdminEmployee employee = employeeRepository.findById(empNo).get();

		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}

	@Transactional
	public EmployeeAndAllDTO returnEmployee(int empNo) {
		AdminEmployee employee = employeeRepository.findById(empNo).get();

		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}

	@Transactional
	public List<ReasonDTO> getRegistDate(int empNo) {
		List<AdminReason> employeeCommitList = reasonRepository.findAllByEmployeeNo(empNo,
				Sort.by("adminNo").descending());
		if (employeeCommitList == null) {
			employeeCommitList = new ArrayList<>();
		}
		return employeeCommitList.stream().map(commit -> modelMapper.map(commit, ReasonDTO.class)).toList();
	}

	@Transactional
	public List<AdminDTO> getAdminName(int empNo) {
		List<Admin> employeeCommitList = adminRepository.findByAdminName(empNo);
		if (employeeCommitList == null) {
			employeeCommitList = new ArrayList<>();
		}
		return employeeCommitList.stream().map(commit -> modelMapper.map(commit, AdminDTO.class)).toList();
	}

//	@Transactional
//	public List<ReasonDTO> returnEmployeeRest(int empNo) {
//		List<AdminReason> employeeCommitList = reasonRepository.findByEmployeeNo(empNo);
//		
//		return employeeCommitList.stream().map(commit -> modelMapper.map(commit, ReasonDTO.class)).toList();
//	}

	@Transactional
	public void insertRestCommitConfirm(ReasonDTO restCommitDTO) {
		reasonRepository.save(modelMapper.map(restCommitDTO, AdminReason.class));
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();
		employee.setEmployeeFirstConfirmYn("Y");

	}

	@Transactional
	public void insertRestCommitReturn(ReasonDTO restCommitDTO) {
		reasonRepository.save(modelMapper.map(restCommitDTO, AdminReason.class));
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();
		employee.setEmployeeRegistReturnYn("Y");
	}

	@Transactional
	public void insertAndupdateRestCommitConfirmBoss(ReasonDTO restCommitDTO) {
		LocalDate now = LocalDate.now();
		java.sql.Date today = java.sql.Date.valueOf(now);

		restCommitDTO.setEmployeeRegistDate(today);
		reasonRepository.save(modelMapper.map(restCommitDTO, AdminReason.class));
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();

		employee.setEmployeeHireDate(today);
		employee.setEmployeeSecondConfirmYn("Y");
		employee.setEmployeeLastConfirmYn("Y");
		employee.setEmployeeLastConfirmDate(today);

	}

	/* 사장 2차 반려 */
	@Transactional
	public void insertAndupdateRestCommitReturnBoss(ReasonDTO restCommitDTO) {
		LocalDate now = LocalDate.now();
		java.sql.Date today = java.sql.Date.valueOf(now);

		restCommitDTO.setEmployeeRegistDate(today);

		reasonRepository.save(modelMapper.map(restCommitDTO, AdminReason.class));
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();
		employee.setEmployeeRegistReturnYn("Y");

	}



	public VacationDTO selectMyVacaionList(int vacationNo) {
		Vacation vacation = vacationRepository.findByVacationNo(vacationNo, Sort.by("adminNo").descending());
		return modelMapper.map(vacation, VacationDTO.class);
	}

	public List<AdminDTO> selectAdmin() {
		List<Admin> admin = adminRepository.findAll(Sort.by("adminNo"));
		return admin.stream().map(adminInfo -> modelMapper.map(adminInfo, AdminDTO.class)).toList();

	}

	
	@Transactional
	public Map<String, Object> selectReturnYEmployee(String category, String categoryValue, Pageable pageable) {
		Page<AdminEmployee> paging;
		List<EmployeeAndAllDTO> empDTOList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = employeeRepository.findByEmployeeRegistReturnYn("Y", pageable);
		} else if ("no".equals(category)) {
			paging = employeeRepository.findByEmployeeRegistReturnYnAndEmployeeNoContaining("Y", categoryValue, pageable);
		} else if ("name".equals(category)) {
			paging = employeeRepository.findByEmployeeRegistReturnYnAndEmployeeNameContaining("Y", categoryValue, pageable);
		} else if ("phone".equals(category)) {
			paging = employeeRepository.findByEmployeeRegistReturnYnAndEmployeePhoneContaining("Y", categoryValue, pageable);
		} else {
			paging = employeeRepository.findByEmployeeRegistReturnYn("Y", pageable);
		}
		List<AdminEmployee> employeeList = paging.getContent();

		empDTOList = employeeList.stream().map(name -> modelMapper.map(name, EmployeeAndAllDTO.class)).toList();

		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}

		map.put("returnEmployeeList", empDTOList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);

		return map;
	}

	public Map<String, Object> selectBlackList(String category, String categoryValue, Pageable pageable) {
		
		Page<AdminEmployee> paging;
		List<EmployeeAndAllDTO> empDTOList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = employeeRepository.findByEmployeeBlackListYn("Y", pageable);
		} else if ("address".equals(category)) {
			paging = employeeRepository.findByEmployeeBlackListYnAndEmployeeAddressContaining("Y", categoryValue, pageable);
		} else if ("name".equals(category)) {
			paging = employeeRepository.findByEmployeeBlackListYnAndEmployeeNameContaining("Y", categoryValue, pageable);
		} else if ("phone".equals(category)) {
			paging = employeeRepository.findByEmployeeBlackListYnAndEmployeePhoneContaining("Y", categoryValue, pageable);
		} else {
			paging = employeeRepository.findByEmployeeBlackListYn("Y", pageable);
		}
		
		List<AdminEmployee> employeeList = paging.getContent();

		empDTOList = employeeList.stream().map(name -> modelMapper.map(name, EmployeeAndAllDTO.class)).toList();

		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}

		map.put("employeeList", empDTOList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);

		return map;
	}

	public Map<String, Object> selectMyVacaionList(int adminNo, Pageable pageable) {
		Page<Vacation> paging;
		List<VacationDTO> vacationList = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		paging = vacationRepository.findByAdminNo(adminNo, pageable);
		
//		if ("all".equals(category)) {
//		} else if ("request".equals(category)) {
//			paging = vacationRepository.findByAdminNoAndRequestDateBetweeen(adminNo, startDate, endDate, pageable);
//		} else if ("start".equals(category)) {
//			paging = vacationRepository.findByAdminNoAndVacationStartDateBetweeen(adminNo, startDate, endDate, pageable);
//		} else if ("end".equals(category)) {
//			paging = vacationRepository.findByAdminNoAndVacationEndDateBetweeen(adminNo, startDate, endDate, pageable);
//		} else {
//			paging = vacationRepository.findByAdminNo(adminNo, pageable);
//		}
		
		List<Vacation> employeeList = paging.getContent();

		vacationList = employeeList.stream().map(name -> modelMapper.map(name, VacationDTO.class)).toList();

		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}

		map.put("vacationList", vacationList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
//		map.put("category", category);
//		map.put("categoryValue", categoryValue);

		return map;
	}

	@Transactional
	public Map<String, Object> selectAllVacaionList(String category, String categoryValue, String StartDate, String endDate, Pageable pageable) {
		
		Page<Vacation> paging;
		List<VacationDTO> vacationList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = vacationRepository.findAll(pageable);
		} else if ("name".equals(category)) {
			paging = vacationRepository.findByRequestAdminContaining(categoryValue, pageable);
			List<Vacation> vacationInfoList = paging.getContent();
			vacationList = vacationInfoList.stream().map(name -> modelMapper.map(name, VacationDTO.class)).toList();
			
		} else if ("startDate".equals(category)) {
			paging = vacationRepository.selectBetweenStartDate(StartDate, endDate, pageable);
		} else if ("endDate".equals(category)) {
			paging = vacationRepository.selectBetweenEndDate(StartDate, endDate, pageable);
		} else {
			paging = vacationRepository.findAll(pageable);
		}
		
		List<Vacation> vacationInfoList = paging.getContent();

		vacationList = vacationInfoList.stream().map(name -> modelMapper.map(name, VacationDTO.class)).toList();
		
		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;

		while (endPage > maxPage) {
			endPage -= 1;
		}
		
		map.put("vacationList", vacationList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);

		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Transactional
	public Map<String, Object> selectAllVacationConfirmList(String category, String categoryValue, String StartDate, String endDate, Pageable pageable) {
		
		Page<Vacation> paging;
		List<VacationDTO> vacationList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = vacationRepository.findByVacationLastConfirmYn("Y", pageable);
		} else if ("name".equals(category)) {
			paging = vacationRepository.findByVacationLastConfirmYnAndRequestAdminContaining("Y", categoryValue, pageable);
			List<Vacation> vacationInfoList = paging.getContent();
			vacationList = vacationInfoList.stream().map(name -> modelMapper.map(name, VacationDTO.class)).toList();
			
		} else if ("startDate".equals(category)) {
			paging = vacationRepository.selectConfirmBetweenStartDate(StartDate, endDate, pageable);
		} else if ("endDate".equals(category)) {
			paging = vacationRepository.selectConfirmBetweenEndDate(StartDate, endDate, pageable);
		} else {
			paging = vacationRepository.findByVacationLastConfirmYn("Y", pageable);
		}
		
		List<Vacation> vacationInfoList = paging.getContent();
		
		vacationList = vacationInfoList.stream().map(name -> modelMapper.map(name, VacationDTO.class)).toList();
		
		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;
		
		while (endPage > maxPage) {
			endPage -= 1;
		}
		
		map.put("vacationList", vacationList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);
		
		return map;
	}
	
	
	
	
	@Transactional
	public Map<String, Object> selectAllVacaionReturnList(String category, String categoryValue, String StartDate, String endDate, Pageable pageable) {
		
		Page<Vacation> paging;
		List<VacationDTO> vacationList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if ("all".equals(category)) {
			paging = vacationRepository.findByVacationReturnYn("Y",pageable);
		} else if ("name".equals(category)) {
			paging = vacationRepository.findByVacationReturnYnAndRequestAdminContaining("Y", categoryValue, pageable);
			List<Vacation> vacationInfoList = paging.getContent();
			vacationList = vacationInfoList.stream().map(name -> modelMapper.map(name, VacationDTO.class)).toList();
			
		} else if ("startDate".equals(category)) {
			paging = vacationRepository.selectReturnBetweenStartDate(StartDate, endDate, pageable);
		} else if ("endDate".equals(category)) {
			paging = vacationRepository.selectReturnBetweenEndDate(StartDate, endDate, pageable);
		} else {
			paging = vacationRepository.findByVacationReturnYn("Y",pageable);
		}
		
		List<Vacation> vacationInfoList = paging.getContent();
		
		vacationList = vacationInfoList.stream().map(name -> modelMapper.map(name, VacationDTO.class)).toList();
		
		int currentPage = paging.getNumber();
		int maxPage = paging.getTotalPages();
		int startPage = (int) (currentPage / 5) * 5;
		int endPage = (int) (currentPage / 5) * 5 + 5;
		
		while (endPage > maxPage) {
			endPage -= 1;
		}
		
		map.put("vacationList", vacationList);
		map.put("maxPage", maxPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		map.put("category", category);
		map.put("categoryValue", categoryValue);
		
		return map;
	}

	@Transactional
	public void retireEmployee(int empNo) {
		AdminEmployee employeeDTO = employeeRepository.findById(empNo).get();
		employeeDTO.setEmployeeRetireYn("Y");
	}

	@Transactional
	public EmployeeAndAllDTO deletePicture(int employeeNo) {
		AdminEmployee empDTO = employeeRepository.findById(employeeNo).get(); 
		EmployeeAndAllDTO employeeDTO = modelMapper.map(empDTO, EmployeeAndAllDTO.class);
		
		/* 2. 사진 삭제를 위한 saveName 구하고 */
		String saveName = employeeDTO.getEmployeePictureSaveName();
		employeeDTO.setEmployeePictureSaveName(saveName);
		
		/*3. 해당 직원 사진 관련 DB null로 변경하고 */
		empDTO.setEmployeePictureSaveName(null);
		empDTO.setEmployeePictureSaveRoot(null);
		
		return employeeDTO;
	}
}































