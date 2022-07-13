package com.project.clean.model.service.employee.task;

import java.util.List;

import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;

public interface TaskService {

	List<ReservationInfoDTO> selectReservationListByEmployeeId(String employeeId);

	int selectEmployeeNo(String userId);

	int registNewCheckList(CheckListDTO checkListDTO);

}
