package com.project.clean.model.service.common;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.employee.EmpRepository;

@Service
@Transactional
public class FindServiceImpl implements FindService{

	private final AdminRepository adminRepositroy;
	private final ModelMapper modelMapper;
	private final EmpRepository empRepository;
	
	@Autowired
	public FindServiceImpl(AdminRepository adminRepositroy, ModelMapper modelMapper, EmpRepository empRepository) {
		this.adminRepositroy = adminRepositroy;
		this.modelMapper = modelMapper;
		this.empRepository = empRepository;
	} 

	@Override
	public AdminDTO findAdminIdByPhone(String userPhone) {
		
		Admin admin = adminRepositroy.findByAdminPhone(userPhone);
		
		if(admin == null) {
			admin = new Admin();
		}
		
		return modelMapper.map(admin, AdminDTO.class);
		
	}

	@Override
	public EmployeeDTO findEmpIdByPhone(String userPhone) {

		Employee emp = empRepository.findByEmployeePhone(userPhone);
		
		if(emp == null) {
			emp = new Employee();
		}
		
		return modelMapper.map(emp, EmployeeDTO.class);
		
	}

}
