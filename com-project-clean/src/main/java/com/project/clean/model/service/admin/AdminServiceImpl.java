package com.project.clean.model.service.admin;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.adminEntity.*;
import com.project.clean.model.dto.commonDTO.*;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.repository.admin.*;

@Service
public class AdminServiceImpl implements AdminService {

	private final ModelMapper modelMapper; // modelMapper 빈을 선언
	private final EmployeeReopsitory employeeRepository;
	private final EmployeeAddressRepository employeeAddressRepository;
	private final EmployeeEmailRepository employeeEmailRepository;
	private final EmployeePictureRepository employeePictureRepository;
	private final EmployeeRestCommitRepository employeeRestCommitRepository;

	@Autowired
	public AdminServiceImpl(EmployeeReopsitory employeeReopsitory, EmployeeAddressRepository employeeAddressRepository
			   , ModelMapper modelMapper, EmployeeEmailRepository employeeEmailRepository, EmployeePictureRepository employeePictureRepository, EmployeeRestCommitRepository employeeRestCommitRepository) {
		this.employeeRepository = employeeReopsitory;
		this.employeeAddressRepository = employeeAddressRepository;
		this.employeeEmailRepository = employeeEmailRepository;
		this.employeePictureRepository = employeePictureRepository;
		this.modelMapper = modelMapper;
		this.employeeRestCommitRepository = employeeRestCommitRepository;
		}

	/* 전체 직원 조회(재직자) */
	@Transactional
	public List<EmployeeAndAllDTO> selectRetireNEmployee() {
		List<AdminEmployee> selectRetireNEmployeeList = 
				employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYn("N","Y");
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return selectRetireNEmployeeList.stream().map(employee -> modelMapper.map(employee, EmployeeAndAllDTO.class))
				.toList();
	}
	
	
	/* 전체 직원 조회(퇴사자) */
	@Transactional
	public List<EmployeeAndAllDTO> selectRetireYEmployee() {
		List<AdminEmployee> selectRetireNEmployeeList = employeeRepository.findByEmployeeRetireYnAndEmployeeLastConfirmYn("Y","Y");
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return selectRetireNEmployeeList.stream().map(employee -> modelMapper.map(employee, EmployeeAndAllDTO.class))
				.toList();
	}
	
	

	/* 직원 주소 조회(재직자) */
	@Transactional
	public List<EmployeeAddressDTO> selectRetireNEmployeeAddress() {
		List<AdminEmployeeAddress> selectRetireYEmployeeAddressList = 
				employeeAddressRepository.selectRetireNEmployeeAddressList();
		return selectRetireYEmployeeAddressList.stream()
				.map(address -> modelMapper.map(address, EmployeeAddressDTO.class)).toList();
	}
	/* 직원 주소 조회(퇴사자) */
	@Transactional
	public List<EmployeeAddressDTO> selectRetireYEmployeeAddress() {
		List<AdminEmployeeAddress> selectRetireYEmployeeAddressList = 
				employeeAddressRepository.selectRetireYEmployeeAddressList();
		return selectRetireYEmployeeAddressList.stream()
				.map(address -> modelMapper.map(address, EmployeeAddressDTO.class)).toList();
	}
	@Transactional
	public EmployeeAndAllDTO selectOneEmployee(int EmpNo) {
		AdminEmployee employee = employeeRepository.findById(EmpNo).get();

		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}
	@Transactional
	public EmployeeAddressDTO selectOneEmployeeAddress(int EmpNo) {
		AdminEmployeeAddress employeeAddress = employeeAddressRepository.findById(EmpNo).get();

		return modelMapper.map(employeeAddress, EmployeeAddressDTO.class);
	}
	@Transactional
	public EmployeeEmailDTO selectOneEmployeeEmail(int empNo) {
		AdminEmployeeEmail employeeEmail = employeeEmailRepository.findById(empNo).get();

		return modelMapper.map(employeeEmail, EmployeeEmailDTO.class);
	}
	@Transactional
	public EmployeePictureDTO selectOneEmployeePicture(int empNo) {
		AdminEmployeePicture employeePicture = employeePictureRepository.findById(empNo).get();

		return modelMapper.map(employeePicture, EmployeePictureDTO.class);
	}
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
		employeeAddressRepository.save(modelMapper.map(employeeDTO.getEmployeeAddressDTO(), AdminEmployeeAddress.class));
		employeeEmailRepository.save(modelMapper.map(employeeDTO.getEmployeeEmailDTO(), AdminEmployeeEmail.class));
		employeeRestCommitRepository.save(modelMapper.map(employeeDTO.getEmployeeRestCommitDTO(), AdminEmployeeRestCommit.class));
		
	}
	@Transactional
	public void registEmployeeAddress(EmployeeAddressDTO addressDTO) {
		employeeAddressRepository.save(modelMapper.map(addressDTO, AdminEmployeeAddress.class));
		
	}
	@Transactional
	public void registEmployeeEmail(EmployeeEmailDTO emailDTO) {
		employeeEmailRepository.save(modelMapper.map(emailDTO, AdminEmployeeEmail.class));
		
	}
	@Transactional
	public void registEmployeeCommit(EmployeeRestCommitDTO employeeRestCommitDTO) {
		employeeRestCommitRepository.save(modelMapper.map(employeeRestCommitDTO, AdminEmployeeRestCommit.class));
		
	}

	@Transactional
	public void modifyEmployee(EmployeeAndAllDTO employeeDTO) {
		AdminEmployee employee = employeeRepository.findById(employeeDTO.getEmployeeNo()).get();
		AdminEmployeeEmail email = employeeEmailRepository.findById(employeeDTO.getEmployeeNo()).get();
		AdminEmployeeAddress address = employeeAddressRepository.findById(employeeDTO.getEmployeeNo()).get();

		
		employee.setEmployeePhone(employeeDTO.getEmployeePhone());
		employee.setEmployeeRetireYn(employeeDTO.getEmployeeRetireYn());
		email.setEmployeeEmail(employeeDTO.getEmployeeEmailDTO().getEmployeeEmail());
		email.setEmployeeDomain(employeeDTO.getEmployeeEmailDTO().getEmployeeDomain());
		address.setEmployeeAddressNo(employeeDTO.getEmployeeAddressDTO().getEmployeeAddressNo());
		address.setEmployeeAddress(employeeDTO.getEmployeeAddressDTO().getEmployeeAddress());
		address.setEmployeeDetailAddress(employeeDTO.getEmployeeAddressDTO().getEmployeeDetailAddress());
		
	}
	@Transactional
	public List<EmployeeAndAllDTO> selectWaitingEmployeeList() {
		List<AdminEmployee> selectWaitingEmployeeList = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeLastConfirmYn("N","N");
		
		return selectWaitingEmployeeList.stream().map(waiting -> modelMapper.map(waiting, EmployeeAndAllDTO.class)).toList();
	}
	@Transactional
	public List<EmployeeAddressDTO> selectWaitingEmployeeAddressList() {
		List<AdminEmployeeAddress> waitingEmployeeAddressList = employeeAddressRepository.findByFirstConfirmN();
		return waitingEmployeeAddressList.stream().map(waitingAddress -> modelMapper.map(waitingAddress, EmployeeAddressDTO.class)).toList();
	}
	@Transactional
	public List<EmployeeAndAllDTO> selectReturnEmployeeList() {
		List<AdminEmployee> selectReturnEmployeeList = employeeRepository.findByEmployeeRegistReturnYn("Y");
		
		return selectReturnEmployeeList.stream().map(waiting -> modelMapper.map(waiting, EmployeeAndAllDTO.class)).toList();
	}

	@Transactional
	public List<EmployeeAddressDTO> selectReturnEmployeeAddressList() {
		List<AdminEmployeeAddress> selectReturnEmployeeAddressList = employeeAddressRepository.findByRegistReturnN();
		return selectReturnEmployeeAddressList.stream().map(waitingAddress -> modelMapper.map(waitingAddress, EmployeeAddressDTO.class)).toList();
	}
	@Transactional
	public EmployeeAndAllDTO waitingEmployee(int empNo) {
		AdminEmployee employee = employeeRepository.findById(empNo).get();
		modelMapper.map(employee, EmployeeAddressDTO.class);
		
		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}
	@Transactional
	public EmployeeAddressDTO waitingEmployeeAddress(int empNo) {
		AdminEmployeeAddress employeeAddress = employeeAddressRepository.findById(empNo).get();
		modelMapper.map(employeeAddress, EmployeeAddressDTO.class);
		
		return modelMapper.map(employeeAddress, EmployeeAddressDTO.class);
	}
	@Transactional
	public EmployeeEmailDTO waitingEmployeeEmail(int empNo) {
		AdminEmployeeEmail employeeEmail = employeeEmailRepository.findById(empNo).get();
		modelMapper.map(employeeEmail, EmployeeEmailDTO.class);
		
		return modelMapper.map(employeeEmail, EmployeeEmailDTO.class);
	}
	@Transactional
	public EmployeeAndAllDTO returnEmployee(int empNo) {
		AdminEmployee employee = employeeRepository.findById(empNo).get();
		
		modelMapper.map(employee, EmployeeAddressDTO.class);
		
		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}
	@Transactional
	public EmployeeEmailDTO returnEmployeeEmail(int empNo) {
		AdminEmployeeEmail employeeEmail = employeeEmailRepository.findById(empNo).get();
		modelMapper.map(employeeEmail, EmployeeEmailDTO.class);
		
		return modelMapper.map(employeeEmail, EmployeeEmailDTO.class);
	}
	@Transactional
	public EmployeeAddressDTO returnEmployeeAddress(int empNo) {
		AdminEmployeeAddress employeeAddress = employeeAddressRepository.findById(empNo).get();
		modelMapper.map(employeeAddress, EmployeeAddressDTO.class);
		
		return modelMapper.map(employeeAddress, EmployeeAddressDTO.class);
	}
	@Transactional
	public EmployeeRestCommitDTO waitingEmployeeRest(int empNo) {
		AdminEmployeeRestCommit employeeCommit = employeeRestCommitRepository.findById(empNo).get();
		
		return modelMapper.map(employeeCommit, EmployeeRestCommitDTO.class);
	}
	@Transactional
	public EmployeeRestCommitDTO returnEmployeeRest(int empNo) {
		AdminEmployeeRestCommit employeeCommit = employeeRestCommitRepository.findByEmployeeNo(empNo);
		
		return modelMapper.map(employeeCommit, EmployeeRestCommitDTO.class);
	}

	@Transactional
	public void updateRestCommitConfirm(EmployeeRestCommitDTO restCommitDTO) {
		AdminEmployeeRestCommit restCommitConfirm = employeeRestCommitRepository.findByEmployeeNo(restCommitDTO.getEmployeeNo());
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();
		restCommitConfirm.setAdminNo(restCommitDTO.getAdminNo());
		restCommitConfirm.setReturnReason(restCommitDTO.getReturnReason());
		
		employee.setEmployeeFirstConfirmYn("Y");
		
	}
	
	@Transactional
	public void updateRestCommitReturn(EmployeeRestCommitDTO restCommitDTO) {
		AdminEmployeeRestCommit restCommitReturn = employeeRestCommitRepository.findByEmployeeNo(restCommitDTO.getEmployeeNo());
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();
		employee.setEmployeeFirstConfirmYn("Y");
		employee.setEmployeeRegistReturnYn("Y");
		restCommitReturn.setAdminNo(restCommitDTO.getAdminNo());
		restCommitReturn.setReturnReason(restCommitDTO.getReturnReason());
		
	}










}
