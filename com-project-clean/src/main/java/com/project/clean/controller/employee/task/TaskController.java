package com.project.clean.controller.employee.task;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.service.employee.task.TaskService;

@Controller
@RequestMapping("employee/task")
public class TaskController {
	
	private TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	 
	@GetMapping("selectMyTask")
	public void selectMyTask() {
		
	}
  
	@PostMapping(value = "selectMyTask", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectMyTask(Principal principal, HttpServletResponse response,
			ModelAndView mv) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		String employeeId = principal.getName();
		List<ReservationInfoDTO> reservationList =  taskService.selectReservationListByEmployeeId(employeeId);
		 
		System.out.println("Controller에서 가져온 결과값 : " +  reservationList);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		mapper.setDateFormat(dateFormat); 
		return mapper.writeValueAsString(reservationList);
	}
}	
