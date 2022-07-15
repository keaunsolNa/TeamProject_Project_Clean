package com.project.clean.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.service.common.FindService;

@Controller
@RequestMapping("/common/find")
public class FindController {

	private final FindService findService;
	
	@Autowired
	public FindController(FindService findService) {
		this.findService = findService;
	}
	
	@GetMapping("id")
	public void Findid() {
		
	}
	
	@PostMapping("id")
	public String FindId(ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr) {
		
		String adminOrEmplyoee = request.getParameter("adminOrEmp");
		String userName = request.getParameter("name");
		String userPhone = request.getParameter("phone");
				
		if(adminOrEmplyoee.equals("admin")) {
			AdminDTO admin = findService.findAdminIdByPhone(userPhone);
			
			if(null != admin.getAdminId()) {
				if(admin.getAdminName().equals(userName)) {
					rttr.addFlashAttribute("resultMessage", admin.getAdminId());
					
				}
			} else {
				System.out.println("rttr 확인 : " + rttr);
				}
			
		} else if(adminOrEmplyoee.equals("employee")){
			EmployeeDTO emp = findService.findEmpIdByPhone(userPhone);
			
			if(null != emp.getEmployeeId()) {
				if(emp.getEmployeeName().equals(userName)) {
					rttr.addFlashAttribute("resultMessage", emp.getEmployeeId());
				}
			} else {rttr.addFlashAttribute("resultMessage", "입력하신 정보를 확인하세요.");}
		
		System.out.println(rttr);
		
		}
		
		return "redirect:/main";
	}
	
}

