package com.project.clean.model.service.employee.checkList;

import java.util.List;

import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;

public interface CheckListService {

	List<ReservationInfoDTO> selectReservationListByEmployeeId(String employeeId);

	int selectEmployeeNo(String userId);

	int registNewCheckList(CheckListDTO checkListDTO);

	CheckListDTO InsertCheckList(String userId);

	int updateCheckList(CheckListDTO checkListDTO);

	List<CheckListAndReservationInfoAndEmployeeDTO> selectCheckList(String employeeId, int parameter);

	CheckListDTO selectCheckListDetails(int reservationNo);

	int selectCheckListIsNotNull();
	 
}
