package com.project.clean.controller.admin.checklist;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;
import com.project.clean.model.service.admin.checkList.AdminCheckListService;

@Controller
@RequestMapping("admin/checklist")
public class AdminCheckListController {
	
	private AdminCheckListService adminCheckListService;
	
	@Autowired 
	public AdminCheckListController(AdminCheckListService adminCheckListService) {
		this.adminCheckListService = adminCheckListService;
	}
	
	/* KS. 미처리 체크리스트 목록 조회 */
	@GetMapping("select")
	public String selectStandCheckList() {
		System.out.println("체크리스트 조회 확인");
		return "admin/checkList/selectStandCheckList";
	}
	
	/* KS.미처리 체크리스트 목록 조회 */
	@PostMapping(value ="select", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectStandCheckList(Principal principal) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String adminId = principal.getName();
		
		int parameter = 1;
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = adminCheckListService.selectCheckList(adminId, parameter);
		
		return mapper.writeValueAsString(checkList);
		
	}
	
	/* KS.미처리 체크리스트 상세 조회 */
	@GetMapping("selectDetails")
	public ModelAndView selectStandCheckListDetails(Principal principal, @RequestParam int re, @RequestParam String en,
			ModelAndView mv) {
		 
		String adminName = principal.getName();
		int reservationNo = re;
		int parameter = 1;
		CheckListDTO checkList = adminCheckListService.selectCheckListDetails(adminName, reservationNo, parameter);
		
		System.out.println(checkList.getCheckHTML());
		
			mv.addObject("checkList", checkList);
			mv.addObject("employeeName", en);
			mv.setViewName("admin/checkList/selectStandCheckListDetails");
		
		return mv;
		
	}
	
	/* KS.체크리스트 반려 */
	@PostMapping(value = "denial", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String denialCheckList(HttpServletRequest request) throws JsonProcessingException {
		int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
		String htmlData = request.getParameter("jbHtml");
		int BlackListYn = Integer.parseInt(request.getParameter("blackYn"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		CheckListDTO checkList = new CheckListDTO();
		checkList.setCheckHTML(htmlData);
		checkList.setCheckReservationNo(reservationNo);
		
		if(BlackListYn == 1) {
			checkList.setCheckStatus("D");
		} else if(BlackListYn == 2) {
			checkList.setCheckStatus("B");
		}
		
		int result = adminCheckListService.modifyCheckList(checkList);
		
		return mapper.writeValueAsString(result);
	}
	
	/* KS. 반려 체크리스트 목록 조회 */
	@GetMapping("denial/select")
	public String denialCheckListSelect() {
		
		return "admin/checkList/selectDenialCheckList";
	}
	
	/* KS. 반려 체크리스트 목록 조회 */
	@PostMapping(value = "denial/select", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String denialCheckListSelect(Principal principal) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String adminId = principal.getName();
		int parameter = 2;
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = adminCheckListService.selectCheckList(adminId, parameter);

		return mapper.writeValueAsString(checkList);
		
	}
	
	/* KS. 반려 체크리스트 상세 조회 */
	@GetMapping("denialselectDetails")
	public ModelAndView selectDenialCheckListDetails(Principal principal, ModelAndView mv, @RequestParam int re, @RequestParam String en) {
		
		String adminName = principal.getName();
		int reservationNo = re;
		int parameter = 2;
		
		CheckListDTO checkList = adminCheckListService.selectCheckListDetails(adminName, reservationNo, parameter);

		mv.addObject("checkList", checkList);
		mv.addObject("employeeName", en);
		mv.setViewName("admin/checkList/selectDenialCheckListDetails");
		
		return mv;
		
	}
	
	/* KS. 승인 체크리스트 목록 조회 */
	@GetMapping("accept/select")
	public String acceptCheckListSelect() {
		return "admin/checkList/selectAcceptCheckList";
	}
	
	/* KS. 승인 체크리스트 목록 조회 */
	@PostMapping(value = "accept/select", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String acceptCheckListSelect(Principal principal) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String adminId = principal.getName();
		int parameter = 3;
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = adminCheckListService.selectCheckList(adminId, parameter);
		
		return mapper.writeValueAsString(checkList);
	}
	
	/* KS. 승인 체크리스트 상세 조회 */
	@GetMapping("acceptSelectDetails")
	public ModelAndView selectAcceptCheckListDetails(Principal principal, ModelAndView mv, @RequestParam int re ) {
		
		String adminName = principal.getName();
		int reservationNo = re;
		int parameter = 2;
		
		CheckListDTO checkList = adminCheckListService.selectCheckListDetails(adminName, reservationNo, parameter);

		mv.addObject("checkList", checkList);
		mv.setViewName("admin/checkList/selectAcceptCheckListDetails");
		
		return mv;
		
	}
	
	/* KS. 체크리스트 승인 */
	@PostMapping(value = "accept", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String acceptCheckList(HttpServletRequest request) throws JsonProcessingException {
		int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
		String htmlData = request.getParameter("jbHtml");
		
		ObjectMapper mapper = new ObjectMapper();
		
		CheckListDTO checkList = new CheckListDTO();
		checkList.setCheckHTML(htmlData);
		checkList.setCheckReservationNo(reservationNo);
		checkList.setCheckStatus("A");
		
		int result = adminCheckListService.modifyCheckList(checkList);
		return mapper.writeValueAsString(result);
	}
	
	/* KS. 블랙된 체크리스트 조회 */
	@GetMapping(value="black/select")
	public String blackCheckListSelect() {
		return "admin/checkList/selectBlackCheckList";
	}
	
	/* KS. 블랙된 체크리스트 조회 */
	@PostMapping(value = "black/select", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String blackCheckListSelect(Principal principal) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		String adminId = principal.getName();
		int parameter = 4;
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = adminCheckListService.selectCheckList(adminId, parameter);
		
		return mapper.writeValueAsString(checkList);
	}
	
	/* KS. 블랙된 체크리스트 상세 조회 */
	@GetMapping("blackselectDetails")
	public ModelAndView selectBlackCheckListDetails(Principal principal, ModelAndView mv, @RequestParam int re ) {
		
		String adminName = principal.getName();
		int reservationNo = re;
		int parameter = 3;
		
		CheckListDTO checkList = adminCheckListService.selectCheckListDetails(adminName, reservationNo, parameter);

		mv.addObject("checkList", checkList);
		mv.setViewName("admin/checkList/selectBlackCheckListDetails");
		
		return mv;
		
	}
	
}	
