package com.project.clean.controller.pay;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.controller.common.paging.Pagenation;
import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;
import com.project.clean.model.dto.joinDTO.EmployeePayAndApplyEmployeeDTO;
import com.project.clean.model.service.pay.PayService;

@Controller
@RequestMapping("/employee/pay/myPay")
public class EmployeePayController {

	private final PayService payService;

	@Autowired
	public EmployeePayController(PayService payService) {
		this.payService = payService;
	}

	// 나의 급여조회
    @GetMapping("/myPayForEmployee")
	public ModelAndView myPayForAdmin(HttpServletRequest request, ModelAndView mv, Principal principal) {

		/* 관리자 번호 가져오기 */
		String employeeId = principal.getName();
		
		EmployeeDTO employee = payService.findEmployeeByEmployeeId(employeeId);
		int employeeNo = employee.getEmployeeNo();
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		int totalCount = payService.selectMyPayForEmployeeTotalCount(employeeNo);
		
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10; // 얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

		List<EmployeePayAndApplyEmployeeDTO> employeePayList = payService.myPayForEmployee(employeeNo, selectCriteria);
		if (pageNo == 1 && employeePayList.size() == 0) {
			mv.addObject("ListNullMessage", "아직 받은 급여가 없습니다");
			mv.setViewName("pay/myPay/myPayForEmployee");
			return mv;
		}
		
		mv.addObject("employeePayList", employeePayList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("employee/pay/myPay/myPayForEmployee");
		return mv;
	}
		
		/* 나의 직원 급여 상세조회 */
		@GetMapping("/myPayForEmployeeSelectInfo/{payHistoryEmployeeNo}")
		public ModelAndView findMyPayForAdminByPayHistoryAdminNo(ModelAndView mv, @PathVariable int payHistoryEmployeeNo) {

			EmployeePayAndApplyEmployeeDTO pay = payService.findEmployeePayByPayHistoryEmployeeNo(payHistoryEmployeeNo);

			mv.addObject("pay", pay);
			mv.setViewName("employee/pay/myPay/myPayForEmployeeSelectInfo");
			
			return mv;
		}

	
}
