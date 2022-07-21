package com.project.clean.model.service.employee.checkList;

import java.util.List;

import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;

public interface CheckListService {

	/* KS. 본인 예약 업무 조회 */
	List<ReservationInfoDTO> selectReservationListByEmployeeId(String employeeId);

	/* KS. 직원 번호 조회 */
	int selectEmployeeNo(String userId);

	/* KS. 업무 시작 시 빈 체크리스트 Insert */
	int registNewCheckList(CheckListDTO checkListDTO);

	/* KS. 업무 시작 후 체크리스트 작성 (UPDATE) */
	CheckListDTO InsertCheckList(String userId);

	/* KS. 체크리스트 수정(반려 시 사유서 작성) */
	int updateCheckList(CheckListDTO checkListDTO);

	/* KS. parameter값에 일치하는 체크리스트 조회 */
	List<CheckListAndReservationInfoAndEmployeeDTO> selectCheckList(String employeeId, int parameter);

	/* KS. 체크리스트 상세 조회 */
	CheckListDTO selectCheckListDetails(int reservationNo);

	/* KS. 작성하지 않은 체크리스트 판별 */
	int selectCheckListIsNotNull();
	 
}
