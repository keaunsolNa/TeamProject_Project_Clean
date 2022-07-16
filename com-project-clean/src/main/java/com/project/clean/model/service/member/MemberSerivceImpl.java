package com.project.clean.model.service.member;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.repository.employee.EmployeeReopsitory;

@Service
public class MemberSerivceImpl implements MemberService{

	private final EmployeeReopsitory employeeRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public MemberSerivceImpl(EmployeeReopsitory employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}

	public EmployeeAndAllDTO selectOneEmployee(int EmpNo) {
		AdminEmployee employee = employeeRepository.findById(EmpNo).get();
		
		return modelMapper.map(employee, EmployeeAndAllDTO.class);
	}
}
