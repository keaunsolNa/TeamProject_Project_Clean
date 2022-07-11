package com.project.clean.controller.employee.checkList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("employee/checklist")
public class CheckListController {

	@GetMapping("insert")
	public String checkListInsert(ModelAndView mv) {
		
		mv.setViewName("main");
		
		
		return "employee/checklist/insert/checklist";
	}
	
	@PostMapping(value="insert")
	@ResponseBody
	public ModelAndView checkListInsert(ModelAndView mv, HttpServletRequest request) {
		
		String inputText = request.getParameter("jbHtml");
		System.out.println(inputText);
		
		
		return mv;
	}
} 
