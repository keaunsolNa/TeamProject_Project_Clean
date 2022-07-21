package com.project.clean.controller.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.clean.model.dto.joinDTO.EmployeeAndAllDTO;
import com.project.clean.model.service.admin.AdminEmployeeService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final AdminEmployeeService employeeService;
	
	@Autowired
	EmployeeController(AdminEmployeeService employeeService){
			this.employeeService = employeeService;
		}
	
	
	@PostMapping("empMainPage")
	public void EmployeeMainPage() {}
	
	/* (관리자가)직원 수정 페이지로 이동 */
	@GetMapping("/modify/employee/{empNo}")
	public String adminModifyEmployee(@PathVariable int empNo, Model mv) {

		EmployeeAndAllDTO employeeDTO = employeeService.selectOneEmployee(empNo);

		String[] seperatephone = employeeDTO.getEmployeePhone().split("-");

		String firstPhoneNumber = seperatephone[0];
		String middlePhoneNumber = seperatephone[1];
		String lastPhoneNumber = seperatephone[2];

		Map<String, String> phone = new HashMap<>();
		phone.put("firstPhoneNumber", firstPhoneNumber);
		phone.put("middlePhoneNumber", middlePhoneNumber);
		phone.put("lastPhoneNumber", lastPhoneNumber);

		String[] seperateAddress = employeeDTO.getEmployeeAddress().split("@");
		String addressNo = seperateAddress[0];
		String address = seperateAddress[1];
		String addressDetail = seperateAddress[2];

		Map<String, String> addressMap = new HashMap<>();
		addressMap.put("addressNo", addressNo);
		addressMap.put("address", address);
		addressMap.put("addressDetail", addressDetail);

		String[] seperateEmail = employeeDTO.getEmployeeEmail().split("@");
		String email = seperateEmail[0];
		String domain = seperateEmail[1];

		Map<String, String> emailMap = new HashMap<>();
		emailMap.put("email", email);
		emailMap.put("domain", domain);

		employeeDTO.setEmployeePhone(firstPhoneNumber + "-" + middlePhoneNumber + "-" + lastPhoneNumber);

		mv.addAttribute("phone", phone);
		mv.addAttribute("address", addressMap);
		mv.addAttribute("email", emailMap);
		mv.addAttribute("employee", employeeDTO);

		return "/employee/modifyEmployee";
	}
	
	@GetMapping("/regist/employee/findMiddlePhone")
	@ResponseBody
	public List<String> findMiddlePhoneNum(Model mv) {

		List<EmployeeAndAllDTO> employeeList = employeeService.findMiddlePhoneNum();
		List<String> middlePhoneList = new ArrayList<>();

		for (int i = 0; i < employeeList.size(); i++) {
			String middlePhoneChange = employeeList.get(i).getEmployeePhone();

			if (employeeList.get(i).getEmployeePhone().length() == 12) {
				String middlePhoneNum = middlePhoneChange.replaceAll("-", "");
				middlePhoneList.add(middlePhoneNum);

			} else if (employeeList.get(i).getEmployeePhone().length() == 13) {
				String middlePhoneNum = middlePhoneChange.replaceAll("-", "");
				middlePhoneList.add(middlePhoneNum);
			}
		}
		for (String e : middlePhoneList) {
			System.out.println("eeeeeeeeeeeeasdeee" + e);
		}
		return middlePhoneList;
	}
	
}

