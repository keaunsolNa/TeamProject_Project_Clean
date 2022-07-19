package com.project.clean.model.repository.pay;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.commonEntity.AdminPay;
import com.project.clean.model.domain.joinEntity.AdminAndAdminPay;
import com.project.clean.model.domain.joinEntity.AdminPayAndAdmin;

public interface AdminPayAndAdminRepository extends JpaRepository<AdminPayAndAdmin, Integer>{
	
	// 페이징처리(갯수 새기)
	int countByAdminAdminNameContaining(String searchValue);
	
	int countByAdminAdminJobContaining(String searchValue);

	int countByAdminAdminPhoneContaining(String searchValue);
	
	int countByPayAdminDateContaining(Integer valueOf);
	
	// 관리자이름으로 검색
	List<AdminPayAndAdmin> findByAdminAdminNameContaining(String searchValue, Pageable paging);
	
	// 관리자직급으로 검색
	List<AdminPayAndAdmin> findByAdminAdminJobContaining(String searchValue, Pageable paging);
	
	// 관리자전화번호로 검색
	List<AdminPayAndAdmin> findByAdminAdminPhoneContaining(Integer valueOf, Pageable paging);


	
	
	


	

	

	
	
	

	



//	List<AdminPayAndAdmin> findByPayAdminDateContaining(String strDate, Pageable paging);







	


	
}
