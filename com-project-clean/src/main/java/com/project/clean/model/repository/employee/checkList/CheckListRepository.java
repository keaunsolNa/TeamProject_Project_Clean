package com.project.clean.model.repository.employee.checkList;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.CheckList;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;

public interface CheckListRepository  extends JpaRepository<CheckList, Integer>{

	/* KS. 예약번호와 체크리스트 처리상태로 체크리스트 조회 */
	CheckList findBycheckReservationNoAndCheckStatus(Integer reservationNo, String string);

	/* KS. 체크리스트 상태로 모든 체크리스트 조회 */
	List<CheckList> findAllByCheckStatus(String string);

	/* KS. 예약번호로 체크리스트 조회 */
	CheckList findByCheckReservationNo(Integer checkReservationNo);

	/* KS. 체크리스트 상태 3개로 체크리스트 모두 조회 */
	List<CheckList> findAllByCheckStatusOrCheckStatusOrCheckStatus(String string, String string2, String string3);

	/* KS. 체크리스트 상태로 체크리스트 조회 */
	CheckList findByCheckStatus(String checkStatus);

	/* KS. 모든 체크리스트 목록 조회 (페이징) */
	Page<CheckList> findAllByCheckStatus(String status, Pageable pageable);

	/* KS. 모든 체크리스트 목록 조회2 (페이징) */
	Page<CheckList> findAllByCheckStatusAndAdminNo(String status, Integer adminNo, Pageable pageable);

	/* KS. 모든 체크리스트 목록 조회 3 (페이징) */
	Page<CheckList> findAllByCheckStatusOrCheckStatusAndAdminNo(String status, String status2, Integer adminNo,
			Pageable pageable);



	

}
