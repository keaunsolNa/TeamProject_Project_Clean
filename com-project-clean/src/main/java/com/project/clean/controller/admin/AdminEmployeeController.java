package com.project.clean.controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
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

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.PageDTO;
import com.project.clean.model.dto.commonDTO.ReasonDTO;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.service.admin.AdminEmployeeService;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

	private final AdminEmployeeService adminService;
	private final PageDTO pageDTO;
	private final int maxPage = 5;

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

	
	
	
	
	
	
	
	
	/* 직원 전체 조회(재직자 main page) & 페이징 */
	@GetMapping("/select/AllEmployee/move")
	public String selectAllEmployeeMove() {
		return "/admin/humanResource/selectAllEmployee/selectAllEmployee";
	}
	
	/* 직원 전체 조회(재직자 main) */
	@GetMapping("/select/retireN")
	@ResponseBody
		   public Map<String, Object> selectRetireNEmployee(String category, String categoryValue, @PageableDefault(sort="employeeNo", size = 3) Pageable pageable) {
		      
		      Map<String,Object> map = new HashMap<>();
		      map = adminService.selectRetireNEmployee(category, categoryValue, pageable);
		      
		      return map;
		   }
	
	/* 직원 전체 조회(퇴사자 main) */
	@GetMapping("/select/retireY")
	@ResponseBody
	public Map<String, Object> selectRetireYEmployee(String category, String categoryValue, @PageableDefault(sort="employeeNo", size = 5) Pageable pageable) {
		
		Map<String,Object> map = new HashMap<>();
		map = adminService.selectRetireYEmployee(category, categoryValue, pageable);
		
		return map;
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
		return "redirect:/admin/select/AllEmployee/move";
	}

	/* 직원 등록 페이지 이동 */
	@GetMapping("/hr/regist/EmployeePage")
	public String registEmployeeList(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		int maxMemberNo = adminService.getMaxMemberNo();

		String sysdateTimeStemp = String.valueOf(today());
		String sysdate = sysdateTimeStemp.substring(0, 10);

		String frontId = sysdate.replaceAll("-", "").substring(2);

		int id = Integer.valueOf(frontId + maxMemberNo);

		mv.addAttribute("employeeNo", maxMemberNo + 1);
		mv.addAttribute("employeeHireDate", sysdate);
		mv.addAttribute("employeeId", id + 1);
		mv.addAttribute("mainTain", "N");

		return "admin/humanResource/registEmployee/registEmployeeList";
	}

	@GetMapping("/hr/waiting/employeeHR/move")
	public String waitingEmployeeHrMove() {
		return "/admin/humanResource/registEmployee/waitingEmployeeListHR";
	}
	
	/* 대기중 직원 페이지 이동(HR) */
	@GetMapping("/hr/waiting/employeeHR")
	@ResponseBody
	public Map<String, Object> waitingEmployeeListHr(String category, String categoryValue, @PageableDefault(sort="employeeNo", size = 3) Pageable pageable) {
		
	      Map<String,Object> map = new HashMap<>();
	      map = adminService.selectWaitingEmployeeListHr(category, categoryValue, pageable);
	      return map;
	}
	
	@GetMapping("/hr/waiting/employeeBoss/move")
	public String waitingEmployeeBossMove() {
		return "/admin/humanResource/registEmployee/waitingEmployeeListBoss";
	}
	
	/* 대기중 직원 페이지 이동(Boss) */
	@GetMapping("/hr/waiting/employeeBoss")
	@ResponseBody
	public Map<String, Object> waitingEmployeeListBoss(String category, String categoryValue, @PageableDefault(sort="employeeNo", size = 3) Pageable pageable) {
		
		Map<String,Object> map = new HashMap<>();
		map = adminService.selectWaitingEmployeeListBoss(category, categoryValue, pageable);
		
		return map;
	}

	
//	/* 직원 대기중 페이지 이동(HR) */
//	@GetMapping("/hr/waiting/employeeBoss")
//	public String waitingEmployeeListBoss(Model mv) {
//		int maxMemberNo = adminService.getMaxMemberNo();
//		
//		String sysdateTimeStemp = String.valueOf(today());
//		String sysdate = sysdateTimeStemp.substring(0, 10);
//		
//		String frontId = sysdate.replaceAll("-", "").substring(2);
//		
//		int id = Integer.valueOf(frontId + maxMemberNo);
//		
//		/* HR 대기중인 인원 조회 */
//		List<EmployeeAndAllDTO> selectWaitingEmployeeList = adminService.selectWaitingEmployeeList();
//		
//		/* BOSS 대기중인 인원 조회 */
//		List<EmployeeAndAllDTO> selectWaitingEmployeeListBoss = adminService.selectWaitingEmployeeListBoss();
//		/* 직원들 중 1번째 승인과 최종승인이 N인 직원 */
//		
//		mv.addAttribute("waitingEmployeeList", selectWaitingEmployeeList); // HR authority
//		mv.addAttribute("waitingEmployeeListBoss", selectWaitingEmployeeListBoss); // BOSS authority
//		
//		mv.addAttribute("employeeNo", maxMemberNo + 1);
//		mv.addAttribute("employeeHireDate", sysdate);
//		mv.addAttribute("employeeId", id + 1);
//		
//		return "admin/humanResource/registEmployee/waitingEmployeeListBoss";
//	}

	
	/* 반려 직원 페이지 이동 */
	@GetMapping("/hr/return/employee/move")
	public String returnEmployeeListMove() {
		return "/admin/humanResource/registEmployee/returnEmployeeList";
	}
	
	/* 반려 직원 리스트 조회*/
	@GetMapping("/hr/return/employee")
	@ResponseBody
	public Map<String, Object> returnEmployeeList(String category, String categoryValue, @PageableDefault(sort="employeeNo", size = 3) Pageable pageable) {
		
		Map<String,Object> map = new HashMap<>();
		map = adminService.selectReturnYEmployee(category, categoryValue, pageable);
		
		return map;
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
		LocalDate now = LocalDate.now();
		java.sql.Date today = java.sql.Date.valueOf(now);

		restCommitDTO.setEmployeeRegistDate(today);
		restCommitDTO.setAdminDTO(adminDTO);

		adminService.insertRestCommitConfirm(restCommitDTO);

		return "redirect:/admin/hr/regist/EmployeePage";

	}

	/* 인사관리자 1차 반려 */
	@PostMapping("/hr/return/restCommit")
	public String insertRestCommitReturn(ReasonDTO restCommitDTO) {
		adminService.insertRestCommitReturn(restCommitDTO);
		return "redirect:/admin/hr/regist/EmployeePage";
	}

	/* 사장 직원 2차승인 */
	@PostMapping("/boss/confirm/restCommit")
	public String insertAndUpdateRestCommitConfirmBoss(ReasonDTO restCommitDTO) {
		adminService.insertAndupdateRestCommitConfirmBoss(restCommitDTO);

		return "redirect:/admin/hr/regist/EmployeePage";

	}

	/* 사장 직원 2차반려 */
	@PostMapping("/boss/return/restCommit")
	public String insertAndupdateRestReturnReturnBoss(ReasonDTO restCommitDTO) {
		adminService.insertAndupdateRestCommitReturnBoss(restCommitDTO);
		return "redirect:/admin/hr/regist/EmployeePage";
	}

	/* 블랙리스트 조회 */
	@GetMapping("/select/blackList")
	public String selectBlackList(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		Page<EmployeeAndAllDTO> startPage = adminService.selectBlackList(page);
		List<EmployeeAndAllDTO> startList = startPage.toList();

		/* .getnumber 메서드를 위해 list로 변환 x */
		mv.addAttribute("pages", startPage);
		mv.addAttribute("maxPage", maxPage);
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

	/* 나의 휴가 조회 */
	@GetMapping("/select/selectMyVacation")
	public String selectMyVacaionList(@RequestParam(value = "page", defaultValue = "0") int page, String adminNo,
			Model mv) {
		int adminNoVal = Integer.valueOf(adminNo);
		Page<VacationDTO> startPage = adminService.selectMyVacaionList(page, adminNoVal);
		List<VacationDTO> myVacationList = startPage.toList();
		
		mv.addAttribute("pages", startPage);
		mv.addAttribute("maxPage", maxPage);
		mv.addAttribute("mainTain", "N");
		mv.addAttribute("vacation", myVacationList);
		return "admin/humanResource/vacation/selectMyVacation";
	}

	/* 휴가 상세 조회 */
	@GetMapping("/select/selectMyVacation/Detail/{vacationNo}")
	public String selectMyVacaionDetail(@PathVariable int vacationNo, Model mv) {
		/* 휴가와 관련된 정보 */
		VacationDTO selectVacation = adminService.selectMyVacaionList(vacationNo);
		/*
		 * 휴가신청한 사람의 adminDTO(admin Table) 휴가 승인한 사람의 name(admin Table)이 필요하다. 휴가신청한 사람의
		 * 정보를 얻기 위해선 vacation Table에서 관리자 번호를 알아낸 후 그 관리자 번호에 해당하는 데이터를 admin Table로부터
		 * 가져와야 한다. 휴가 승한 사람의 name을 알기 위해선 vacationCommit 에서 관리자 번호를 알아낸 후 그 관리자 번호에
		 * 해당하는 데이터를 admin Table로부터 가져와야 한다. DB를 2번 다녀오느니 persistance context도 있겠다 한번에
		 * 가져와서 코드가 좀 길어지더라도 그렇게 처리하는 게 맞다 생각하여 다 가져오기로 하였다.
		 */

		List<AdminDTO> adminList = adminService.selectAdmin();

		AdminDTO adminDTO = new AdminDTO();
		for (int i = 0; i < adminList.size(); i++) {
			adminDTO = adminList.get(i);
			/* 휴가 사용한 사람의 정보 */
			if (adminDTO.getAdminNo() == selectVacation.getAdminNo()) {
				break;
			}
		}

		if (selectVacation.getVacationCommitList().size() == 0) {
			mv.addAttribute("hrConfirm", "");
			mv.addAttribute("hrReason", "");
			mv.addAttribute("bossConfirm", "");
			mv.addAttribute("bossReason", "");
		} else if (selectVacation.getVacationCommitList().size() == 1) {
			mv.addAttribute("hrConfirm", selectVacation.getVacationCommitList().get(0).getConfirmDate());
			mv.addAttribute("hrReason", selectVacation.getVacationCommitList().get(0).getConfirmReason());

		} else if (selectVacation.getVacationCommitList().size() == 2) {
			mv.addAttribute("hrConfirm", selectVacation.getVacationCommitList().get(1).getConfirmDate());
			mv.addAttribute("hrReason", selectVacation.getVacationCommitList().get(1).getConfirmReason());
			mv.addAttribute("bossConfirm", selectVacation.getVacationCommitList().get(0).getConfirmDate());
			mv.addAttribute("bossReason", selectVacation.getVacationCommitList().get(0).getConfirmReason());
		}

		String firstName = "";
		String secondName = "";
		if (selectVacation.getVacationCommitList().size() == 0) {

			mv.addAttribute("hrName", "");
			mv.addAttribute("bossName", "");
		} else if (selectVacation.getVacationCommitList().size() == 1) {
			for (int i = 0; i < adminList.size(); i++) {
				if (adminList.get(i).getAdminNo() == selectVacation.getVacationCommitList().get(0).getAdminNo()) {
					firstName = adminList.get(i).getAdminName();
				}
			}
			mv.addAttribute("hrName", firstName);
		} else if (selectVacation.getVacationCommitList().size() == 2) {
			for (int i = 0; i < adminList.size(); i++) {
				if (adminList.get(i).getAdminNo() == selectVacation.getVacationCommitList().get(1).getAdminNo()) {
					firstName = adminList.get(i).getAdminName();
				}
				if (adminList.get(i).getAdminNo() == selectVacation.getVacationCommitList().get(0).getAdminNo()) {
					secondName = adminList.get(i).getAdminName();
				}
			}
			mv.addAttribute("hrName", firstName);
			mv.addAttribute("bossName", secondName);
		}

		mv.addAttribute("vacation", selectVacation);
		mv.addAttribute("admin", adminDTO);
		return "admin/humanResource/vacation/selectMyVacationDetail";
	}

	/* 휴가 전체 조회 */
	@GetMapping("/hr/select/selectAllVacation")
	public String selectAllVacationList(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {

		Page<VacationDTO> vacationAllPage = adminService.selectVacationAllPage(page);
		List<VacationDTO> vacationAllList = vacationAllPage.toList();
		List<AdminDTO> adminList = adminService.selectAdmin();

		mv.addAttribute("pages", vacationAllPage);
		mv.addAttribute("maxPage", maxPage);
		mv.addAttribute("mainTain", "N");
		mv.addAttribute("vacation", vacationAllList);
		return "/admin/humanResource/vacation/selectAllVacation";

	}

	/* 휴가 승인 전체 조회 */
	@GetMapping("/hr/select/selectAllVacationConfirmList")
	public String selectAllVacationConfirmList(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		Page<VacationDTO> vacationAllPage = adminService.selectVacationConfirmPage(page);
		List<VacationDTO> vacationAllList = vacationAllPage.toList();
		List<AdminDTO> adminList = adminService.selectAdmin();

		mv.addAttribute("pages", vacationAllPage);
		mv.addAttribute("maxPage", maxPage);
		mv.addAttribute("mainTain", "N");
		mv.addAttribute("vacation", vacationAllList);
		return "/admin/humanResource/vacation/selectAllVacationConfirmList";

	}

	/* 휴가 반려 전체 조회 */
	@GetMapping("/hr/select/selectAllVacationReturnList")
	public String selectAllVacationReturnList(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {		
		Page<VacationDTO> vacationAllPage = adminService.selectVacationReturnPage(page);
	List<VacationDTO> vacationAllList = vacationAllPage.toList();
	List<AdminDTO> adminList = adminService.selectAdmin();

	mv.addAttribute("pages", vacationAllPage);
	mv.addAttribute("maxPage", maxPage);
	mv.addAttribute("mainTain", "N");
	mv.addAttribute("vacation", vacationAllList);
		return "admin/humanResource/vacation/selectAllVacationReturnList";

	}
	
	
	
	
	
	
	
	
	
	
	

}
