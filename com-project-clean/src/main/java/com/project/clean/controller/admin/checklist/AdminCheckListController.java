package com.project.clean.controller.admin.checklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.clean.model.service.employee.task.TaskService;

@Controller
@RequestMapping("admin/checkList")
public class AdminCheckListController {
	/*
	 * private TaskService taskService;
	 * 
	 * @Autowired public CheckListController(TaskService taskService) {
	 * this.taskService = taskService; }
	 */
	
	@GetMapping("select")
	public void selectStandCheckList() {
	  
		
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
