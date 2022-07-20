package com.project.clean.controller.employee.checkList;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;
import com.project.clean.model.service.employee.checkList.CheckListService;

@Controller
@RequestMapping("employee/checkList")
public class CheckListController {

	private CheckListService checkListService;
	
	@Autowired
	public CheckListController(CheckListService checkListService) {
		this.checkListService = checkListService;
	}
	
	/* KS. 본인 예약 업무 리스트 조회 */
	@GetMapping("selectMyCheckList")
	public void selectMyTask() {
		
	}

	/* KS. 본인 예약 리스트 조회 */
	@PostMapping(value = "selectMyCheckList", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String selectMyTask(Principal principal) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		String employeeId = principal.getName();
		List<ReservationInfoDTO> reservationList =  checkListService.selectReservationListByEmployeeId(employeeId);
		System.out.println("TESTTTTTTTTTT" + reservationList);
		System.out.println("TESTTTTTTTTTTTTTTTTTT");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		mapper.setDateFormat(dateFormat); 
		return mapper.writeValueAsString(reservationList);
	}
	
	
	/* KS. 반려 및 대기 체크리스트 목록 조회 */
	@GetMapping("denial/select")
	public String selectDenialCheckList() {
		return "employee/checkList/selectDenialCheckList";
		
	}
	
	/* KS. 반려 및 대기 체크리스트 목록 조회 */
	@PostMapping(value = "denial/select", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectDenialCheckList(Principal principal) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String employeeId = principal.getName();
		int parameter = 1;
		System.out.println("TEST");
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = checkListService.selectCheckList(employeeId, parameter);
		System.out.println("TEST");
		
		return mapper.writeValueAsString(checkList);
		
	}
	
	/* KS. 업무 시작 GetMapping 및 작성 중 체크리스트 유무 확인*/
	@GetMapping("start")
	public ModelAndView checkListInsert(ModelAndView mv, HttpServletRequest request, @RequestParam int re) {
			
		int reservationNo = re;
		
		int result = checkListService.selectCheckListIsNotNull();
		
		if(result > 0) {

			mv.addObject("Message", "작성 중인 체크리스트가 있습니다.");
			mv.setViewName("employee/checkList/selectMyCheckList");
			return mv;
			
		} else {  
			
			mv.setViewName("employee/checkList/startChecklist");
			mv.addObject("resNo", reservationNo);
			
			return mv;
		}
		
		
		
	}
	
	/* KS. 업무 시작 후 빈 체크리스트 폼 등록 */
	@PostMapping(value="start")
	public ModelAndView checkListInsert(RedirectAttributes rttr, HttpServletRequest request, Principal principal
			, ModelAndView mv, @RequestParam int resNo) {
		
		String inputText = request.getParameter("jbHtml");
		String userId = principal.getName();
		
		int employeeNo = checkListService.selectEmployeeNo(userId);
		
		CheckListDTO checkListDTO = new CheckListDTO();

		checkListDTO.setCheckHTML(inputText);
		checkListDTO.setCheckStatus("N");
		checkListDTO.setCheckReservationNo(resNo);

		int result = checkListService.registNewCheckList(checkListDTO);
        
		LocalDate now = LocalDate.now();

		rttr.addFlashAttribute("Message", now + " 시에 업무를 시작하셨습니다. 업무 완료 후 작성 버튼을 눌러주세요.");
        
        mv.addObject("Message", rttr);
        mv.setViewName("/employee/checkList/selectMyCheckList");
        
		return mv;
	}

	
	/* KS. 업무 종료 후 체크리스트 작성 */
	@GetMapping("insert")
	public ModelAndView InsertCheckList(Principal principal, @ModelAttribute ModelAndView mv) {
		
		String userId = principal.getName();
		
		CheckListDTO checklistDTO = checkListService.InsertCheckList(userId);
		
		if(null == checklistDTO) {
			
			mv.addObject("Message", "작성 가능한 체크리스트가 없습니다.");
			mv.setViewName("/employee/checkList/selectMyCheckList");

		} else {
		
			checklistDTO.getCheckHTML();
			mv.addObject("checkList", checklistDTO);
			mv.addObject("userId", userId);
			mv.setViewName("/employee/checkList/insertCheckList");
		
		}
		
		return mv;
		
	}
	
	/* KS. 체크리스트 작성 및 등록 */
	@PostMapping("update")
	public String updateCheckList(HttpServletRequest request) {
		
		int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));

		CheckListDTO checkListDTO = new CheckListDTO();
		checkListDTO.setCheckReservationNo(reservationNo);
		checkListDTO.setCheckHTML(request.getParameter("jbHtml"));
		checkListDTO.setCheckStatus("R");
		
		int result = checkListService.updateCheckList(checkListDTO);
		
		return "/employee/checkList/selectMyCheckList";
	}
	
	/* KS. 반려 체크리스트 상세 조회 */
	@GetMapping("denialSelectDetails")
	public ModelAndView selectDenialCheckListDetails(Principal principal, ModelAndView mv, @RequestParam int re ) {
		
		String adminName = principal.getName();
		int reservationNo = re;
		
		CheckListDTO checkList = checkListService.selectCheckListDetails(reservationNo);

		mv.addObject("checkList", checkList);
		mv.addObject("userId", adminName);
		mv.setViewName("employee/checkList/selectDenialCheckListDetails");
		return mv;
		
	}
	
	/* KS. 반려 체크리스트 사유서 제출 */
	@PostMapping("submmit")
	public String updateDenialCheckList(HttpServletRequest request) {
		
		int reservationNo = Integer.parseInt(request.getParameter("reservationNo"));
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println(reservationNo);
		System.out.println("TEST");
		System.out.println("TEST");
		System.out.println("TEST");
		
		CheckListDTO checkListDTO = new CheckListDTO();
		
		checkListDTO.setCheckReservationNo(reservationNo);
		checkListDTO.setCheckHTML(request.getParameter("jbHtml"));
		checkListDTO.setCheckStatus("D");
		
		int result = checkListService.updateCheckList(checkListDTO);
		
		return "/employee/checkList/selectMyCheckList";
	}
	
	/* KS. 승낙 체크리스트 조회 */
	@GetMapping("accept/select")
	public String selectAcceptCheckList() {
		
		return "/employee/checkList/selectAcceptCheckList";
	}
	
	/* KS. 승낙 체크리스트 조회 */
	@PostMapping(value = "accept/select", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectAcceptCheckList(Principal principal) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String employeeId = principal.getName();
		int parameter = 2;
		
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = checkListService.selectCheckList(employeeId, parameter);
		
		return mapper.writeValueAsString(checkList);
		
	}
	
	/* KS. 승낙 체크리스트 상세 조회 */
	@GetMapping("slectacceptDetails")
	public ModelAndView selectAcceptCheckListDetails(Principal principal, ModelAndView mv, @RequestParam int re ) {
		
		String adminName = principal.getName();
		int reservationNo = re;
		
		CheckListDTO checkList = checkListService.selectCheckListDetails(reservationNo);

		mv.addObject("checkList", checkList);
		
		mv.setViewName("employee/checkList/selectAcceptlCheckListDetails");
		
		return mv;
		
	}
	
	/* KS. 블랙된 체크리스트 조회 */
	@GetMapping("black/select")
	public String selectBlackCheckList() {
		
		return "/employee/checkList/selectBlackCheckList";
	}
	
	/* KS. 블랙된 체크리스트 조회 */
	@PostMapping(value = "black/select", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String selectBlackCheckList(Principal principal) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String employeeId = principal.getName();
		int parameter = 3;
		
		List<CheckListAndReservationInfoAndEmployeeDTO> checkList = checkListService.selectCheckList(employeeId, parameter);
		
		return mapper.writeValueAsString(checkList);
		
	}
	
	/* KS. 블랙된 체크리스트 상세 조회 */
	@GetMapping("slectBlackDetails")
	public ModelAndView selectBlackCheckListDetails(Principal principal, ModelAndView mv, @RequestParam int re ) {
		
		String adminName = principal.getName();
		int reservationNo = re;
		
		CheckListDTO checkList = checkListService.selectCheckListDetails(reservationNo);

		mv.addObject("checkList", checkList);
		
		mv.setViewName("employee/checkList/selectBlackCheckListDetails");
		
		return mv;
		
	}
	
} 
