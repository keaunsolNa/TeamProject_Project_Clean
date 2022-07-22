package com.project.clean.controller.reservation;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.controller.common.paging.Pagenation;
import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.BookMarkDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.BookMarkAndReservationInfoDTO;
import com.project.clean.model.service.reservation.EmployeeBookmarkService;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

	private final EmployeeBookmarkService employeeBookmarkService;

	@Autowired
	public BookmarkController(EmployeeBookmarkService employeeBookmarkService) {
		super();
		this.employeeBookmarkService = employeeBookmarkService;
	}

	/* 즐겨찾기 리스트 조회 */
	@GetMapping("/list")
	public ModelAndView bookmarkList(HttpServletRequest request, ModelAndView mv, Principal principal) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		/* 직원 번호 불러옴 */
		String employeeId = principal.getName();
		EmployeeDTO employee = employeeBookmarkService.findByEmployeeId(employeeId);
		int employeeNo = employee.getEmployeeNo();

		String n = "N";
		int totalCount = employeeBookmarkService.selectTotalCountByBookmarkCancelYn(employeeNo, n);
		
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		
		/* 해당 직원의 즐겨찾기 목록 전체 select */
		List<BookMarkAndReservationInfoDTO> bookmarkList = employeeBookmarkService.findByBookmarkEmployeeNoAndBookmarkCancelYn(employeeNo, n, selectCriteria);

		if (pageNo ==1 && bookmarkList.size() == 0) { // 즐겨찾기 한 내역이 없으면 return
			mv.addObject("message", "즐겨찾기 한 예약이 없습니다. \n즐겨찾기 후 이용해주세요");
			mv.setViewName("reservation/bookmarkList");
			return mv;
		}
		System.out.println("bookmarkList" + bookmarkList);
		mv.addObject("bookmarkList", bookmarkList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("reservation/bookmarkList");

		return mv;
	}
}
