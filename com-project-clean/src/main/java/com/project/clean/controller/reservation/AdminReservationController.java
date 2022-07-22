package com.project.clean.controller.reservation;

import java.security.Principal;
import java.util.ArrayList;
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
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.NotificationDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.service.reservation.AdminReservationService;

@Controller
@RequestMapping("admin/reservation")
public class AdminReservationController {
	private final AdminReservationService adminReservationService;
	
	@Autowired
	AdminReservationController(AdminReservationService adminReservationService) {
		super();
		this.adminReservationService = adminReservationService;
	}
	
	@GetMapping("/main")
	public ModelAndView adminReservationMain(ModelAndView mv) {
		
		List<String> reservationDateList = adminReservationService.findDistinctByBusinessDate();
		
    	List<Integer> ableCountList = new ArrayList<>();
    	List<Integer> unableCountList = new ArrayList<>();
    	List<String> dateList = new ArrayList<>();
    	
    	for(int i=0; i<reservationDateList.size(); i++) {
    		
    		String date = reservationDateList.get(i).substring(0,10);
    		dateList.add(date);
    		
    		int ableCount = adminReservationService.selectAbleCount(date);
    		ableCountList.add(ableCount);
    		 
    		int unableCount = adminReservationService.selectUnableCount(date);
    		unableCountList.add(unableCount);
        } 
		
    	mv.addObject("reservationDateList", reservationDateList);
    	mv.addObject("dateList", dateList);
    	mv.addObject("ableCountList", ableCountList);
    	mv.addObject("unableCountList", unableCountList);
    	mv.setViewName("reservation/admin/adminReservationMain");
		return mv;
	}
	
	@GetMapping("/date/{date}")
	public ModelAndView reservationListByDate(HttpServletRequest request, ModelAndView mv, @PathVariable String date) {

		String currentPage = request.getParameter("currentPage");
		int pageNo = 1;

		if (currentPage != null && !"".equals(currentPage)) {
			pageNo = Integer.parseInt(currentPage);
		}

		java.sql.Date businessDate = java.sql.Date.valueOf(date);
		int totalCount = adminReservationService.selectTotalCount(businessDate);
		/* 한 페이지에 보여 줄 게시물 수 */
		int limit = 10; // 얘도 파라미터로 전달받아도 된다.

		/* 한 번에 보여질 페이징 버튼의 갯수 */
		int buttonAmount = 5;

		SelectCriteria selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount, limit, buttonAmount);

		System.out.println(selectCriteria);

		List<ReservationInfoDTO> reservationList = adminReservationService
				.findReservationByBusinessDate(businessDate, selectCriteria);
//		System.out.println("reservationList" + reservationList);

		mv.addObject("reservationList", reservationList);
		mv.addObject("date", businessDate);
		mv.addObject("selectCriteria", selectCriteria);
		mv.setViewName("reservation/admin/adminReservationList");
		return mv;
	}
	
	@GetMapping("/detail/{reservationNo}")
	public ModelAndView reservationDetail(ModelAndView mv, @PathVariable int reservationNo) {
		
		ReservationInfoDTO reservation = adminReservationService.findByReservationNo(reservationNo);
		System.out.println("reservation : " + reservation);
		
		/* 일할 시간 계산 */
		java.sql.Timestamp startTime = reservation.getBusinessStartTime();
		java.sql.Timestamp endTime = reservation.getBusinessEndTime();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		int end = Integer.valueOf(sdf.format(endTime));
		int start = Integer.valueOf(sdf.format(startTime));
		int workTime = end - start;
		
		/* 지원자가 0이 아닌경우 지원 테이블 select */
		List<ApplyEmployeeDTO> applyEmployeeList = new ArrayList<>();
		List<EmployeeDTO> applyEmployeeInfoList = new ArrayList<>();
		applyEmployeeList = null;
		if (reservation.getBusinessApplyPeople() != 0) {
			applyEmployeeList = adminReservationService.findAllByApplyReservationNo(reservationNo);
			
			for(int i = 0; i < applyEmployeeList.size();i++) {
				ApplyEmployeeDTO app = applyEmployeeList.get(i);
				/* 지원한 직원의 정보만 따로 list에 담아줌 */
				EmployeeDTO applyemployee = adminReservationService.findByEmployeeNo(app.getApplyEmployeeNo());
				applyEmployeeInfoList.add(applyemployee);
			}
		}
		mv.addObject("workTime", workTime);
		mv.addObject("reservation", reservation);
		mv.addObject("applyEmployeeList", applyEmployeeList);
		mv.addObject("applyEmployeeInfoList", applyEmployeeInfoList);
		mv.addObject("date", reservation.getBusinessDate());
		mv.addObject("sysdate", new java.util.Date());
		mv.setViewName("reservation/admin/adminReservationDetail");
		return mv;
	}
	
	/* 지원한 직원 삭제 */
	@GetMapping("/employeeDelete/{penalty}/{employee}/{reservation}")
	public ModelAndView reservationEmployeeDelete(ModelAndView mv,
			@PathVariable String penalty,@PathVariable String employee, @PathVariable String reservation, Principal principal) {
		
		/* 관리자 번호 불러옴 */
		String adminId = principal.getName();
		AdminDTO admin = adminReservationService.findByAdminId(adminId);
		int adminNo = admin.getAdminNo();

		int employeeNo = Integer.valueOf(employee);
		int reservationNo = Integer.valueOf(reservation);
		ReservationInfoDTO reservationInfo = adminReservationService.findByReservationNo(reservationNo);
		
		/* applyEmployee테이블에서 직원 취소시킴 */
		adminReservationService.modifyApplyEmployeeCancel(employeeNo, reservationNo);
		
		/* 지원인원 -1 , 지원마감 여부 "N"으로 변경 */
		adminReservationService.modifyApplyPeopleAndApplyEndYn(reservationNo);
		
		/* 해당 직원에게 메세지 전송 */
		NotificationDTO newNotification = new NotificationDTO();
		String notificationMessage = "관리자가 직원님을 예약일정에 삭제했습니다.";
		newNotification.setNotificationText(notificationMessage);
		newNotification.setNotificationEmployeeNo(employeeNo);
		newNotification.setNotificationReservationNo(reservationNo);
		newNotification.setNotificationAdminNo(adminNo);
		adminReservationService.applyNewNotificationMessage(newNotification);
		
		/* 패널티 부여 */
		if("2".equals(penalty)) {		// 패털티 부여 하면
			adminReservationService.modifyEmployeePenalty(employeeNo);
			
			/* 해당 직원에게 메세지 전송 */
			NotificationDTO newNotification2 = new NotificationDTO();
			String notificationMessage2 = "관리자가 직원님에게 패널티를 1회 부여했습니다.";
			newNotification2.setNotificationText(notificationMessage2);
			newNotification2.setNotificationEmployeeNo(employeeNo);
			newNotification2.setNotificationReservationNo(reservationNo);
			newNotification2.setNotificationAdminNo(adminNo);
			adminReservationService.applyNewNotificationMessage(newNotification2);
			
			mv.addObject("message", "해당직원 지원취소 및 패널티 부여를 완료했습니다.");
			mv.addObject("reservationNo", reservationNo);
			mv.addObject("reservation", reservationInfo);
			mv.setViewName("reservation/admin/adminReservationDetail");
			return mv;
		}
		
		mv.addObject("message", "해당직원 지원취소를 완료했습니다.");
		mv.addObject("reservationNo", reservationNo);
		mv.addObject("reservation", reservationInfo);
		mv.setViewName("reservation/admin/adminReservationDetail");
		return mv;
	}
	
	/* 예약건에 직원 추가 */
	@GetMapping("/employeeInsert/{employee}/{reservation}")
	public ModelAndView reservationEmployeeDelete(ModelAndView mv,
			@PathVariable String employee, @PathVariable String reservation, Principal principal) {
		
		int reservationNo = Integer.valueOf(reservation);
		ReservationInfoDTO reservationInfo = adminReservationService.findByReservationNo(reservationNo);
		int employeeNo = 0;
		try {
			employeeNo = Integer.valueOf(employee);
		} catch(Exception e){
			mv.addObject("message", "사원번호에 해당하는 '숫자'만 입력하세요.");
			mv.addObject("reservationNo", reservationNo);
			mv.addObject("reservation", reservationInfo);
			mv.setViewName("reservation/admin/adminReservationDetail");
			return mv;
		}
		/* 직원번호가 다르면 return */		
		EmployeeDTO employeeDTO = adminReservationService.findByEmployeeNo(employeeNo);
		if(employeeDTO.getEmployeeNo() == 0 || "Y".equals(employeeDTO.getEmployeeRetireYn()) || "Y".equals(employeeDTO.getEmployeeBlackListYn())) {
			mv.addObject("message", "해당 번호의 직원이 없습니다.\n다시 확인 후 추가해주세요");
			mv.addObject("reservationNo", reservationNo);
			mv.addObject("reservation", reservationInfo);
			mv.setViewName("reservation/admin/adminReservationDetail");
			return mv;
		}
		
		ApplyEmployeeDTO existApplyEmployee = adminReservationService.findByApplyReservationNoAndCancelYn(reservationNo, "N");
		if(existApplyEmployee.getApplyEmployeeNo() == employeeNo) {
			mv.addObject("message", "이미 지원한 인원입니다.");
			mv.addObject("reservationNo", reservationNo);
			mv.addObject("reservation", reservationInfo);
			mv.setViewName("reservation/admin/adminReservationDetail");
			return mv;
		}
		
		/* 관리자 번호 불러옴 */
		String adminId = principal.getName();
		AdminDTO admin = adminReservationService.findByAdminId(adminId);
		int adminNo = admin.getAdminNo();
		
		ApplyEmployeeDTO newApply = new ApplyEmployeeDTO();
		newApply.setApplyReservationNo(reservationNo);
		newApply.setApplyEmployeeNo(employeeNo);
		newApply.setApplyCancelYn("N");
		if(reservationInfo.getBusinessApplyPeople() == 1) {		// 이미 지원한 인원이 있으면
			// 해당 예약건에 지원한 인원 중 취소 안한인원을 불러옴 */
			ApplyEmployeeDTO applyEmp = adminReservationService.findByApplyReservationNoAndApplyCancelYn(reservationNo, "N");
			if("Y".equals(applyEmp.getCheckEmployeeYn())) {
				newApply.setCheckEmployeeYn("N");
			} else {
				newApply.setCheckEmployeeYn("Y");
			}
		}else {
			newApply.setCheckEmployeeYn("Y");
		}
		
		/* 예약 지원 테이블 insert */
		adminReservationService.insertNewApply(newApply);
		/* 예약 정보 테이블 지원인원 update */
		adminReservationService.modifyReservationApplyPeoplePlus(reservationNo);
		ReservationInfoDTO reservationAfter = adminReservationService.findByReservationNo(reservationNo);
		/* 업데이트된 예약정보 select 후 정원과 지원인원 비교해서 mv에 값을 넣어줌 */
		if (reservationAfter.getBusinessFixedPeople() == reservationAfter.getBusinessApplyPeople()) {
			adminReservationService.modifyReservationApplyEndYnByY(reservationNo);
		}
		
		/* 해당 직원에게 메세지 전송 */
		NotificationDTO newNotification = new NotificationDTO();
		String notificationMessage = "관리자가 직원님을 예약일정에 추가했습니다.";
		newNotification.setNotificationText(notificationMessage);
		newNotification.setNotificationEmployeeNo(employeeNo);
		newNotification.setNotificationReservationNo(reservationNo);
		newNotification.setNotificationAdminNo(adminNo);
		adminReservationService.applyNewNotificationMessage(newNotification);
		
		mv.addObject("message", "직원추가가 완료되었습니다");
		mv.addObject("reservationNo", reservationNo);
		mv.addObject("reservation", reservationAfter);
		mv.setViewName("reservation/admin/adminReservationDetail");
		return mv;
	}
}
