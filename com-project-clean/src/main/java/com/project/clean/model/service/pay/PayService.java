package com.project.clean.model.service.pay;

import java.util.List;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;

public interface PayService {
	public List<SurchargeDTO> findSurchargeList();

	void modifySurcharge(SurchargeDTO surcharge);

//	public int selectTotalCount(String searchCondition, String searchValue);
//
//	public List<AdminPayAndAdminDTO> searchAdminPayList(SelectCriteria selectCriteria);

	


}
