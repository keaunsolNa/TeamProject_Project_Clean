package com.project.clean.model.service.admin;

import java.util.List;

import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;


public interface AdminService {

	List<AdminDTO> findAdminList();

	AdminDTO findByAdminNo(int adminNo);

	AdminEmailDTO findAdminEmailByAdminNoEqual(int adminNo);

	AdminAddressDTO findAdminAddressByAdminNo(int adminNo);

	
	
	

}
