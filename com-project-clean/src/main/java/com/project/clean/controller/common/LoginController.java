package com.project.clean.controller.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
		return "/main/main";
	};
	
	@PostMapping("afterLogin")
	@ResponseBody
	public ModelAndView afterMemberLoginForm(@AuthenticationPrincipal User userinfo, ModelAndView mv ) {

		System.out.println(userinfo.getAuthorities());

		userinfo.getAuthorities();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

			boolean isAdmin = authorities.stream().filter(o -> o.getAuthority().equals("모든 관리자")).findAny().isPresent();
	        
	        boolean isEmployee = authorities.stream().filter(o -> o.getAuthority().equals("직원")).findAny().isPresent();

	        if(isAdmin == true) {
	        	mv.setViewName("admin/adminMainPage");
	        } else if(isEmployee == true) {
	        	mv.setViewName("employee/empMainPage");
	        }
	        
		
		return mv;
	}
	
}
