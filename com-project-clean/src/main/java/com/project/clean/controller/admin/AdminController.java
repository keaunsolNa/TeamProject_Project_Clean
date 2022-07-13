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
import com.project.clean.model.dto.commonDTO.EmployeeAddressDTO;
import com.project.clean.model.dto.commonDTO.EmployeeEmailDTO;
import com.project.clean.model.dto.commonDTO.ReasonDTO;
import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.service.admin.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	/* 직원 전체 조회(재직자 main page) */
	@GetMapping("/select/retireNEmployee")
	public String selectRetireNEmployee(Model mv) {
		
		List<EmployeeAndAllDTO> selectRetireNEmployeeList = adminService.selectRetireNEmployee();
		List<EmployeeAddressDTO> employeeAddress = adminService.selectRetireNEmployeeAddress();
		
		/* 직원들 중 퇴직여부가 Y인 직원 */
		List<EmployeeAndAllDTO> entireNEmployeeList = new ArrayList();
		if(!selectRetireNEmployeeList.isEmpty() && !employeeAddress.isEmpty()) {
		for(int i = 0; i < selectRetireNEmployeeList.size(); i ++) {
			EmployeeAndAllDTO employeeDTO = selectRetireNEmployeeList.get(i);
			EmployeeAddressDTO employeeAddressDTO = employeeAddress.get(i);
			
			/* employeeDTO 안에 Address를 넣는 작업 */
				employeeDTO.setEmployeeAddressDTO(employeeAddressDTO);
				entireNEmployeeList.add(employeeDTO);
			
			/* 출력으로 중간 확인 */
//			for(EmployeeDTO d : employeeList) {
//				System.out.println("dddd= " + d);
//			}
		}
	}
		
		mv.addAttribute("entireNEmployeeList", entireNEmployeeList);
		return "admin/humanResource/selectAllEmployee/selectAllEmployee";
	}
	
	/* ajax 직원 전체 조회 (재직자) */
	@GetMapping(value = "/select/retireNEmployeeAjax", produces = "application/json; charset=UTF-8;")
	@ResponseBody
	public List<EmployeeAndAllDTO> selectRetireNEmployeeAjax(Model mv) {
		List<EmployeeAndAllDTO> selectRetireNEmployeeList = adminService.selectRetireNEmployee();
		List<EmployeeAddressDTO> selectRetireNEmployeeAddressList = adminService.selectRetireNEmployeeAddress();
		if(!selectRetireNEmployeeList.isEmpty() && !selectRetireNEmployeeAddressList.isEmpty()) {

		List<EmployeeAndAllDTO> entireNEmployeeList = new ArrayList<>();
		for(int i = 0; i < selectRetireNEmployeeList.size(); i ++) {
			EmployeeAndAllDTO employeeDTO = selectRetireNEmployeeList.get(i);
			EmployeeAddressDTO employeeAddressDTO = selectRetireNEmployeeAddressList.get(i);
				employeeDTO.setEmployeeAddressDTO(employeeAddressDTO);
				entireNEmployeeList.add(employeeDTO);
		}
	}
		System.out.println("entireYEmployeeList: " + selectRetireNEmployeeList);
		
		return selectRetireNEmployeeList;
	}
	
	/* ajax 직원 전체 조회 (퇴사자) */
	@GetMapping(value = "/select/retireYEmployee", produces = "application/json; charset=UTF-8;")
	@ResponseBody
	public List<EmployeeAndAllDTO> selectRetireYEmployee(Model mv) {
		List<EmployeeAndAllDTO> selectRetireYEmployeeList = adminService.selectRetireYEmployee();
		List<EmployeeAddressDTO> selectRetireYEmployeeAddressList = adminService.selectRetireYEmployeeAddress();
		
		List<EmployeeAndAllDTO> entireYEmployeeList = new ArrayList<>();
		for(int i = 0; i < selectRetireYEmployeeList.size(); i ++) {
			EmployeeAndAllDTO employeeDTO = selectRetireYEmployeeList.get(i);
			EmployeeAddressDTO employeeAddressDTO = selectRetireYEmployeeAddressList.get(i);
				employeeDTO.setEmployeeAddressDTO(employeeAddressDTO);
				entireYEmployeeList.add(employeeDTO);
		}
		System.out.println("entireYEmployeeList: " + entireYEmployeeList);
		
		return entireYEmployeeList;
	}

	/* (관리자가)직원 수정 페이지로 이동 */
	@GetMapping("/modify/employee/{empNo}")
	public String adminModifyEmployee(@PathVariable int empNo, Model mv) {
		
		EmployeeAndAllDTO employeeDTO = adminService.selectOneEmployee(empNo);
		EmployeeAddressDTO employeeAddressDTO = adminService.selectOneEmployeeAddress(empNo);
		EmployeeEmailDTO EmployeeEmailDTO = adminService.selectOneEmployeeEmail(empNo);
//		EmployeePictureDTO employeePictureDTO = adminService.selectOneEmployeePicture(empNo);
		System.out.println("employeeDTOemployeeDTOemployeeDTOemployeeDTOemployeeDTO: "+ employeeDTO.getEmployeePhone());
		String middlePhoneNumber = "";
		String lastPhoneNumber = "";
		if(employeeDTO.getEmployeePhone().length() == 11) {
			middlePhoneNumber = employeeDTO.getEmployeePhone().substring(3,7);
			lastPhoneNumber = employeeDTO.getEmployeePhone().substring(7,11);
		}else if(employeeDTO.getEmployeePhone().length() == 10) {
			middlePhoneNumber = employeeDTO.getEmployeePhone().substring(3,6);
			lastPhoneNumber = employeeDTO.getEmployeePhone().substring(6,10);
		}
		System.out.println("employeeDTOemployeeDTOemployeeDTOemployeeDTOemployeeDTO: "+ middlePhoneNumber);
		System.out.println("employeeDTOemployeeDTOemployeeDTOemployeeDTOemployeeDTO: "+ lastPhoneNumber);
		Map<String, String> phoneNumber = new HashMap();
		phoneNumber.put("middlePhoneNumber", middlePhoneNumber);
		phoneNumber.put("lastPhoneNumber", lastPhoneNumber);
		
		mv.addAttribute("employee", employeeDTO);
		mv.addAttribute("employeeAddress", employeeAddressDTO);
		mv.addAttribute("employeeEmail", EmployeeEmailDTO);
//		mv.addAttribute("employeePicture", employeePictureDTO);
		mv.addAttribute("employeeNo", empNo);
		mv.addAttribute("phoneNumber", phoneNumber);
		
		return "/admin/humanResource/selectAllEmployee/modifyEmployee";
	}
	
	/* (관리자가)직원 정보 수정 */
	@PostMapping("/modify/employee")
	public String modifyEmployee(EmployeeAndAllDTO employeeDTO, EmployeeAddressDTO addressDTO, EmployeeEmailDTO emailDTO, 
								String employeePhone1, String employeePhone2, String employeePhone3, Model mv) {
		
		String employeePhone = employeePhone1 + employeePhone2 + employeePhone3;
		employeeDTO.setEmployeePhone(employeePhone);
		employeeDTO.setEmployeeAddressDTO(addressDTO);
		employeeDTO.setEmployeeEmailDTO(emailDTO);
		
		adminService.modifyEmployee(employeeDTO);
		
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
		List<EmployeeAddressDTO> waitingEmployeeAddress = adminService.selectWaitingEmployeeAddressList();
		/* 직원들 중 1번째 승인과 최종승인이 N인 직원 */
		List<EmployeeAndAllDTO> waitingEmployeeList = new ArrayList<>();
		if(!selectWaitingEmployeeList.isEmpty() && !waitingEmployeeAddress.isEmpty()) {
			
		for(int i = 0; i < selectWaitingEmployeeList.size(); i ++) {
			EmployeeAndAllDTO employeeDTO = selectWaitingEmployeeList.get(i);
			EmployeeAddressDTO employeeAddressDTO = waitingEmployeeAddress.get(i);
			
			/* employeeDTO 안에 Address를 넣는 작업 */
				employeeDTO.setEmployeeAddressDTO(employeeAddressDTO);
				waitingEmployeeList.add(employeeDTO);
			}
		}
		
		/* BOSS 대기중인 인원 조회 */
		List<EmployeeAndAllDTO> selectWaitingEmployeeListBoss = adminService.selectWaitingEmployeeListBoss();
		List<EmployeeAddressDTO> waitingEmployeeAddressBoss = adminService.selectWaitingEmployeeAddressListBoss();
		/* 직원들 중 1번째 승인과 최종승인이 N인 직원 */
		List<EmployeeAndAllDTO> waitingEmployeeListBoss = new ArrayList<>();
		if(!(selectWaitingEmployeeListBoss.size() == 0) && !(waitingEmployeeAddressBoss.size() == 0)) {
			
			for(int i = 0; i < selectWaitingEmployeeListBoss.size(); i ++) {
				EmployeeAndAllDTO employeeDTO = selectWaitingEmployeeListBoss.get(i);
				EmployeeAddressDTO employeeAddressDTO = waitingEmployeeAddressBoss.get(i);
				
				/* employeeDTO 안에 Address를 넣는 작업 */
				employeeDTO.setEmployeeAddressDTO(employeeAddressDTO);
				waitingEmployeeListBoss.add(employeeDTO);
			}
		}
		
		/* 반려인원 조회 */
		List<EmployeeAndAllDTO> selectReturnEmployeeList = adminService.selectReturnEmployeeList();
		List<EmployeeAddressDTO> selectReturnEmployeeAddressList = adminService.selectReturnEmployeeAddressList();
		
		List<EmployeeAndAllDTO> returnEmployeeList = new ArrayList<>();
		if(!(selectReturnEmployeeList.size() == 0) && !(selectReturnEmployeeAddressList.size() == 0)) {
		for(int i = 0; i < selectReturnEmployeeList.size(); i ++) {
			EmployeeAndAllDTO employeeDTO = selectReturnEmployeeList.get(i);
			EmployeeAddressDTO employeeAddressDTO = selectReturnEmployeeAddressList.get(i);
			
			/* employeeDTO 안에 Address를 넣는 작업 */
			employeeDTO.setEmployeeAddressDTO(employeeAddressDTO);
			returnEmployeeList.add(employeeDTO);
			}
		}
		
		mv.addAttribute("waitingEmployeeList", waitingEmployeeList);		//HR authority
		mv.addAttribute("returnEmployeeList", returnEmployeeList);			
		mv.addAttribute("waitingEmployeeListBoss", waitingEmployeeListBoss);	//BOSS authority
		
		mv.addAttribute("employeeNo", maxMemberNo + 1);
		mv.addAttribute("employeeHireDate", sysdate);
		mv.addAttribute("employeeId", id + 1);
		
		return "admin/humanResource/registEmployee/registMainEmployee";
	}
	
	/* 직원등록 */
	@PostMapping("/hr/regist/employee")
		public String registEmployee(EmployeeAndAllDTO employeeDTO, EmployeeAddressDTO addressDTO, EmployeeEmailDTO emailDTO,
										String employeePhone1, String employeePhone2, String employeePhone3) {
		
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
		  employeeDTO.setEmployeeAddressDTO(addressDTO);
		  employeeDTO.setEmployeeEmailDTO(emailDTO);
		  adminService.registEmployee(employeeDTO);
		
		return "redirect:/main";
			
		}
	
	/* 승인 대기인원 상세 조회 */
	@GetMapping("/select/waitingDetail/{empNo}")
	public String waitingDetail(@PathVariable int empNo, Model mv) {
		List<ReasonDTO> getAdminNameAndRegistDate = adminService.adminSignWaitingEmployee(empNo);
		EmployeeAndAllDTO waitingEmployee = adminService.waitingEmployee(empNo);
		EmployeeAddressDTO employeeAddress = adminService.waitingEmployeeAddress(empNo);
		EmployeeEmailDTO employeeEmail = adminService.waitingEmployeeEmail(empNo);


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
		
		waitingEmployee.setEmployeeAddressDTO(employeeAddress);
		waitingEmployee.setEmployeeEmailDTO(employeeEmail);
		waitingEmployee.setEmployeeRestCommitList(getAdminNameAndRegistDate);
		
		if(getAdminNameAndRegistDate.size() == 0) {
			mv.addAttribute("hrReturnReason", "");
			mv.addAttribute( "bossReturnReason", "");
		} else if(getAdminNameAndRegistDate.size() == 1) {
			mv.addAttribute("hrReturnReason", getAdminNameAndRegistDate.get(0).getReason());
			mv.addAttribute("bossReturnReason", "");
			
		} else if(getAdminNameAndRegistDate.size() == 2) {
			mv.addAttribute("hrReturnReason", getAdminNameAndRegistDate.get(0).getReason());
			mv.addAttribute("bossReturnReason", getAdminNameAndRegistDate.get(1).getReason());
		}


		if(getAdminNameAndRegistDate.size() == 0) {
			
		}
		
		mv.addAttribute("waitingEmployee", waitingEmployee);
		return "admin/humanResource/registEmployee/waitingEmployee";
	}
	/* 반려인원 상세 조회 */
	@GetMapping("/select/returnDetail/{empNo}")
	public String returnDetail(@PathVariable int empNo, Model mv) {
		EmployeeAndAllDTO returnEmployee = adminService.returnEmployee(empNo);
		EmployeeAddressDTO employeeAddress = adminService.returnEmployeeAddress(empNo);
		EmployeeEmailDTO employeeEmail = adminService.returnEmployeeEmail(empNo);
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
		returnEmployee.setEmployeeAddressDTO(employeeAddress);
		returnEmployee.setEmployeeEmailDTO(employeeEmail);

		String hrReturnReason = "";
		String bossReturnReason = "";
		/* 1차 2차 null 판별 알고리즘 */
//		if(employeeRestList.size() == 0) {
//			mv.addAttribute("hrReturnReason", "내용없음");
//			mv.addAttribute( "bossReturnReason", "내용없음");
//		} else if(employeeRestList.size() == 1) {
//			mv.addAttribute("hrReturnReason", employeeRestList.get(0).getReason());
//			mv.addAttribute("bossReturnReason", "내용없음");
//			
//		} else if(employeeRestList.size() == 2) {
//			mv.addAttribute("hrReturnReason", employeeRestList.get(0).getReason());
//			mv.addAttribute("bossReturnReason", employeeRestList.get(1).getReason());
//		}
		
		
		mv.addAttribute("returnEmployee", returnEmployee);
		return "admin/humanResource/registEmployee/returnEmployee";
	}
	
	@PostMapping("/hr/confirm/restCommit")
	public String insertRestCommitConfirm(ReasonDTO restCommitDTO, AdminDTO adminDTO) {
		
		restCommitDTO.setAdminDTO(adminDTO);

		adminService.insertRestCommitConfirm(restCommitDTO);

		return "redirect:/admin/hr/regist/employee";
		
	}
	
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
