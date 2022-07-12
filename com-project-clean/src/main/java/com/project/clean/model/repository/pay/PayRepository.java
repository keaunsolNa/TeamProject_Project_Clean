package com.project.clean.model.repository.pay;


import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.Surcharge;
import com.project.clean.model.domain.joinEntity.AdminPayAndAdmin;

public interface PayRepository extends JpaRepository<Surcharge, Integer>{

//	int countByAdminNameContaining(String searchValue);
//
//	int countByAdminJob(String searchValue);
//
//	int countByAdminPayDate(String format);
//
//	List<AdminPayAndAdmin> findByAdminNameContaining(String searchValue, Pageable paging);
//
//	List<AdminPayAndAdmin> findByAdminJobContaining(String searchValue, Pageable paging);
//
//	List<AdminPayAndAdmin> findByAdminPayDateContaining(String searchValue, Pageable paging);
//	
	

	
}
