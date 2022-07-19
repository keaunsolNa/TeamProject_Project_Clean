package com.project.clean.controller.reservation;

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
import com.project.clean.model.dto.commonDTO.NotificationDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.service.reservation.EmployeeNotificationService;

@Controller
@RequestMapping("/notification")
public class NotificationController {


	private final EmployeeNotificationService employeeNotificationService;

	@Autowired
	public NotificationController(EmployeeNotificationService employeeNotificationService) {
		super();
		this.employeeNotificationService = employeeNotificationService;
	}
	
	@GetMapping("/list")
	public ModelAndView notificationList(HttpServletRequest request, ModelAndView mv, Principal principal) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		/* 직원 번호 불러옴 */ 
		String employeeId = principal.getName();
		EmployeeDTO employee = employeeNotificationService.findByEmployeeId(employeeId);
		int employeeNo = employee.getEmployeeNo();
		
		int totalCount = employeeNotificationService.selectTotalCount(employeeNo);
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 3;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		 
		/* 해당 직원의 알림 목록 전체 select */
		List<NotificationDTO> notificationList = employeeNotificationService.findAllByNotificationEmployeeNo(employeeNo, selectCriteria);
		System.out.println("notificationList" + notificationList);
		
		if(notificationList.size() == 0) {		
			mv.addObject("notificationMessage", "알림이 없습니다.");
			mv.setViewName("reservation/notificationList");
			return mv;
		 } 
	
		mv.addObject("notificationList", notificationList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("reservation/notificationList");
		return mv;
	}
	
	/* 알림 세부 내용 */
	@GetMapping("/detail/{notificationNo}")
	public ModelAndView reservationDetail(ModelAndView mv, @PathVariable int notificationNo) {
		
		/* 해당 알림 테이블 조회 */
		NotificationDTO notification = employeeNotificationService.findByNotificationNo(notificationNo);
		/* 알림 읽음 여부 update */
		employeeNotificationService.modifyNotificationReadYn(notificationNo, "Y");
		
		/* 해당 예약건 select */
		ReservationInfoDTO reservation = employeeNotificationService.findReservation(notification.getNotificationReservationNo());
		/* 일할 시간 계산 */
		java.sql.Timestamp startTime = reservation.getBusinessStartTime();
		java.sql.Timestamp endTime = reservation.getBusinessEndTime();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		int end = Integer.valueOf(sdf.format(endTime));
		int start = Integer.valueOf(sdf.format(startTime));
		int workTime = end - start;
		mv.addObject("notification", notification);
		mv.addObject("reservation", reservation);
		mv.addObject("workTime", workTime);
		mv.setViewName("reservation/notificationDetail");
		return mv;
	}
		
	
	
}
