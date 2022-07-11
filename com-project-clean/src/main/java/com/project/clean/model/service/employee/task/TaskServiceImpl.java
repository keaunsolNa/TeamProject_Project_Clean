package com.project.clean.model.service.employee.task;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.joinEntity.EmployeeAndApplyEmployee;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.employee.apply.ApplyRepository;
import com.project.clean.model.repository.employee.applyEmp.ApplyEmpRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

	private final EmpRepository empRepository;
	private final ApplyRepository applyRepository;
	private final ModelMapper modelMapper;
	private final ApplyEmpRepository applyEmpRepository;
	
	@Autowired
	public TaskServiceImpl(EmpRepository empRepository, ApplyRepository applyRepository, ModelMapper modelMapper, ApplyEmpRepository applyEmpRepository) {
		this.empRepository = empRepository;
		this.applyRepository = applyRepository;
		this.modelMapper = modelMapper;
		this.applyEmpRepository = applyEmpRepository;
	}
	
	@Override
	@Transactional
	public List<ApplyEmployeeDTO> selectReservationListByEmployeeId(String employeeId) {

		System.out.println("테스트");
		System.out.println("테스트");
		System.out.println("테스트");
		System.out.println("테스트");
		
		
		List<ApplyEmployee> applyEmployee = new ArrayList<>();
		
		Employee empAndApplyEmp = empRepository.findByEmployeeId(employeeId);
		EmployeeDTO emplist = modelMapper.map(empAndApplyEmp, EmployeeDTO.class);
		
		Integer employeeNo = emplist.getEmployeeNo();
		System.out.println("조회한 회원 번호 : " + employeeNo);
		
		List<ApplyEmployee> applyEmployeeList = applyRepository.findByApplyEmployeeNo(emplist.getEmployeeNo());
				
		System.out.println(applyEmployeeList);

		return  null;
		   
	}

	
}
