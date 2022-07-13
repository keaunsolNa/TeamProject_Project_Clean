package com.project.clean.controller.employee.checkList;

import java.security.Principal;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.service.employee.task.TaskService;

@Controller
@RequestMapping("employee/checklist")
public class CheckListController {

	private TaskService taskService;
	
	@Autowired
	public CheckListController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping("insert")
	public String checkListInsert(ModelAndView mv) {
		
		mv.setViewName("main");
		
		
		return "employee/checklist/insert/checklist";
	}
	
	@PostMapping(value="insert")
	public String checkListInsert(RedirectAttributes rttr, HttpServletRequest request, Principal principal) {
		
		String inputText = request.getParameter("jbHtml");
		String userId = principal.getName();
		String re = request.getParameter("re");
		
		System.out.println("넘어온 값 확인 : " + re );
		int employeeNo = taskService.selectEmployeeNo(userId);
		
		System.out.println("로그인 한 회원 번호 : " + employeeNo);
		
		CheckListDTO checkListDTO = new CheckListDTO();

		checkListDTO.setAdminNo(employeeNo);
		checkListDTO.setCheckHTML(inputText);
		checkListDTO.setCheckStatus("N");
		
		/* 테스트 데이터. 추후 수정 요망 */
		checkListDTO.setCheckReservationNo(5);

		System.out.println("수행 전 ");
		int result = taskService.registNewCheckList(checkListDTO);
		System.out.println("수행 후 ");
		
		LocalDate now = LocalDate.now();
		
		rttr.addFlashAttribute("resultMessage", now + " 시에 업무를 시작하셨습니다. 업무 완료 후 작성 버튼을 눌러주세요.");
		
		System.out.println("rttr : " + rttr);
		
		return "common/main";
	}
} 
