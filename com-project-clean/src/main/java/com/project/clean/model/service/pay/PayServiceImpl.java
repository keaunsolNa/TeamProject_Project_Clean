package com.project.clean.model.service.pay;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Surcharge;
import com.project.clean.model.domain.joinEntity.AdminPayAndAdmin;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;
import com.project.clean.model.repository.pay.AdminPayRepository;
import com.project.clean.model.repository.pay.SurchargeRepository;

@Service
public class PayServiceImpl implements PayService{
	
	private final SurchargeRepository surchargeRepository;
	private final AdminPayRepository adminPayRepository;
	private final ModelMapper modelMapper;			// modelMapper 빈을 선언
	
	@Autowired
	public PayServiceImpl(SurchargeRepository surchargeRepository,AdminPayRepository adminPayRepository,ModelMapper modelMapper) {
		this.surchargeRepository = surchargeRepository;
		this.adminPayRepository = adminPayRepository;
		this.modelMapper = modelMapper;
	}
	
	
	// 부가요금 조회
	@Override
	public List<SurchargeDTO> findSurchargeList() {
		List<Surcharge> surchargeList = surchargeRepository.findAll();				
		
		System.out.println("잘 되지?" + surchargeList );

		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return surchargeList.stream().map(surcharge -> modelMapper.map(surcharge, SurchargeDTO.class)).collect(Collectors.toList());
	}
	
	// 부가요금 수정
	@Transactional
	@Override
	public void modifySurcharge(SurchargeDTO surcharge) {
		System.out.println("서비스임플");
		
		Surcharge foundSurcharge = surchargeRepository.findById(surcharge.getSurchargeInsurance()).get();
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
		
		
		surchargeRepository.save(foundSurcharge);
		
	}
	
	
	public int selectTotalCount(String searchCondition, String searchValue) {

		int count = 0;
		if(searchValue != null) {
			if("adminName".equals(searchCondition)) {
				count = adminPayRepository.countByAdminAdminNameContaining(searchValue);
			}

			if("adminjob".equals(searchCondition)) {
				count = adminPayRepository.countByAdminAdminJobContaining(searchValue);
			}
			
			if("adminPhone".equals(searchCondition)) {
				count = adminPayRepository.countByAdminAdminPhoneContaining(searchValue);
			}
				
			if("payAdminDate".equals(searchCondition)) {
				count = adminPayRepository.countByPayAdminDateContaining(Integer.valueOf(searchValue));
			}
		} else {
			count = (int)adminPayRepository.count();
		}

		return count;
	}

	public List<AdminPayAndAdminDTO> searchAdminPayList(SelectCriteria selectCriteria) {

		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();

		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("payHistoryAdminNo").descending());	// Pageable은 org.springframework.data.domain패키지로 import

		List<AdminPayAndAdmin> adminPayList = new ArrayList<AdminPayAndAdmin>();
		if(searchValue != null) {

			/* 관리자 이름 검색일 경우 */
			if("adminName".equals(selectCriteria.getSearchCondition())) {
				adminPayList = adminPayRepository.findByAdminAdminNameContaining(selectCriteria.getSearchValue(), paging);
			}
			
			/* 관리자 직급 검색일 경우 */
			if("adminJob".equals(selectCriteria.getSearchCondition())) {
				adminPayList = adminPayRepository.findByAdminAdminJobContaining(selectCriteria.getSearchValue(), paging);
			}
			
			/* 관리자 전화번호 검색일 경우 */
			if("adminPhone".equals(selectCriteria.getSearchCondition())) {
				adminPayList = adminPayRepository.findByAdminAdminPhoneContaining(Integer.valueOf(selectCriteria.getSearchValue()), paging);
			}
			
			/* 지급 날짜 검색일 경우 - 일단 빼자*/
//			if("payAdminDate".equals(selectCriteria.getSearchCondition())) {
//				/* 날짜를 string으로 변환하기 위함 */
//				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//				String strDate = simpleDateFormat.format(selectCriteria.getSearchValue());
//				adminPayList = adminPayRepository.findByPayAdminDateContaining(strDate, paging);
//			}
			
		} else {
			adminPayList = adminPayRepository.findAll(paging).toList();
		}

		/* 자바의 Stream API와 ModelMapper를 이용하여 entity를 DTO로 변환 후 List<MenuDTO>로 반환 */
		return adminPayList.stream().map(pay -> modelMapper.map(pay, AdminPayAndAdminDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional
	public AdminPayAndAdminDTO findAdminPayByPayHistoryNo(int payHistoryNo) {

		/* findById메소드로 Optional 객체 조회후 Optional객체의 get메소드를 통해 조회 */
		AdminPayAndAdmin pay = adminPayRepository.findById(payHistoryNo).get();
		
		/* ModelMapper를 이용하여 entity를 DTO로 변환 후 MenuDTO로 반환 */
		return modelMapper.map(pay, AdminPayAndAdminDTO.class);
	}



}
