package com.project.clean.controller.admin.checklist;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.service.admin.checkList.AdminCheckListService;
import com.project.clean.model.service.pay.PayService;

@Controller
@RequestMapping("admin/checklist")
public class AdminCheckListController {
	
	private AdminCheckListService adminCheckListService;
	private PayService payService;
	private final int maxLine = 5;
	
	@Autowired 
	public AdminCheckListController(AdminCheckListService adminCheckListService,PayService payService) {
		this.adminCheckListService = adminCheckListService;
		this.payService = payService; 
	}
	
	/* KS. 미처리 체크리스트 목록 조회 */
	@GetMapping("select")
	public String selectStandCheckList() {
		return "admin/checkList/selectStandCheckList";
	}
	
	/* KS.미처리 체크리스트 목록 조회 */
	@PostMapping(value = "select")
	@ResponseBody
	public Map<String, Object> selectStandCheckList(Principal principal, String category, String categoryValue,
			@PageableDefault(sort = "checkReservationNo", size = maxLine) Pageable pageable) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		String adminId = principal.getName();
		int parameter = 1;
		
		map = adminCheckListService.selectCheckList(adminId, parameter, category, categoryValue, pageable);
		
		return map;
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
	public ModelAndView denialCheckListSelect(ModelAndView mv) {
		
		List<CheckListDTO> checkList = adminCheckListService.selectAllCheckList(); 
		
		mv.addObject("CheckListArray", checkList);
		mv.setViewName("admin/checkList/selectDenialCheckList");
		return mv;
	}
	
	/* KS. 반려 체크리스트 목록 조회 */
	@PostMapping(value = "denial/select")
	@ResponseBody
	public Map<String, Object> denialCheckListSelect(Principal principal, String category, String categoryValue,
			@PageableDefault(size = maxLine)
			@SortDefault.SortDefaults({
				@SortDefault(sort = "checkStatus", direction = Sort.Direction.DESC), 
				@SortDefault(sort = "checkReservationNo", direction = Sort.Direction.DESC)
			})Pageable pageable)
					throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		String adminId = principal.getName();
		int parameter = 2;
		
		map = adminCheckListService.selectCheckList(adminId, parameter, category, categoryValue, pageable);
		
		return map;
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
	@PostMapping(value = "accept/select")
	@ResponseBody
	public Map<String, Object> acceptCheckListSelect(Principal principal, String category, String categoryValue,
			@PageableDefault(sort = "checkReservationNo", size = maxLine, direction = Sort.Direction.DESC) Pageable pageable) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		String adminId = principal.getName();
		int parameter = 3;
		
		map = adminCheckListService.selectCheckList(adminId, parameter, category, categoryValue, pageable);
		
		return map;
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
		
		
		/* 다영 - 체크리스트가 승인되었을때 바로 급여를 지급함 ------------------------------------------------------------------*/
		// 1. 예약번호로 원래 급여(계산전 급여)를 가져옴
		ReservationInfoDTO reservationInfo = payService.findByTotalPaymentByReservationNo(reservationNo);
		int totalPayment = reservationInfo.getTotalPayment();
		
		// 2. 부가요금을 가져옴
		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		int insurance = surchargeList.get(0).getSurchargeInsurance(); 			  // 4대보험
		int commission = surchargeList.get(0).getSurchargeCommission();           // 수수료
		
		// 3. 예약번호로 예약별 직원의 목록을 가져옴 
		List<ApplyEmployeeDTO> applyEmployee = payService.findByApplyReservationNo(reservationNo);
		System.out.println("예약별 직원 리스트 개수" + applyEmployee.size());
		
		// 4. 청소를 맡은 직원이 한명일때, 직원이 2명일때 나누어서 계산
		if(applyEmployee.size() == 1) {
			int applyReservationNo = applyEmployee.get(0).getApplyReservationNo();
			int applyEmployeeNo = applyEmployee.get(0).getApplyEmployeeNo();
			
			// 4대보험과 수수료를 뺀 최종 급여
			int payEmployeeFinalSalary = totalPayment - (totalPayment * insurance / 100) - (totalPayment * commission / 100);
			
			// 5. 급여 지급하기(직원번호, 예약번호, 최종급여 service로 보냄)
			payService.registEmployeePay(applyReservationNo, applyEmployeeNo, payEmployeeFinalSalary);
			
		}else {
			int applyReservationNo = applyEmployee.get(0).getApplyReservationNo();
			int applyEmployeeNo = applyEmployee.get(0).getApplyEmployeeNo();
			int applyReservationNo2 = applyEmployee.get(1).getApplyReservationNo();
			int applyEmployeeNo2 = applyEmployee.get(1).getApplyEmployeeNo();
			
			// 4대보험과 수수료를 뺀 최종 급여(2명일때 나누기 2를 추가로한다)
			int payEmployeeFinalSalary = (totalPayment - (totalPayment * insurance / 100) - (totalPayment * commission / 100))/2 ;
			
			// 5. 급여 지급하기(직원번호, 예약번호, 최종급여 service로 보냄)
			payService.registEmployeePay(applyReservationNo, applyEmployeeNo, payEmployeeFinalSalary);
			
			// 한명 더
			payService.registEmployeePay(applyReservationNo2, applyEmployeeNo2, payEmployeeFinalSalary);
		}
		
		
		
		int result = adminCheckListService.modifyCheckList(checkList);
		return mapper.writeValueAsString(result);
	}
	
	/* KS. 블랙된 체크리스트 조회 */
	@GetMapping(value="black/select")
	public String blackCheckListSelect() {
		return "admin/checkList/selectBlackCheckList";
	}
	
	/* KS. 블랙된 체크리스트 조회 */
	@PostMapping(value = "black/select")
	@ResponseBody
	public Map<String, Object> blackCheckListSelect(Principal principal, String category, String categoryValue,
				@PageableDefault(sort = "checkReservationNo", size = maxLine, direction = Sort.Direction.DESC) Pageable pageable) throws JsonProcessingException {
			
			Map<String, Object> map = new HashMap<>();
			String adminId = principal.getName();
			int parameter = 4;
			
			map = adminCheckListService.selectCheckList(adminId, parameter, category, categoryValue, pageable);
			
			return map;
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
