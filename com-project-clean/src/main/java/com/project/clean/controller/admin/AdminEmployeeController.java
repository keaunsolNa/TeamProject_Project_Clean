package com.project.clean.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ReasonDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.service.admin.AdminEmployeeService;

@Controller
@RequestMapping("/admin")
public class AdminEmployeeController {
	
	private final AdminEmployeeService adminService;
	
	@Autowired
	public AdminEmployeeController(AdminEmployeeService adminService) {
		this.adminService = adminService;
	}
	/* 직원 전체 조회(재직자 main page) */
	@GetMapping("/select/retireNEmployee")
	public String selectRetireNEmployee(Model mv) {
		
		List<EmployeeAndAllDTO> selectRetireNEmployeeList = adminService.selectRetireNEmployee();
		
		/* 직원들 중 퇴직여부가 Y인 직원 */
		List<EmployeeAndAllDTO> entireNEmployeeList = new ArrayList();
		if(!selectRetireNEmployeeList.isEmpty() && selectRetireNEmployeeList != null) {
			
			selectRetireNEmployeeList = new ArrayList<>();
	}
		
		mv.addAttribute("entireNEmployeeList", entireNEmployeeList);
		return "admin/humanResource/selectAllEmployee/selectAllEmployee";
	}
	
	/* ajax 직원 전체 조회 (재직자) */
	@GetMapping(value = "/select/retireNEmployeeAjax", produces = "application/json; charset=UTF-8;")
	@ResponseBody
	public List<EmployeeAndAllDTO> selectRetireNEmployeeAjax(Model mv) {
		List<EmployeeAndAllDTO> selectRetireNEmployeeList = adminService.selectRetireNEmployee();
//		List<EmployeeAddressDTO> selectRetireNEmployeeAddressList = adminService.selectRetireNEmployeeAddress();
		
		if(!selectRetireNEmployeeList.isEmpty() && selectRetireNEmployeeList != null) {
			selectRetireNEmployeeList = new ArrayList<>();
	}
		
		return selectRetireNEmployeeList;
	}
	
	/* ajax 직원 전체 조회 (퇴사자) */
	@GetMapping(value = "/select/retireYEmployee", produces = "application/json; charset=UTF-8;")
	@ResponseBody
	public List<EmployeeAndAllDTO> selectRetireYEmployee(Model mv) {
		List<EmployeeAndAllDTO> selectRetireYEmployeeList = adminService.selectRetireYEmployee();
		
		if(!selectRetireYEmployeeList.isEmpty() && selectRetireYEmployeeList != null){
			selectRetireYEmployeeList = new ArrayList<>();
	}
		
		return selectRetireYEmployeeList;
	}

	/* (관리자가)직원 수정 페이지로 이동 */
	@GetMapping("/modify/employee/{empNo}")
	public String adminModifyEmployee(@PathVariable int empNo, Model mv) {
		
		EmployeeAndAllDTO employeeDTO = adminService.selectOneEmployee(empNo);
		
		String middlePhoneNumber = "";
		String lastPhoneNumber = "";
		
		if(employeeDTO.getEmployeePhone().length() == 11) {
			middlePhoneNumber = employeeDTO.getEmployeePhone().substring(3,7);
			lastPhoneNumber = employeeDTO.getEmployeePhone().substring(7,11);
		}else if(employeeDTO.getEmployeePhone().length() == 10) {
			middlePhoneNumber = employeeDTO.getEmployeePhone().substring(3,6);
			lastPhoneNumber = employeeDTO.getEmployeePhone().substring(6,10);
		}
		
		Map<String, String> phoneNumber = new HashMap();
		phoneNumber.put("middlePhoneNumber", middlePhoneNumber);
		phoneNumber.put("lastPhoneNumber", lastPhoneNumber);
		
		mv.addAttribute("employee", employeeDTO);
//		mv.addAttribute("employeePicture", employeePictureDTO);
		mv.addAttribute("employeeNo", empNo);
		mv.addAttribute("phoneNumber", phoneNumber);
		
		return "/admin/humanResource/selectAllEmployee/modifyEmployee";
	}
	
	/* (관리자가)직원 정보 수정 */
	@PostMapping("/modify/employee")
	public String modifyEmployee(EmployeeAndAllDTO employeeDTO, String employeePhone1, String employeePhone2, String employeePhone3, Model mv) {
		
		String employeePhone = employeePhone1 + employeePhone2 + employeePhone3;
		
		/* 이게 있어야 하나? */
		EmployeeAndAllDTO employee = adminService.selectOneEmployee(employeeDTO.getEmployeeNo());
		
		employeeDTO.setEmployeePhone(employeePhone);
		employee.setEmployeeAddress(employeeDTO.getEmployeeAddress());
		employee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
		
//		adminService.modifyEmployee(employeeDTO);
		
		mv.addAttribute("employeeDTO", employeeDTO);
		return "redirect:/admin/select/retireNEmployee";
	}

	
	/* 직원 등록 페이지 이동*/
	@GetMapping("/hr/regist/employee")
	public String registEmployee(Model mv) {
		int maxMemberNo = adminService.getMaxMemberNo();
		String sysdateTimeStemp = adminService.getsysdate();
		String sysdate = sysdateTimeStemp.substring(0, 10);
		
		String frontId = sysdate.replaceAll("-", "").substring(2);
		
		int id = Integer.valueOf(frontId + maxMemberNo);
		
		/* HR 대기중인 인원 조회 */
		List<EmployeeAndAllDTO> selectWaitingEmployeeList = adminService.selectWaitingEmployeeList();
		
		/* BOSS 대기중인 인원 조회 */
		List<EmployeeAndAllDTO> selectWaitingEmployeeListBoss = adminService.selectWaitingEmployeeListBoss();
		/* 직원들 중 1번째 승인과 최종승인이 N인 직원 */
		
		/* 반려인원 조회 */
		List<EmployeeAndAllDTO> selectReturnEmployeeList = adminService.selectReturnEmployeeList();
		
		
		mv.addAttribute("waitingEmployeeList", selectWaitingEmployeeList);		//HR authority
		mv.addAttribute("waitingEmployeeListBoss", selectWaitingEmployeeListBoss);	//BOSS authority
		mv.addAttribute("returnEmployeeList", selectReturnEmployeeList);			
		
		mv.addAttribute("employeeNo", maxMemberNo + 1);
		mv.addAttribute("employeeHireDate", sysdate);
		mv.addAttribute("employeeId", id + 1);
		
		return "admin/humanResource/registEmployee/registMainEmployee";
	}
	
	/* 직원등록 */
	@PostMapping("/hr/regist/employee")
		public String registEmployee(EmployeeAndAllDTO employeeDTO, String employeePhone1, String employeePhone2, String employeePhone3) {
		
		String employeePhone = employeePhone1 + employeePhone2 + employeePhone3;
		employeeDTO.setEmployeePhone(employeePhone);
		
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
		  
		  employeeDTO.setEmployeeAddress(employeeDTO.getEmployeeAddress());
		  employeeDTO.setEmployeeEmail(employeeDTO.getEmployeeEmail());
		  adminService.registEmployee(employeeDTO);
		
		return "redirect:/main";
			
		}
	
	/* 승인 대기인원 상세 조회 */
	@GetMapping("/select/waitingDetail/{empNo}")
	public String waitingDetail(@PathVariable int empNo, Model mv) {
		List<ReasonDTO> getRegistDate = adminService.getRegistDate(empNo);
		List<AdminDTO> getAdminName = adminService.getAdminName(empNo);
		
		EmployeeAndAllDTO waitingEmployee = adminService.waitingEmployee(empNo);
		
		/* 핸드폰 번호 출력 시 - 추가 */
		String firstPhoneNumber = "";
		String middlePhoneNumber = "";
		String lastPhoneNumber = "";
		if(waitingEmployee.getEmployeePhone().length() == 11) {
			firstPhoneNumber = waitingEmployee.getEmployeePhone().substring(0,3);
			middlePhoneNumber = waitingEmployee.getEmployeePhone().substring(3,7);
			lastPhoneNumber = waitingEmployee.getEmployeePhone().substring(7,11);
		}else if(waitingEmployee.getEmployeePhone().length() == 10) {
			middlePhoneNumber = waitingEmployee.getEmployeePhone().substring(3,6);
			lastPhoneNumber = waitingEmployee.getEmployeePhone().substring(6,10);
		}
		waitingEmployee.setEmployeePhone(firstPhoneNumber+"-"+middlePhoneNumber+"-"+lastPhoneNumber);
		
		waitingEmployee.setEmployeeAddress(waitingEmployee.getEmployeeAddress());
		waitingEmployee.setEmployeeEmail(waitingEmployee.getEmployeeEmail());
		
//		해야댐
//		해야댐
//		해야댐
//		해야댐
//		waitingEmployee.setEmployeeRestCommitList(getRegistDate);
//		해야댐
//		해야댐
//		해야댐
//		해야댐

		if(getRegistDate.size() == 0) {
			mv.addAttribute("hrConfirm", "");
			mv.addAttribute( "bossCrconfirm", "");
		} else if(getRegistDate.size() == 1) {
			mv.addAttribute("hrConfirm", getRegistDate.get(0).getEmployeeRegistDate());
			mv.addAttribute("bossCrconfirm", "");
			
		} else if(getRegistDate.size() == 2) {
			mv.addAttribute("hrConfirm", getRegistDate.get(0).getEmployeeRegistDate());
			mv.addAttribute("bossCrconfirm", getRegistDate.get(1).getEmployeeRegistDate());
		}

		if(getAdminName.size() == 0) {
			
		}
		if(getAdminName.size() == 0) {
			mv.addAttribute("hrName", "");
			mv.addAttribute( "bossName", "");
		} else if(getAdminName.size() == 1) {
			mv.addAttribute("hrName", getAdminName.get(0).getAdminName());
			mv.addAttribute("bossReturnReason", "");
			
		} else if(getAdminName.size() == 2) {
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
//		List<ReasonDTO> employeeRestList = adminService.returnEmployeeRest(empNo);
		
		
		/* 핸드폰 번호 출력 시 - 추가 */
		String firstPhoneNumber = "";
		String middlePhoneNumber = "";
		String lastPhoneNumber = "";
		if(returnEmployee.getEmployeePhone().length() == 11) {
			firstPhoneNumber = returnEmployee.getEmployeePhone().substring(0,3);
			middlePhoneNumber = returnEmployee.getEmployeePhone().substring(3,7);
			lastPhoneNumber = returnEmployee.getEmployeePhone().substring(7,11);
		}else if(returnEmployee.getEmployeePhone().length() == 10) {
			middlePhoneNumber = returnEmployee.getEmployeePhone().substring(3,6);
			lastPhoneNumber = returnEmployee.getEmployeePhone().substring(6,10);
		}
		
		returnEmployee.setEmployeePhone(firstPhoneNumber+"-"+middlePhoneNumber+"-"+lastPhoneNumber);

		if(reason.size() == 0) {
			mv.addAttribute("hrConfirm", "");
			mv.addAttribute("hrReason", "");
			mv.addAttribute( "bossConfirm", "");
			mv.addAttribute( "bossReason", "");
		} else if(reason.size() == 1) {
			mv.addAttribute("hrConfirm", reason.get(0).getEmployeeRegistDate());
			mv.addAttribute("hrReason", reason.get(0).getReason());

		} else if(reason.size() == 2) {
			mv.addAttribute("hrConfirm", reason.get(0).getEmployeeRegistDate());
			mv.addAttribute("hrReason", reason.get(0).getReason());
			mv.addAttribute("bossConfirm", reason.get(1).getEmployeeRegistDate());
			mv.addAttribute( "bossReason", reason.get(1).getReason());
		}

		if(getAdminName.size() == 0) {
			mv.addAttribute("hrName", "");
			mv.addAttribute( "bossName", "");
		} else if(getAdminName.size() == 1) {
			mv.addAttribute("hrName", getAdminName.get(0).getAdminName());
		} else if(getAdminName.size() == 2) {
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
	public String employeeModify() {
		return "admin/humanResource/blackList/selectBlackList";
	}
	
	/* 휴가 조회 */
	@GetMapping("/select/vacation")
	public String selectMyVacaion() {
		return "admin/humanResource/vacation/selectMyVacation";
	}
	
	/* 휴가 전체 조회 */
	@GetMapping("/hr/select/Allvacation")
	public String selectAllVacation() {
		return "admin/humanResource/vacation/selectAllVacation";

	}
}
