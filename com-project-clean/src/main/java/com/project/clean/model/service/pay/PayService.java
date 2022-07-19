package com.project.clean.model.service.pay;

import java.util.List;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminPayDTO;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminAndAdminPayDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;

public interface PayService {
	
	// 부가요금 조회
	public List<SurchargeDTO> findSurchargeList();
	
	// 부가요금 수정
	void modifySurcharge(SurchargeDTO surcharge);

	// 관리자 급여 전체 조회 페이징처리 카운트
	public int selectAdminPayTotalCount(String searchCondition, String searchValue);

	// 관리자 급여 전체 조회
	public List<AdminPayAndAdminDTO> adminPaySearch(SelectCriteria selectCriteria);

	// 관리자 급여 상세 조회
	public AdminPayAndAdminDTO findAdminPayByPayHistoryNo(int payHistoryAdminNo);
	
	// 급여를 한번도 받지 않은 신입 관리자 조회
	public List<AdminAndAdminPayDTO> findNullAdmin();
	
	// 이번 달 급여를 받은 관리자 조회
	public List<AdminAndAdminPayDTO> findPaidAdmin();
	
	public List<AdminAndAdminPayDTO> findAllAdmin();

	// 관리자 번호로 관리자 한명 조회
	public AdminDTO findAdminByPayAdminNo(int adminNo);
	
	public void registAdminPay(int adminNo, int salary, int insurance);








	



	


	


}
