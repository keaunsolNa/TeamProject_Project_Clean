package com.project.clean.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.RetireAdminDTO;
import com.project.clean.model.service.admin.AdminAdminService;

@Controller
@RequestMapping("admin")
public class AdminAdminController {
	
private final AdminAdminService adminService;
	
	@Autowired
	public AdminAdminController(AdminAdminService adminService) {
		this.adminService = adminService;
	}
	
	
	/* 관리자 등록 양식 및 최대 관리자 번호, 아이디 조회 */
	@GetMapping("hrCard/adminRegist")
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
	public ModelAndView registAdminHrCard(@ModelAttribute AdminDTO newAdmin, ModelAndView mv, HttpServletRequest request,
											@RequestParam("thumbnailImg") MultipartFile thumbnailImg) throws UnsupportedEncodingException, ThumbnailRegistException {
		
		/* 기본값 셋팅 */
		String pwd = "000000";
		String adminRetireYn = "N";
		Integer adminSalary = 2000000;
		
		/* 주소 합치기 */
		String adminAddressNo = request.getParameter("addressNo");
		String adminAddress1 = request.getParameter("address");
		String adminAddress2 = request.getParameter("addressDetail");
		
		String adminAddress = adminAddressNo + "@" + adminAddress1 + "@" + adminAddress2;
		
		newAdmin.setAdminPwd(pwd);
		newAdmin.setAdminRetireYn(adminRetireYn);
		newAdmin.setAdminSalary(adminSalary);
		newAdmin.setAdminAddress(adminAddress);
		
		
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

					/* DB에 업로드할 파일의 정보를 DTO에 set */
					newAdmin.setAdminPictureSaveName(adminPictureSaveName);
					newAdmin.setAdminPictureSaveRoot(adminPictureSaveRoot);

					adminService.registNewAdmin(newAdmin);
					
						
								
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
	public ModelAndView findAdminDetail(ModelAndView mv, @PathVariable int adminNo, HttpServletRequest request) {
		
		/* 관리자 기본 정보 조회 */
		System.out.println("동작하나용");
		AdminDTO admin = adminService.findByAdminNo(adminNo);
		
		/* DB에 저장된 주소 @로 분리 */
		String[] seperateAddress = admin.getAdminAddress().split("@");
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];
		
		System.out.println("addressNo" + addressNo);
		System.out.println("address" + address);
		System.out.println("addressDetail" + addressDetail);
		
		/* 나눠진 주소를 담아준다. */
		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);


		/* 화면에 보여줄 정보를 담아준다. */
		mv.addObject("address", addressMap);
		mv.addObject("admin", admin);
		
		/* 경로 설정 */
		mv.setViewName("admin/hrCard/adminDetail");
		
		return mv;
	}
	
	/* 관리자 퇴사처리 */
	@Transactional
	@PostMapping("hrCard/adminDetail/Delete")
	public String modisfyAdminRetire(RedirectAttributes rttr, @ModelAttribute AdminDTO admin, RetireAdminDTO retireAdmin, HttpServletRequest request) {
		
		/* 퇴사할 관리자의 번호 가져오기 */
		int adminNo = Integer.parseInt(request.getParameter("adminNo"));
		
		/* 퇴사 여부 수정 */
		adminService.modifyAdminRetireY(adminNo);
		
		/* 퇴사일 수정 */
		adminService.modifyAdminRetireDate(adminNo);
		
		/* 수정한 정보 읽어오기 */
		admin = adminService.findByAdminNo(adminNo);
		
		int retireAdminNo =  adminNo;
		String retireAdminName = admin.getAdminName();
		String retireAdminId = admin.getAdminId();
		String retireAdminPwd = admin.getAdminPwd();
		Date retireAdminBrith = admin.getAdminBirth();
		String retireAdminGender = admin.getAdminGender();
		String retireAdminPhone = admin.getAdminPhone();
		Date retireAdminHireDate = admin.getAdminHireDate();
		Date retireAdminRetireDate = admin.getAdminRetireDate();
		String retireAdminRetireYn = admin.getAdminRetireYn();
		String retireAdminJob = admin.getAdminJob();
		Date retireAdminLastLoginDate = admin.getAdminLastLoginDate();
		int retireAdminSalary = admin.getAdminSalary();
		int retireAnnualVacationUse = admin.getAnnualVacationUse();
		
		retireAdmin.setRetireAdminNo(retireAdminNo);
		retireAdmin.setRetireAdminName(retireAdminName);
		retireAdmin.setRetireAdminId(retireAdminId);
		retireAdmin.setRetireAdminPwd(retireAdminPwd);
		retireAdmin.setRetireAdminBrith(retireAdminBrith);
		retireAdmin.setRetireAdminGender(retireAdminGender);
		retireAdmin.setRetireAdminPhone(retireAdminPhone);
		retireAdmin.setRetireAdminHireDate(retireAdminHireDate);
		retireAdmin.setRetireAdminRetireDate(retireAdminRetireDate);
		retireAdmin.setRetireAdminRetireYn(retireAdminRetireYn);
		retireAdmin.setRetireAdminJob(retireAdminJob);
		retireAdmin.setRetireAdminLastLoginDate(retireAdminLastLoginDate);
		retireAdmin.setRetireAdminSalary(retireAdminSalary);
		retireAdmin.setRetireAnnualVacationUse(retireAnnualVacationUse);
		
		/* 읽어온 정보 퇴사자 테이블에 인서트 */
		adminService.registRetireAdmin(retireAdmin);
		
//		rttr.addFlashAttribute("modifySuccessMessage", "퇴사 처리에 성공하였습니다.");
		
		return "redirect:/admin/hrCard/adminList";
	}
	
	
	/* 관리자 본인 인사카드 수정 페이지로 이동 */
	@GetMapping("hrCard/adminModifySelf/{adminNo}")
	public ModelAndView adminModifySelf(@PathVariable int adminNo, ModelAndView mv) {
		
		AdminDTO adminModify = adminService.selectOneAdmin(adminNo);
		
		/* 핸드폰 번호 출력 시 - 추가 */
//		String firstPhoneNumber = "";
//		String middlePhoneNumber = "";
//		String lastPhoneNumber = "";
//		if (employeeDTO.getEmployeePhone().length() == 11) {
//			firstPhoneNumber = employeeDTO.getEmployeePhone().substring(0, 3);
//			middlePhoneNumber = employeeDTO.getEmployeePhone().substring(3, 7);
//			lastPhoneNumber = employeeDTO.getEmployeePhone().substring(7, 11);
//		} else if (employeeDTO.getEmployeePhone().length() == 10) {
//			middlePhoneNumber = employeeDTO.getEmployeePhone().substring(3, 6);
//			lastPhoneNumber = employeeDTO.getEmployeePhone().substring(6, 10);
//		}

		
		/* DB에 저장된 전화번호 - 로 분리 */
		String[] seperatephone = adminModify.getAdminPhone().split("-");		
		
		String firstPhoneNumber = seperatephone[0];
		String middlePhoneNumber = seperatephone[1];
		String lastPhoneNumber = seperatephone[2];
		
		System.out.println(firstPhoneNumber);
		System.out.println(middlePhoneNumber);
		System.out.println(lastPhoneNumber);
		
		/* 나눠진 번호를 담아준다. */
		Map<String, String> phone = new HashMap<>();
		phone.put("firstPhoneNumber", firstPhoneNumber);
		phone.put("middlePhoneNumber", middlePhoneNumber);
		phone.put("lastPhoneNumber", lastPhoneNumber);

		/* DB에 저장된 주소 @로 분리 */
		String[] seperateAddress = adminModify.getAdminAddress().split("@");
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];
		
		/* 나눠진 주소를 담아준다. */
		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);

		/* DB에 저장된 이메일을 @로 분리 */
		String[] seperateEmail = adminModify.getAdminEmail().split("@");
		String email = seperateEmail[0];
		String domain = seperateEmail[1];
		
		/* 분리한 이메일을 담아준다. */
		Map<String, String> emailMap = new HashMap<>();
		emailMap.put("email", email);
		emailMap.put("domain", domain);

//		adminModify.setAdminPhone(firstPhoneNumber+"-"+middlePhoneNumber+"-"+lastPhoneNumber);
		
		
		/* 화면에 보여줄 정보를 담아준다. */
		mv.addObject("phone", phone);
		mv.addObject("address", addressMap);
		mv.addObject("email", emailMap);
		mv.addObject("adminModify", adminModify);
		
		/* 경로를 설정 */
		mv.setViewName("admin/hrCard/adminModifySelf");
		
		return mv;
	}
	
	
	/* 관리자 본인 정보 수정 */
	@PostMapping("hrCard/adminModifySelf/run")
	public String adminModify(RedirectAttributes rttr,@ModelAttribute AdminDTO admin, HttpServletRequest request) {
		
		int adminNo = Integer.parseInt(request.getParameter("adminNo"));
		
		String adminAddressNo = request.getParameter("addressNo");
		String adminAddress1 = request.getParameter("address");
		String adminAddress2 = request.getParameter("addressDetail");
		
		System.out.println(adminAddressNo);
		System.out.println(adminAddress1);
		System.out.println(adminAddress1);
		System.out.println(adminAddress1);
		System.out.println(adminAddress1);
		System.out.println(adminAddress1);
		
		String adminAddress = adminAddressNo + "@" + adminAddress1 + "@" + adminAddress2;
 	
		String adminPhone = request.getParameter("adminPhone");
		
		String adminEmail = request.getParameter("adminEmail");
		
		admin.setAdminAddress(adminAddress);
		admin.setAdminEmail(adminEmail);
		admin.setAdminPhone(adminPhone);
		
		System.out.println(adminAddress);
		System.out.println(adminAddress);
		System.out.println(adminAddress);
		System.out.println(adminAddress);
		System.out.println(adminAddress);
		
		System.out.println(adminEmail);
		System.out.println(adminEmail);
		System.out.println(adminEmail);
		System.out.println(adminPhone);
		
		adminService.modifyAdminSelfCard(admin);

		rttr.addFlashAttribute("adminModifySuccessMessage", "수정에 성공하셨습니다");
		
		return "redirect:/admin/hrCard/adminList";
	}


}
