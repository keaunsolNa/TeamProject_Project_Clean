package com.project.clean.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@ComponentScan("com.project.clean")
public class AuthSuccessHandler implements AuthenticationSuccessHandler{
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		ModelAndView mv = new ModelAndView();
		
		authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		boolean isAdmin = authorities.stream().filter(o -> o.getAuthority().equals("모든 관리자")).findAny().isPresent();
        
        boolean isEmployee = authorities.stream().filter(o -> o.getAuthority().equals("직원")).findAny().isPresent();

        if(isAdmin == true) {
        	mv.setViewName("admin/adminMainPage");
        } else if(isEmployee == true) {
        	mv.setViewName("employee/empMainPage");
        }  

		response.sendRedirect("/main");
		
		
	}

}