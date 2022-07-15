package com.project.clean.controller.admin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.clean.controller.admin.test.TestAddressDTO;
import com.project.clean.controller.admin.test.TestAddressEntity;
import com.project.clean.controller.admin.test.TestAddressRepository;
import com.project.clean.controller.admin.test.TestEmployeeDTO;
import com.project.clean.controller.admin.test.TestEmployeeEntity;
import com.project.clean.controller.admin.test.TestRepository;

@Controller
@RequestMapping("admin")
public class AdminMainController {
	
	private final TestRepository testRepository;
	private final TestAddressRepository testAddressRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	AdminMainController(TestRepository testRepository, TestAddressRepository testAddressRepository,ModelMapper modelMapper){
		this.testRepository = testRepository;
		this.testAddressRepository = testAddressRepository;
		this.modelMapper = modelMapper;
	}

		@PostMapping("adminMainPage")
		public void AdminMainpage() {}
		
		@GetMapping("test")
		public void test(Model mv) {
			
			TestEmployeeEntity employeeEntity = testRepository.findById(1).get();
			TestAddressEntity addressEntity = testAddressRepository.findById(1).get();
			
			TestEmployeeDTO employee = modelMapper.map(employeeEntity, TestEmployeeDTO.class);
			TestAddressDTO address = modelMapper.map(addressEntity, TestAddressDTO.class);
			
			mv.addAttribute("employee", employee);
			mv.addAttribute("address", address);
		}
}
