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

	/* KS. 체크리스트 상태 2개로 체크리스트 모두 조회 */
	List<CheckList> findAllByCheckStatusOrCheckStatus(String string, String string2);

	/* KS. 체크리스트 상태로 체크리스트 조회 */
	CheckList findByCheckStatus(String checkStatus);

	/* KS. 체크리스트 페이징 */
	int countByCheckStatus(String string);

	Page<CheckList> findAllByCheckStatus(String status, Pageable pageable);



	

}
