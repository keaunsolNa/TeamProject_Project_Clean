package com.project.clean.model.service.common;

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;

public interface FindService {

	EmployeeDTO findEmpIdByPhone(String userPhone);

	AdminDTO findAdminIdByPhone(String userPhone);


}
