package com.project.clean.model.service.admin.checkList;

import java.util.List;

import com.project.clean.model.dto.commonDTO.CheckListDTO;

public interface AdminCheckListService {

	List<CheckListDTO> selectAllStandCheckList();

}
