package com.project.clean.controller.vacation;

import java.security.Principal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.controller.admin.paging.Pagenation;
import com.project.clean.controller.admin.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.VacationCommitDTO;
import com.project.clean.model.dto.commonDTO.VacationDTO;
import com.project.clean.model.service.admin.AdminAdminService;
import com.project.clean.model.service.vacation.VacationService;

@Controller
@RequestMapping("admin/hrCard")
public class VacationStandbyController {
	
	private final VacationService vacationService;
	private final AdminAdminService adminService;
	
	@Autowired
	public VacationStandbyController(VacationService vacationService, AdminAdminService adminService) {
		this.vacationService = vacationService;
		this.adminService = adminService;
	}

	/* 1차 승인 대기 목록 검색, 페이징 */
	@GetMapping("vacation/standbyFirstVacationList")
	public ModelAndView standbyFirstVacationSearch(ModelAndView mv, HttpServletRequest request) {

		try {	
		
			String currentPage = request.getParameter("currentPage");
			int pageNo = 1;
	
			if(currentPage != null && !"".equals(currentPage)) {
				pageNo = Integer.parseInt(currentPage);
			}
	
			String searchCondition = request.getParameter("searchCondition");
			String searchValue = request.getParameter("searchValue");
	
			int totalCount = vacationService.selectTotalCount(searchCondition, searchValue);
	
			/* 한 페이지에 보여 줄 게시물 수 */
			int limit = 5;		//얘도 파라미터로 전달받아도 된다.
	
			/* 한 번에 보여질 페이징 버튼의 갯수 */
			int buttonAmount = 3;
	
			/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
			SelectCriteria selectCriteria = null;
			if(searchValue != null && !"".equals(searchValue)) {
				selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
			} else {
				selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
			}
			System.out.println(selectCriteria);
	
			
			List<VacationDTO> vacationList = vacationService.findByVacationList(selectCriteria);
			
			mv.addObject("vacationList", vacationList);
			mv.addObject("selectCriteria", selectCriteria);
			mv.setViewName("admin/hrCard/vacation/standbyFirstVacationList");
			
			return mv;
		
		}catch(Exception e){
			
			mv.setViewName("admin/error.html");
			
			return mv;
		}
	}

	
	/* 2차 승인 대기 목록 조회 */
	@GetMapping("boss/standbySecondVacationList")
	public ModelAndView standbySecondVacation(ModelAndView mv, HttpServletRequest request) {
	
		try {
		
			String currentPage = request.getParameter("currentPage");
			int pageNo = 1;
	
			if(currentPage != null && !"".equals(currentPage)) {
				pageNo = Integer.parseInt(currentPage);
			}
	
			String searchCondition = request.getParameter("searchCondition");
			String searchValue = request.getParameter("searchValue");
	
			int totalCount = vacationService.selectSecondTotalCount(searchCondition, searchValue);
	
			/* 한 페이지에 보여 줄 게시물 수 */
			int limit = 5;		//얘도 파라미터로 전달받아도 된다.
	
			/* 한 번에 보여질 페이징 버튼의 갯수 */
			int buttonAmount = 3;
	
			/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
			SelectCriteria selectCriteria = null;
			if(searchValue != null && !"".equals(searchValue)) {
				selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
			} else {
				selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
			}
			System.out.println(selectCriteria);
	
			
			List<VacationDTO> vacationList = vacationService.findVacationSecondList(selectCriteria);
			
			mv.addObject("vacationList", vacationList);
			mv.addObject("selectCriteria", selectCriteria);
			mv.setViewName("admin/hrCard/boss/standbySecondVacationList");
			
			return mv;
		
		}catch(Exception e){
			
			mv.setViewName("admin/error.html");
			
			return mv;
		}
	}
	
	/* 대기 휴가 상세 조회 - 인사관리자 */
	@GetMapping("vacation/standbyVacationDetail/{vacationNo}")
	public ModelAndView standbyVacationDetail(ModelAndView mv, @PathVariable int vacationNo) {
		
		try {
		
			/*
			 * 승인 대기 상세 조회에서는 1차 승인(1차 결재여부 N), 2차 승인(1차 결재여부Y, 반려여부 N을 조회할 수 있다.)
			 * 따라서 2차 승인 내역을 조회할 필요는 없다.
			 * 효율을 높이기 위해 관리자, 결재, 휴가관리 세 개의 테이블을 묶은 엔티티와 디티오를 준비하여 한 번에 정보를 읽어온다.
			 * 다음으로 1차 결재 내역이 존재하는 경우, 다시 말해 VacationCommitList의 size가 1인 경우에만
			 * 결재를 한 관리자의 번호를 받아와 해당 관리자의 정보를 조회해 온다.
			 */
			
			/* 휴가에 관한 전체 정보를 불러온다. */
			VacationDTO vacation = vacationService.findAdminByVacationNo(vacationNo);
	
			/* 휴가 신청서 정보를 담는다 */
			mv.addObject("vacation", vacation);
			
			
			/* 휴가 신청자의 기본 정보를 꺼낸다. */
			
			int adminNo = vacation.getAdminNo();
			AdminDTO requestAdmin = adminService.findByAdminNo(adminNo);
			
			mv.addObject("adminNo", adminNo);
			mv.addObject("adminJob", requestAdmin.getAdminJob());
			mv.addObject("adminPhone", requestAdmin.getAdminPhone());
	
			
			/* 경로를 지정한다. */
			mv.setViewName("admin/hrCard/vacation/standbyVacationDetail");
			
			return mv;
			
		}catch(Exception e){
			
			mv.setViewName("admin/error.html");
			
			return mv;
		}
	}
	
	/* 대기 휴가 상세 조회 - 대표 */
	@GetMapping("boss/standbySecondVacationDetail/{vacationNo}")
	public ModelAndView standbyVacationSecondDetail(ModelAndView mv, @PathVariable int vacationNo) {
		
		
		/*
		 * 승인 대기 상세 조회에서는 1차 승인(1차 결재여부 N), 2차 승인(1차 결재여부Y, 반려여부 N을 조회할 수 있다.)
		 * 따라서 2차 승인 내역을 조회할 필요는 없다.
		 * 효율을 높이기 위해 관리자, 결재, 휴가관리 세 개의 테이블을 묶은 엔티티와 디티오를 준비하여 한 번에 정보를 읽어온다.
		 * 다음으로 1차 결재 내역이 존재하는 경우, 다시 말해 VacationCommitList의 size가 1인 경우에만
		 * 결재를 한 관리자의 번호를 받아와 해당 관리자의 정보를 조회해 온다.
		 */
		
		/* 휴가에 관한 전체 정보를 불러온다. */
		
		try {
		
			VacationDTO vacation = vacationService.findByVacationNo(vacationNo);
			
			if(vacation.getVacationCommitList().size() == 1) {
				
				/* 해당 휴가에 대한 결재 내역을 꺼낸다. */
				mv.addObject("hrReason", vacation.getVacationCommitList().get(0).getConfirmReason());
				mv.addObject("hrDate", vacation.getVacationCommitList().get(0).getConfirmDate());
				
				/* 휴가를 승인한 관리자의 정보를 조회한다. */
				int adminNo = vacation.getVacationCommitList().get(0).getAdminNo();
				
				AdminDTO hrAdmin = adminService.findByAdminNo(adminNo);
				mv.addObject("hrAdmin", hrAdmin.getAdminName());
				mv.addObject("commit", vacation.getVacationCommitList());
			}
			
			/* 휴가 신청자의 기본 정보를 꺼낸다. */
		
			int adminNo = vacation.getAdminNo();
			AdminDTO requestAdmin = adminService.findByAdminNo(adminNo);
			
			mv.addObject("adminNo", adminNo);
			mv.addObject("adminJob", requestAdmin.getAdminJob());
			mv.addObject("adminPhone", requestAdmin.getAdminPhone());
	
			/* 휴가 신청서 정보를 담는다 */
			mv.addObject("vacation", vacation);
			
			/* 경로를 지정한다. */
			mv.setViewName("admin/hrCard/boss/standbySecondVacationDetail");
			
			return mv;
			
		}catch(Exception e){
			
			mv.setViewName("admin/error.html");
			
			return mv;
		}
	}
	
	/* 휴가 신청폼 조회 */
	@GetMapping("registVacation")
	@ResponseBody
	public ModelAndView registVacationSelect(ModelAndView mv, Principal principal) {
		
		try {
		
			/* 접속한 회원의 아이디를 불러온다. */
			String adminId = principal.getName(); 
			
			AdminDTO admin = adminService.findByAdminId(adminId);
			
			/* 화면에 보일 현재 날짜를 불러온다. */
			LocalDateTime now = LocalDateTime.now();             
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");         
			String today = now.format(formatter);
	
			
			mv.addObject("admin", admin);
			mv.addObject("today", today);
			mv.setViewName("admin/hrCard/registVacation");
			
			return mv;
			
		}catch(Exception e){
			
			mv.setViewName("admin/error.html");
			
			return mv;
		}
	}
	
	/* 휴가 신청 */
	@PostMapping("registVacation/run")
	public String registVacation(@ModelAttribute VacationDTO vacation, 
			RedirectAttributes rttr, HttpServletRequest request) {
		
		/* 
		 * 관리자 정보는 테이블에 저장할 필요 없이,
		 * vacation 테이블에만 insert 되면 된다.
		 */
		
		try {
			
			/* 설정값 변수 선언 */
			
			int adminNo = Integer.parseInt(request.getParameter("adminNo"));
			String drafter = "이시원";
		 
	//		/* 입력값 DTO에 담기 */
			vacation.setAdminNo(adminNo);
			vacation.setDrafter(drafter);
			
			vacationService.registNewVacationApply(vacation);
			
			return "redirect:/admin/select/selectMyVacation/move";
			
		}catch(Exception e){
			
			return "redirect:/admin/error.html";
		}
	}
	
	/* 휴가 1차 승인 */
	@PostMapping("vacation/standbyVacationDetail/approval")
	public String approvalFirstVacation(ModelAndView mv, RedirectAttributes rttr, @ModelAttribute 
			VacationCommitDTO vacationCommit, Principal principal, HttpServletRequest request) {
		
		try {
			/*
			 * 휴가 1차 승인의 경우 결재 내역이 결재 테이블에 insert 되어야 하며
			 * 결재한 휴가 번호에 부합하는 휴가신청 테이블의 1차 승인 여부가 Y로 update 되어야 한다.
			 */
			
			/* principal을 사용해 승인한 결재자의 정보를 불러온다. */
			String adminId = principal.getName(); 
			
			AdminDTO admin = adminService.findByAdminId(adminId);
			
			int adminNo = admin.getAdminNo();		
			
			
			/* HttpServletRequest를 이용해 vacationNo를 받아온다. */
			
			int vacationNo = Integer.parseInt(request.getParameter("vacationNo"));
			
			String confirmReason = request.getParameter("hrConfirmReason");
			
			/* 결재일자를 가져온다. */
			LocalDate now = LocalDate.now();
			java.sql.Date confirmDate = java.sql.Date.valueOf(now);
			
			
			/* 불러온 정보를 담아준다. */
			vacationCommit.setAdminNo(adminNo);
			vacationCommit.setVacationNo(vacationNo);
			vacationCommit.setConfirmDate(confirmDate);
			vacationCommit.setConfirmReason(confirmReason);
			
			vacationService.registVacationCommit(vacationCommit);
			
			/* 결재 내역 인서트 후 승인여부를 업데이트 한다. */
			vacationService.modifyFirstConfirmYn(vacationNo);
			
			return "redirect:/admin/hrCard/vacation/standbyFirstVacationList";
			
		}catch(Exception e){
			
			return "redirect:/admin/error.html";
		}
	}
	
	/* 휴가 2차 승인 */
	@PostMapping("boss/standbySecondVacationDetail/approvalSecond")
	public String approvalSecondVacation(ModelAndView mv, RedirectAttributes rttr, @ModelAttribute 
			VacationCommitDTO vacationCommit, Principal principal, HttpServletRequest request) {
		
		try {
			/*
			 * 휴가 2차 승인의 경우 결재 내역이 결재 테이블에 insert 되어야 하며
			 * 결재한 휴가 번호에 부합하는 휴가신청 테이블의 2차 승인 여부와 최종 승인 여부가 Y로 update 되어야 한다.
			 * 또, 휴가명이 연차일 경우 관리자 테이블의 연차 사용 내역을 1회 추가한다.
			 */
			
			/* principal을 사용해 승인한 결재자의 정보를 불러온다. */		
			String adminId = principal.getName(); 
			
			if(adminId != null) {
				
				AdminDTO admin = adminService.findByAdminId(adminId);
				
				int adminNo = admin.getAdminNo();	
					
				vacationCommit.setAdminNo(adminNo);
				
			}
				
			/* HttpServletRequest를 이용해 vacationNo를 받아온다. */
			
			int vacationNo = Integer.parseInt(request.getParameter("vacationNo"));
			
			String confirmReason = request.getParameter("bossConfirmReason");
			
			/* 결재일자를 가져온다. */
			LocalDate now = LocalDate.now();
			java.sql.Date confirmDate = java.sql.Date.valueOf(now);
			
		
			/* 불러온 정보를 담아준다. */
			
			vacationCommit.setVacationNo(vacationNo);
			vacationCommit.setConfirmDate(confirmDate);
			vacationCommit.setConfirmReason(confirmReason);
			
			/* 휴가 테이블에 저장해준다. */
			vacationService.registVacationCommit(vacationCommit);
			
			/* 결재 내역 인서트 후 승인여부를 업데이트 한다. */
			vacationService.modifySecondConfirmYn(vacationNo);
	
			
			/* 휴가가 연차일 경우 사용 내역을 업데이트 한다. */
			if(request.getParameter("vacationName").equals("연차")) {
				
				int adminNo = Integer.parseInt(request.getParameter("adminNo"));
				
				adminService.modifyAnnualVacationUse(adminNo);
			
			}
			
			
			return "redirect:/admin/hrCard/boss/standbySecondVacationList";
			
		}catch(Exception e){
			
			return "redirect:/admin/error.html";
		}
	}
	
	/* 휴가 1차 반려 */
	@PostMapping("vacation/standbyVacationDetail/return")
	public String returnFirstVacation(ModelAndView mv, RedirectAttributes rttr, @ModelAttribute 
			VacationCommitDTO vacationCommit, Principal principal, HttpServletRequest request) {
	
		try {
			/*
			 * 휴가 반려의 경우
			 * 1차 승인여부, 2차 승인여부, 최종 승인여부 모두 기본값인 'N'을 변화시킬 필요가 없으며
			 * 오직 반려여부를 'Y'로 변화시킨다.
			 * 
			 * 휴가 승인과 마찬가지로 결재정보를 insert한 후, vacation 테이블의 반려여부를 Y로 update 한다.
			 */
			
			/* principal을 사용해 승인한 결재자의 정보를 불러온다. */
			String adminId = principal.getName(); 
			
			AdminDTO admin = adminService.findByAdminId(adminId);
			
			int adminNo = admin.getAdminNo();		
			
			/* HttpServletRequest를 이용해 vacationNo를 받아온다. */
			
			int vacationNo = Integer.parseInt(request.getParameter("vacationNo"));
			
			String confirmReason = request.getParameter("hrConfirmReason");
			
			/* 결재일자를 가져온다. */
			LocalDate now = LocalDate.now();
			java.sql.Date confirmDate = java.sql.Date.valueOf(now);
			
			/* 불러온 정보를 담아준다. */
			vacationCommit.setAdminNo(adminNo);
			vacationCommit.setVacationNo(vacationNo);
			vacationCommit.setConfirmDate(confirmDate);
			vacationCommit.setConfirmReason(confirmReason);
			
			vacationService.registVacationCommit(vacationCommit);
			
			/* 결재 내역 인서트 후 승인여부를 업데이트 한다. */
			vacationService.modifyReturnYn(vacationNo);
			
			
			return "redirect:/admin/hrCard/vacation/standbyFirstVacationList";
			
		}catch(Exception e){
			
			return "redirect:/admin/error.html";
		}
	}
	
	/* 휴가 2차 반려 */
	@PostMapping("boss/standbySecondVacationDetail/returnSecond")
	public String returnSecondVacation(ModelAndView mv, RedirectAttributes rttr, @ModelAttribute 
			VacationCommitDTO vacationCommit, Principal principal, HttpServletRequest request) {
	
		try {
			/*
			 * 휴가 반려의 경우
			 * 1차 승인여부, 2차 승인여부, 최종 승인여부 모두 기본값인 'N'을 변화시킬 필요가 없으며
			 * 오직 반려여부를 'Y'로 변화시킨다.
			 * 
			 * 휴가 승인과 마찬가지로 결재정보를 insert한 후, vacation 테이블의 반려여부를 Y로 update 한다.
			 */
			
			/* principal을 사용해 승인한 결재자의 정보를 불러온다. */
			String adminId = principal.getName(); 
			
			AdminDTO admin = adminService.findByAdminId(adminId);
			
			int adminNo = admin.getAdminNo();		
			
			/* HttpServletRequest를 이용해 vacationNo를 받아온다. */
			
			int vacationNo = Integer.parseInt(request.getParameter("vacationNo"));
			
			String confirmReason = request.getParameter("bossConfirmReason");
			
			/* 결재일자를 가져온다. */
			LocalDate now = LocalDate.now();
			java.sql.Date confirmDate = java.sql.Date.valueOf(now);
			
			/* 불러온 정보를 담아준다. */
			vacationCommit.setAdminNo(adminNo);
			vacationCommit.setVacationNo(vacationNo);
			vacationCommit.setConfirmDate(confirmDate);
			vacationCommit.setConfirmReason(confirmReason);
			
			vacationService.registVacationCommit(vacationCommit);
			
			/* 결재 내역 인서트 후 승인여부를 업데이트 한다. */
			vacationService.modifyReturnYn(vacationNo);
			
			
			return "redirect:/admin/hrCard/boss/standbySecondVacationList";
		
		}catch(Exception e){
			
			return "redirect:/admin/error.html";
		}
	}
}
