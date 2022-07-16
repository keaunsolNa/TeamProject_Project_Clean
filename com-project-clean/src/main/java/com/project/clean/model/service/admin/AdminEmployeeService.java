package com.project.clean.model.service.admin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.domain.adminEntity.AdminReason;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ReasonDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.admin.ReasonRepository;
import com.project.clean.model.repository.employee.EmployeeReopsitory;

@Service
public class AdminEmployeeService {

	private final ModelMapper modelMapper; // modelMapper 빈을 선언
	private final EmployeeReopsitory employeeRepository;
	private final ReasonRepository reasonRepository;
	private final AdminRepository adminRepository;

	@Autowired
	public AdminEmployeeService(EmployeeReopsitory employeeReopsitory, ModelMapper modelMapper,
			ReasonRepository reasonRepository, AdminRepository adminRepository) {
		this.employeeRepository = employeeReopsitory;
		this.modelMapper = modelMapper;
		this.reasonRepository = reasonRepository;
		this.adminRepository = adminRepository;
	}

	/* 전체 직원 조회(재직자) */
	@Transactional
	public List<EmployeeAndAllDTO> selectRetireNEmployee() {
		List<AdminEmployee> selectRetireNEmployeeList = employeeRepository
				.findByEmployeeRetireYnAndEmployeeLastConfirmYn("N", "Y");
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return selectRetireNEmployeeList.stream().map(employee -> modelMapper.map(employee, EmployeeAndAllDTO.class))
				.toList();
	}

	/* 전체 직원 조회(퇴사자) */
	@Transactional
	public List<EmployeeAndAllDTO> selectRetireYEmployee() {
		List<AdminEmployee> selectRetireNEmployeeList = employeeRepository
				.findByEmployeeRetireYnAndEmployeeLastConfirmYn("Y", "Y");

		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return selectRetireNEmployeeList.stream().map(employee -> modelMapper.map(employee, EmployeeAndAllDTO.class))
				.toList();
	}

	@Transactional
	public EmployeeAndAllDTO selectOneEmployee(int EmpNo) {
		AdminEmployee employee = employeeRepository.findById(EmpNo).get();

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
	public String getsysdate() {
		String sysdate = employeeRepository.sysdate();
		return sysdate;
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

		employee.setEmployeePhone(employeeDTO.getEmployeePhone());
		employee.setEmployeeRetireYn(employeeDTO.getEmployeeRetireYn());
		employee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
		employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());

	}

	@Transactional
	public List<EmployeeAndAllDTO> selectWaitingEmployeeList() {
		List<AdminEmployee> selectWaitingEmployeeList = employeeRepository
				.findByEmployeeFirstConfirmYnAndEmployeeLastConfirmYn("N", "N");

		return selectWaitingEmployeeList.stream().map(waiting -> modelMapper.map(waiting, EmployeeAndAllDTO.class))
				.toList();
	}

	@Transactional
	public List<EmployeeAndAllDTO> selectReturnEmployeeList() {
		List<AdminEmployee> selectReturnEmployeeList = employeeRepository.findByEmployeeRegistReturnYn("Y");

		return selectReturnEmployeeList.stream().map(waiting -> modelMapper.map(waiting, EmployeeAndAllDTO.class))
				.toList();
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
		employee.setEmployeeFirstConfirmYn("Y");
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
		/* save를 했는데 왜 update? */
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();
		employee.setEmployeeRegistReturnYn("Y");

	}

	@Transactional
	public List<EmployeeAndAllDTO> selectWaitingEmployeeListBoss() {
		List<AdminEmployee> selectWaitingEmployeeList = employeeRepository
				.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeLastConfirmYnAndEmployeeRegistReturnYn(
						"Y", "N", "N", "N");

		return selectWaitingEmployeeList.stream().map(waiting -> modelMapper.map(waiting, EmployeeAndAllDTO.class))
				.toList();
	}


}
