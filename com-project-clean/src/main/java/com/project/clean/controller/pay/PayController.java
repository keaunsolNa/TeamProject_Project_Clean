package com.project.clean.controller.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.service.pay.PayService;

@Controller
@RequestMapping("pay")
public class PayController {
	
	private final PayService payService;
	
	@Autowired
	public PayController(PayService payService) {
		this.payService = payService;
	}
	
	/* 관리자 급여 전체조회 */
	@GetMapping("/employeePaySelect")
	public void employeePaySelect() {}
	
	/* 관리자 급여 상세조회 */
	@GetMapping("/employeePaySelectInfo")
	public void employeePaySelectInfo() {}
	
	/* 부가요금 페이지 */
	@GetMapping("/surcharge")
	public ModelAndView findSurchargeList(ModelAndView mv) {

		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		
		mv.addObject("surchargeList", surchargeList);
		mv.setViewName("pay/surcharge");
		
		return mv;
	}
	
	/* 부가요금 수정 */
//	@PostMapping("/surcharge")
//	public String modify(RedirectAttributes rttr, @ModelAttribute SurchargeDTO surcharge) {
//		
//		System.out.println("여기도 못 오냐?");
//		payService.modifySurcharge(surcharge);
//		 
//		rttr.addFlashAttribute("modifySuccessMessage", "부가요금 수정에 성공하셨습니다");
//		  
//		return "redirect:/surcharge/" + surcharge.getSurchargeCommission() + surcharge.getSurchargeBonus();
//		 
//
//	}
	
}
