package com.project.clean.model.service.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	






	


	

}
