package com.project.clean.controller.employee.checkList;

import java.security.Principal;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.service.employee.task.TaskService;

@Controller
@RequestMapping("employee/checkList")
public class CheckListController {

	private TaskService taskService;
	private int reservationNo;
	
	@Autowired
	public CheckListController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("start")
	public String checkListInsert(ModelAndView mv, HttpServletRequest request, @RequestParam int re) {
			
		System.out.println(re);
		reservationNo = re;
		mv.setViewName("main");
		return "employee/checkList/startChecklist";
	}
	
	@PostMapping(value="start")
	public ModelAndView checkListInsert(RedirectAttributes rttr, HttpServletRequest request, Principal principal, ModelAndView mv) {
		
		String inputText = request.getParameter("jbHtml");
		String userId = principal.getName();
		
		int employeeNo = taskService.selectEmployeeNo(userId);
		
		System.out.println("로그인 한 직원 번호 : " + employeeNo);
		System.out.println("예약 번호 : " + reservationNo);
		
		CheckListDTO checkListDTO = new CheckListDTO();

		checkListDTO.setAdminNo(employeeNo);
		checkListDTO.setCheckHTML(inputText);
		checkListDTO.setCheckStatus("N");
		checkListDTO.setCheckReservationNo(reservationNo);

		int result = taskService.registNewCheckList(checkListDTO);
		
		LocalDate now = LocalDate.now();
		
		
		return mv;
	}
	
	@GetMapping("insert")
	public ModelAndView selectCheckList(Principal principal, @ModelAttribute ModelAndView mv) {
		
		String userId = principal.getName();
		
		CheckListDTO checklistDTO = taskService.selectScheckList(userId);
		checklistDTO.getCheckHTML();
		mv.addObject("checkList", checklistDTO);
		mv.setViewName("/employee/checkList/insertCheckList");
		
		return mv;
		
	}
	
	@PostMapping("update")
	public String updateCheckList(HttpServletRequest request, ModelAndView mv) {
		
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println(request.getParameter("reservationNo"));
		int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
				
		System.out.println(request.getParameter("jbHtml"));
		CheckListDTO checkListDTO =new CheckListDTO();
		checkListDTO.setCheckReservationNo(reservationNo);
		checkListDTO.setCheckHTML(request.getParameter("jbHtml"));
		int result = taskService.updateCheckList(checkListDTO);
		
		
		return "/employee/task/selectMyTask";
	}
} 
