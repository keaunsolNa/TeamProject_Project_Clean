package com.project.clean.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.clean.controller.common.LoginController;
import com.project.clean.model.service.common.LoginService;

@Configuration
@EnableWebSecurity
@ComponentScan("com.project.clean")
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private LoginService loginService;
	private DataSource dataSource;
	private AuthFailedHandler authFailureHandler;
	private AuthSuccessHandler authSuccessHandler;
	private UserDetailsService userDetailsService;
	
	@Autowired 
	public SpringSecurityConfiguration(LoginService loginService, DataSource dataSource, LoginController successLoginHandler, AuthFailedHandler authFailureHandler,
			AuthSuccessHandler authSuccessHandler, UserDetailsService userDetailsService) {
		this.loginService = loginService;
		this.dataSource = dataSource;
		this.authFailureHandler = authFailureHandler;
		this.authSuccessHandler = authSuccessHandler;
		this.userDetailsService = userDetailsService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
		return jdbcTokenRepository;
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("**/css/**", "**/lib/**", "**/image/**", "**/js/**" );
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		
			.authorizeRequests()
				
				.antMatchers("/client/**").hasRole("CLIENT")
				.antMatchers("/member/**").hasRole("MEMBER")
				.antMatchers("/employee/**").hasRole("EMPLOYEE")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/admin/boss/**").hasRole("ADMIN_BOSS")
				.antMatchers("/admin/sec/**").hasRole("ADMIN_SEC")
				.antMatchers("/admin/hr/**").hasRole("ADMIN_HR")
				.antMatchers("/admin/fnc/**").hasRole("ADMIN_FNC")
				.antMatchers("/member/**").hasRole("MEMBER")
				
				.anyRequest().permitAll() 
			.and()
				.formLogin()
				.loginPage("/common/login")
				.authenticationDetailsSource(null)
				.successHandler(authSuccessHandler)
				.failureHandler(authFailureHandler)
				.permitAll()
				
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.deleteCookies("JESSIONID")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/") 
			.and()
				.exceptionHandling()
				.accessDeniedPage("/common/denied");
	
		http.rememberMe()
			.key("rembmerMeKeyByK")
			.rememberMeParameter("_spring_security_remember_me")
			.tokenValiditySeconds(3600)
			.alwaysRemember(false)
			.userDetailsService(userDetailsService);
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
	}
	
}
