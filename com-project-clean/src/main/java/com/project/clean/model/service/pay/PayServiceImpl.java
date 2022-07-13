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
	
	@Transactional
	@Override
	public void modifySurcharge(SurchargeDTO surcharge) {
		System.out.println("서비스임플");
		
		Surcharge foundSurcharge = payRepository.findById(surcharge.getSurchargeInsurance()).get();
		Integer Commission = surcharge.getSurchargeCommission();
		Integer Bonus = surcharge.getSurchargeBonus();
		if(Commission != 0 && Bonus != 0) {
			foundSurcharge.setSurchargeCommission(surcharge.getSurchargeCommission());
			foundSurcharge.setSurchargeBonus(surcharge.getSurchargeBonus());
		} else if(Commission != 0 && Bonus == 0){
			foundSurcharge.setSurchargeCommission(surcharge.getSurchargeCommission());
		} else if(Commission == 0 && Bonus != 0) {
			foundSurcharge.setSurchargeBonus(surcharge.getSurchargeBonus());
		} else {
		  System.out.println("오류 발생");	
		}
		
		
		payRepository.save(foundSurcharge);
		
	}

//	public int selectTotalCount(String searchCondition, String searchValue) {
//		int count = 0;
//		if(searchValue != null) {
//			if("adminName".equals(searchCondition)) {
//				count = payRepository.countByAdminNameContaining(searchValue);
//			}
//			if("adminJob".equals(searchCondition)) {
//				count = payRepository.countByAdminJob(searchValue);
//			}
//			if("adminPayDate".equals(searchCondition)) {
//				count = payRepository.countByAdminPayDate(new SimpleDateFormat(searchValue).format(new Date()));
//			}
//		} else {
//			count = (int)payRepository.count();
//		}
//
//		return count;
//	}
//
//
//	public List<AdminPayAndAdminDTO> searchAdminPayList(SelectCriteria selectCriteria) {
//		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
//		int count = selectCriteria.getLimit();
//		String searchValue = selectCriteria.getSearchValue();
//
//		/* 페이징 처리와 정렬을 위한 객체 생성 */
//		Pageable paging = PageRequest.of(index, count, Sort.by("payHistoryAdminNo"));	// Pageable은 org.springframework.data.domain패키지로 import
//
//		List<AdminPayAndAdmin> adminPayList = new ArrayList<AdminPayAndAdmin>();
//		if(searchValue != null) {
//
//			
//			if("adminName".equals(selectCriteria.getSearchCondition())) {
//				adminPayList = payRepository.findByAdminNameContaining(selectCriteria.getSearchValue(), paging);
//			}
//
//			if("adminJob".equals(selectCriteria.getSearchCondition())) {
//				adminPayList = payRepository.findByAdminJobContaining(selectCriteria.getSearchValue(), paging);
//			}
//			
//			if("adminPayDate".equals(selectCriteria.getSearchCondition())) {
//				adminPayList = payRepository.findByAdminPayDateContaining(selectCriteria.getSearchValue(), paging);
//			}
//		} else {
//			adminPayList = payRepository.findAll(paging).toList();
//		}
//
//		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
//		return adminPayList.stream().map(pay -> modelMapper.map(pay, AdminPayAndAdminDTO.class)).collect(Collectors.toList());
//	}



}
