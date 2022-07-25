package com.project.clean.controller.employee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	/* (직원이)직원 수정 페이지로 이동 */
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
		mv.addAttribute("fullPhone", firstPhoneNumber+middlePhoneNumber+lastPhoneNumber);
		mv.addAttribute("address", addressMap);
		mv.addAttribute("email", emailMap);
		mv.addAttribute("employee", employeeDTO);

		return "/employee/modifyEmployee";
	}
	
	/* (직원이)직원 정보 수정 */
	@PostMapping("/modify/employee")
	public String modifyEmployee(EmployeeAndAllDTO employeeDTO, String oldSaveRoot, String oldSaveName, Model mv,
			@RequestParam("picture") MultipartFile singleFile, HttpServletRequest request, String status) {

		String root = request.getSession().getServletContext().getRealPath("/");
		String filePath = root + "adminEmployeePicture";

		if ("modify".equals(status)) {
			if (!singleFile.getOriginalFilename().isEmpty()) {

				String originFileName = singleFile.getOriginalFilename();
				System.out.println("원본 이름 : " + originFileName);
				String ext = originFileName.substring(originFileName.lastIndexOf("."));

				String saveName = UUID.randomUUID().toString().replace("-", "") + ext;
				System.out.println("변경한 이름 : " + saveName);

				/* 3. 파일을 저장한다. */
				try {
					singleFile.transferTo(new File(filePath + "/" + saveName));
					employeeDTO.setEmployeePictureSaveName(saveName);
					employeeDTO.setEmployeePictureSaveRoot(filePath);

					new File(oldSaveRoot + "/" + oldSaveName).delete();
					/* employeeDTO.setEmployeePictureThumbnail(saveName); */
					/* DB에 업로드한 파일의 정보를 저장하는 비즈니스 로직 수행 */

					mv.addAttribute("message", "파일 업로드 성공!");
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();

					/* 실패 시 파일 삭제 */
					new File(filePath + "/" + saveName).delete();
					mv.addAttribute("message", "파일 업로드 실패!");
				}
			} else {

				/*
				 * null이면 사진 바꾸지말고 다시 화면으로 가 (들어와서 사진 변경 안하고 수정확인 눌렀을 경우 file은 null이기 때문에 대비함)
				 */
			}

			employeeService.modifyEmployee(employeeDTO);

			mv.addAttribute("employeeDTO", employeeDTO);

		} else if ("delete".equals(status)) {

			/* 1. 사용자가 delete 누르면 DB가서 해당 직원 조회하고 */
			EmployeeAndAllDTO empDTO = employeeService.deletePicture(employeeDTO.getEmployeeNo());

			/* 4. 해당 파일 삭제 */
			new File(filePath + "/" + empDTO.getEmployeePictureSaveName()).delete();

		}

		return "redirect:/member/selectEmployee/move";
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
		return middlePhoneList;
	}
	
}
