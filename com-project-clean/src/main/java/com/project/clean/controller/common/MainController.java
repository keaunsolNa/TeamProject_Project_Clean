package com.project.clean.controller.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.service.admin.AdminAdminService;
import com.project.clean.model.service.reservation.EmployeeReservationService;


@Controller
public class MainController {
	
	private final EmployeeReservationService employeeReservationService;
	private final AdminAdminService adminService;

	@Autowired
	public MainController(EmployeeReservationService employeeReservationService, AdminAdminService adminService) {
		this.employeeReservationService = employeeReservationService;
		this.adminService = adminService;
	}
	

	@GetMapping(value= {"/", "/main"})
	public ModelAndView main(@AuthenticationPrincipal User userinfo, ModelAndView mv) {
			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	        boolean isAdmin = authorities.stream().filter(o -> o.getAuthority().equals("ROLE_ADMIN")).findAny().isPresent();

	        boolean isEmployee = authorities.stream().filter(o -> o.getAuthority().equals("ROLE_EMPLOYEE")).findAny().isPresent();
	        
	        if(isAdmin == true) {
	        	
	        	AdminDTO admin =  adminService.findByAdminId(userinfo.getUsername());
	        	
	        	int adminNo = admin.getAdminNo();
	        	
	        	/* DB에 저장된 주소 @로 분리 */
	    		String[] seperateAddress = admin.getAdminAddress().split("@");
	    		String addressNo = seperateAddress[0];
	    		String address = seperateAddress[1];
	    		String addressDetail = seperateAddress[2];
	    		
	    		System.out.println("addressNo" + addressNo);
	    		System.out.println("address" + address);
	    		System.out.println("addressDetail" + addressDetail);
	    		
	    		/* 나눠진 주소를 담아준다. */
	    		Map<String, String> addressMap = new HashMap<>();
	    		addressMap.put("addressNo", addressNo);
	    		addressMap.put("address", address);
	    		addressMap.put("addressDetail", addressDetail);
	    		
	    		/* 화면에 보여줄 정보를 담아준다. */
	    		mv.addObject("address", addressMap);
	    		mv.addObject("admin", admin);
	        	
	        	
//	        	mv.setViewName("admin/adminMainPage");
	        	
	        	mv.setViewName("admin/hrCard/adminDetail");
	        	
	        } else if(isEmployee == true){
	        	/* 예약날짜 중복없이 select */
	        	List<String> reservationDateList = employeeReservationService.findDistinctByBusinessDate();
	        	List<Integer> ableCountList = new ArrayList<>();
	        	List<Integer> unableCountList = new ArrayList<>();
	        	List<String> dateList = new ArrayList<>();
	        	
	        	for(int i=0; i<reservationDateList.size(); i++) {
	        		
	        		String date = reservationDateList.get(i).substring(0,10);
	        		dateList.add(date);
	        		
	        		int ableCount = employeeReservationService.selectAbleCount(date);
	        		ableCountList.add(ableCount);
	        		 
	        		int unableCount = employeeReservationService.selectUnableCount(date);
	        		unableCountList.add(unableCount);
	        	}
	        	
	        	mv.addObject("reservationDateList", reservationDateList);
	        	mv.addObject("dateList", dateList);
	        	mv.addObject("ableCountList", ableCountList);
	        	mv.addObject("unableCountList", unableCountList);
	        	mv.setViewName("reservation/empMainPage");
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
