//package com.project.clean.model.service.admin;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.clean.model.domain.commonEntity.Admin;
//import com.project.clean.model.domain.commonEntity.AdminAddress;
//import com.project.clean.model.domain.commonEntity.AdminEmail;
//import com.project.clean.model.domain.commonEntity.AdminPicture;
//import com.project.clean.model.domain.commonEntity.RetireAdmin;
//import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
//import com.project.clean.model.dto.commonDTO.AdminDTO;
//import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
//import com.project.clean.model.dto.commonDTO.AdminPictureDTO;
//import com.project.clean.model.dto.commonDTO.RetireAdminDTO;
//import com.project.clean.model.repository.admin.AdminAddressRepository;
//import com.project.clean.model.repository.admin.AdminEmailRepository;
//import com.project.clean.model.repository.admin.AdminPictureRepository;
//import com.project.clean.model.repository.admin.AdminRepository;
//import com.project.clean.model.repository.admin.RetireAdminRepository;
//
//
//@Service
//public class AdminServiceImpl implements AdminService {
//	
//	private final AdminRepository adminRepository;
//	private final ModelMapper modelMapper;
//	private final AdminEmailRepository adminEmailRepository;
//	private final AdminAddressRepository adminAddressRepository;
//	private final AdminPictureRepository adminPictureRepository;
//	private final RetireAdminRepository retireAdminRepository;
//	
//	@Autowired
//	public AdminServiceImpl(AdminRepository adminRepository, ModelMapper modelMapper, AdminEmailRepository adminEmailRepository, AdminAddressRepository adminAddressRepository
//			, AdminPictureRepository adminPictureRepository, RetireAdminRepository retireAdminRepository) {
//		this.adminRepository = adminRepository;
//		this.modelMapper = modelMapper;
//		this.adminEmailRepository = adminEmailRepository;
//		this.adminAddressRepository = adminAddressRepository;
//		this.adminPictureRepository = adminPictureRepository;
//		this.retireAdminRepository = retireAdminRepository;
//	}
//	
//	@Override
//	public List<AdminDTO> findAdminList() {
//		
//		/* 퇴사여부가 Y(yes) 가 아닌 관리자 조회 */
//		List<Admin> adminList = adminRepository.findAdminByAdminRetireN();
//		
//		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
//	}
//	
//	
//	/* 신규 관리자 등록을 위한 기본 정보(신규 번호) 조회 */
//	@Override
//	public int findMaxAdmin() {
//		
//		int admin = adminRepository.findMaxAdmin();
//		
//		return admin;
//
//	}
//
//
//	/* 관리자 기본정보 상세 조회 */
//	@Override
//	public AdminDTO findByAdminNo(int adminNo) {
//		
//		Admin admin = adminRepository.findByAdminNo(adminNo).get();
//		
//		return modelMapper.map(admin, AdminDTO.class);
//	}
//
//
//	/* 관리자 상세 조회 - 이메일 */
//	@Override
//	public AdminEmailDTO findAdminEmailByAdminNoEqual(int adminNo) {
//		
//		AdminEmail adminEmail = adminEmailRepository.findByAdminNo(adminNo);
//		
//		return modelMapper.map(adminEmail, AdminEmailDTO.class);
//
//	}
//
//
//	/* 관리자 상세 조회 - 주소 */
//	@Override
//	public AdminAddressDTO findAdminAddressByAdminNo(int adminNo) {
//		
//		AdminAddress adminAddress = adminAddressRepository.findByAdminNo(adminNo);
//		
//		return modelMapper.map(adminAddress, AdminAddressDTO.class);
//		
//	}
//
//	/* 관리자 상세 조회 - 사진 */
//	@Override
//	public AdminPictureDTO findAdminPictureByAdminNo(int adminNo) {
//		
//		AdminPicture adminPicture = adminPictureRepository.findByAdminNo(adminNo);
//		
//		return modelMapper.map(adminPicture, AdminPictureDTO.class);
//	}
//
//	/* 신규 관리자 기본 정보 등록 */
//	@Override
//	public void registNewAdmin(AdminDTO newAdmin) {
//		
//		adminRepository.save(modelMapper.map(newAdmin, Admin.class));
//		
//	}
//	
//	/* 관리자 등록을 위한 기본 등록된 관리자 번호 조회 */
//	@Override
//	public int findNewAdminNo() {
//		
//		int adminNo = adminRepository.findNewAdminNo();
//		
//		return adminNo;
//	}
//
//
//	/* 신규 관리자 이메일 등록 */
//	@Override
//	public void registNewAdminEmail(AdminEmailDTO adminEmail) {
//		
//		adminEmailRepository.save(modelMapper.map(adminEmail, AdminEmail.class));
//	}
//
//
//	/* 신규 관리자 주소 등록 */
//	@Override
//	public void registNewAdminAddress(AdminAddressDTO adminAddress) {
//		
//		adminAddressRepository.save(modelMapper.map(adminAddress, AdminAddress.class));
//		
//	}
//
//
//	/* 신규 관리자 이미지 등록 */
//	@Override
//	public void registNewAdminPicture(AdminPictureDTO adminPicture) {
//		
//		adminPictureRepository.save(modelMapper.map(adminPicture, AdminPicture.class));
//		
//	}
//
//
//	/* 퇴사자 조회(Ajax) */
//	@Override
//	public List<RetireAdminDTO> findRetireAdminList() {
//		
//		List<RetireAdmin> retireAdminList = retireAdminRepository.findRetireAdminList();
//		
//		return retireAdminList.stream().map(retireAdmin -> modelMapper.map(retireAdmin, RetireAdminDTO.class)).collect(Collectors.toList());
//
//	}
//	
//	/* 재직자 조회(Ajax) */
//	@Override
//	public List<AdminDTO> findAdminListAjax() {
//		
//		List<Admin> adminListAjax = adminRepository.findAdminList();
//		
//		return adminListAjax.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
//	}
//
//
//	@Override
//	public void modifyAdminRetire(AdminDTO admin) {
//		
//		adminRepository.modifyAdminRetire(modelMapper.map(admin, Admin.class));
//		
//	}
//	
//}
