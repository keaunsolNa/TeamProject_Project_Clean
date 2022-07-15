package com.project.clean.controller.reservation;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.BookMarkDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.service.reservation.EmployeeReservationService;


@Controller
@RequestMapping("/reservation")
public class reservationController {

	private final EmployeeReservationService employeeReservationService;

	@Autowired
	public reservationController(EmployeeReservationService employeeReservationService) {
		this.employeeReservationService = employeeReservationService;
	}

	@GetMapping("/client")
	public String clientReservation() {
		return "/reservation/client";
	}

	/* 고객 예약 */
	@PostMapping("/client")
	public ModelAndView clientReservation(ModelAndView mv, @ModelAttribute ReservationInfoDTO newReservation,
			String startTime, String endTime, String gashoodCleanYn, String moldCleanYn, String filterCleanYn,
			String warehouseCleanYn, String petYn, String multipleLayerYn) {

		/* startTime / endTime 형태변경 후 DTO에 담아줌 */
		Calendar cal;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startDate = new String(newReservation.getBusinessDate() + " " + startTime + ":00");
		String endDate = new String(newReservation.getBusinessDate() + " " + endTime + ":00");
		try {
			sd.parse(startDate);
			cal = sd.getCalendar();
			newReservation.setBusinessStartTime(new Timestamp(cal.getTime().getTime()));
			sd.parse(endDate);
			cal = sd.getCalendar();
			newReservation.setBusinessEndTime(new Timestamp(cal.getTime().getTime()));
			System.out.println("newReservation : " + newReservation);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int count = 0;
		/* 부가서비스 배열로 넘어온 값 DTO에 담아줌 */
		if (gashoodCleanYn != null) {
			newReservation.setGashoodCleanYn("Y");
			count++;
		}
		if (moldCleanYn != null) {
			newReservation.setMoldCleanYn("Y");
			count++;
		}
		if (filterCleanYn != null) {
			newReservation.setFilterCleanYn("Y");
			count++;

		}
		if (warehouseCleanYn != null) {
			newReservation.setWarehouseCleanYn("Y");
			count++;
		}
		if (petYn != null) {
			newReservation.setPetYn("Y");
			count++;
		}
		if (multipleLayerYn != null) {
			newReservation.setMultipleLayerYn("Y");
			count++;
		}

		/* 평수에 따른 정원 계산 */
		if (newReservation.getUserHouseSize() < 15) { // 15평 미만일 때 1명
			newReservation.setBusinessFixedPeople(1);
		} else {
			newReservation.setBusinessFixedPeople(2); // 15평 이상일 때 2명
		}

		/* 총 결제금액 */
//		String date = reservationDateList.get(i).substring(0,10);
		int startT = Integer.valueOf(startTime.substring(0, 2));
		int endT = Integer.valueOf(endTime.substring(0, 2));
		int pay = 0;
		if (endT - startT == 2) { // 2시간인 경우
			pay = 33000;
			for (int i = 1; i <= 6; i++) {
				if (count == i) {
					pay = pay + (i * 10000);
				}
			}
		} else if (endT - startT == 3) { // 3시간인 경우
			pay = 44000;
			for (int i = 1; i <= 6; i++) {
				if (count == i) {
					pay = pay + (i * 10000);
				}
			}
		} else if (endT - startT == 4) { // 4시간인 경우

			if (newReservation.getUserHouseSize() >= 15) {
				pay = 110000;
				for (int i = 1; i <= 6; i++) {
					if (count == i) {
						pay = pay + (i * 10000);
					}
				}
			} else {
				pay = 55000;
				for (int i = 1; i <= 6; i++) {
					if (count == i) {
						pay = pay + (i * 10000);
					}
				}
			}
		}
		System.out.println("pay: " + pay);
		newReservation.setTotalPayment(pay);
		System.out.println("newReservation" + newReservation);

		employeeReservationService.insertNewReservation(newReservation);

		mv.addObject("successCode", "successCode");
		mv.setViewName("reservation/empMainPage");

		return mv;
	}

	/* 날짜이벤트 클릭 시 해당 날짜 예약건 리스트 요청 */
	@GetMapping("/{date}")
	public ModelAndView reservationListByDate(ModelAndView mv, @PathVariable String date) {

		java.sql.Date businessDate = java.sql.Date.valueOf(date);

		List<ReservationInfoDTO> reservationList = employeeReservationService
				.findReservationByBusinessDate(businessDate);
//		System.out.println("reservationList" + reservationList);

		mv.addObject("reservationList", reservationList);
		mv.setViewName("reservation/reservationList");
		return mv;
	}

	/* 예약 상세조회 */
	@GetMapping("/detail/{reservationNo}")
	public ModelAndView reservationDetail(ModelAndView mv, @PathVariable int reservationNo
			,Principal principal) {

		ReservationInfoDTO reservation = employeeReservationService.findReservationByReservationNo(reservationNo);
		System.out.println("reservation : " + reservation);

		/* 일할 시간 계산 */
		java.sql.Timestamp startTime = reservation.getBusinessStartTime();
		java.sql.Timestamp endTime = reservation.getBusinessEndTime();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH");
		int end = Integer.valueOf(sdf.format(endTime));
		int start = Integer.valueOf(sdf.format(startTime));
		int workTime = end - start;

		/* 직원 테이블 select */
		String employeeId = principal.getName();
		EmployeeDTO employee = employeeReservationService.findByEmployeeId(employeeId);
		int employeeNo = employee.getEmployeeNo();
		
		/* 지원자가 0이 아닌경우 지원 테이블 select */
		List<ApplyEmployeeDTO> applyEmployeeList = new ArrayList<>();
		applyEmployeeList = null;
		if(reservation.getBusinessApplyPeople() != 0) {
			applyEmployeeList = employeeReservationService.findAllByApplyReservationNo(reservationNo);
		}
		/* 즐겨찾기 테이블 select */
		BookMarkDTO bookmark = employeeReservationService.findByBookmarkEmployeeNoAndBookmarkReservationNo(employeeNo, reservationNo);
		
		
		
		/* 페이지 지원하기 버튼 처리 */
		if(applyEmployeeList != null) {								// 지원한 사람이 있으면
			
			for(int i = 0; i < applyEmployeeList.size(); i++) {		// 지원테이블 list 반복(지원자가 있으면)
				ApplyEmployeeDTO app = applyEmployeeList.get(i);
				
				if(app.getApplyEmployeeNo() == employeeNo) {			// 내가 지원한 건이면
					mv.addObject("applyButtonText", "지원취소");
					break;
				}
				if(reservation.getBusinessFixedPeople() != reservation.getBusinessApplyPeople()) {	// 마감이 안됐으면
					mv.addObject("applyButtonText", "지원하기");
				} else if(reservation.getBusinessFixedPeople() == reservation.getBusinessApplyPeople()) {	// 마감이면
					if(bookmark.getBookmarkEmployeeNo() == employeeNo) {
						mv.addObject("applyButtonText", "즐겨찾기 취소");
						break;
					} else if (bookmark.getBookmarkEmployeeNo() != employeeNo){
						mv.addObject("applyButtonText", "즐겨찾기");
						break;
					}
				}
			}
			
		} else if(applyEmployeeList == null) {		// 지원한 사람이 없으면
			mv.addObject("applyButtonText", "지원하기");
		}
		System.out.println("bookmark" + bookmark);
		mv.addObject("workTime", workTime);
		mv.addObject("reservation", reservation);
		mv.addObject("employee", employee);
		mv.addObject("applyEmployeeList", applyEmployeeList);
		mv.setViewName("reservation/reservationDetail");
		return mv;
	}

	
	 /* 예약 지원하기 */
	 @GetMapping("/apply/{reservationNo}") 
	 public ModelAndView reservationApply(ModelAndView mv, @PathVariable int reservationNo
			 ,Principal principal) {
	 
		 
		 /* 직원 번호 불러옴 */ 
		 String employeeId = principal.getName();
		 EmployeeDTO employee = employeeReservationService.findByEmployeeId(employeeId);
		 int employeeNo = employee.getEmployeeNo();
		 
		 ApplyEmployeeDTO newApply = new ApplyEmployeeDTO();
		 newApply.setApplyReservationNo(reservationNo);
		 newApply.setApplyEmployeeNo(employeeNo);
		 
		 /* 예약 지원 테이블 insert */
		 employeeReservationService.insertNewApply(newApply);
		 /* 예약 정보 테이블 지원인원 update */
		 employeeReservationService.modifyReservationApplyPeoplePlus(reservationNo);
		 /* 업데이트된 예약정보 select 후 정원과 지원인원 비교해서 mv에 값을 넣어줌 */
		 ReservationInfoDTO reservation = employeeReservationService.findReservationByReservationNo(reservationNo);
		 if(reservation.getBusinessFixedPeople() == reservation.getBusinessApplyPeople()) {		
			 employeeReservationService.modifyReservationApplyEndYnByY(reservationNo);
		 }
		 mv.addObject("applyMessage", "지원이 완료되었습니다");
		 mv.addObject("reservationNo", reservationNo);
		 mv.setViewName("reservation/reservationDetail");
		 return mv; 
	 }
	 
	 /* 지원 취소하기 */
	 @GetMapping("/applyCancel/{reservationNo}") 
	 public ModelAndView reservationApplyCancel(ModelAndView mv, @PathVariable int reservationNo
			 ,Principal principal) {
		 
		 /* 직원 번호 불러옴 */ 
		 String employeeId = principal.getName();
		 EmployeeDTO employee = employeeReservationService.findByEmployeeId(employeeId);
		 int employeeNo = employee.getEmployeeNo();
		 
		 /* 예약 지원테이블 지원취소로 update */
		 employeeReservationService.modifyApplyEmployeeApplyCancelYn(reservationNo, employeeNo);
		 /* 예약테이블 지원인원 -1 */
		 employeeReservationService.modifyReservationApplyPeopleMinus(reservationNo);
		 /* 정원 != 지원인원일때 마감여부 n으로 변경 */
		 ReservationInfoDTO reservation = employeeReservationService.findReservationByReservationNo(reservationNo);
		 employeeReservationService.modifyReservationApplyEndYnByN(reservationNo);
		 
		 mv.addObject("applyMessage", "지원취소가 완료되었습니다");
		 mv.addObject("reservationNo", reservationNo);
		 mv.setViewName("reservation/reservationDetail");
		 
		 return mv;
	 }
	 
	 /* 즐겨찾기 등록 */
	 @GetMapping("/bookmark/{reservationNo}") 
	 public ModelAndView insertbookmark(ModelAndView mv, @PathVariable int reservationNo
			 ,Principal principal) {
		 
		 /* 직원 번호 불러옴 */ 
		 String employeeId = principal.getName();
		 EmployeeDTO employee = employeeReservationService.findByEmployeeId(employeeId);
		 int employeeNo = employee.getEmployeeNo();
		 
		 /* 즐겨찾기 테이블 insert */
		 BookMarkDTO newBookmark = new BookMarkDTO();
		 newBookmark.setBookmarkEmployeeNo(employeeNo);
		 newBookmark.setBookmarkReservationNo(reservationNo);
		 newBookmark.setBookmarkCancelYn("N");
		 employeeReservationService.insertNewBookmark(newBookmark);

		 mv.addObject("applyMessage", "즐겨찾기 등록이 완료되었습니다. 해당 예약이 지원가능상태로 변경 시 알림메세지를 전송하겠습니다.");
		 mv.addObject("reservationNo", reservationNo);
		 mv.setViewName("reservation/reservationDetail");
		 
		 return mv;
	 }
	 
	 /* 즐겨찾기 취소 */
	 @GetMapping("/bookmarkCancel/{reservationNo}") 
	 public ModelAndView modigyBookmarkCancel(ModelAndView mv, @PathVariable int reservationNo
			 ,Principal principal) {
		 
		 
		 
		 return mv;
	 }
}
