package com.project.clean.model.service.admin;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.RetireAdmin;
import com.project.clean.model.dto.commonDTO.RetireAdminDTO;
import com.project.clean.model.repository.admin.RetireAdminRepository;

@Service
public class RetireAdminServiceImpl implements RetireAdminService{
	
	private final RetireAdminRepository retireAdminRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public RetireAdminServiceImpl(RetireAdminRepository retireAdminRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.retireAdminRepository = retireAdminRepository;
	}
	

}
