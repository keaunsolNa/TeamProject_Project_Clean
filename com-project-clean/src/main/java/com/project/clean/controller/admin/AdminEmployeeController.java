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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.project.clean.model.dto.commonDTO.RetireEmployeeDTO;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.service.admin.AdminEmployeeService;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {

	private final AdminEmployeeService adminService;
	private final PageDTO pageDTO;
	private BCryptPasswordEncoder passwordEncoder;
	private final int maxLine = 5;

	/* 생성자를 통한 DI */
	@Autowired
	public AdminEmployeeController(AdminEmployeeService adminService, PageDTO pageDTO,
			BCryptPasswordEncoder passwordEncoder) {
		this.adminService = adminService;
		this.pageDTO = pageDTO;
		this.passwordEncoder = passwordEncoder;
	}
	/* sysdate를 대체할 today method */
	public Date today() {
		LocalDate now = LocalDate.now();
		java.sql.Date today = java.sql.Date.valueOf(now);
		return today;

	}
	/* 직원 등록 시 사진 업로드에 필요한 method */
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

		/*파일을 전달받아 파일명 변경 처리 */
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
	public Map<String, Object> selectRetireNEmployee(String categoryN, String categoryValue,
			@PageableDefault(sort = "employeeNo", size = maxLine) Pageable pageable) {

		Map<String, Object> map = new HashMap<>();
		map = adminService.selectRetireNEmployee(categoryN, categoryValue, pageable);

		return map;
	}

	/* 직원 전체 조회(퇴사자 main) */
	@GetMapping("/select/retireY")
	@ResponseBody
	public Map<String, Object> selectRetireYEmployee(String categoryY, String categoryValue,
			@PageableDefault(sort = "employeeNo", size = maxLine) Pageable pageable) {

		Map<String, Object> map = new HashMap<>();
		map = adminService.selectRetireYEmployee(categoryY, categoryValue, pageable);

		return map;
	}


	
	
	
	
	
	
	
	
	@GetMapping("/reitre/employee")
	public String retireEmployee(RetireEmployeeDTO retireEmployeeDTO) {
		adminService.retireEmployee(retireEmployeeDTO);

		return "redirect:/admin/select/AllEmployee/move";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	/* (관리자가)직원 수정 페이지로 이동 */
	@GetMapping("/modify/employee/{empNo}")
	public String adminModifyEmployee(@PathVariable int empNo, Model mv) {
		
		try {
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
		
		mv.addAttribute("fullPhone", firstPhoneNumber+middlePhoneNumber+lastPhoneNumber);
		mv.addAttribute("phone", phone);
		mv.addAttribute("address", addressMap);
		mv.addAttribute("email", emailMap);
		mv.addAttribute("employee", employeeDTO);
		return "/admin/humanResource/selectAllEmployee/modifyEmployee";
		
		} catch(IndexOutOfBoundsException e) {
			return "admin/error.html";
		}
	}

	/* (관리자가)직원 정보 수정 */
	@PostMapping("/modify/employee")
	public String modifyEmployee(EmployeeAndAllDTO employeeDTO, String oldSaveRoot, String oldSaveName, Model mv,
			@RequestParam("picture") MultipartFile singleFile, HttpServletRequest request, String status) {

		String root = request.getSession().getServletContext().getRealPath("/");
		String filePath = root + "adminEmployeePicture";

		if ("modify".equals(status)) {
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
			} else {

			}
			adminService.modifyEmployee(employeeDTO);


			mv.addAttribute("employeeDTO", employeeDTO);

		} else if ("delete".equals(status)) {

			/* 1. 사용자가 delete 누르면 DB가서 해당 직원 조회하고 */
			EmployeeAndAllDTO empDTO = adminService.deletePicture(employeeDTO.getEmployeeNo());

			/* 4. 해당 파일 삭제 */
			new File(filePath + "/" + empDTO.getEmployeePictureSaveName()).delete();

		}

		return "redirect:/admin/select/AllEmployee/move";
	}

	/* 직원 등록 페이지 이동 */
	@GetMapping("/hr/regist/employeePage")
	public String registEmployeeList(@RequestParam(value = "page", defaultValue = "0") int page, Model mv) {
		int maxMemberNo = adminService.getMaxMemberNo();

		String sysdateTimeStemp = String.valueOf(today());
		String sysdate = sysdateTimeStemp.substring(0, 10);

		String frontId = sysdate.replaceAll("-", "").substring(2);

		int id = Integer.valueOf(frontId);

		mv.addAttribute("employeeNo", maxMemberNo + 1);
		mv.addAttribute("employeeHireDate", sysdate);
		mv.addAttribute("employeeId", id);
		mv.addAttribute("mainTain", "N");

		return "admin/humanResource/registEmployee/registEmployeeList";
	}

	@GetMapping("/hr/regist/employee/findMiddlePhone")
	@ResponseBody
	public List<String> findMiddlePhoneNum(Model mv) {

		List<EmployeeAndAllDTO> employeeList = adminService.findMiddlePhoneNum();
		List<String> middlePhoneList = new ArrayList<>();
		
		for (int i = 0; i < employeeList.size(); i++) {
			String middlePhoneChange = employeeList.get(i).getEmployeePhone();

			if (employeeList.get(i).getEmployeePhone().length() == 12) {
				String middlePhoneNum = middlePhoneChange.replaceAll("-", "");
				middlePhoneList.add(middlePhoneNum);

			} else if (employeeList.get(i).getEmployeePhone().length() == 13) {
				String middlePhoneNum = middlePhoneChange.replaceAll("-", "");
				middlePhoneList.add(middlePhoneNum);
			}
		}
		return middlePhoneList;
	}

	@GetMapping("/hr/waiting/employeeHR/move")
	public String waitingEmployeeHrMove() {
		return "/admin/humanResource/registEmployee/waitingEmployeeListHR";
	}

	/* 대기중 직원 페이지 이동(HR) */
	@GetMapping("/hr/waiting/employeeHR")
	@ResponseBody
	public Map<String, Object> waitingEmployeeListHr(String category, String categoryValue,
			@PageableDefault(sort = "employeeNo", size = maxLine) Pageable pageable) {

		Map<String, Object> map = new HashMap<>();
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
	public Map<String, Object> waitingEmployeeListBoss(String category, String categoryValue,
			@PageableDefault(sort = "employeeNo", size = maxLine) Pageable pageable) {

		Map<String, Object> map = new HashMap<>();
		map = adminService.selectWaitingEmployeeListBoss(category, categoryValue, pageable);

		return map;
	}


	/* 반려 직원 페이지 이동 */
	@GetMapping("/hr/return/employee/move")
	public String returnEmployeeListMove() {
		return "/admin/humanResource/registEmployee/returnEmployeeList";
	}

	/* 반려 직원 리스트 조회 */
	@GetMapping("/hr/return/employee")
	@ResponseBody
	public Map<String, Object> returnEmployeeList(String category, String categoryValue,
			@PageableDefault(sort = "employeeNo", size = maxLine) Pageable pageable) {

		Map<String, Object> map = new HashMap<>();
		map = adminService.selectReturnYEmployee(category, categoryValue, pageable);

		return map;
	}

	/* 직원등록 */

	@PostMapping("/hr/regist/employee")
	public String registEmployee(EmployeeAndAllDTO employeeDTO, @RequestParam("picture") MultipartFile singleFile,
			HttpServletRequest request, Model mv) {
		
		employeeDTO.setEmployeePwd(passwordEncoder.encode("0000"));

		registEmployeePicture(employeeDTO, singleFile, request, mv);
		adminService.registEmployee(employeeDTO);

		return "redirect:/admin/hr/regist/employeePage";

	}

	/* 승인 대기인원 상세 조회 */
	@GetMapping("/select/waitingDetail/{empNo}")
	public String waitingDetail(@PathVariable int empNo, Model mv) {
		
		try {
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
			mv.addAttribute("bossconfirm", "");
		} else if (getRegistDate.size() == 1) {
			mv.addAttribute("hrConfirm", getRegistDate.get(0).getEmployeeRegistDate());
			mv.addAttribute("bossConfirm", "");

		} else if (getRegistDate.size() == 2) {
			mv.addAttribute("hrConfirm", getRegistDate.get(0).getEmployeeRegistDate());
			mv.addAttribute("bossConfirm", getRegistDate.get(1).getEmployeeRegistDate());
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
		} catch (Exception e) {
			return "admin/error.html";
		}
	}

	/* 반려인원 상세 조회 */
	@GetMapping("/select/returnDetail/{empNo}")
	public String returnDetail(@PathVariable int empNo, Model mv) {
		try {
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
		} catch (Exception e) {
			return "admin/error.html";
		}
	}

	/* 인사관리자 1차 승인 */
	@PostMapping("/hr/confirm/restCommit")
	public String insertRestCommitConfirm(ReasonDTO restCommitDTO, AdminDTO adminDTO) {
		restCommitDTO.setAdminDTO(adminDTO);
		adminService.insertRestCommitConfirm(restCommitDTO);

		return "redirect:/admin/hr/regist/employeePage";

	}

	/* 인사관리자 1차 반려 */
	@PostMapping("/hr/return/restCommit")
	public String insertRestCommitReturn(ReasonDTO restCommitDTO) {
		adminService.insertRestCommitReturn(restCommitDTO);
		return "redirect:/admin/hr/regist/employeePage";
	}

	/* 사장 직원 2차승인 */
	@PostMapping("/boss/confirm/restCommit")
	public String insertAndUpdateRestCommitConfirmBoss(ReasonDTO restCommitDTO) {
		adminService.insertAndupdateRestCommitConfirmBoss(restCommitDTO);

		return "redirect:/admin/hr/regist/employeePage";

	}

	/* 사장 직원 2차반려 */
	@PostMapping("/boss/return/restCommit")
	public String insertAndupdateRestReturnReturnBoss(ReasonDTO restCommitDTO) {
		adminService.insertAndupdateRestCommitReturnBoss(restCommitDTO);
		return "redirect:/admin/hr/regist/employeePage";
	}

	/* 블랙리스트 조회페이지 이동 */
	@GetMapping("/select/blackList/move")
	public String selectBlackListMove() {
		return "/admin/humanResource/blackList/selectBlackList";
	}

	/* 블랙리스트 조회 */
	@GetMapping("/select/blackList")
	@ResponseBody
	public Map<String, Object> selectBlackList(String category, String categoryValue,
			@PageableDefault(sort = "employeeNo", size = maxLine) Pageable pageable) {

		Map<String, Object> map = new HashMap<>();
		map = adminService.selectBlackList(category, categoryValue, pageable);

		return map;

	}

	/* 블랙리스트 상세 조회 */
	@GetMapping("/selectBlackListDetail/{empNo}")
	public String selectBlackListDetail(@PathVariable int empNo, Model mv) {
		try {
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
		} catch (Exception e) {
			return "admin/error.html";
		}
	}

	@GetMapping("/test")
	public String test() {
		return "/admin/humanResource/registEmployee/test.html";
	}

	/* 나의 휴가조회 페이지 이동 */
	@GetMapping("/select/selectMyVacation/move")
	public String selectMyvacationListMove() {
		return "/admin/humanResource/vacation/selectMyVacation";
	}

	/* 나의 휴가 조회 */
	@GetMapping("/select/selectMyVacation")
	@ResponseBody
	public Map<String, Object> selectMyVacaionList(int adminNo, Date startDate, Date endDate, String category,
			String categoryValue,
			@PageableDefault(direction = Direction.DESC, sort = "vacationNo", size = maxLine) Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map = adminService.selectMyVacaionList(adminNo, pageable);

		return map;

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
			mv.addAttribute("hrConfirm", selectVacation.getVacationCommitList().get(0).getConfirmDate());
			mv.addAttribute("hrReason", selectVacation.getVacationCommitList().get(0).getConfirmReason());
			mv.addAttribute("bossConfirm", selectVacation.getVacationCommitList().get(1).getConfirmDate());
			mv.addAttribute("bossReason", selectVacation.getVacationCommitList().get(1).getConfirmReason());
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

	/* 휴가 전체 조회 리셋 */
	@GetMapping("/vacation/main")
	@ResponseBody
	public Map<String, Object> selectAllVacationReset(String category, String categoryValue, String startDate,
			String endDate, @PageableDefault(size = maxLine) Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map = adminService.selectAllVacaionList(category, categoryValue, startDate, endDate, pageable);

		return map;

	}

	/* 휴가 전체 조회 페이지 이동 */
	@GetMapping("/hr/humanResource/selectAllVacation/move")
	public String selectAllVacationListMove() {
		return "/admin/humanResource/vacation/selectAllVacation";
	}

	/* 휴가 전체 조회 */
	@GetMapping("/hr/select/selectAllVacation")
	@ResponseBody
	public Map<String, Object> selectAllVacationList(String category, String categoryValue, String startDate,
			String endDate, @PageableDefault(direction = Direction.DESC, sort = "vacationNo", size = maxLine) Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map = adminService.selectAllVacaionList(category, categoryValue, startDate, endDate, pageable);

		return map;

	}

	/* 휴가 승인 조회 페이지 이동 */
	@GetMapping("/hr/select/selectAllVacationConfirmList/move")
	public String selectAllVacationConfirmListMove() {
		return "/admin/humanResource/vacation/selectAllVacationConfirmList";
	}

	/* 휴가 승인 조회 */
	@GetMapping("/hr/select/selectAllVacationConfirmList")
	@ResponseBody
	public Map<String, Object> selectAllVacationConfirmList(String category, String categoryValue, String startDate,
			String endDate, @PageableDefault(direction = Direction.DESC ,size = maxLine) Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map = adminService.selectAllVacationConfirmList(category, categoryValue, startDate, endDate, pageable);

		return map;

	}

	/* 휴가 반려 조회 페이지 이동 */
	@GetMapping("/hr/select/selectAllVacationReturnList/move")
	public String selectAllVacationReturnListMove() {
		return "/admin/humanResource/vacation/selectAllVacationReturnList";
	}

	/* 휴가 반려 조회 */
	@GetMapping("/hr/select/selectAllVacationReturnList")
	@ResponseBody
	public Map<String, Object> selectAllVacationReturnList(String category, String categoryValue, String startDate,
			String endDate, @PageableDefault(direction = Direction.DESC, sort = "vacationNo", size = maxLine) Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		map = adminService.selectAllVacaionReturnList(category, categoryValue, startDate, endDate, pageable);

		return map;
	}
	

}
