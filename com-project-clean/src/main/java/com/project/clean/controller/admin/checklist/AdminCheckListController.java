package com.project.clean.controller.admin.checklist;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;
import com.project.clean.model.service.admin.checkList.AdminCheckListService;

@Controller
@RequestMapping("admin/checklist")
public class AdminCheckListController {
	
	private AdminCheckListService adminCheckListService;
	
	@Autowired 
	public AdminCheckListController(AdminCheckListService adminCheckListService) {
		this.adminCheckListService = adminCheckListService;
	}
	
	@GetMapping("select")
	public String selectStandCheckList() {
		System.out.println("체크리스트 조회 확인");
		return "admin/checkList/selectStandCheckList";
	}
	
	@PostMapping(value ="select", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectStandCheckList(Principal principal, HttpServletRequest request,
			ModelAndView mv) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String adminId = principal.getName();
		
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = adminCheckListService.selectAllStandCheckList();
		
		System.out.println("출력 값 확인~~" + checkList);
		
		return mapper.writeValueAsString(checkList);
		
	}
	/*
	 * @PostMapping(value = "select", produces="application/json; charset=UTF-8")
	 * 
	 * @ResponseBody public String selectStandCheckList(Principal principal) {
	 * 
	 * ObjectMapper mapper = new ObjectMapper();
	 * 
	 * String employeeId = principal.getName(); List<ReservationInfoDTO>
	 * reservationList = taskService.selectReservationListByEmployeeId(employeeId);
	 * 
	 * System.out.println("Controller에서 가져온 결과값 : " + reservationList);
	 * 
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 * 
	 * mapper.setDateFormat(dateFormat); return
	 * mapper.writeValueAsString(reservationList); }
	 */
	
} 