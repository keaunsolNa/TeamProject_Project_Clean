package com.project.clean.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
import com.project.clean.model.dto.commonDTO.RetireAdminDTO;
import com.project.clean.model.service.admin.AdminService;
import com.project.clean.model.service.admin.RetireAdminService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private final AdminService adminService;
	private final RetireAdminService retireAdminService;
	
	@Autowired
	public AdminController(AdminService adminService, RetireAdminService retireAdminService) {
		this.adminService = adminService;
		this.retireAdminService = retireAdminService;
	}

	@PostMapping("adminMainPage")
	public void AdminMainpage() {}
	
	
	
	/* 관리자 목록 조회(퇴사여부 N) */
	@GetMapping("hrCard/adminList")
	public ModelAndView findAdminList(ModelAndView mv) {

		List<AdminDTO> adminList = adminService.findAdminList();
		
		mv.addObject("adminList", adminList);
		mv.setViewName("admin/hrCard/adminList");
		
		return mv;
		
	}
	
	/* 퇴사자 목록 조회 */
	@GetMapping("hrCard/adminList/retireAdminList")
	public ModelAndView findRetireAdminList(ModelAndView mv, HttpServletResponse response) {
		
		
		List<RetireAdminDTO> retireAdminList = retireAdminService.findRetireAdminList();
		
		
		Gson gson = new GsonBuilder()
			      .setDateFormat("yyyy-MM-dd hh:mm:ss:SSS")
			      .setPrettyPrinting()
			      .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
			      .serializeNulls()
			      .disableHtmlEscaping()
			      .create();
		
		mv.addObject("retireAdminList", gson.toJson(retireAdminList));
		mv.setViewName("admin/hrCard/adminList/retireAdminList");
		
		return mv;
	}
	
	/* 관리자 상세조회 */
	@GetMapping("hrCard/adminDetail/{adminNo}")
	@Transactional
	public ModelAndView findRetireAdminDetail(ModelAndView mv, @PathVariable int adminNo, HttpServletRequest request) {
		
		/* 관리자 기본 정보 조회 */
		System.out.println("동작하나용");
		AdminDTO admin = adminService.findByAdminNo(adminNo);
		
		System.out.println(admin);
		
		
		
		/* 관리자 이메일(리스트) 조회 */
		AdminEmailDTO adminEmail = adminService.findAdminEmailByAdminNoEqual(adminNo);
		
		String email = adminEmail.getEmail() + "@" + adminEmail.getDomain();
		
		System.out.println(email);
		
		
		/* 관리자 주소 조회 */
		AdminAddressDTO adminAddress = adminService.findAdminAddressByAdminNo(adminNo);
		
		System.out.println(adminAddress);
		
		/* 관리자 사진 조회 */
		
		/* 조회값 mv에 담기 */
		mv.addObject("admin", admin);
		mv.addObject("email", email);
		mv.addObject("adminAddress", adminAddress);

		mv.setViewName("admin/hrCard/adminDetail");

		
		return mv;
	}
	
	
	

}
