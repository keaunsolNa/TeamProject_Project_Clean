package com.project.clean.model.service.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.RetireAdmin;
import com.project.clean.model.dto.commonDTO.RetireAdminDTO;
import com.project.clean.model.repository.admin.RetireAdminRepository;


public interface RetireAdminService {


	List<RetireAdminDTO> findRetireAdminList();

	
	


}
