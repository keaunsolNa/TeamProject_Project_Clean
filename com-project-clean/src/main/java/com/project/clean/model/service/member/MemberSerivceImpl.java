package com.project.clean.model.service.member;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.domain.adminEntity.AdminEmployeeAddress;
import com.project.clean.model.domain.adminEntity.AdminEmployeeEmail;
import com.project.clean.model.dto.commonDTO.EmployeeAddressDTO;
import com.project.clean.model.dto.commonDTO.EmployeeEmailDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.repository.employee.EmployeeAddressRepository;
import com.project.clean.model.repository.employee.EmployeeEmailRepository;
import com.project.clean.model.repository.employee.EmployeePictureRepository;
import com.project.clean.model.repository.employee.EmployeeReopsitory;

@Service
public class MemberSerivceImpl implements MemberService{

	private final EmployeeReopsitory employeeRepository;
	private final EmployeeAddressRepository employeeAddressRepository;
	private final EmployeeEmailRepository employeeEmailRepository;
	private final EmployeePictureRepository employeePictureRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public MemberSerivceImpl(EmployeeReopsitory employeeRepository, EmployeeAddressRepository employeeAddressRepository
					   , ModelMapper modelMapper, EmployeeEmailRepository employeeEmailRepository, EmployeePictureRepository employeePictureRepository) {
		this.employeeRepository = employeeRepository;
		this.employeeAddressRepository = employeeAddressRepository;
		this.employeeEmailRepository = employeeEmailRepository;
		this.employeePictureRepository = employeePictureRepository;
		this.modelMapper = modelMapper;
	}

	public EmployeeAndAllDTO selectOneEmployee(int EmpNo) {
		AdminEmployee employee = employeeRepository.findById(EmpNo).get();
		
		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}
	
	public EmployeeAddressDTO selectOneEmployeeAddress(int EmpNo) {
		AdminEmployeeAddress employeeAddress = employeeAddressRepository.findById(EmpNo).get();
		
		return modelMapper.map(employeeAddress, EmployeeAddressDTO.class);
	}

	public EmployeeEmailDTO selectOneEmployeeEmail(int empNo) {
		AdminEmployeeEmail employeeEmail = employeeEmailRepository.findById(empNo).get();
		
		return modelMapper.map(employeeEmail, EmployeeEmailDTO.class);
	}
}
