package com.project.clean.model.service.admin;

import java.util.List;

import com.project.clean.controller.admin.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
import com.project.clean.model.dto.joinDTO.AdminAndEmailDTO;


public interface AdminService {

	List<AdminDTO> findAdminList();

	AdminDTO findByAdminNo(int adminNo);

	AdminEmailDTO findAdminEmailByAdminNoEqual(int adminNo);

	AdminAddressDTO findAdminAddressByAdminNo(int adminNo);

	
	
	

}
