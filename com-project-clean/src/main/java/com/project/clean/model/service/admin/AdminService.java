package com.project.clean.model.service.admin;

import java.util.List;

import com.project.clean.model.dto.commonDTO.EmployeeAddressDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeEmailDTO;
import com.project.clean.model.dto.commonDTO.EmployeePictureDTO;
import com.project.clean.model.dto.commonDTO.EmployeeRestCommitDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;

public interface AdminService {

	/* 전체 직원 조회(재직자) */
	public List<EmployeeAndAllDTO> selectRetireNEmployee();

	/* 전체 직원 조회(퇴사자) */
	public List<EmployeeAndAllDTO> selectRetireYEmployee();
	
	/* 직원 주소 조회(재직자) */
	public List<EmployeeAddressDTO> selectRetireNEmployeeAddress();
	
	/* 직원 주소 조회(퇴사자) */
	
	public List<EmployeeAddressDTO> selectRetireYEmployeeAddress();
	
	
	public EmployeeAndAllDTO selectOneEmployee(int EmpNo);
	
	
	public EmployeeAddressDTO selectOneEmployeeAddress(int EmpNo);
	
	
	public EmployeeEmailDTO selectOneEmployeeEmail(int empNo);
	
	
	public EmployeePictureDTO selectOneEmployeePicture(int empNo);
	
	
	public void modifyEmployee(int empNo, String entireYn);
	
	
	public int getMaxMemberNo();
	
	
	public String getsysdate();
	
	
	public void registEmployee(EmployeeAndAllDTO employeeDTO);
	
	
	public void registEmployeeAddress(EmployeeAddressDTO addressDTO);
	
	public void registEmployeeEmail(EmployeeEmailDTO emailDTO);
	
	public void registEmployeeCommit(EmployeeRestCommitDTO employeeRestCommitDTO);

	
	public void modifyEmployee(EmployeeAndAllDTO employeeDTO);
	
	public List<EmployeeAndAllDTO> selectWaitingEmployeeList();
	
	public List<EmployeeAddressDTO> selectWaitingEmployeeAddressList();
	
	public List<EmployeeAndAllDTO> selectReturnEmployeeList();

	
	public List<EmployeeAddressDTO> selectReturnEmployeeAddressList();
	
	public EmployeeAndAllDTO waitingEmployee(int empNo);
	
	public EmployeeAddressDTO waitingEmployeeAddress(int empNo);
	
	public EmployeeEmailDTO waitingEmployeeEmail(int empNo);
	
	public EmployeeAndAllDTO returnEmployee(int empNo);
	
	public EmployeeEmailDTO returnEmployeeEmail(int empNo);
	
	public EmployeeAddressDTO returnEmployeeAddress(int empNo);
	
	public EmployeeRestCommitDTO waitingEmployeeRest(int empNo);
	
	public EmployeeRestCommitDTO returnEmployeeRest(int empNo);

	
	public void updateRestCommitConfirm(EmployeeRestCommitDTO restCommitDTO);
	
	
	public void updateRestCommitReturn(EmployeeRestCommitDTO restCommitDTO);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}