package com.project.clean.model.repository.pay;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.joinEntity.BestEmployeePayAndEmployee;

public interface BestEmployeePayAndEmployeeRepository  extends JpaRepository<BestEmployeePayAndEmployee, Integer> {

	int countByEmployeeEmployeeNameContaining(String searchValue);

	int countByEmployeeEmployeePhoneContaining(String searchValue);

	List<BestEmployeePayAndEmployee> findByEmployeeEmployeeNameContaining(String searchValue, Pageable paging);

	List<BestEmployeePayAndEmployee> findByEmployeeEmployeePhoneContaining(String searchValue, Pageable paging);
	
	


	

}
