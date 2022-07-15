package com.project.clean.model.service.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.repository.employee.EmployeeReopsitory;

public class MemberServiceImpl implements MemberService {

	private final EmployeeReopsitory employeeRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public MemberServiceImpl(EmployeeReopsitory employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	public EmployeeDTO selectOneEmployee(int EmpNo) {
		AdminEmployee employee = employeeRepository.findById(EmpNo).get();
		
		return modelMapper.map(employee, EmployeeDTO.class);
	}
	
}
