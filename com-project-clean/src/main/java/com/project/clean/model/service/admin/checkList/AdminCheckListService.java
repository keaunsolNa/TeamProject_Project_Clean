package com.project.clean.model.service.admin.checkList;

import java.util.List;

import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;

public interface AdminCheckListService {

	List<CheckListAndReservationInfoAndEmployeeDTO> selectAllStandCheckList();

	CheckListDTO selectStandCheckListDetails(String adminName, int reservationNo);

}
