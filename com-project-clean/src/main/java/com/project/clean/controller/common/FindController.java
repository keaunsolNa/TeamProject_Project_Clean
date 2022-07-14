package com.project.clean.controller.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminEmailDTO;
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
	
	@GetMapping("password")
	public void FindPwd() {
		
	}
	
	@PostMapping("password")
	@Transactional
	public String FindPwd(ModelAndView mv, HttpServletRequest request, RedirectAttributes rttr, HttpServletResponse response)  {
		
		String adminOrEmployee = request.getParameter("adminOrEmp");
		String userId = request.getParameter("userId");
		String userPhone = request.getParameter("phone");
		
		if(adminOrEmployee.equals("admin")){
			
			AdminDTO admin = findService.findAdminIdByPhone(userPhone);
			
			if(null != admin.getAdminId()) {
				if(admin.getAdminId().equals(userId)) {
					
					Random random = new Random();
					String setOfCharacters = "abcdefghxyz1234567-/@";
					
					int newPwdchar = random.nextInt(setOfCharacters.length());
					int newPwdchar2 = random.nextInt(setOfCharacters.length());
					char newPwd1 = setOfCharacters.charAt(newPwdchar);
					char newPwd2 = setOfCharacters.charAt(newPwdchar2);
					int newPwd3 = (int)(Math.random() * 10);
					int newPwd4 = (int)(Math.random() * 10);
					int newPwd5 = (int)(Math.random() * 10);
					int newPwd6 = (int)(Math.random() * 10);
					
					String password = ("" + newPwd1 + newPwd2 + newPwd3 + newPwd4 + newPwd5 + newPwd6);	
					
					BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
					String newPassword = passwordEncoder.encode(password);
					
					AdminEmailDTO reciverEmail = findService.findAdminEmailById(admin.getAdminId(),  password, admin.getAdminNo());
					String emailAddress = reciverEmail.getEmail() + "@" + reciverEmail.getDomain();
					
					System.out.println(emailAddress);
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out=null;
					
					SimpleEmail email = new SimpleEmail();
					email.setHostName("smtp.naver.com");
//					email.setSmtpPort(587);
					email.setAuthentication("knsol1992@naver.com", "V4K81B8YTWRJ");
					email.setSslSmtpPort("465");
					email.setSSLOnConnect(true);
					email.setStartTLSEnabled(false);
					
					String response1 = "fail";
					
					try {
						email.setFrom("knsol1992@naver.com", "관리자", "UTF-8");
						
						email.addTo(emailAddress, "테스트", "UTF-8");
						
						email.setSubject("제목");
						
						email.setMsg("메시지");
						
						try {
							out = response.getWriter();
							response1 = email.send();
						} catch (IOException e) {
							e.printStackTrace();
						}
					} catch (EmailException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} finally {
						
					}
					
					
					System.out.println(emailAddress);
					
					rttr.addFlashAttribute("resultMessage", "등록하신 메일로 임시 비밀번호를 발송하였습니다.");
				}
			} else {
				rttr.addFlashAttribute("resultMessage", "일치하는 계정이 없습니다. 입력하신 정보를 확인해 주세요.");
			}
		} else {
			
		} 
		
		return "redirect:/main";
	}
	
	
}

