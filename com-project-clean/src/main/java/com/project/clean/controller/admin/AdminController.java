package com.project.clean.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.exception.ThumbnailRegistException;
import com.project.clean.model.dto.commonDTO.AdminAddressDTO;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
import com.project.clean.model.dto.commonDTO.AdminPictureDTO;
import com.project.clean.model.dto.commonDTO.RetireAdminDTO;
import com.project.clean.model.service.admin.AdminService;

@Controller
@RequestMapping("admin")
public class AdminController {
	
	private final AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("adminMainPage")
	public void AdminMainpage() {}
	
	/* 관리자 등록 양식 및 최대 관리자 번호, 아이디 조회 */
	@GetMapping("hrCard/adminRegist")
	@Transactional
	public ModelAndView registAdmin(ModelAndView mv, HttpServletRequest request) {
		
		int admin = adminService.findMaxAdmin();
		int adminNo = admin + 1;
		
		System.out.println(adminNo);
		
		/* 기본 아이디 문자열 저장 */
		String clean = "cleanup";
		
		// 아이디 지정을 위한 현재 날짜 구하기        
		LocalDateTime now = LocalDateTime.now();             
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddhhmmss");         
		String up = now.format(formatter);          
		
		System.out.println(up); 
	
		/* 아이디 */
		String adminId = clean + up;
		
		System.out.println("새로운 아이디 : " + adminId);
		
		mv.addObject("adminNo", adminNo);
		mv.addObject("adminId", adminId);
		mv.setViewName("admin/hrCard/adminRegist");
		
		return mv;
	}
	
	/* 관리자 등록 */
	@PostMapping("hrCard/adminRegist")
	@Transactional
	public ModelAndView registAdminHrCard(@ModelAttribute AdminPictureDTO adminPicture, ModelAndView mv, AdminDTO newAdmin, 
											AdminEmailDTO adminEmail, AdminAddressDTO adminAddress, HttpServletRequest request,
											@RequestParam("thumbnailImg") MultipartFile thumbnailImg) throws UnsupportedEncodingException, ThumbnailRegistException {
		
		/* 기본값 셋팅 */
		String pwd = "000000";
		String adminRetireYn = "N";
		Integer adminSalary = 2000000;
	
		newAdmin.setAdminPwd(pwd);
		newAdmin.setAdminRetireYn(adminRetireYn);
		newAdmin.setAdminSalary(adminSalary);
		
		adminService.registNewAdmin(newAdmin);
		
		
		/* 저장한 관리자번호 조회해오기 */
		int adminNo = adminService.findNewAdminNo();
		
		System.out.println("저장된 관리자 번호 :" + adminNo);
		
		
		/* 이메일 저장 */
		adminEmail.setAdminNo(adminNo);
		
		adminService.registNewAdminEmail(adminEmail);
		
		
		/* 주소 저장 */
		adminAddress.setAdminNo(adminNo);
		
		adminService.registNewAdminAddress(adminAddress);
		
		
		/* 이미지 저장 */
		
		String rootLocation = System.getProperty("user.dir");
	
		/* 저장경로 설정 */
		String adminPictureSaveRoot = rootLocation + "/src/main/resources/static/adminPicture/thumbnail";
//		String adminPictureThumbNamilName = rootLocation + "/adminThumbnail";
		
		/* 파일 저장경로가 존재하지 않을 경우를 대비해 생성하는 코드 */
		File directory = new File(adminPictureSaveRoot);
		
		if(!directory.exists()) {
			System.out.println("폴더 생성 : " + directory.mkdir());
		}
		
		
		
		List<MultipartFile> fileList = new ArrayList<>();
		fileList.add(thumbnailImg);
		
		for(MultipartFile file : fileList) {
			if(file.getSize() > 0) {
				
				/* 파일명 전달받기 */
				String adminPictureOriginName = file.getOriginalFilename();
				
				System.out.println("원본 파일명은 ? : " + adminPictureOriginName);
				
				/* 파일명 변경 */
				String ext = adminPictureOriginName.substring(adminPictureOriginName.lastIndexOf("."));
				String adminPictureSaveName = UUID.randomUUID().toString().replace("-", "") + ext;
				
				/* 이미지 확장자 검사 */
//				String[] supportFormat = { "bmp", "jpg", "jpeg", "png" };
//                if (!Arrays.asList(supportFormat).contains(formatName)) {
//                    throw new IllegalArgumentException("지원하지 않는 format 입니다.");
//                }
				
				System.out.println("변경할 이름 확인 : " + adminPictureSaveName);
				
				
				try {
							
					thumbnailImg.transferTo(new File(adminPictureSaveRoot + "/" + adminPictureSaveName));
					

					/* DB에 업로드할 파일의 정보를 Map에 담아 저장하는 비즈니스 로직 */
					adminPicture.setAdminNo(adminNo);
					adminPicture.setPictureOriginName(adminPictureOriginName);
					adminPicture.setPictureSaveName(adminPictureSaveName);
					adminPicture.setPictureSaveRoot(adminPictureSaveRoot);

					adminService.registNewAdminPicture(adminPicture);
						
								
				} catch (IllegalStateException | IOException e) {
					
					e.printStackTrace();
					
					/* 실패시 파일 삭제 */
					new File(adminPictureSaveRoot + "/" + adminPictureSaveName).delete();
					
				}
				
			}
		}

		mv.setViewName("redirect:/admin/hrCard/adminList");
		
		return mv;
	}
	
	
	/* 관리자 목록 조회(퇴사여부 N) */
	@GetMapping("hrCard/adminList")
	@ResponseBody
	public ModelAndView findAdminList(ModelAndView mv) {

		List<AdminDTO> adminList = adminService.findAdminList();
		
		mv.addObject("adminList", adminList);
		mv.setViewName("admin/hrCard/adminList");
		
		return mv;
		
	}
	
	
	/* 퇴사한 관리자 목록 조회(Ajax) */
	@GetMapping(value = "hrCard/adminList/retireAdminAjax", produces = "application/json; charset=UTF-8;")
	@ResponseBody
	public List<RetireAdminDTO> findRetireAdminListAjax(Model mv) {
		
		List<RetireAdminDTO> retireAdminList = adminService.findRetireAdminList();
		
		return retireAdminList;
	}
	
	
	/* 재직 관리자 목록 조회(Ajax) */
	@GetMapping(value = "hrCard/adminList/adminAjax", produces = "application/json; charset=UTF-8;")
	@ResponseBody
	public List<AdminDTO> findAdminListAjax(Model mv){
		
		List<AdminDTO> adminListAjax = adminService.findAdminListAjax();
		
		return adminListAjax;
		
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
		
		String email = adminEmail.getAdminEmail() + "@" + adminEmail.getAdminDomain();
		
		System.out.println(email);
		
		
		/* 관리자 주소 조회 */
		AdminAddressDTO adminAddress = adminService.findAdminAddressByAdminNo(adminNo);
		
		System.out.println(adminAddress);
		
		/* 관리자 사진 조회 */
		AdminPictureDTO adminPicture = adminService.findAdminPictureByAdminNo(adminNo);
		
		/* 조회값 mv에 담기 */
		mv.addObject("admin", admin);
		mv.addObject("email", email);
		mv.addObject("adminAddress", adminAddress);
		mv.addObject("adminPicture", adminPicture);

		mv.setViewName("admin/hrCard/adminDetail");

		
		return mv;
	}
	
	/* 관리자 퇴사처리 */
	@PostMapping("admin/hrCard/Delete")
	public String modifyAdminRetire(RedirectAttributes rttr, @ModelAttribute AdminDTO admin) {
		
		adminService.modifyAdminRetire(admin);
		
		rttr.addFlashAttribute("modifySuccessMessage", "퇴사 처리에 성공하였습니다.");
		
		return "redirect:/admin/hrCard/adminDetail" + admin.getAdminNo();
	}

}
