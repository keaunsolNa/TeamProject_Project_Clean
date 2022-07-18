package com.project.clean.controller.vacation;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.dto.joinDTO.AdminVacationCommitDTO;
import com.project.clean.model.service.admin.AdminAdminService;
import com.project.clean.model.service.vacation.VacationService;

@Controller
@RequestMapping("admin")
public class VacationStandbyController {
	
	private final VacationService vacationService;
	private final AdminAdminService adminService;
	
	@Autowired
	public VacationStandbyController(VacationService vacationService, AdminAdminService adminService) {
		this.vacationService = vacationService;
		this.adminService = adminService;
	}
	
	/* 1차 승인 대기 목록 조회 */
	@GetMapping("vacation/standbyFirstVacationList")
	public ModelAndView standbyFirstVacation(ModelAndView mv) {
		
		List<VacationDTO> vacationList = vacationService.findVacationFirstList();
		
		mv.addObject("vacationList", vacationList);
		mv.setViewName("admin/vacation/standbyFirstVacationList");
		
		return mv;
	}
	
	/* 2차 승인 대기 목록 조회 */
	@GetMapping("vacation/standbySecondVacationList")
	public ModelAndView standbySecondVacation(ModelAndView mv) {
		
		List<VacationDTO> vacationList = vacationService.findVacationSecondList();
		
		mv.addObject("vacationList", vacationList);
		mv.setViewName("admin/vacation/standbySecondVacationList");
		
		return mv;
	}
	
	/* 대기 상세 조회 */
	@GetMapping("vacation/standbyVacationDetail/{vacationNo}")
	public ModelAndView standbyVacationDetail(ModelAndView mv, @PathVariable int vacationNo) {
		
		/*
		 * 승인 대기 상세 조회에서는 1차 승인(1차 결재여부 N), 2차 승인(1차 결재여부Y, 반려여부 N을 조회할 수 있다.)
		 * 따라서 2차 승인 내역을 조회할 필요는 없다.
		 * 효율을 높이기 위해 관리자, 결재, 휴가관리 세 개의 테이블을 묶은 엔티티와 디티오를 준비하여 한 번에 정보를 읽어온다.
		 * 다음으로 1차 결재 내역이 존재하는 경우, 다시 말해 VacationCommitList의 size가 1인 경우에만
		 * 결재를 한 관리자의 번호를 받아와 해당 관리자의 정보를 조회해 온다.
		 */
		
		/* 휴가에 관한 전체 정보를 불러온다. */
		AdminVacationCommitDTO vacation = vacationService.findByVacationNo(vacationNo);
		
		/* 해당 휴가에 대한 결재 내역이 1개 존재할 경우 */
		if(vacation.getVacationCommitList().size() == 1) {
			
			int adminNo = vacation.getVacationCommitList().get(0).getAdminNo();
	
			AdminDTO hrAdmin = adminService.findByAdminNo(adminNo);
			
			mv.addObject("hrAdmin", hrAdmin.getAdminName());
			mv.addObject("hrReason", vacation.getVacationCommitList().get(0).getConfirmReason());
			mv.addObject("hrDate", vacation.getVacationCommitList().get(0).getConfirmDate());
			
		} else {
			
			/* 결재가 존재하지 않는 경우, 공백을 보내 사유를 보이지 않게 한다. */
			mv.addObject("hrAdmin", "");
			mv.addObject("hrReason", "");
			mv.addObject("hrDate", "");
			
		}
		
		/* 기본 정보 중 휴가관리 테이블에 존재하지 않는 정보를 불러온다. */
		mv.addObject("adminJob", vacation.getAdminList().get(0).getAdminJob());
		mv.addObject("adminPhone", vacation.getAdminList().get(0).getAdminPhone());

		/* 휴가 신청서 정보를 담는다 */
		mv.addObject("vacation", vacation);
		
		/* 경로를 지정한다. */
		mv.setViewName("admin/vacation/standbyVacationDetail");
		
		return mv;
	}
	
	/* 휴가 신청폼 조회 */
	@GetMapping("vacation/registVacation")
	@ResponseBody
	public ModelAndView registVacation(ModelAndView mv, Principal principal) {
		
		/* 접속한 회원의 아이디를 불러온다. */
		String adminId = principal.getName(); 
		
		AdminDTO admin = adminService.findByAdminId(adminId);
		
		/* 화면에 보일 현재 날짜를 불러온다. */
		LocalDateTime now = LocalDateTime.now();             
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");         
		String today = now.format(formatter);

		
		mv.addObject("admin", admin);
		mv.addObject("today", today);
		mv.setViewName("admin/vacation/registVacation");
		
		return mv;
	}

}
