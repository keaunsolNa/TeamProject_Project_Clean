package com.project.clean.model.service.common;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.repository.admin.AdminEmailRepository;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.employee.EmpRepository;

@Service
@Transactional
public class FindServiceImpl implements FindService{

	private final AdminRepository adminRepositroy;
	private final ModelMapper modelMapper;
	private final EmpRepository empRepository;
	private final AdminEmailRepository adminEmailRepository;
	
	@Autowired
	public FindServiceImpl(AdminRepository adminRepositroy, ModelMapper modelMapper, EmpRepository empRepository, AdminEmailRepository adminEmailRepository) {
		this.adminRepositroy = adminRepositroy;
		this.modelMapper = modelMapper;
		this.empRepository = empRepository;
		this.adminEmailRepository = adminEmailRepository;
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

//	@Override
//	public AdminEmailDTO findAdminEmailById(String adminId, String password, int adminNo) {
//		
//		AdminEmail adminEmail = adminEmailRepository.findById(adminNo).get();
//		
//		Admin admin = adminRepositroy.findByAdminId(adminId);
//		
//		admin.setAdminPwd(password);
//		
//		return modelMapper.map(adminEmail, AdminEmailDTO.class);
//	}

}
