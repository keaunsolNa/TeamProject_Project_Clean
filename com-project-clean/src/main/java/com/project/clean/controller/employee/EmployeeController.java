package com.project.clean.controller.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("employee")
public class EmployeeController {

	@PostMapping("empMainPage")
	public void EmployeeMainPage() {}
}
