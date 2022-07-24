package com.project.clean.controller.pay;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminAndAdminPayDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;
import com.project.clean.model.dto.joinDTO.BestEmployeePayAndEmployeeDTO;
import com.project.clean.model.dto.joinDTO.EmployeePayAndApplyEmployeeDTO;
import com.project.clean.model.repository.pay.AdminRepositoryByPay;
import com.project.clean.model.service.pay.PayService;

@Controller
@RequestMapping("/admin/pay")
public class PayController {
	
	private final PayService payService;
	
	@Autowired
	public PayController(PayService payService) {
		this.payService = payService;
	}
	
	// 직원 급여 -----------------------------------------------------------------------------------------------
	
	/* 직원 급여 전체 조회 */
	@GetMapping("/management/employeePaySelect")
	public ModelAndView employeePaySearch(HttpServletRequest request, ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		int totalCount = payService.selectEmployeePayTotalCount(searchCondition, searchValue);
		
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
		
		List<EmployeePayAndApplyEmployeeDTO> employeePayList = payService.employeePaySearch(selectCriteria);
		
		for(EmployeePayAndApplyEmployeeDTO pay : employeePayList) {
			System.out.println(pay);
		}

		mv.addObject("employeePayList", employeePayList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("admin/pay/management/employeePaySelect");

		return mv;
		
	}
	
	/* 직원 급여 상세 조회 */
	@GetMapping("/management/employeePaySelectInfo/{payHistoryEmployeeNo}")
	public ModelAndView findEmployeePayByPayHistoryEmployeeNo(ModelAndView mv, @PathVariable int payHistoryEmployeeNo) {
		
		EmployeePayAndApplyEmployeeDTO pay = payService.findEmployeePayByPayHistoryEmployeeNo(payHistoryEmployeeNo);	// 급여 내역 번호(pk)

		mv.addObject("pay", pay);
		mv.setViewName("admin/pay/management/employeePaySelectInfo");
		
		return mv;
	}
	

	
	/* 직원 급여 지급 */
	// admin.checklist.AdminCheckListController에 acceptCheckList부분에 작성했다(체크리스트가 승인될때 바로 급여를 지급하게 만들었음)

	
	// 관리자 급여 ---------------------------------------------------------------------------------------------
	
	/* 관리자 급여 전체 조회 */
	@GetMapping("/management/adminPaySelect")
	public ModelAndView adminPaySelect(HttpServletRequest request, ModelAndView mv) {

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
		mv.setViewName("/admin/pay/management/adminPaySelect");

		return mv;
	}
	
	/* 관리자 급여 상세 조회 */
	@GetMapping("/management/adminPaySelectInfo/{payHistoryAdminNo}")
	public ModelAndView findAdminPayByPayHistoryAdminNo(ModelAndView mv, @PathVariable int payHistoryAdminNo) {

		AdminPayAndAdminDTO pay = payService.findAdminPayByPayHistoryAdminNo(payHistoryAdminNo);	// 급여 내역 번호(pk)

		mv.addObject("pay", pay);
		mv.setViewName("admin/pay/management/adminPaySelectInfo");
		
		return mv;
	}
	
	/* 관리자 급여 대기목록 */
	@GetMapping("/management/adminPayWaiting")
	public ModelAndView findAdminList(ModelAndView mv) {
		List<AdminAndAdminPayDTO> adminList = payService.findNullAdmin();		// 급여를 한번도 받지 않은 관리자(신입사원)
		List<AdminAndAdminPayDTO> adminList2 = payService.findPaidAdmin();		// 급여를 이미 받은 관리자(달 기준)
		List<AdminAndAdminPayDTO> adminList3 = payService.findAllAdmin();		// 모든 관리자(급여를 받은 관리자와 비교하기 위함)
		List<SurchargeDTO> surchargeList = payService.findSurchargeList();		// 부가요금 테이블
		
		// 부가요금 테이블에서 4대보험료 불러오기 (급여계산에 쓰기 위해) 
		int insurance =surchargeList.get(0).getSurchargeInsurance();
		
		mv.addObject("adminList", adminList);
		mv.addObject("adminList2", adminList2);
		mv.addObject("adminList3", adminList3);
		mv.addObject("insurance", insurance);
		mv.setViewName("admin/pay/management/adminPayWaiting");
		
		return mv;
		
	}
	
	/* 관리자 급여 대기목록 - 모든 관리자 조회(ajax)*/
	@GetMapping(value="/admin", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<AdminAndAdminPayDTO> findAdminSelect(){
		
		return payService.findAllAdmin();
	
	}
	
	/* 관리자 급여 지급 */
	@PostMapping("/management/adminPayWaiting")
	public String regist(RedirectAttributes rttr, int adminNo) {
		
		// 급여를 계산하기 위해 4대보험료를 가져옴
		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		int insurance = surchargeList.get(0).getSurchargeInsurance();
		System.out.println("부가요금 확인" + insurance);
		
		
		// adminNo로 해당 관리자의 모든 정보를 가져옴
		AdminDTO admin = payService.findAdminByAdminNo(adminNo);
		System.out.println("관리자 확인" + admin);
		
		// 급여만 빼옴
		int salary = admin.getAdminSalary();
		// 관리자번호, 그 관리자의 급여, 4대보험료 service로 보냄
		payService.registAdminPay(adminNo, salary, insurance);
		 
		rttr.addFlashAttribute("modifySuccessMessage", "급여 지급에 성공하였습니다");
		  
		return "redirect:/admin/pay/management/adminPayWaiting";
		 
	}
	
	
	// 이달의 우수직원 급여 ---------------------------------------------------------------------------------------
	
	/* 이달의 우수직원 급여 조회 */
	@GetMapping("/management/bestEmployeePaySelect")
	public ModelAndView bestEmployeePaySearch(HttpServletRequest request, ModelAndView mv) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if(currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		int totalCount = payService.selectBestEmployeePayTotalCount(searchCondition, searchValue);
		
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
		
		List<BestEmployeePayAndEmployeeDTO> bestEmployeePayList = payService.bestEmployeePaySearch(selectCriteria);
		
		for(BestEmployeePayAndEmployeeDTO pay : bestEmployeePayList) {
			System.out.println(pay);
		}

		mv.addObject("bestEmployeePayList", bestEmployeePayList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("admin/pay/management/bestEmployeePaySelect");

		return mv;
		
	}
	
	
	/* 이달의 우수직원 급여 지급 페이지(Get) */
	@GetMapping("/management/bestEmployeePayWaiting")
	public void bestEmployeePayWaiting() {}
	
	/* 이달의 우수직원 급여 지급 페이지 - 모든 직원 조회(ajax)*/
	@GetMapping(value="/employee", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<EmployeeDTO> findEmployeeSelect(){
		
		return payService.findAllEmployee();
	
	}
	
	/* 이달의 우수직원 급여 지급 */
	@PostMapping("/management/bestEmployeePayWaiting")
	public String registBestEmployeePay(RedirectAttributes rttr, int bestEmployeeNo) {
		
		// 우수직원 보너스를 가져옴
		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		int bestEmployeeBonus = surchargeList.get(0).getSurchargeBonus();
		
		
		// 우수직원 번호, 보너스를 service로 보냄
		payService.registBestEmployeePay(bestEmployeeNo, bestEmployeeBonus);
		 
		rttr.addFlashAttribute("modifySuccessMessage", "우수사원 보너스 지급에 성공하였습니다");
		  
		return "redirect:/admin/pay/management/bestEmployeePayWaiting";
		 
	}
	
	
	// 부가요금 ------------------------------------------------------------------------------------------------
	
	/* 부가요금 페이지(조회) */
	@GetMapping("/management/surcharge")
	public ModelAndView findSurchargeList(ModelAndView mv) {

		List<SurchargeDTO> surchargeList = payService.findSurchargeList();
		
		mv.addObject("surchargeList", surchargeList);
		mv.setViewName("admin/pay/management/surcharge");
		
		return mv;
	}
	
	/* 부가요금 수정 */
	@PostMapping("/management/surcharge")
	public String modify(RedirectAttributes rttr, SurchargeDTO surcharge) {

		payService.modifySurcharge(surcharge);
		 
		rttr.addFlashAttribute("modifySuccessMessage", "부가요금 수정에 성공하셨습니다");
		  
		return "redirect:/admin/pay/management/surcharge";
		 
	}
	
	
	// 나의 급여(접근권한때문에 직원의 나의급여는 다른 컨트롤러에 있음) ----------------------------------------------------
	
	/* 나의 급여 조회(관리자) */
	@GetMapping("/myPay/myPayForAdmin")
	public ModelAndView myPayForAdmin(HttpServletRequest request, ModelAndView mv, Principal principal) {

		/* 관리자 번호 가져오기 */
		String adminId = principal.getName();
		
		AdminDTO admin = payService.findAdminByAdminId(adminId);
		int adminNo = admin.getAdminNo();
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		int totalCount = payService.selectMyPayForAdminTotalCount(adminNo);
		
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10; // 얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		
		/* 받은 급여가 없을때 예외처리 */ 
		List<AdminPayAndAdminDTO> adminPayList = payService.myPayForAdmin(adminNo, selectCriteria);
		if (pageNo == 1 && adminPayList.size() == 0) {
			mv.addObject("ListNullMessage", "아직 받은 급여가 없습니다");
			mv.setViewName("admin/pay/myPay/myPayForAdmin");
			return mv;
		}
		
		mv.addObject("adminPayList", adminPayList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("admin/pay/myPay/myPayForAdmin");
		return mv;
	}
	
	
	/* 나의 급여 상세 조회(관리자) */
	@GetMapping("/myPay/myPayForAdminSelectInfo/{payHistoryAdminNo}")
	public ModelAndView findMyPayForAdminByPayHistoryAdminNo(ModelAndView mv, @PathVariable int payHistoryAdminNo) {

		AdminPayAndAdminDTO pay = payService.findAdminPayByPayHistoryAdminNo(payHistoryAdminNo);	// 급여 내역 번호(pk)

		mv.addObject("pay", pay);
		mv.setViewName("admin/pay/myPay/myPayForAdminSelectInfo");
		
		return mv;
	}


}
