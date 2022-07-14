package com.project.clean.model.service.admin;

import java.util.List;

import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
import com.project.clean.model.dto.commonDTO.AdminPictureDTO;
import com.project.clean.model.dto.commonDTO.RetireAdminDTO;



public interface AdminService {

	List<AdminDTO> findAdminList();

	AdminDTO findByAdminNo(int adminNo);

	AdminEmailDTO findAdminEmailByAdminNoEqual(int adminNo);

	AdminAddressDTO findAdminAddressByAdminNo(int adminNo);

	int findMaxAdmin();

	void registNewAdmin(AdminDTO newAdmin);

	int findNewAdminNo();

	void registNewAdminEmail(AdminEmailDTO adminEmail);

	void registNewAdminAddress(AdminAddressDTO adminAddress);

	void registNewAdminPicture(AdminPictureDTO adminPicture);

	void modifyAdminRetire(AdminDTO admin);

	AdminPictureDTO findAdminPictureByAdminNo(int adminNo);

	List<RetireAdminDTO> findRetireAdminList();

	List<AdminDTO> findAdminListAjax();


}
