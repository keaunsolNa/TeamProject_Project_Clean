package com.project.clean.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@ComponentScan("com.project.clean")
public class AuthFailedHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String errorMessage = null;
		
		if(exception instanceof BadCredentialsException) {
			errorMessage ="아이디나 비밀번호가 일치하지 않습니다. 다시 확인 해 주십시오.";
		} else if(exception instanceof DisabledException) {
			errorMessage = "계정이 비활성화 되었습니다. 보안 담당자에게 문의하세요";
		} else if(exception instanceof InternalAuthenticationServiceException) {
			errorMessage = "아이디나 비밀번호가 일치하지 않습니다. 다시 확인 해 주십시오.";
		} else if(exception instanceof CredentialsExpiredException) {
			errorMessage = "비밀번호 유효기간 만료. 보안 담당자에게 문의하세요.";
		} else {
			errorMessage = "알 수 없는 이유로 로그인에 실패하였습니다. 보안 담당자에게 문의하세요.";
		}
		
		System.out.println(errorMessage);
		request.setAttribute("errorMessage", errorMessage);
		
		request.getRequestDispatcher("/common/loginFailed").forward(request, response);
		
	}
	
}
