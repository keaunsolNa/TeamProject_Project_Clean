package com.project.clean.controller.reservation;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.controller.common.paging.Pagenation;
import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.NotificationDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.service.reservation.AdminNotificationService;
import com.project.clean.model.service.reservation.AdminReservationService;

@Controller
@RequestMapping("/admin/notification")
public class AdminNotificationController {

	private final AdminNotificationService adminNotificationService;
	private final AdminReservationService adminReservationService;

	@Autowired
	AdminNotificationController(AdminNotificationService adminNotificationService,
			AdminReservationService adminReservationService) {
		super();
		this.adminNotificationService = adminNotificationService;
		this.adminReservationService = adminReservationService;
	}
	
	@GetMapping("list")
	public ModelAndView adminNotificationList(HttpServletRequest request, ModelAndView mv, Principal principal) {
		
		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}
		
		/* 관리자 번호 불러옴 */
		String adminId = principal.getName();
		AdminDTO admin = adminReservationService.findByAdminId(adminId);
		int adminNo = admin.getAdminNo();
		
		int totalCount = adminNotificationService.selectTotalCount(adminNo);
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10;		//얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;
		
		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);
		 
		/* 해당 직원의 알림 목록 전체 select */
		List<NotificationDTO> notificationList = adminNotificationService.findAllByNotificationAdminNoAndNotificationAdminYn(adminNo, "Y", selectCriteria);
		System.out.println("notificationList" + notificationList);
		
		if(notificationList.size() == 0) {		
			mv.addObject("message", "알림이 없습니다.");
			mv.setViewName("reservation/admin/adminNotificationList");
			return mv;
		 } 
	
		mv.addObject("notificationList", notificationList);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("reservation/admin/adminNotificationList");
		return mv;
	}
	
	@GetMapping("/detail/{notificationNo}")
	public ModelAndView notificationDetail(ModelAndView mv, @PathVariable int notificationNo) {
		
		/* 해당 알림 테이블 조회 */
		NotificationDTO notification = adminNotificationService.findByNotificationNo(notificationNo);
		/* 알림 읽음 여부 update */
		adminNotificationService.modifyNotificationReadYn(notificationNo, "Y");
		
		/* 해당 예약건 select */
		ReservationInfoDTO reservation = adminNotificationService.findReservation(notification.getNotificationReservationNo());
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
		mv.setViewName("reservation/admin/adminNotificationDetail");
		return mv;
	}
	
	@GetMapping("/time")
	@ResponseBody
	public int notificationTime(Principal principal) {
		
		/* 관리자 번호 불러옴 */
		String adminId = principal.getName();
		AdminDTO admin = adminReservationService.findByAdminId(adminId);
		int adminNo = admin.getAdminNo();
		
		List<NotificationDTO> notificationList = adminNotificationService.findAllByNotificationAdminNoAndNotificationAdminYn(adminNo, "Y");
		
		int result = 0;
		for(int i = 0; i < notificationList.size(); i++) {
			NotificationDTO noti = notificationList.get(i);
			if(noti.getNotificationReadYn().equals("N")) {
				result++;
			}
		}
		return result;
	}
	
}
