package com.project.clean.model.service.admin;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

	
	public List<AdminDTO> findAdminList() {
		
		/* 퇴사여부가 Y(yes) 가 아닌 관리자 조회 */
		List<Admin> adminList = adminRepository.findAdminByAdminRetireN();
		
		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
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

	/* 관리자 기본정보 상세 조회 */
	public AdminDTO findByAdminNo(int adminNo) {
		
		Admin admin = adminRepository.findByAdminNo(adminNo).get();
		
		return modelMapper.map(admin, AdminDTO.class);
	}


	/* 신규 관리자 기본 정보 등록 */
	public void registNewAdmin(AdminDTO newAdmin) {
		
		adminRepository.save(modelMapper.map(newAdmin, Admin.class));
	}

	
	/* 퇴사자 조회(Ajax) */
	@Transactional
	public List<RetireAdminDTO> findRetireAdminList() {
		
		List<RetireAdmin> retireAdminList = retireAdminRepository.findRetireAdminList();
		
		return retireAdminList.stream().map(retireAdmin -> modelMapper.map(retireAdmin, RetireAdminDTO.class)).collect(Collectors.toList());

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
	public void modifyAdminSelfCard(AdminDTO adminDTO) {
		
		System.out.println("서비스 잘왔나~");
		
		
		Admin adminModify = adminRepository.findByAdminNo(adminDTO.getAdminNo()).get();
		
		adminModify.setAdminAddress(adminDTO.getAdminAddress());
		adminModify.setAdminPhone(adminDTO.getAdminPhone());
		adminModify.setAdminEmail(adminDTO.getAdminEmail());
		
		System.out.println(adminModify);
		System.out.println(adminModify);

		System.out.println(adminModify);

		System.out.println(adminModify);

		System.out.println(adminModify);

		System.out.println(adminModify);


	}

	/* 퇴사자 정보 퇴사자 테이블에 삽입 */
	public void registRetireAdmin(RetireAdminDTO retireAdmin) {
		
		retireAdminRepository.save(modelMapper.map(retireAdmin, RetireAdmin.class));
		
	}


	

	
}
