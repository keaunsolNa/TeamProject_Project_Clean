package com.project.clean.model.service.admin.checkList;

import java.util.List;

import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;

public interface AdminCheckListService {

	List<CheckListAndReservationInfoAndEmployeeDTO> selectAllStandCheckList(String adminId, int parameter);

	CheckListDTO selectStandCheckListDetails(String adminName, int reservationNo);

	int modifyCheckListDenial(CheckListDTO checkList);

	CheckListDTO selectDenialCheckListDetails(int reservationNo);

}
