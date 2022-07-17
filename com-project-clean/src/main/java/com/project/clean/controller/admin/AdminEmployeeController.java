package com.project.clean.controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.PageDTO;
import com.project.clean.model.dto.commonDTO.ReasonDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.service.admin.AdminEmployeeService;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

	private final AdminEmployeeService adminService;
	private final PageDTO pageDTO;

	@Autowired
	public AdminEmployeeController(AdminEmployeeService adminService, PageDTO pageDTO) {
		this.adminService = adminService;
		this.pageDTO = pageDTO;
	}

	public Date today() {
		LocalDate now = LocalDate.now();
		java.sql.Date today = java.sql.Date.valueOf(now);
		return today;

	}

	public void registEmployeePicture(EmployeeAndAllDTO employeeDTO, @RequestParam("picture") MultipartFile singleFile,
			HttpServletRequest request, Model mv) {

		String root = request.getSession().getServletContext().getRealPath("/");
		System.out.println("root까지의 경로 : " + root);

		String filePath = root + "adminEmployeePicture";
		/* 1-2. uploadFiles 폴더 생성 */
		File mkdir = new File(filePath); // file.io 패키지로 import
		if (!mkdir.exists()) {
			mkdir.mkdirs();
		}

		/* 2. 파일을 전달받아 파일명 변경 처리 */
		if (!singleFile.getOriginalFilename().isEmpty()) {

			String originFileName = singleFile.getOriginalFilename();
			System.out.println("원본 이름 : " + originFileName);
			String ext = originFileName.substring(originFileName.lastIndexOf("."));

			String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
			System.out.println("변경한 이름 : " + saveName);

			/* 3. 파일을 저장한다. */
			try {
				singleFile.transferTo(new File(filePath + "/" + saveName));
				employeeDTO.setEmployeePictureSaveName(saveName);
				employeeDTO.setEmployeePictureSaveRoot(filePath);
				/* employeeDTO.setEmployeePictureThumbnail(saveName); */

				/* DB에 업로드한 파일의 정보를 저장하는 비즈니스 로직 수행 */

				mv.addAttribute("message", "파일 업로드 성공!");
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();

				/* 실패 시 파일 삭제 */
				new File(filePath + "/" + saveName).delete();
				mv.addAttribute("message", "파일 업로드 실패!");
			}
		}

	}

	
	
	
	
	/* 직원 전체 조회(재직자 main page) & 페이징*/
	@GetMapping("/select/retireN")
	public String selectRetireNEmployee(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		Page<EmployeeAndAllDTO> startPage = adminService.selectRetireNEmployee(page);
		List<EmployeeAndAllDTO> startList = startPage.toList();
		
		/* .getnumber 메서드를 위해 list로 변환 x */
		mv.addAttribute("pages", startPage );
		mv.addAttribute("maxPage", 5);
		mv.addAttribute("employeeAllList", startList);
		mv.addAttribute("mainTain", "N");
		mv.addAttribute("entireYn", startList);
		return "/admin/humanResource/selectAllEmployee/selectAllEmployee";
	}
	
	/* 직원 전체 조회(퇴사자 main page) & 페이징*/
	@GetMapping("/select/retireY")
	public String selectRetireYEmployee(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		Page<EmployeeAndAllDTO> startPage = adminService.selectRetireYEmployee(page);
		List<EmployeeAndAllDTO> startList = startPage.toList();
		
		/* .getnumber 메서드를 위해 list로 변환 x */
		mv.addAttribute("pages", startPage );
		mv.addAttribute("maxPage", 5);
		mv.addAttribute("employeeAllList", startList);
		mv.addAttribute("entireYn", startList);
		mv.addAttribute("mainTain", "Y");
		return "/admin/humanResource/selectAllEmployee/selectAllEmployee";
	}
	

	
//	/* 직원 전체 조회(퇴사자 main page) & 페이징*/
//	@GetMapping(value = "/select/retireYAjax", produces = "application/json; charset=UTF-8")
//	@ResponseBody
//	public List<EmployeeAndAllDTO> retireYAjax(@RequestParam(value = "page", defaultValue = "0") int page, Model mv, HttpServletRequest request) {
//		Page<EmployeeAndAllDTO> startPage = adminService.selectRetireYEmployee(page);
//		List<EmployeeAndAllDTO> startList = startPage.toList();
//		
//		mv.addAttribute("pages", startPage );
//		mv.addAttribute("maxPage", 5);
//		mv.addAttribute("employeeAllList", startList);
//		mv.addAttribute("entireYn", startList);
//		mv.addAttribute("mainTain", "Y");
//		return startList;
//	}
	
	/* ajax 직원 전체 조회 (퇴사자) */
//	@GetMapping(value = "/select/retireYEmployee", produces = "application/json; charset=UTF-8;")
//	@ResponseBody
//	public List<EmployeeAndAllDTO> selectRetireYEmployee(Model mv, @PageableDefault(size = 2) Pageable page) {
//		List<EmployeeAndAllDTO> selectRetireYEmployeeList = new ArrayList<>();
//		selectRetireYEmployeeList = adminService.selectRetireYEmployee(page);
//
//		return selectRetireYEmployeeList;
//	}
	
	
	
	
	
	
//	@GetMapping("/list")
//	public String welcome(@RequestParam(value = "page", defaultValue = "0") int page, Model mv){
//
//	    // 페이징 처리
//	    Page<AdminEmployee> entireNEmployeeList = adminService.findAllPage(page);
//
//	    mv.addAttribute("pages", entireNEmployeeList );
//	    mv.addAttribute("maxPage", 5);
//	    return "admin/humanResource/selectAllEmployee/selectAllEmployee";
//	}
	
//	/* 직원 전체 조회(재직자 main page) & 재직자버튼 & 다음 페이지*/
//	@GetMapping("/select/retireNEmployee/next")
//	public String selectRetireNEmployeeNext(@PageableDefault(size = 1) Pageable pageable, Model mv) {
//		List<EmployeeAndAllDTO> startList = adminService.selectRetireNEmployee(pageable.next());
//		
//		
//		for(EmployeeAndAllDTO d : startList) {
//			System.out.println(d);
//		}
//		
//		mv.addAttribute("page", pageDTO);
//		mv.addAttribute("entireNEmployeeList", startList);
//		return "/admin/humanResource/selectAllEmployee/selectAllEmployee";
//	}
//	
//	/* 직원 전체 조회(재직자 main page) & 재직자버튼 & 이전 페이지*/
//	@GetMapping("/select/retireNEmployee/previous")
//	public String selectRetireNEmployeePrevious(@PageableDefault(size = 5) Pageable pageable, Model mv) {
//		List<EmployeeAndAllDTO> startList = adminService.selectRetireNEmployee(pageable.previousOrFirst());
//		pageDTO.setPageNo(pageable.getPageNumber());
//		
//		mv.addAttribute("page", pageDTO);
//		mv.addAttribute("entireNEmployeeList", startList);
//		return "/admin/humanResource/selectAllEmployee/selectAllEmployee";
//	}
	
	
	


//	/* ajax 직원 전체 조회 (재직자) */
//	@GetMapping(value = "/select/retireNEmployeeAjax", produces = "application/json; charset=UTF-8;")
//	@ResponseBody
//	public List<EmployeeAndAllDTO> selectRetireNEmployeeAjax(Model mv) {
//		List<EmployeeAndAllDTO> selectRetireNEmployeeList = new ArrayList<>();
//		selectRetireNEmployeeList = adminService.selectRetireNEmployee();
//
//		return selectRetireNEmployeeList;
//	}



	/* (관리자가)직원 수정 페이지로 이동 */
	@GetMapping("/modify/employee/{empNo}")
	public String adminModifyEmployee(@PathVariable int empNo, Model mv) {

		EmployeeAndAllDTO employeeDTO = adminService.selectOneEmployee(empNo);

		String[] seperatephone = employeeDTO.getEmployeePhone().split("-");

		String firstPhoneNumber = seperatephone[0];
		String middlePhoneNumber = seperatephone[1];
		String lastPhoneNumber = seperatephone[2];

		Map<String, String> phone = new HashMap<>();
		phone.put("firstPhoneNumber", firstPhoneNumber);
		phone.put("middlePhoneNumber", middlePhoneNumber);
		phone.put("lastPhoneNumber", lastPhoneNumber);

		String[] seperateAddress = employeeDTO.getEmployeeAddress().split("@");
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];

		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);

		String[] seperateEmail = employeeDTO.getEmployeeEmail().split("@");
		String email = seperateEmail[0];
		String domain = seperateEmail[1];

		Map<String, String> emailMap = new HashMap<>();
		emailMap.put("email", email);
		emailMap.put("domain", domain);

		employeeDTO.setEmployeePhone(firstPhoneNumber + "-" + middlePhoneNumber + "-" + lastPhoneNumber);

		mv.addAttribute("phone", phone);
		mv.addAttribute("address", addressMap);
		mv.addAttribute("email", emailMap);
		mv.addAttribute("employee", employeeDTO);

		return "/admin/humanResource/selectAllEmployee/modifyEmployee";
	}

	/* (관리자가)직원 정보 수정 */
	@PostMapping("/modify/employee")
	public String modifyEmployee(EmployeeAndAllDTO employeeDTO, String oldSaveRoot, String oldSaveName, Model mv,
			@RequestParam("picture") MultipartFile singleFile, HttpServletRequest request) {

		String root = request.getSession().getServletContext().getRealPath("/");
		String filePath = root + "adminEmployeePicture";
		
			if (!singleFile.getOriginalFilename().isEmpty()) {

				String originFileName = singleFile.getOriginalFilename();
				System.out.println("원본 이름 : " + originFileName);
				String ext = originFileName.substring(originFileName.lastIndexOf("."));

				String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("변경한 이름 : " + saveName);

				/* 3. 파일을 저장한다. */
				try {
					singleFile.transferTo(new File(filePath + "/" + saveName));
					employeeDTO.setEmployeePictureSaveName(saveName);
					employeeDTO.setEmployeePictureSaveRoot(filePath);
					
					new File(oldSaveRoot + "/" + oldSaveName).delete();
					/* employeeDTO.setEmployeePictureThumbnail(saveName); */
					/* DB에 업로드한 파일의 정보를 저장하는 비즈니스 로직 수행 */

					mv.addAttribute("message", "파일 업로드 성공!");
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();

					/* 실패 시 파일 삭제 */
					new File(filePath + "/" + saveName).delete();
					mv.addAttribute("message", "파일 업로드 실패!");
				}
			}
			
			adminService.modifyEmployee(employeeDTO);
//		new File(oldSaveRoot + "/" + oldSaveName).delete();

		mv.addAttribute("employeeDTO", employeeDTO);
		return "redirect:/admin/select/retireNEmployee";
	}

	/* 직원 등록 페이지 이동 */
	@GetMapping("/hr/regist/employee")
	public String registEmployee(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		int maxMemberNo = adminService.getMaxMemberNo();

		String sysdateTimeStemp = String.valueOf(today());
		String sysdate = sysdateTimeStemp.substring(0, 10);

		String frontId = sysdate.replaceAll("-", "").substring(2);

		int id = Integer.valueOf(frontId + maxMemberNo);

		/* 반려인원 조회 */
		Page<EmployeeAndAllDTO> startPage = adminService.selectRetireYEmployee(page);
		List<EmployeeAndAllDTO> startList = startPage.toList();
//		List<EmployeeAndAllDTO> selectReturnEmployeeList = adminService.selectReturnEmployeeList();

		/* HR 대기중인 인원 조회 */
		List<EmployeeAndAllDTO> selectWaitingEmployeeList = adminService.selectWaitingEmployeeList();

		/* BOSS 대기중인 인원 조회 */
		List<EmployeeAndAllDTO> selectWaitingEmployeeListBoss = adminService.selectWaitingEmployeeListBoss();
		/* 직원들 중 1번째 승인과 최종승인이 N인 직원 */

		mv.addAttribute("pages", startPage );
		mv.addAttribute("maxPage", 5);
		mv.addAttribute("employeeAllList", startList);

		mv.addAttribute("waitingEmployeeList", selectWaitingEmployeeList); // HR authority
		mv.addAttribute("waitingEmployeeListBoss", selectWaitingEmployeeListBoss); // BOSS authority
		mv.addAttribute("returnEmployeeList", startPage);

		mv.addAttribute("employeeNo", maxMemberNo + 1);
		mv.addAttribute("employeeHireDate", sysdate);
		mv.addAttribute("employeeId", id + 1);

		return "admin/humanResource/registEmployee/registMainEmployee";
	}
	

	

	/* 직원등록 */
	@PostMapping("/hr/regist/employee")
	public String registEmployee(EmployeeAndAllDTO employeeDTO, @RequestParam("picture") MultipartFile singleFile,
			HttpServletRequest request, Model mv) {

		/* 비크립트 */
		/* 비크립트 */
		/* 비크립트 */
		/* 비크립트 */
		employeeDTO.setEmployeePwd("0000");
		/* 비크립트 */
		/* 비크립트 */
		/* 비크립트 */
		/* 비크립트 */
		/* 비크립트 */
		registEmployeePicture(employeeDTO, singleFile, request, mv);
		adminService.registEmployee(employeeDTO);

		return "redirect:/main";

	}

	/* 승인 대기인원 상세 조회 */
	@GetMapping("/select/waitingDetail/{empNo}")
	public String waitingDetail(@PathVariable int empNo, Model mv) {
		List<ReasonDTO> getRegistDate = adminService.getRegistDate(empNo);
		List<AdminDTO> getAdminName = adminService.getAdminName(empNo);

		EmployeeAndAllDTO waitingEmployee = adminService.waitingEmployee(empNo);

		String[] seperateAddress = waitingEmployee.getEmployeeAddress().split("@");
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];

		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);
		mv.addAttribute("address", addressMap);

		if (getRegistDate.size() == 0) {
			mv.addAttribute("hrConfirm", "");
			mv.addAttribute("bossCrconfirm", "");
		} else if (getRegistDate.size() == 1) {
			mv.addAttribute("hrConfirm", getRegistDate.get(0).getEmployeeRegistDate());
			mv.addAttribute("bossCrconfirm", "");

		} else if (getRegistDate.size() == 2) {
			mv.addAttribute("hrConfirm", getRegistDate.get(0).getEmployeeRegistDate());
			mv.addAttribute("bossCrconfirm", getRegistDate.get(1).getEmployeeRegistDate());
		}

		if (getAdminName.size() == 0) {

		}
		if (getAdminName.size() == 0) {
			mv.addAttribute("hrName", "");
			mv.addAttribute("bossName", "");
		} else if (getAdminName.size() == 1) {
			mv.addAttribute("hrName", getAdminName.get(0).getAdminName());
			mv.addAttribute("bossReturnReason", "");

		} else if (getAdminName.size() == 2) {
			mv.addAttribute("hrName", getAdminName.get(0).getAdminName());
			mv.addAttribute("bossName", getAdminName.get(1).getAdminName());
		}

		mv.addAttribute("waitingEmployee", waitingEmployee);
		return "admin/humanResource/registEmployee/waitingEmployee";
	}

	/* 반려인원 상세 조회 */
	@GetMapping("/select/returnDetail/{empNo}")
	public String returnDetail(@PathVariable int empNo, Model mv) {
		List<ReasonDTO> reason = adminService.getRegistDate(empNo);
		List<AdminDTO> getAdminName = adminService.getAdminName(empNo);

		EmployeeAndAllDTO returnEmployee = adminService.returnEmployee(empNo);

		String[] seperateAddress = returnEmployee.getEmployeeAddress().split("@");
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];

		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);
		mv.addAttribute("address", addressMap);

		if (reason.size() == 0) {
			mv.addAttribute("hrConfirm", "");
			mv.addAttribute("hrReason", "");
			mv.addAttribute("bossConfirm", "");
			mv.addAttribute("bossReason", "");
		} else if (reason.size() == 1) {
			mv.addAttribute("hrConfirm", reason.get(0).getEmployeeRegistDate());
			mv.addAttribute("hrReason", reason.get(0).getReason());

		} else if (reason.size() == 2) {
			mv.addAttribute("hrConfirm", reason.get(0).getEmployeeRegistDate());
			mv.addAttribute("hrReason", reason.get(0).getReason());
			mv.addAttribute("bossConfirm", reason.get(1).getEmployeeRegistDate());
			mv.addAttribute("bossReason", reason.get(1).getReason());
		}

		if (getAdminName.size() == 0) {
			mv.addAttribute("hrName", "");
			mv.addAttribute("bossName", "");
		} else if (getAdminName.size() == 1) {
			mv.addAttribute("hrName", getAdminName.get(0).getAdminName());
		} else if (getAdminName.size() == 2) {
			mv.addAttribute("hrName", getAdminName.get(0).getAdminName());
			mv.addAttribute("bossName", getAdminName.get(1).getAdminName());
		}

		mv.addAttribute("returnEmployee", returnEmployee);
		return "admin/humanResource/registEmployee/returnEmployee";
	}

	/* 인사관리자 1차 승인 */
	@PostMapping("/hr/confirm/restCommit")
	public String insertRestCommitConfirm(ReasonDTO restCommitDTO, AdminDTO adminDTO) {

		restCommitDTO.setAdminDTO(adminDTO);

		adminService.insertRestCommitConfirm(restCommitDTO);

		return "redirect:/admin/hr/regist/employee";

	}

	/* 인사관리자 1차 반려 */
	@PostMapping("/hr/return/restCommit")
	public String insertRestCommitReturn(ReasonDTO restCommitDTO) {
		adminService.insertRestCommitReturn(restCommitDTO);
		return "redirect:/admin/hr/regist/employee";
	}

	/* 사장 직원 2차승인 */
	@PostMapping("/boss/confirm/restCommit")
	public String insertAndUpdateRestCommitConfirmBoss(ReasonDTO restCommitDTO) {
		adminService.insertAndupdateRestCommitConfirmBoss(restCommitDTO);

		return "redirect:/admin/hr/regist/employee";

	}

	/* 사장 직원 2차반려 */
	@PostMapping("/boss/return/restCommit")
	public String insertAndupdateRestReturnReturnBoss(ReasonDTO restCommitDTO) {
		adminService.insertAndupdateRestCommitReturnBoss(restCommitDTO);
		return "redirect:/admin/hr/regist/employee";
	}

	/* 블랙리스트 조회 */
	@GetMapping("/select/blackList")
	public String selectBlackList(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		Page<EmployeeAndAllDTO> startPage = adminService.selectBlackList(page);
		List<EmployeeAndAllDTO> startList = startPage.toList();
		
		/* .getnumber 메서드를 위해 list로 변환 x */
		mv.addAttribute("pages", startPage );
		mv.addAttribute("maxPage", 5);
		mv.addAttribute("mainTain", "N");
		mv.addAttribute("blackList", startList);
		
		return "admin/humanResource/blackList/selectBlackList";
	}

	/* 블랙리스트 상세 조회 */
	@GetMapping("/selectBlackListDetail/{empNo}")
	public String selectBlackListDetail(@PathVariable int empNo, Model mv) {
		
		EmployeeAndAllDTO employeeDTO = adminService.selectOneEmployee(empNo);

		String[] seperatephone = employeeDTO.getEmployeePhone().split("-");

		String firstPhoneNumber = seperatephone[0];
		String middlePhoneNumber = seperatephone[1];
		String lastPhoneNumber = seperatephone[2];

		Map<String, String> phone = new HashMap<>();
		phone.put("firstPhoneNumber", firstPhoneNumber);
		phone.put("middlePhoneNumber", middlePhoneNumber);
		phone.put("lastPhoneNumber", lastPhoneNumber);

		String[] seperateAddress = employeeDTO.getEmployeeAddress().split("@");
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];

		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);

		String[] seperateEmail = employeeDTO.getEmployeeEmail().split("@");
		String email = seperateEmail[0];
		String domain = seperateEmail[1];

		Map<String, String> emailMap = new HashMap<>();
		emailMap.put("email", email);
		emailMap.put("domain", domain);

		employeeDTO.setEmployeePhone(firstPhoneNumber + "-" + middlePhoneNumber + "-" + lastPhoneNumber);

		mv.addAttribute("phone", phone);
		mv.addAttribute("address", addressMap);
		mv.addAttribute("email", emailMap);
		mv.addAttribute("employee", employeeDTO);

		return "admin/humanResource/blackList/selectBlackListDetail";
	}


	/* 휴가 전체 조회 */
	@GetMapping("/hr/select/Allvacation")
	public String selectAllVacation() {
		return "admin/humanResource/vacation/selectAllVacation";

	}
	

}
