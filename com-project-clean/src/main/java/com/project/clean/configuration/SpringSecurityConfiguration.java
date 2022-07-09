package com.project.clean.configuration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.clean.model.service.common.LoginService;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private LoginService loginService;
	
	@Autowired 
	public SpringSecurityConfiguration(LoginService loginService) {
		this.loginService = loginService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/lib/**");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		
			.authorizeRequests()
				.antMatchers("/client/**").hasRole("CLIENT")
				.antMatchers("/member/**").hasRole("MEMBER")
				.antMatchers("/employee/**").hasRole("EMPLOYEE")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/boss/**").hasRole("ADMIN_BOSS")
				.antMatchers("/sec/**").hasRole("ADMIN_SEC")
				.antMatchers("/hr/**").hasRole("ADMIN_HR")
				.antMatchers("/fnc/**").hasRole("ADMIN_FNC")
				.anyRequest().permitAll()
				
			.and()
				.formLogin()
				.loginPage("/common/login")
				.successForwardUrl("/common/afterLogin")
				.failureForwardUrl("/")
				
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.deleteCookies("JESSIONID")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling()
				.accessDeniedPage("/common/denied");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
	}
	
}
