package com.project.clean.model.service.employee.task;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.ApplyEmployee;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.joinEntity.EmployeeAndApplyEmployee;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.employee.applyEmp.ApplyEmpRepository;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

//	private final ReservationRepository reservationRepository;
	private final EmpRepository empRepository;
	private final ApplyEmpRepository applyEmpRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public TaskServiceImpl(EmpRepository empRepository, ApplyEmpRepository applyEmpRepository, ModelMapper modelMapper) {
		this.empRepository = empRepository;
		this.applyEmpRepository = applyEmpRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<ApplyEmployeeDTO> selectReservationListByEmployeeId(String employeeId) {

		System.out.println("테스트");
		System.out.println("테스트");
		System.out.println("테스트");
		System.out.println("테스트");
		
//		EmployeeAndApplyEmployee empAndApplyEmp = applyEmpRepository.findByEmployeeId(employeeId);
		
//		System.out.println(empAndApplyEmp);
		
		
//		return null;
		
		Employee emp = empRepository.findByEmployeeId(employeeId);
		
		System.out.println(emp);
		
		EmployeeDTO emplist = modelMapper.map(emp, EmployeeDTO.class);
		
		int empNo = emplist.getEmployeeNo();
		
		
		List<EmployeeAndApplyEmployee> applyemp = applyEmpRepository.findAll(Sort.by("employeeNo"));
		
		return  applyemp.stream().map(applyEmpList -> modelMapper.map(applyEmpList, ApplyEmployeeDTO.class)).collect(Collectors.toList());
		   
	}

	
}
