package com.project.clean.model.service.pay;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.model.domain.commonEntity.Surcharge;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.repository.pay.PayRepository;

@Service
public class PayServiceImpl implements PayService{
	
	private final PayRepository payRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public PayServiceImpl(PayRepository payRepository,ModelMapper modelMapper) {
		this.payRepository = payRepository;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public List<SurchargeDTO> findSurchargeList() {
		List<Surcharge> surchargeList = payRepository.findAll();				
		
		System.out.println("잘 되지?" + surchargeList );

		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return surchargeList.stream().map(surcharge -> modelMapper.map(surcharge, SurchargeDTO.class)).collect(Collectors.toList());
	}


//	@Transactional
//	@Override
//	public void modifySurcharge(SurchargeDTO surcharge) {
//		System.out.println("서비스임플");
//		Surcharge foundSurcharge = payRepository.findById(surcharge.getSurchargeInsurance()).get();
//		
//		foundSurcharge.setSurchargeCommission(surcharge.getSurchargeCommission());
//		foundSurcharge.setSurchargeBonus(surcharge.getSurchargeBonus());
//		payRepository.save(foundSurcharge);
//		
//	}
}
