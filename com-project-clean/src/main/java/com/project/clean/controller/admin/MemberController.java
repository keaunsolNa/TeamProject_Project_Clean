package com.project.clean.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		String[] seperateAddress = employeeDTO.getEmployeeAddress().split("@");
		
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];
		
		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);
		
		
		mv.addAttribute("employee", employeeDTO);
		mv.addAttribute("addressMap", addressMap);
		
		return "/admin/humanResource/selectAllEmployee/selectEmployee";
	}
	


}
