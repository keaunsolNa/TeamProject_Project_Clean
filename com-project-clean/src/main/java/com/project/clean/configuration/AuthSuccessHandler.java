package com.project.clean.configuration;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@Service("com.project.clean")
public class AuthSuccessHandler implements AuthenticationSuccessHandler{
	
	@Autowired
	UserDetailsService loginService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		ModelAndView mv = new ModelAndView();

		System.out.println(authentication.getName());
		if(null != request.getCookies()) {
			
			Cookie[] myCookie = request.getCookies();
			for(Cookie c : myCookie) {
				System.out.println(c.getName());
				System.out.println(c.getValue());
			}
		}
		
		authentication = SecurityContextHolder.getContext().getAuthentication();

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		boolean isAdmin = authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();
        
        boolean isEmployee = authorities.stream().filter(o -> o.getAuthority().equals("RLOE_EMPLOYEE")).findAny().isPresent();
        
        System.out.println("테스트1");
        System.out.println("테스트2");
        System.out.println("테스트3");
        System.out.println("테스트4");

        if(isAdmin == true) {
        	
        	mv.setViewName("admin/adminMainPage");
        	
        } else if(isEmployee == true) {
        	System.out.println("테스트1");
        	System.out.println("테스트2");
        	System.out.println("테스트3");
        	System.out.println("테스트4");
        	mv.setViewName("employee/empMainPage");
        }  

		response.sendRedirect("/main");
		
		
	}

}