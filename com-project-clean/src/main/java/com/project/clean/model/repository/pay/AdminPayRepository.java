package com.project.clean.model.repository.pay;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.clean.model.domain.joinEntity.AdminPayAndAdmin;

public interface AdminPayRepository extends JpaRepository<AdminPayAndAdmin, Integer>{
	int countByAdminAdminNameContaining(String searchValue);
	
	int countByAdminAdminJobContaining(String searchValue);

	int countByAdminAdminPhoneContaining(String searchValue);
	
	int countByPayAdminDateLessThanEqual(Integer valueOf);
	
	List<AdminPayAndAdmin> findByAdminAdminNameContaining(String searchValue, Pageable paging);
	
	List<AdminPayAndAdmin> findByAdminAdminJobContaining(String searchValue, Pageable paging);



//	List<AdminPayAndAdmin> findByPayAdminDateContaining(String strDate, Pageable paging);







	


	
}
