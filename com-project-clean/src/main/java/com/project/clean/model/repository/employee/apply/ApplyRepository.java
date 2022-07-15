package com.project.clean.model.repository.employee.apply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.joinEntity.ApplyEmployeeEmbedded;
import com.project.clean.model.domain.joinEntity.ApplyEmployeeIdAndApplyReservationNo;

public interface ApplyRepository extends JpaRepository<ApplyEmployeeEmbedded, ApplyEmployeeIdAndApplyReservationNo>{

	@Query(value = "SELECT A.APPLY_EMPLOYEE_NO, A.APPLY_RESERVATION_NO, A.APPLY_CANCEL_YN, A.CHECK_EMPLOYEE_YN FROM TBL_APPLY_EMPLOYEE A WHERE A.APPLY_EMPLOYEE_NO = ? AND A.APPLY_CANCEL_YN = 'N' AND A.CHECK_EMPLOYEE_YN = 'Y'", nativeQuery = true)
	public List<ApplyEmployeeEmbedded> findAllEmployeeApply(Integer employeeNo);

}
