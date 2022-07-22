package com.project.clean.model.service.admin.checkList;

import java.util.List;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;

public interface AdminCheckListService {

	/* KS. adminId와 parameter로 체크리스트 모두 조회 */
	List<CheckListAndReservationInfoAndEmployeeDTO> selectCheckList(String adminId, int parameter);

	/* KS. 파라미터와 예약번호, 관리자 아이디로 체크리스트 상세 조회 */
	CheckListDTO selectCheckListDetails(String adminName, int reservationNo, int parameter);

	/* KS. 체크리스트 수정 (소견서, 반려/승인 등) */
	int modifyCheckList(CheckListDTO checkList);

	/* KS. 체크리스트 페이징 처리를 위한 총 게시글 수 검색 */
	int selectTotalCount(int parameter);

	/* KS. 체크리스트 페이징 및 검색처리 */
	List<CheckListDTO> searchCheckList(SelectCriteria selectCriteria);


}
