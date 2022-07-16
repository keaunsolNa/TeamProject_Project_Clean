package com.project.clean.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminMainController {

		@PostMapping("adminMainPage")
		public void AdminMainpage() {}
}
