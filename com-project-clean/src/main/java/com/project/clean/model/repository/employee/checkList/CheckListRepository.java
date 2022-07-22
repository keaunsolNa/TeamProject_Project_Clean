package com.project.clean.model.repository.employee.checkList;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.CheckList;

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

	
	int countByCheckStatus(String string);
	

}
