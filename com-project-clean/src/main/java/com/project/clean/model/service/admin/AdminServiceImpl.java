package com.project.clean.model.service.admin;

<<<<<<< HEAD
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
=======
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> master

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.domain.adminEntity.AdminEmployeeAddress;
import com.project.clean.model.domain.adminEntity.AdminEmployeeEmail;
import com.project.clean.model.domain.adminEntity.AdminEmployeePicture;
import com.project.clean.model.domain.adminEntity.AdminReason;
import com.project.clean.model.dto.commonDTO.EmployeeAddressDTO;
import com.project.clean.model.dto.commonDTO.EmployeeEmailDTO;
import com.project.clean.model.dto.commonDTO.EmployeePictureDTO;
import com.project.clean.model.dto.commonDTO.ReasonDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.repository.admin.ReasonRepository;
import com.project.clean.model.repository.employee.EmployeeAddressRepository;
import com.project.clean.model.repository.employee.EmployeeEmailRepository;
import com.project.clean.model.repository.employee.EmployeePictureRepository;
import com.project.clean.model.repository.employee.EmployeeReopsitory;

import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.AdminAddress;
import com.project.clean.model.domain.commonEntity.AdminEmail;
import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
import com.project.clean.model.repository.admin.AdminAddressRepository;
import com.project.clean.model.repository.admin.AdminEmailRepository;
import com.project.clean.model.repository.admin.AdminRepository;


@Service
public class AdminServiceImpl implements AdminService {
	
	private final AdminRepository adminRepository;
	private final ModelMapper modelMapper;
	private final AdminEmailRepository adminEmailRepository;
	private final AdminAddressRepository adminAddressRepository;

	
	@Autowired
	public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper, AdminEmailRepository adminEmailRepository, AdminAddressRepository adminAddressRepository) {
		this.adminRepository = adminRepository;
		this.modelMapper = modelMapper;
		this.adminEmailRepository = adminEmailRepository;
		this.adminAddressRepository = adminAddressRepository;
	}


	@Override
	public List<AdminDTO> findAdminList() {
		
		/* 퇴사여부가 Y(yes) 가 아닌 관리자 조회 */
		List<Admin> adminList = adminRepository.findAdminByAdminRetireYnNotLike("Y");
		
		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
	}


//	@Override
//	public List<AdminAndEmailDTO> findByAdminNo(int adminNo) {
//		
//		List<Admin> adminEmail = adminRepository.findByAdminNo(adminNo).get();
//		
//		return modelMapper.map(adminEmail, AdminAndEmailDTO.class);
//	}


	/* 관리자 기본정보 상세 조회 */
	@Override
	public AdminDTO findByAdminNo(int adminNo) {
		
		Admin admin = adminRepository.findByAdminNo(adminNo).get();
		
		return modelMapper.map(admin, AdminDTO.class);
	}


	@Override
	public AdminEmailDTO findAdminEmailByAdminNoEqual(int adminNo) {
		
		AdminEmail adminEmail = adminEmailRepository.findByAdminNo(adminNo);
		
		return modelMapper.map(adminEmail, AdminEmailDTO.class);

	}


	@Override
	public AdminAddressDTO findAdminAddressByAdminNo(int adminNo) {
		
		AdminAddress adminAddress = adminAddressRepository.findByAdminNo(adminNo);
		
		return modelMapper.map(adminAddress, AdminAddressDTO.class);
		
	}


	






	


	

	private final ModelMapper modelMapper; // modelMapper 빈을 선언
	private final EmployeeReopsitory employeeRepository;
	private final EmployeeAddressRepository employeeAddressRepository;
	private final EmployeeEmailRepository employeeEmailRepository;
	private final EmployeePictureRepository employeePictureRepository;
	private final ReasonRepository reasonRepository;

	@Autowired
	public AdminServiceImpl(EmployeeReopsitory employeeReopsitory, EmployeeAddressRepository employeeAddressRepository
			   , ModelMapper modelMapper, EmployeeEmailRepository employeeEmailRepository, EmployeePictureRepository employeePictureRepository, ReasonRepository reasonRepository) {
		this.employeeRepository = employeeReopsitory;
		this.employeeAddressRepository = employeeAddressRepository;
		this.employeeEmailRepository = employeeEmailRepository;
		this.employeePictureRepository = employeePictureRepository;
		this.modelMapper = modelMapper;
		this.reasonRepository = reasonRepository;
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
	public void registEmployeeCommit(ReasonDTO ReasonDTO) {
		reasonRepository.save(modelMapper.map(ReasonDTO, AdminReason.class));
		
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
	public List<ReasonDTO> adminSignWaitingEmployee(int empNo) {
		List<AdminReason> employeeCommitList = reasonRepository.findById(empNo);
		if(employeeCommitList == null) {
			employeeCommitList = new ArrayList<>();
		}
		
		
		return employeeCommitList.stream().map(commit -> modelMapper.map(commit, ReasonDTO.class)).toList(); 
				
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Transactional
	public List<ReasonDTO> returnEmployeeRest(int empNo) {
		List<AdminReason> employeeCommitList = reasonRepository.findByEmployeeNo(empNo);
		
		return employeeCommitList.stream().map(commit -> modelMapper.map(commit, ReasonDTO.class)).toList();
	}

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
		reasonRepository.save(modelMapper.map(restCommitDTO, AdminReason.class));
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();

		LocalDate now = LocalDate.now();
		java.sql.Date today = java.sql.Date.valueOf(now);
		
		employee.setEmployeeHireDate(today);
		employee.setEmployeeSecondConfirmYn("Y");
		employee.setEmployeeLastConfirmYn("Y");
		employee.setEmployeeLastConfirmDate(today);
		
	}
	
	@Transactional
	public void insertAndupdateRestCommitReturnBoss(ReasonDTO restCommitDTO) {
		reasonRepository.save(modelMapper.map(restCommitDTO, AdminReason.class));
		
		AdminEmployee employee = employeeRepository.findById(restCommitDTO.getEmployeeNo()).get();
		employee.setEmployeeRegistReturnYn("Y");
		
		
	}

	@Transactional
	public List<EmployeeAndAllDTO> selectWaitingEmployeeListBoss() {
		List<AdminEmployee> selectWaitingEmployeeList = employeeRepository.findByEmployeeFirstConfirmYnAndEmployeeSecondConfirmYnAndEmployeeLastConfirmYnAndEmployeeRegistReturnYn("Y","N","N","N");
		
		return selectWaitingEmployeeList.stream().map(waiting -> modelMapper.map(waiting, EmployeeAndAllDTO.class)).toList();
	}
	@Transactional
	public List<EmployeeAddressDTO> selectWaitingEmployeeAddressListBoss() {
		List<AdminEmployeeAddress> waitingEmployeeAddressList = employeeAddressRepository.secondConfirmN();
															   
		return waitingEmployeeAddressList.stream().map(waitingAddress -> modelMapper.map(waitingAddress, EmployeeAddressDTO.class)).toList();
	}



	





}
