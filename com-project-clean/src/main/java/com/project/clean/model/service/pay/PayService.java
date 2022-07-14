package com.project.clean.model.service.pay;

import java.util.List;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;

public interface PayService {
	
	// 부가요금 조회
	public List<SurchargeDTO> findSurchargeList();
	
	// 부가요금 수정
	void modifySurcharge(SurchargeDTO surcharge);

	// 페이징처리 카운트
	public int selectTotalCount(String searchCondition, String searchValue);

	// 관리자 급여 전체 조회
	public List<AdminPayAndAdminDTO> searchAdminPayList(SelectCriteria selectCriteria);
	


	


}
