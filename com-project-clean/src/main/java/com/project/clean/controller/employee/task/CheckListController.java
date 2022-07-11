package com.project.clean.controller.employee.task;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("employee")
public class CheckListController {

	@GetMapping("checklist")
	public String checkListInsert(ModelAndView mv) {
		
		mv.setViewName("main");
		
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		return "/";
	}
}
