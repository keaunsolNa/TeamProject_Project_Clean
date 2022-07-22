package com.project.clean.model.service.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

	/* 재직자 조회 */
	public List<AdminDTO> findAdminList() {
		
		/* 퇴사여부가 Y(yes) 가 아닌 관리자 조회 */
		List<Admin> adminList = adminRepository.findAdminByAdminRetireN();
		
		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
	}
	
	/* 퇴사자 조회(Ajax) */
	public List<AdminDTO> findRetireAdminList() {
		
		List<Admin> retireAdminList = adminRepository.findAdminByAdminRetireY();
	
		
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

//	public AdminDTO findAllByBossNo(int bossNo) {
//		
//		Admin admin = adminRepository.findByCommitAdmin(bossNo);
//		
//		return modelMapper.map(admin, AdminDTO.class);
//	}

	
}
