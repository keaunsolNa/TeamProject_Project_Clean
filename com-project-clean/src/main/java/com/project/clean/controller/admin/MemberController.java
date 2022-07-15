package com.project.clean.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.service.member.MemberSerivceImpl;


@Controller
@RequestMapping("/member")
public class MemberController {
	private final MemberSerivceImpl memberService;
	
	@Autowired
	public MemberController(MemberSerivceImpl memberService) {
		this.memberService = memberService; 
	}
	
	/* 직원 한명 조회 */
	@GetMapping("/selectEmployee/{empNo}")
	public String selectOneEmployee(@PathVariable int empNo, Model mv) {
		EmployeeAndAllDTO employeeDTO = memberService.selectOneEmployee(empNo);
//		EmployeePictureDTO employeePictureDTO = memberService.selectOneEmployeePicture(empNo);
		
		mv.addAttribute("employee", employeeDTO);
//		mv.addAttribute("employeePicture", employeePictureDTO);
		mv.addAttribute("employeeNo", empNo);
		
//		System.out.println("employeeDTO? "+ employeeDTO);
//		System.out.println("employeeDTO.getEmployeePictureDTO(): " + employeeDTO);
		
		return "/admin/humanResource/selectAllEmployee/selectEmployee";
	}

}
