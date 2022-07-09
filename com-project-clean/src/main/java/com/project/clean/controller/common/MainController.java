package com.project.clean.controller.common;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@GetMapping(value= {"/", "/main"})
	public ModelAndView main(@AuthenticationPrincipal User userinfo, ModelAndView mv) {
			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	        boolean isAdmin = authorities.stream().filter(o -> o.getAuthority().equals("모든 관리자")).findAny().isPresent();

	        boolean isEmployee = authorities.stream().filter(o -> o.getAuthority().equals("직원")).findAny().isPresent();
	        
	        if(isAdmin == true) {
	        	mv.setViewName("admin/adminMainPage");
	        } else if(isEmployee == true){
	        	mv.setViewName("employee/empMainPage");
	        } else {
	        	mv.setViewName("common/main");
	        }

	        return mv;
	        
	}
	
	@PostMapping(value="/")
	public String redirectMain(@AuthenticationPrincipal User userinfo, ModelAndView mv) {
		return "redirect:/";
	}
}
