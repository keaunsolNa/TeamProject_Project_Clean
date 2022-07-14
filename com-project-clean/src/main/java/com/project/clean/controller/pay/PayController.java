package com.project.clean.controller.pay;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.controller.common.paging.Pagenation;
import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;
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

	
	/* 관리자 급여 상세조회 */
	@GetMapping("/adminPaySelectInfo")
	public void adminPaySelectInfo() {}
	
	/* 부가요금 페이지(조회) */
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
	@PostMapping("/surcharge")
	public String modify(RedirectAttributes rttr, SurchargeDTO surcharge) {

		payService.modifySurcharge(surcharge);
		 
		rttr.addFlashAttribute("modifySuccessMessage", "부가요금 수정에 성공하셨습니다");
		  
		return "redirect:/pay/surcharge";
		 

	}
	
	/* 관리자 급여 전체조회 */
	@GetMapping("/adminPaySelect")
	public ModelAndView searchPage(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = payService.selectTotalCount(searchCondition, searchValue);

		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		/* 페이징 처리를 위한 로직 호출 후 페이징 처리에 관한 정보를 담고 있는 인스턴스를 반환받는다. */
		SelectCriteria selectCriteria = null;
		if(searchValue != null && !"".equals(searchValue)) {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount, searchCondition, searchValue);
		} else {
			selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		}
		System.out.println(selectCriteria);

		List<AdminPayAndAdminDTO> adminPayList = payService.searchAdminPayList(selectCriteria);

		for(AdminPayAndAdminDTO pay : adminPayList) {
			System.out.println(pay);
		}

		mv.addObject("adminPayList", adminPayList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("pay/adminPaySelect");

		return mv;
	}
	

	
}
