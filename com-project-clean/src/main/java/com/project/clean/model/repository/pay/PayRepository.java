package com.project.clean.model.repository.pay;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.clean.model.domain.commonEntity.Surcharge;

public interface PayRepository extends JpaRepository<Surcharge, Integer>{
	
	

	
}
