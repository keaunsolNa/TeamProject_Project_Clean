package com.project.clean.model.service.admin;

import com.project.clean.model.dto.commonDTO.EmployeeAddressDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeEmailDTO;

public interface MemberService {

	public EmployeeDTO selectOneEmployee(int EmpNo);
	
	public EmployeeAddressDTO selectOneEmployeeAddress(int EmpNo);

	public EmployeeEmailDTO selectOneEmployeeEmail(int empNo) ;
	
}
