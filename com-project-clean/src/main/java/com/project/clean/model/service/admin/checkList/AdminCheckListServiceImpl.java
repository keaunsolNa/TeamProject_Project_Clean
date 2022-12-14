package com.project.clean.model.service.admin.checkList;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.domain.commonEntity.Admin;
import com.project.clean.model.domain.commonEntity.CheckList;
import com.project.clean.model.domain.commonEntity.Employee;
import com.project.clean.model.domain.commonEntity.ReservationInfo;
import com.project.clean.model.domain.joinEntity.ApplyEmployeeEmbedded;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.CheckListDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.joinDTO.CheckListAndReservationInfoAndEmployeeDTO;
import com.project.clean.model.repository.admin.AdminRepository;
import com.project.clean.model.repository.employee.EmpRepository;
import com.project.clean.model.repository.employee.apply.ApplyRepository;
import com.project.clean.model.repository.employee.apply.ReservationInfoRepository;
import com.project.clean.model.repository.employee.checkList.CheckListRepository;

@Service
@Transactional
public class AdminCheckListServiceImpl implements AdminCheckListService {

	private CheckListRepository checkListRepository;
	private ModelMapper modelMapper;
	private EmpRepository empRepository;
	private ReservationInfoRepository reservationInfoRepository;
	private ApplyRepository applyRepository;
	private AdminRepository adminRepository;
	
	@Autowired
	public AdminCheckListServiceImpl(CheckListRepository checkListRepository, ModelMapper modelMapper,
			EmpRepository empRepository, ReservationInfoRepository reservationInfoRepository, ApplyRepository applyRepository,
			AdminRepository adminRepository) {
		this.checkListRepository = checkListRepository;
		this.modelMapper = modelMapper;
		this.empRepository = empRepository;
		this.reservationInfoRepository = reservationInfoRepository;
		this.applyRepository = applyRepository;
		this.adminRepository = adminRepository;
	}
	

	/* KS. CheckList ?????? ?????? */
	@Override
	public CheckListDTO selectCheckListDetails(String adminId, int checkReservationNo, int parameter) {
		
		/* ??? ?????? ?????? */
		CheckList checkList = new CheckList();
		
		try {
			
			/* ??????????????? ??????????????? ?????? */
			checkList = checkListRepository.findByCheckReservationNo(checkReservationNo);
			
			/* ?????? ????????? ?????? ?????? ?????????????????? ?????? ????????? ?????? */
			if(parameter == 1) {
				
				Admin admin = adminRepository.findByAdminId(adminId);
				
				int adminNo = admin.getAdminNo();
				
				checkList.setAdminNo(adminNo);
				
				checkList.setCheckStatus("R");
				
			} 
			
			/* ?????????????????? ?????? ??? ???????????? */
		} catch (java.util.NoSuchElementException e) {
			
			CheckListDTO checkListDTO = new CheckListDTO();
			
			System.out.println("???????????? ??????????????? ??????.");
			
			return checkListDTO;
		}
		
		/* ????????? ?????? */
		return modelMapper.map(checkList, CheckListDTO.class);
	}

	/* KS. CheckList ?????? ??? ?????? */
	@Override
	public int modifyCheckList(CheckListDTO checkList) {
		
		/* ??????????????? ??????????????? ?????? */
		CheckList checkListEntity = checkListRepository.findByCheckReservationNo(checkList.getCheckReservationNo()); 
		
		/* ??????????????? HTML ??? ?????? */
		checkListEntity.setCheckHTML(checkList.getCheckHTML());

		int reservationNo = checkListEntity.getCheckReservationNo();
		ReservationInfo reservationInfo = reservationInfoRepository.findByReservationNo(checkList.getCheckReservationNo());
		
		/* ??????????????? List<ApplyEmployeeEmbedded> ?????? */
		List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply2(reservationNo);
		
		/* ?????? ??????, ?????? ?????? ????????? */
		java.util.Date startTime = reservationInfo.getBusinessStartTime();
		java.util.Date endTime = reservationInfo.getBusinessEndTime();
		
		/* SimpleDateFormat */
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH"); 
		String strDate = simpleDateFormat.format(startTime);
		String endDate = simpleDateFormat.format(endTime);
		Integer strbnsDate = Integer.valueOf(strDate);
		Integer endbnxDate = Integer.valueOf(endDate);
		
		/* ??? ?????? ?????? ????????? */
		int bnsDate = endbnxDate - strbnsDate;
		
		/* parameter??? ????????? ????????? ????????? ??????????????? ?????? ??????*/
		if(checkList.getCheckStatus().equals("D")) {
			
			checkListEntity.setCheckStatus("D");
			
		} else if(checkList.getCheckStatus().equals("A")) {
			
			checkListEntity.setCheckStatus("A");
			
			
			for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
				
				Integer employeeNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyEmployeeNo();
						
				Employee employee = empRepository.findByEmployeeNo(employeeNo);
				int time = employee.getEmployeeSumTime();
				employee.setEmployeeSumTime(time + bnsDate);
			}
			
			
		} else if(checkList.getCheckStatus().equals("B")) {
			
			/* ?????? ??? ?????? ???????????? ?????? ?????? */
			checkListEntity.setCheckStatus("B");
			
			/* for??? ?????? */
			for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
				
				/* ???????????? ?????? ?????? ?????? */
				Integer employeeNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyEmployeeNo();
				
				/* ??????????????? ?????? Entity ?????? */
				Employee employee = empRepository.findByEmployeeNo(employeeNo);
				
				/* ??????????????? ?????? ?????? ?????? + 1 ????????? ??????*/
				int sumCount = employee.getEmployeeSumCount()+1;
				
					if(sumCount >= 5) {
						employee.setEmployeeBlackListYn("Y");
					}
					
				/* ??????????????? ?????? ?????? */
				employee.setEmployeeSumCount(sumCount);
				int time = employee.getEmployeeSumTime();
				employee.setEmployeeSumTime(time + bnsDate);
			}
		}
		
		return 0;
	}

	/* ??????????????? ?????? ??? ????????? */
	@Override
	public Map<String, Object> selectCheckList(String adminId, int parameter, 
			Pageable pageable) {
		
			Page<CheckList> paging;
			List<CheckListDTO> checkList = new ArrayList<>();
			Map<String, Object> map = new HashMap<>();
			String status = "";
			String status2 = "";
			/* ??????????????? adminEntity ?????? */
			Admin admin = adminRepository.findByAdminId(adminId);
			
			/* AdminDTO??? ?????? */
			AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
			
			/* AdminNo Integer ????????? ?????? */
			Integer adminNo = adminDTO.getAdminNo();
			
			if(parameter == 1) {
				status = "R";
				paging =  checkListRepository.findAllByCheckStatus(status, pageable);
			} else if(parameter == 2) {
				status = "D";
				status2 = "E";
				paging =  checkListRepository.findAllByCheckStatusOrCheckStatusAndAdminNo(status, status2, adminNo, pageable);
			} else if(parameter == 3) {
				status = "A";
				paging =  checkListRepository.findAllByCheckStatusAndAdminNo(status, adminNo, pageable);
			} else if(parameter == 4) {
				status = "B";
				paging =  checkListRepository.findAllByCheckStatusAndAdminNo(status, adminNo, pageable);
			} else {
				paging =  checkListRepository.findAllByCheckStatus(status, pageable);
			}
				
			List<CheckListAndReservationInfoAndEmployeeDTO> checkListAndReservationInfoAndEmployeeList = new ArrayList<>();
			
			try {
				
				List<CheckList> checkArrayList = paging.getContent();
				
				checkList = checkArrayList.stream().map(list -> modelMapper.map(list, CheckListDTO.class)).toList();
				
				/* ????????? ?????? */
				int currentPage = paging.getNumber();
				int maxPage = paging.getTotalPages();
				int startPage = (int)(currentPage / 5) * 5;
				int endPage = (int)(currentPage / 5) * 5 + 5;
				
				while (endPage > maxPage) {
					endPage -=1;
				}
				
				map.put("maxPage", maxPage);
				map.put("startPage", startPage);
				map.put("endPage", endPage);
				map.put("currentPage", currentPage);
				
				/* ????????? AdminName ????????? ?????? */
				String adminName = "";
				
				/* for-each??? ?????? */
				for (CheckListDTO checkLists : checkList) {
					
					/* ?????? ???????????? ????????? ????????? ????????? ?????????????????? ?????? */
					if((checkLists.getAdminNo() == null || checkLists.getAdminNo() == adminNo)) {
						
						String checkHTML = checkLists.getCheckHTML();
						String checkStatus = checkLists.getCheckStatus();
						Integer checkReservationNo = checkLists.getCheckReservationNo();
						
						/* checkList?????? reservationNo ?????? */
						Integer reservationNo = checkLists.getCheckReservationNo();
						
						/* reservationNo??? ReservationInfo Entity ?????? */
						ReservationInfo reservationInfo = reservationInfoRepository.findByReservationNo(reservationNo);
						
						/* ReservationInfoEntity DTO??? ?????? */
						ReservationInfoDTO reservationInfoDTO = modelMapper.map(reservationInfo, ReservationInfoDTO.class);
						
						/* ReservationInfoDTO ?????? ?????? ?????? ???????????? */
						String userName = reservationInfoDTO.getUserName();
						
						/* DTO??? reservationNo??? List<ApplyEmployeeEmbedded> ?????? */
						List<ApplyEmployeeEmbedded> applyEmployeeList = applyRepository.findAllEmployeeApply2(reservationNo);
						
						/* 2??? for-each??? ?????? */
						for (ApplyEmployeeEmbedded applyEmployeeEmbedded : applyEmployeeList) {
							
							/* ????????? ApplyEmployeeEmbeddedEntity??? employeeNo ?????? */
							Integer employeeNo = applyEmployeeEmbedded.getApplyEmployeeIdAndApplyReservationNo().getApplyEmployeeNo();
							
							/* ????????? employeeNo??? EmployeeEntity ?????? */
							Employee employee = empRepository.findByEmployeeNo(employeeNo);
							
							/* EmployeeEntityEmployeeDTO??? ?????? */
							EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
							
							/* EmployeeDTO?????? employeeName ?????? ???????????? */
							String employeeId = employeeDTO.getEmployeeId();
							
							/* ????????? DTO ?????? ?????? */
							CheckListAndReservationInfoAndEmployeeDTO checkListAndReservationInfoAndEmployeeDTO = new CheckListAndReservationInfoAndEmployeeDTO();
							
							/* ??? ?????? */
							checkListAndReservationInfoAndEmployeeDTO.setEmployeeName(employeeId);
							checkListAndReservationInfoAndEmployeeDTO.setCustomerName(userName);
							checkListAndReservationInfoAndEmployeeDTO.setCheckReservationNo(checkReservationNo);
							
							/* View????????? ????????? checkList ????????? ?????? */
							if(checkStatus.equals("R")) {
								checkStatus = "?????????";
							} else if(checkStatus.equals("D")) {
								checkStatus = "??????";
							} else if(checkStatus.equals("A")) {
								checkStatus = "??????";
							} else if(checkStatus.equals("B")) {
								checkStatus = "??????";
							} else if(checkStatus.equals("E")) {
								checkStatus = "???????????????";
							}
							
							/* ??? ?????? */
							checkListAndReservationInfoAndEmployeeDTO.setCheckStatus(checkStatus);
							checkListAndReservationInfoAndEmployeeDTO.setCheckHTML(checkHTML);
							
							/* ????????? ????????? ?????? */
							if(checkLists.getAdminNo() == adminNo) {
								adminName = adminDTO.getAdminName();
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
							} else {
								adminName = "????????? ??????";
								checkListAndReservationInfoAndEmployeeDTO.setAdminName(adminName);
							}
							
							/*  List<CheckListAndReservationAndEmployee> ????????? ??? ?????? */
							checkListAndReservationInfoAndEmployeeList.add(checkListAndReservationInfoAndEmployeeDTO);
							
						}
					}
					
					}
				
					map.put("checkList", checkListAndReservationInfoAndEmployeeList);
					return map;
				
			} catch(java.util.NoSuchElementException e) {
				
				System.out.println("?????? ??????????????? ??????");
				
				return map;
			}
			
		}

}


