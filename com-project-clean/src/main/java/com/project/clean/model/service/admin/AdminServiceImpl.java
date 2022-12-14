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
//		/* ??????????????? Y(yes) ??? ?????? ????????? ?????? */
//		List<Admin> adminList = adminRepository.findAdminByAdminRetireN();
//		
//		return adminList.stream().map(admin -> modelMapper.map(admin, AdminDTO.class)).collect(Collectors.toList());
//	}
//	
//	
//	/* ?????? ????????? ????????? ?????? ?????? ??????(?????? ??????) ?????? */
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
//	/* ????????? ???????????? ?????? ?????? */
//	@Override
//	public AdminDTO findByAdminNo(int adminNo) {
//		
//		Admin admin = adminRepository.findByAdminNo(adminNo).get();
//		
//		return modelMapper.map(admin, AdminDTO.class);
//	}
//
//
//	/* ????????? ?????? ?????? - ????????? */
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
//	/* ????????? ?????? ?????? - ?????? */
//	@Override
//	public AdminAddressDTO findAdminAddressByAdminNo(int adminNo) {
//		
//		AdminAddress adminAddress = adminAddressRepository.findByAdminNo(adminNo);
//		
//		return modelMapper.map(adminAddress, AdminAddressDTO.class);
//		
//	}
//
//	/* ????????? ?????? ?????? - ?????? */
//	@Override
//	public AdminPictureDTO findAdminPictureByAdminNo(int adminNo) {
//		
//		AdminPicture adminPicture = adminPictureRepository.findByAdminNo(adminNo);
//		
//		return modelMapper.map(adminPicture, AdminPictureDTO.class);
//	}
//
//	/* ?????? ????????? ?????? ?????? ?????? */
//	@Override
//	public void registNewAdmin(AdminDTO newAdmin) {
//		
//		adminRepository.save(modelMapper.map(newAdmin, Admin.class));
//		
//	}
//	
//	/* ????????? ????????? ?????? ?????? ????????? ????????? ?????? ?????? */
//	@Override
//	public int findNewAdminNo() {
//		
//		int adminNo = adminRepository.findNewAdminNo();
//		
//		return adminNo;
//	}
//
//
//	/* ?????? ????????? ????????? ?????? */
//	@Override
//	public void registNewAdminEmail(AdminEmailDTO adminEmail) {
//		
//		adminEmailRepository.save(modelMapper.map(adminEmail, AdminEmail.class));
//	}
//
//
//	/* ?????? ????????? ?????? ?????? */
//	@Override
//	public void registNewAdminAddress(AdminAddressDTO adminAddress) {
//		
//		adminAddressRepository.save(modelMapper.map(adminAddress, AdminAddress.class));
//		
//	}
//
//
//	/* ?????? ????????? ????????? ?????? */
//	@Override
//	public void registNewAdminPicture(AdminPictureDTO adminPicture) {
//		
//		adminPictureRepository.save(modelMapper.map(adminPicture, AdminPicture.class));
//		
//	}
//
//
//	/* ????????? ??????(Ajax) */
//	@Override
//	public List<RetireAdminDTO> findRetireAdminList() {
//		
//		List<RetireAdmin> retireAdminList = retireAdminRepository.findRetireAdminList();
//		
//		return retireAdminList.stream().map(retireAdmin -> modelMapper.map(retireAdmin, RetireAdminDTO.class)).collect(Collectors.toList());
//
//	}
//	
//	/* ????????? ??????(Ajax) */
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
