package com.project.clean.controller.employee.checkList;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;
import com.project.clean.model.service.employee.checkList.CheckListService;

@Controller
@RequestMapping("employee/checkList")
public class CheckListController {

	private CheckListService checkListService;
	private int reservationNo;
	
	@Autowired
	public CheckListController(CheckListService checkListService) {
		this.checkListService = checkListService;
	}
	
	@GetMapping("selectMyCheckList")
	public void selectMyTask() {
		
	}
  
	@PostMapping(value = "selectMyCheckList", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectMyTask(Principal principal) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		String employeeId = principal.getName();
		List<ReservationInfoDTO> reservationList =  checkListService.selectReservationListByEmployeeId(employeeId);
		 
		System.out.println("Controller에서 가져온 결과값 : " +  reservationList);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(dateFormat); 
		return mapper.writeValueAsString(reservationList);
	}
	
	
	@GetMapping("denial/select")
	public String selectDenialCheckList() {
		return "employee/checkList/selectDenialCheckList";
		
	}
	
	@PostMapping(value = "denial/select", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectDenialCheckList(Principal principal) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String employeeId = principal.getName();
		int parameter = 1;
		
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = checkListService.selectDenialCheckList(employeeId, parameter);
		
		return mapper.writeValueAsString(checkList);
		
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
		
		int employeeNo = checkListService.selectEmployeeNo(userId);
		
		System.out.println("로그인 한 직원 번호 : " + employeeNo);
		System.out.println("예약 번호 : " + reservationNo);
		
		CheckListDTO checkListDTO = new CheckListDTO();

		checkListDTO.setCheckHTML(inputText);
		checkListDTO.setCheckStatus("N");
		checkListDTO.setCheckReservationNo(reservationNo);

		int result = checkListService.registNewCheckList(checkListDTO);
        
		LocalDate now = LocalDate.now();

		rttr.addFlashAttribute("resultMessage", now + " 시에 업무를 시작하셨습니다. 업무 완료 후 작성 버튼을 눌러주세요.");
        
        mv.addObject("resultMessage", rttr);
        mv.setViewName("/employee/checkList/selectMyCheckList");
        
		
		return mv;
	}
	
	@GetMapping("insert")
	public ModelAndView selectCheckList(Principal principal, @ModelAttribute ModelAndView mv) {
		
		String userId = principal.getName();
		
		CheckListDTO checklistDTO = checkListService.selectCheckList(userId);
		
		if(null == checklistDTO) {
			mv.addObject("resultMessage", "작성 가능한 체크리스트가 없습니다.");
			mv.setViewName("/employee/checkList/selectMyCheckList");
		} else {
			checklistDTO.getCheckHTML();
			mv.addObject("checkList", checklistDTO);
			mv.setViewName("/employee/checkList/insertCheckList");
		}
		
		return mv;
		
	}
	
	@PostMapping("update")
	public String updateCheckList(HttpServletRequest request, ModelAndView mv) {
		
		int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));

		CheckListDTO checkListDTO = new CheckListDTO();
		checkListDTO.setCheckReservationNo(reservationNo);
		checkListDTO.setCheckHTML(request.getParameter("jbHtml"));
		checkListDTO.setCheckStatus("R");
		
		int result = checkListService.updateCheckList(checkListDTO);
		
		return "/employee/checkList/selectMyCheckList";
	}
	
	@GetMapping("denialselectDetails")
	public ModelAndView selectDenialCheckListDetails(Principal principal, ModelAndView mv, @RequestParam int re ) {
		
		String adminName = principal.getName();
		int reservationNo = re;
		
		CheckListDTO checkList = checkListService.selectDenialCheckListDetails(reservationNo);

		mv.addObject("checkList", checkList);
		
		mv.setViewName("employee/checkList/selectDenialCheckListDetails");
		System.out.println("반환전");
		return mv;
		
	}
} 
