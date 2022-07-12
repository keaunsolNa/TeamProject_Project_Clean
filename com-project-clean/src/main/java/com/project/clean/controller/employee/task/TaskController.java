package com.project.clean.controller.employee.task;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.service.employee.task.TaskService;

@Controller
@RequestMapping("employee/task")
public class TaskController {
	
	private final TaskService taskService;
	
	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	

	@GetMapping("selectMyTask")
	public ModelAndView selectMyTask(ModelAndView mv, Principal principal) {

		String employeeId = principal.getName();
		List<ReservationInfoDTO> reservationList =  taskService.selectReservationListByEmployeeId(employeeId);
		
		System.out.println("Controller에서 가져온 결과값 : " +  reservationList);
		
		mv.addObject("reservationList", reservationList);
		mv.setViewName("employee/task/selectMyTask");
		return mv;
	}
	
}
