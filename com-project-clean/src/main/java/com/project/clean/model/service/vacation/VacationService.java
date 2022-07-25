package com.project.clean.model.service.vacation;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.controller.admin.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Vacation;
import com.project.clean.model.domain.commonEntity.VacationCommit;
import com.project.clean.model.dto.commonDTO.VacationCommitDTO;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.repository.vacation.AdminVacationRepository;
import com.project.clean.model.repository.vacation.VacationCommitRepository;
import com.project.clean.model.repository.vacation.VacationJPQLRepository;
import com.project.clean.model.repository.vacation.VacationRepository;

@Service
public class VacationService {

	private final VacationRepository vacationRepository;
	private final ModelMapper modelMapper;
	private final VacationCommitRepository vacationCommitRepository;
	
	@Autowired
	public VacationService(VacationRepository vacationRepository, ModelMapper modelMapper, VacationCommitRepository vacationCommitRepository
			, AdminVacationRepository adminVacationRepository) {
		this.vacationRepository = vacationRepository;
		this.modelMapper = modelMapper;
		this.vacationCommitRepository = vacationCommitRepository;
	}
	
	
	/* 휴가 1차 승인 대기목록 조회 검색 - 신청자, 시작일, 마지막일, 휴가구분) */
	@Transactional
	public List<VacationDTO> findByVacationList(SelectCriteria selectCriteria){
		
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();
		
		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("vacationNo"));	// Pageable은 org.springframework.data.domain패키지로 import
		
		List<Vacation> vacationList = new ArrayList<Vacation>();
		
		/* 날짜 검색 형변환 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");         // 문자열 -> Date        

		
		if(searchValue != null) {
			
			/* 신청자 검색 */
			if("requestAdmin".equals(selectCriteria.getSearchCondition())) {
				
				vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationReturnYnAndRequestAdminContaining("N", "N", selectCriteria.getSearchValue(), paging);		
				
			}
			
			/* 휴가 시작일 검색 */
			if("vacationStartDate".equals(selectCriteria.getSearchCondition())) {
				
				if(!selectCriteria.getSearchValue().isBlank()) {
					
					Date vacationStartDate = Date.valueOf(selectCriteria.getSearchValue()); 
					
					vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationReturnYnAndVacationStartDate("N", "N", vacationStartDate, paging);
			
				}
			}
			
			/* 휴가 종료일 검색 */
			if("vacationEndDate".equals(selectCriteria.getSearchCondition())) {
				
				if(!selectCriteria.getSearchValue().isBlank()) {
				
					Date vacationEndDate = Date.valueOf(selectCriteria.getSearchValue()); 
					
					vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationReturnYnAndVacationEndDate("N", "N", vacationEndDate, paging);
				}
			}
			
			/* 휴가명 검색 */
			if("vacationName".equals(selectCriteria.getSearchCondition())) {
				
				vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationReturnYnAndVacationNameContaining("N", "N", selectCriteria.getSearchValue(), paging);
				
			}
			
		} else {
			
			/* 1차 승인 여부가 N인 전체 목록 조회 */
			 vacationList = vacationRepository.findAlldByVacationFirstConfirmYnAndVacationReturnYn("N", "N", paging);
		}

		
	return vacationList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
}
	
	/* 페이징 - 신청일, 신청번호, 신청자, 시작일, 마지막일, 휴가구분) */
	public int selectTotalCount(String searchCondition, String searchValue){
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");    
		
		int count = 0;
		if(searchValue != null) {
			
			/* 신청자 이름 */
			if("requestAdmin".equals(searchCondition)) {
				count = vacationRepository.countByVacationFirstConfirmYnAndVacationReturnYnAndRequestAdminContaining("N", "N", searchValue);		
			}
			
			/* 휴가명 */
			if("vacationName".equals(searchCondition)) {
				count = vacationRepository.countByVacationFirstConfirmYnAndVacationReturnYnAndVacationNameContaining("N", "N", searchValue);
				
			}			
			
			/* 휴가 시작일 */
			if("vacationStartDate".equals(searchCondition)){
				
				if(!searchValue.isBlank()) {
					Date vacationStartDate = Date.valueOf(searchValue); 
					
					count = vacationRepository.countByVacationFirstConfirmYnAndVacationReturnYnAndVacationStartDate("N", "N", vacationStartDate);
				}
			}
			
			/* 휴가 종료일 */
			if("vacationEndDate".equals(searchCondition)) {
				
				if(!searchValue.isBlank()) {
					Date vacationEndDate = Date.valueOf(searchValue); 
					
					count = vacationRepository.countByVacationFirstConfirmYnAndVacationReturnYnAndVacationEndDate("N", "N", vacationEndDate);
				}
			}
			
		} else {
			count = (int)vacationRepository.countByVacationFirstConfirmYnAndVacationReturnYn("N", "N");
		}
		
		
		return count;
	}

	/* 휴가 2차 승인 대기 목록 조회 */
	@Transactional
	public List<VacationDTO> findVacationSecondList(SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		
		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("vacationNo"));	// Pageable은 org.springframework.data.domain패키지로 import
		
		List<Vacation> vacationList = new ArrayList<Vacation>();
		
		if(searchValue != null) {
			
			/* 신청자 검색 */
			if("requestAdmin".equals(selectCriteria.getSearchCondition())) {
				
				vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndRequestAdminContaining("Y", "N", "N", selectCriteria.getSearchValue(), paging);		
				
			}
			
			/* 휴가 시작일 검색 */
			if("vacationStartDate".equals(selectCriteria.getSearchCondition())) {
				
				if(!selectCriteria.getSearchValue().isBlank()) {
					
					System.out.println("여기까지 왔니");
					System.out.println("여기까지 왔니");
					System.out.println("여기까지 왔니");
					System.out.println("여기까지 왔니");
					System.out.println("여기까지 왔니");
					System.out.println("여기까지 왔니");
					
					Date vacationStartDate = Date.valueOf(selectCriteria.getSearchValue()); 
					
					vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationStartDate("Y", "N", "N", vacationStartDate, paging);
				}
			}
			
			/* 휴가 종료일 검색 */
			if("vacationEndDate".equals(selectCriteria.getSearchCondition())) {
				
				if(!selectCriteria.getSearchValue().isBlank()) {
					
					Date vacationEndDate = Date.valueOf(selectCriteria.getSearchValue()); 
					
					vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationEndDate("Y", "N", "N", vacationEndDate, paging);
				}	
			}
			
			/* 휴가명 검색 */
			if("vacationName".equals(selectCriteria.getSearchCondition())) {
				
				vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationNameContaining("Y", "N", "N", selectCriteria.getSearchValue(), paging);
				
			}
			
		} else {
			
			/* 1차 승인여부가 Y이며 2차 승인 여부가 N이고 반려 여부가 N인 목록 조회 */ 
			vacationList = vacationRepository.findAllByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYn("Y", "N", "N", paging);
		
		}
		
		return vacationList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
	}

	/* 휴가 정보 상세 조회 */
	public VacationDTO findByVacationNo(int vacationNo) {
		
		Vacation vacation = vacationRepository.findByVacationNo(vacationNo);
		
		return modelMapper.map(vacation, VacationDTO.class);
	}

//	public List<VacationDTO> findbyCommitAdminList(int vacationNo) {
//		
//		List<Vacation> commitAdminList = vacationRepository.findVacationCommitAdminList(vacationNo);
//		
//		return commitAdminList.stream().map(vacation -> modelMapper.map(vacation, VacationDTO.class)).collect(Collectors.toList());
//	}

	/* 휴가 상세 조회 */
//	public VacationDTO findByVacationNo(int vacationNo) {
//
//		Vacation vacationList = vacationRepository.findByVacationNo(vacationNo);
//		
//		return modelMapper.map(vacationList, VacationDTO.class);
//	}

	/* 휴가 신청 */
	public void registNewVacationApply(VacationDTO vacation) {
		
		vacationRepository.save(modelMapper.map(vacation, Vacation.class));
		
		
	}

	/* 결재내역 insert */
	@Transactional
	public void registVacationCommit(VacationCommitDTO vacationCommit) {
		
		vacationCommitRepository.save(modelMapper.map(vacationCommit, VacationCommit.class));
		
	}

	/* 1차 승인에 따른 승인여부 update */
	@Transactional
	public void modifyFirstConfirmYn(int vacationNo) {
		
		vacationRepository.findByVacationFirstConfirm(vacationNo);
		
	}

	/* 2차 승인에 따른 승인 여부 update */
	@Transactional
	public void modifySecondConfirmYn(int vacationNo) {
		
		vacationRepository.findByVacationSecondConfirm(vacationNo);
		
	}

	/* 반려 여부 update */
	@Transactional
	public void modifyReturnYn(int vacationNo) {
		
		vacationRepository.findByVacationReturnYn(vacationNo);
		
	}

	/* 휴가 승인을 위한 상세 조회 */
	public VacationDTO findAdminByVacationNo(int vacationNo) {

		Vacation vacationList = vacationRepository.findByVacationNo(vacationNo);

		return modelMapper.map(vacationList, VacationDTO.class);
		
	}


	/* 휴가 2차 대기목록 상세 조회 페이징 */
	public int selectSecondTotalCount(String searchCondition, String searchValue) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		
		int count = 0;
		if(searchValue != null) {
			
			/* 신청자 이름 */
			if("requestAdmin".equals(searchCondition)) {
				count = vacationRepository.countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndRequestAdminContaining("Y", "N" , "N", searchValue);		
			}
			
			/* 휴가명 */
			if("vacationName".equals(searchCondition)) {
				
				count = vacationRepository.countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationNameContaining("Y", "N", "N", searchValue);
				
			}			
			
			/* 휴가 시작일 */
			if("vacationStartDate".equals(searchCondition)){
				
				if(!searchValue.isBlank()) {
					Date vacationStartDate = Date.valueOf(searchValue); 
					
					count = vacationRepository.countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationStartDate("Y", "N", "N" , vacationStartDate);
				}
			}
			
			/* 휴가 종료일 */
			if("vacationEndDate".equals(searchCondition)) {
				
				if(!searchValue.isBlank()) {
				
					Date vacationEndDate = Date.valueOf(searchValue); 
					
					count = vacationRepository.countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYnAndVacationEndDate("Y", "N" , "N", vacationEndDate);
				
				}
			}
			
		} else {
			count = (int)vacationRepository.countByVacationFirstConfirmYnAndVacationSecondConfirmYnAndVacationReturnYn("Y", "N" ,"N");
	
		}
		
		return count;
		
		
	}


	
	
	

	

	

}
