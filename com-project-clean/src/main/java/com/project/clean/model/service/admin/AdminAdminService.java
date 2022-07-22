package com.project.clean.model.service.admin;

import java.sql.Date;
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
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.RetireAdmin;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.RetireAdminDTO;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.admin.RetireAdminRepository;


@Service
public class AdminAdminService{
	
	private final AdminRepository adminRepository;
	private final ModelMapper modelMapper;
	private final RetireAdminRepository retireAdminRepository;

	@Autowired
	public AdminAdminService(AdminRepository adminRepository, ModelMapper modelMapper, RetireAdminRepository retireAdminRepository) {
		this.adminRepository = adminRepository;
		this.modelMapper = modelMapper;
		this.retireAdminRepository = retireAdminRepository;
	}

	/* 재직자 조회 - 생년월일, 입사일, 직급명, 전화번호 검색 */
	public List<AdminDTO> findAdminList(SelectCriteria selectCriteria) {
		
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();
		
		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("adminNo"));	// Pageable은 org.springframework.data.domain패키지로 import
		
		List<Admin> adminList = new ArrayList<Admin>();
		
		/* 날짜 검색 형변환 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");         // 문자열 -> Date        
		
		if(searchValue != null) {
			
			/* 이름 검색 */
			if("adminName".equals(selectCriteria.getSearchCondition())){
				
				adminList = adminRepository.findAllByAdminRetireYnAndAdminNameContaining("N", selectCriteria.getSearchValue(), paging);
				
			}
			
			/* 생년월일 검색 */
			if("adminBirth".equals(selectCriteria.getSearchCondition())) {
				
				Date adminBirth = Date.valueOf(selectCriteria.getSearchValue()); 
				
				adminList = adminRepository.findAllByAdminRetireYnAndAdminBirth("N", adminBirth, paging);
				
			}
			
			/* 입사일 검색 */
			if("adminHireDate".equals(selectCriteria.getSearchCondition())) {
				
				Date adminHireDate = Date.valueOf(selectCriteria.getSearchValue()); 
				
				adminList = adminRepository.findAllByAdminRetireYnAndAdminHireDate("N", adminHireDate, paging);
				
			}
			
			/* 직책명 검색 */
			if("adminJob".equals(selectCriteria.getSearchCondition())) {
				
				adminList = adminRepository.findAllByAdminRetireYnAndAdminJob("N", selectCriteria.getSearchValue(), paging);
				
			}
			
			/* 휴대전화 번호 검색 */
			if("adminPhone".equals(selectCriteria.getSearchCondition())) {
				
				adminList = adminRepository.findAllByAdminRetireYnAndAdminPhoneContaining("N", selectCriteria.getSearchValue(), paging);
			}
			
			
		} else {
			
			/* 퇴사여부가 N인 관리자 전체 조회 */
			adminList = adminRepository.findAllByAdminRetireYn("N");
		}
		
		
		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
	}
	
	/* 재직 관리자 목록 페이징 - 생년월일, 입사일, 직급명, 전화번호 */
	public int selectTotalCount(String searchCondition, String searchValue) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");    
		
		int count = 0;
		if(searchValue != null) {
			
			/* 이름 페이징 */
			if("adminName".equals(searchCondition)){
				
				count = adminRepository.countByAdminRetireYnAndAdminNameContaining("N", searchValue);
				
			}
			
			/* 생년월일 페이징 */
			if("adminBirth".equals(searchCondition)) {
				
				Date adminBirth = Date.valueOf(searchValue); 
				
				count = adminRepository.countByAdminRetireYnAndAdminBirth("N", adminBirth);
				
			}
			
			/* 입사일 페이징 */
			if("adminHireDate".equals(searchCondition)) {
				
				Date adminHireDate = Date.valueOf(searchValue); 
				
				count = adminRepository.countByAdminRetireYnAndAdminHireDate("N", adminHireDate);
				
			}
			
			/* 직책명 페이징 */
			if("adminJob".equals(searchCondition)) {
				
				count = adminRepository.countByAdminRetireYnAndAdminJob("N", searchValue);
				
			}
			
			/* 휴대전화 번호 페이징 */
			if("adminPhone".equals(searchCondition)) {
				
				count = adminRepository.countByAdminRetireYnAndAdminPhoneContaining("N", searchValue);
			}
			
			/* 전체 재직자 페이징 */
			count = adminRepository.countByAdminRetireYn("N");
		}
		
		return count;
	}
	
	/* 퇴사자 조회(Ajax) */
	public List<AdminDTO> findRetireAdminList(SelectCriteria selectCriteria) {
		
		
		int index = selectCriteria.getPageNo() - 1;			// Pageble객체를 사용시 페이지는 0부터 시작(1페이지가 0)
		int count = selectCriteria.getLimit();
		String searchValue = selectCriteria.getSearchValue();
		
		/* 페이징 처리와 정렬을 위한 객체 생성 */
		Pageable paging = PageRequest.of(index, count, Sort.by("adminNo"));	// Pageable은 org.springframework.data.domain패키지로 import
		
		List<Admin> retireAdminList = new ArrayList<Admin>();
		
		/* 날짜 검색 형변환 */
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");         // 문자열 -> Date        
		
		if(searchValue != null) {
			
			/* 이름 검색 */
			if("adminName".equals(selectCriteria.getSearchCondition())){
				
				retireAdminList = adminRepository.findAllByAdminRetireYnAndAdminNameContaining("Y", selectCriteria.getSearchValue(), paging);
				
			}
			
			/* 생년월일 검색 */
			if("adminBirth".equals(selectCriteria.getSearchCondition())) {
				
				Date adminBirth = Date.valueOf(selectCriteria.getSearchValue()); 
				
				retireAdminList = adminRepository.findAllByAdminRetireYnAndAdminBirth("Y", adminBirth, paging);
				
			}
			
			/* 퇴사일 검색 */
			if("adminRetireDate".equals(selectCriteria.getSearchCondition())) {
				
				Date adminRetireDate = Date.valueOf(selectCriteria.getSearchValue()); 
				
				retireAdminList = adminRepository.findAllByAdminRetireYnAndAdminRetireDate("Y", adminRetireDate, paging);
				
			}
			
			/* 직책명 검색 */
			if("adminJob".equals(selectCriteria.getSearchCondition())) {
				
				retireAdminList = adminRepository.findAllByAdminRetireYnAndAdminJob("Y", selectCriteria.getSearchValue(), paging);
				
			}
			
			/* 휴대전화 번호 검색 */
			if("adminPhone".equals(selectCriteria.getSearchCondition())) {
				
				retireAdminList = adminRepository.findAllByAdminRetireYnAndAdminPhoneContaining("Y", selectCriteria.getSearchValue(), paging);
			}
			
			
		} else {
			
			/* 퇴사여부가 Y인 관리자 전체 조회 */
			retireAdminList = adminRepository.findAllByAdminRetireYn("Y");
		}
	
		
		return retireAdminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());

	}
	
	
////	public List<AdminDTO> findAdminList(SelectCriteria selectCriteria) {
////		
////		/* 퇴사여부가 Y(yes) 가 아닌 관리자 조회 */
////		List<Admin> adminList = adminRepository.findAdminByAdminRetireN();
////		
////		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
////	}
//	
//	
	/* 신규 관리자 등록 페이지 조회를 위한 기본 정보(신규 번호) 조회 */
	public int findMaxAdmin() {
		
		int admin = adminRepository.findMaxAdmin();
		
		return admin;

	}

	/* 관리자 상세 조회 */
	public AdminDTO findByAdminNo(int adminNo) {
		
		Admin admin = adminRepository.findByAdminNo(adminNo).get();
		
		return modelMapper.map(admin, AdminDTO.class);
	}
	
	
	
	/* 퇴사자 상세 조회 */
	public RetireAdminDTO findByRetireAdminNo(int retireAdminNo) {
		
		RetireAdmin retireAdmin = retireAdminRepository.findByRetireAdminNo(retireAdminNo);
		
		return modelMapper.map(retireAdmin, RetireAdminDTO.class);
	}


	/* 신규 관리자 기본 정보 등록 */
	public void registNewAdmin(AdminDTO newAdmin) {
		
		adminRepository.save(modelMapper.map(newAdmin, Admin.class));
	}

	


	/* 재직자 조회(Ajax) */
	@Transactional
	public List<AdminDTO> findAdminListAjax() {
		
		List<Admin> adminListAjax = adminRepository.findAdminList();
		
		return adminListAjax.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
	}

	/* 관리자 퇴사처리 1 - 퇴사여부 수정 */
	@Transactional
	public void modifyAdminRetireY(int adminNo) {
		
		Integer admin = adminRepository.findByAdminRetire(adminNo);
//		admin.setAdminRetireDate(adminDTO.getAdminRetireDate());
		
	}
	
	/* 관리자 퇴사처리 2 - 퇴사일 수정 */
	@Transactional
	public void modifyAdminRetireDate(int adminNo) {
		
		Integer admin = adminRepository.findByAdminRetireDate(adminNo);
		
	}

	/* 관리자 - 본인의 인사카드 수정을 위한 조회 */
	public AdminDTO selectOneAdmin(int adminNo) {
		
		Admin admin = adminRepository.modifyAdmin(adminNo);
		
		return modelMapper.map(admin, AdminDTO.class);
	}


	/* 관리자 - 본인의 인사카드를 수정 */
	@Transactional
	public void modifyAdminSelfCard(AdminDTO adminDTO) {
		
		Admin adminModify = adminRepository.findByAdminNo(adminDTO.getAdminNo()).get();
		
		adminModify.setAdminAddress(adminDTO.getAdminAddress());
		adminModify.setAdminPhone(adminDTO.getAdminPhone());
		adminModify.setAdminEmail(adminDTO.getAdminEmail());
		adminModify.setAdminPictureSaveRoot(adminDTO.getAdminPictureSaveRoot());
		adminModify.setAdminPictureSaveName(adminDTO.getAdminPictureSaveName());
		
	}

	/* 퇴사자 정보 퇴사자 테이블에 삽입 */
	public void registRetireAdmin(RetireAdminDTO retireAdmin) {
		
		retireAdminRepository.save(modelMapper.map(retireAdmin, RetireAdmin.class));
		
	}

	/* 휴가 신청을 위한 조회 - 로그인한 회원의 아이디를 이용해 해당 회원의 정보 찾기 */
	public AdminDTO findByAdminId(String adminId) {
		
		Admin admin = adminRepository.findByAdminId(adminId);
		
		return modelMapper.map(admin, AdminDTO.class);
	}

	/* 휴가 2차 승인시 휴가명이 연차일 경우 관리자의 연차 사용 횟수 증가 */
	public void modifyAnnualVacationUse(int adminNo) {
		
		adminRepository.modifyAnnualVacationUse(adminNo);
	}

	
	/* 퇴사자 페이징 */
	public int selectRetireTotalCount(String searchCondition, String searchValue) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");    
		
		int count = 0;
		if(searchValue != null) {
			
			/* 이름 페이징 */
			if("adminName".equals(searchCondition)){
				
				count = adminRepository.countByAdminRetireYnAndAdminNameContaining("Y", searchValue);
				
			}
			
			/* 생년월일 페이징 */
			if("adminBirth".equals(searchCondition)) {
				
				Date adminBirth = Date.valueOf(searchValue); 
				
				count = adminRepository.countByAdminRetireYnAndAdminBirth("Y", adminBirth);
				
			}
			
			/* 퇴사일 페이징 */
			if("adminRetireDate".equals(searchCondition)) {
				
				Date adminRetireDate = Date.valueOf(searchValue); 
				
				count = adminRepository.countByAdminRetireYnAndAdminRetireDate("Y", adminRetireDate);
				
			}
			
			/* 직책명 페이징 */
			if("adminJob".equals(searchCondition)) {
				
				count = adminRepository.countByAdminRetireYnAndAdminJob("Y", searchValue);
				
			}
			
			/* 휴대전화 번호 페이징 */
			if("adminPhone".equals(searchCondition)) {
				
				count = adminRepository.countByAdminRetireYnAndAdminPhoneContaining("Y", searchValue);
			}
			
			/* 전체 퇴직자 페이징 */
			count = adminRepository.countByAdminRetireYn("Y");
		}
		
		return count;
		
		
	}

}
