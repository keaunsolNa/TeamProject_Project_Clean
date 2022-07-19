package com.project.clean.controller.pay;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.clean.controller.common.paging.Pagenation;
import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminAndAdminPayDTO;
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
	
	// 직원 급여 -----------------------------------------------------------------------------------------------
	
	/* 직원 급여 전체조회 */
	@GetMapping("/employeePaySelect")
	public void employeePaySelect() {}
	
	/* 직원 급여 상세조회 */
	@GetMapping("/employeePaySelectInfo")
	public void employeePaySelectInfo() {}

	// 관리자 급여 ---------------------------------------------------------------------------------------------
	
	/* 관리자 급여 전체조회 */
	@GetMapping("/adminPaySelect")
	public ModelAndView adminPaySearch(HttpServletRequest request, ModelAndView mv) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");

		int totalCount = payService.selectAdminPayTotalCount(searchCondition, searchValue);

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

		List<AdminPayAndAdminDTO> adminPayList = payService.adminPaySearch(selectCriteria);

		for(AdminPayAndAdminDTO pay : adminPayList) {
			System.out.println(pay);
		}

		mv.addObject("adminPayList", adminPayList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("pay/adminPaySelect");

		return mv;
	}
	
	/* 관리자 급여 상세조회 */
	@GetMapping("/adminPaySelectInfo/{payHistoryAdminNo}")
	@Transactional
	public ModelAndView findAdminPayByPayHistoryAdminNo(ModelAndView mv, @PathVariable int payHistoryAdminNo) {

		AdminPayAndAdminDTO pay = payService.findAdminPayByPayHistoryNo(payHistoryAdminNo);
		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		
		// 첫 번째 겟으로 값이 있는 순서(인덱스번호)
		// 4대보험료 불러오기 (급여계산에 쓰기 위해) 
		int insurance =surchargeList.get(0).getSurchargeInsurance();
	
		
		mv.addObject("pay", pay);
		mv.addObject("insurance", insurance);
		mv.setViewName("pay/adminPaySelectInfo");
		
		return mv;
	}
	
	/* 관리자 급여 대기목록 */
	@GetMapping("/adminPayWaiting")
	public ModelAndView findAdminList(ModelAndView mv) {
		List<AdminAndAdminPayDTO> adminList = payService.findNullAdmin();
		List<AdminAndAdminPayDTO> adminList2 = payService.findPaidAdmin();
		List<AdminAndAdminPayDTO> adminList3 = payService.findAllAdmin();
		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		
		// 4대보험료 불러오기 (급여계산에 쓰기 위해) 
		int insurance =surchargeList.get(0).getSurchargeInsurance();
		
		mv.addObject("adminList", adminList);
		mv.addObject("adminList2", adminList2);
		mv.addObject("adminList3", adminList3);
		mv.addObject("insurance", insurance);
		mv.setViewName("pay/adminPayWaiting");
		
		return mv;
		
	}
	

	/* 관리자 급여 대기목록 - 모든 관리자 조회 */
	@GetMapping(value="/admin", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<AdminAndAdminPayDTO> findAdminSelect(){
		
		return payService.findAllAdmin();
	
	}
	
	
	
	
	
	
	
	
	
	/* 관리자 급여 지급 */
	@PostMapping("/adminPayWaiting")
	public String regist(RedirectAttributes rttr, int adminNo) {
		
		// 급여를 계산하기 위해 4대보험료를 가져옴
		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		int insurance = surchargeList.get(0).getSurchargeInsurance();
		System.out.println("부가요금 확인" + insurance);
		System.out.println("부가요금 확인" + insurance);
		System.out.println("부가요금 확인" + insurance);
		System.out.println("부가요금 확인" + insurance);
		
		
		// adminNo로 해당 관리자의 모든 정보를 가져옴
		AdminDTO admin = payService.findAdminByPayAdminNo(adminNo);
		System.out.println("관리자 확인" + admin);
		System.out.println("adminNo 확인" + adminNo);
		System.out.println("adminNo 확인" + adminNo);
		System.out.println("adminNo 확인" + adminNo);
		System.out.println("adminNo 확인" + adminNo);
		
		
//		LocalDate localDate = LocalDate.now();
//		java.sql.Date today = java.sql.Date.valueOf(localDate);
		
		
		// 급여만 빼옴
		int salary = admin.getAdminSalary();
		// 관리자번호, 그 관리자의 급여, 4대보험료 service로 보냄
		payService.registAdminPay(adminNo, salary, insurance);
		 
		rttr.addFlashAttribute("modifySuccessMessage", "급여 지급에 성공하였습니다");
		  
		return "redirect:/pay/adminPayWaiting";
		 

	}
	
	
	
	
	
	
	
	
	

	// 부가요금 ------------------------------------------------------------------------------------------------
	
	/* 부가요금 페이지(조회) */
	@GetMapping("/surcharge")
	public ModelAndView findSurchargeList(ModelAndView mv) {

		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		
		mv.addObject("surchargeList", surchargeList);
		mv.setViewName("pay/surcharge");
		
		return mv;
	}
	
	/* 부가요금 수정 */
	@PostMapping("/surcharge")
	public String modify(RedirectAttributes rttr, SurchargeDTO surcharge) {

		payService.modifySurcharge(surcharge);
		 
		rttr.addFlashAttribute("modifySuccessMessage", "부가요금 수정에 성공하셨습니다");
		  
		return "redirect:/pay/surcharge";
		 

	}
	


}
