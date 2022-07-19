package com.project.clean.model.repository.reservation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.clean.model.domain.joinEntity.ApplyEmployeeAndReservationInfo;
import com.project.clean.model.domain.reservation.IdApplyEmployee2;

public interface ApplyEmployeeAndReservationInfoRepository extends JpaRepository<ApplyEmployeeAndReservationInfo, IdApplyEmployee2> {

	List<ApplyEmployeeAndReservationInfo> findAllByApplyEmployeeNoAndApplyCancelYn(int employeeNo, String string, Pageable paging);

}

