package com.project.clean.model.service.employee.task;

import java.util.List;

import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;

public interface TaskService {

	List<ApplyEmployeeDTO> selectReservationListByEmployeeId(String employeeId);

}
