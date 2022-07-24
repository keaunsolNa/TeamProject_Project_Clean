package com.project.clean.model.service.admin.checkList;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;

public interface AdminCheckListService {

	/* KS. 파라미터와 예약번호, 관리자 아이디로 체크리스트 상세 조회 */
	CheckListDTO selectCheckListDetails(String adminName, int reservationNo, int parameter);

	/* KS. 체크리스트 수정 (소견서, 반려/승인 등) */
	int modifyCheckList(CheckListDTO checkList);

	/* KS. 체크리스트 조회 및 페이징 */
	Map<String, Object> selectCheckList(String adminId, int parameter, String category, String categoryValue, Pageable pageable);

	/* KS. 체크리스트 목록 조회 */
	List<CheckListDTO> selectAllCheckList();
}
