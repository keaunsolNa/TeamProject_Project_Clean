package com.project.clean.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/common")
public class LoginController {

	@GetMapping("login")
	public String  memberLoginForm() {
		System.out.println("로그인 요청");
		return "redirect:/main";
	};
	
	@PostMapping("loginFailed")
	public String afterLoginFailed(ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr) {
		
		System.out.println("로그인 실패");
		System.out.println(request.getAttribute("errorMessage"));
		rttr.addFlashAttribute("Message", request.getAttribute("errorMessage"));
		
		return "redirect:/main";
	}
	
}
