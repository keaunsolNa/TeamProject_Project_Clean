package com.project.clean.model.repository.reservation;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.clean.model.domain.joinEntity.BookMarkAndReservationInfo;

public interface BookMarkAndReservationInfoRepository extends JpaRepository<BookMarkAndReservationInfo, Object> {

	List<BookMarkAndReservationInfo> findByBookmarkEmployeeNoAndBookmarkCancelYn(int employeeNo, String n, Pageable paging);


}

