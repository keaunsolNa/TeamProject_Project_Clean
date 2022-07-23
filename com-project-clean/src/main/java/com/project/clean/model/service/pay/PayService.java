package com.project.clean.model.service.pay;

import java.util.List;

import com.project.clean.controller.common.paging.SelectCriteria;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.ApplyEmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeeDTO;
import com.project.clean.model.dto.commonDTO.EmployeePayDTO;
import com.project.clean.model.dto.commonDTO.ReservationInfoDTO;
import com.project.clean.model.dto.commonDTO.SurchargeDTO;
import com.project.clean.model.dto.joinDTO.AdminAndAdminPayDTO;
import com.project.clean.model.dto.joinDTO.AdminPayAndAdminDTO;
import com.project.clean.model.dto.joinDTO.BestEmployeePayAndEmployeeDTO;
import com.project.clean.model.dto.joinDTO.EmployeePayAndApplyEmployeeDTO;

public interface PayService {
	
	// 부가요금 조회
	public List<SurchargeDTO> findSurchargeList();
	
	// 부가요금 수정
	void modifySurcharge(SurchargeDTO surcharge);

	// 관리자 급여 전체 조회 페이징처리 카운트
	public int selectAdminPayTotalCount(String searchCondition, String searchValue);

	// 관리자 급여 전체 조회
	public List<AdminPayAndAdminDTO> adminPaySearch(SelectCriteria selectCriteria);

	// 관리자 급여 상세 조회
	public AdminPayAndAdminDTO findAdminPayByPayHistoryAdminNo(int payHistoryAdminNo);
	
	// 급여를 한번도 받지 않은 신입 관리자 조회
	public List<AdminAndAdminPayDTO> findNullAdmin();
	
	// 이번 달 급여를 받은 관리자 조회
	public List<AdminAndAdminPayDTO> findPaidAdmin();
	
	// 모든 관리자 조회
	public List<AdminAndAdminPayDTO> findAllAdmin();

	// 관리자 번호로 관리자 한명 조회
	public AdminDTO findAdminByAdminNo(int adminNo);
	
	// 관리자 급여 등록
	public void registAdminPay(int adminNo, int salary, int insurance);

	
	// 직원
	public int selectEmployeePayTotalCount(String searchCondition, String searchValue);

	public List<EmployeePayAndApplyEmployeeDTO> employeePaySearch(SelectCriteria selectCriteria);
	
	
	// 예약번호로 예약별직원 찾기
	public List<ApplyEmployeeDTO> findByApplyReservationNo(int reservationNo);
	
	// 예약번호로 청소급여 찾기
	public ReservationInfoDTO findByTotalPaymentByReservationNo(int reservationNo);

	// 직원 급여 지급
	public void registEmployeePay(int applyReservationNo, int applyEmployeeNo, int payEmployeeFinalSalary);
	
	// 직원 상세 조회
	public EmployeePayAndApplyEmployeeDTO findEmployeePayByPayHistoryEmployeeNo(int payHistoryEmployeeNo);
	
	// 모든 직원 찾기
	public List<EmployeeDTO> findAllEmployee();
	
	// 이달의 우수사원 급여 지급
	public void registBestEmployeePay(int bestEmployeeNo, int bestEmployeeBonus);
	
	// 이달의 우수사원 급여 전체 조회 페이징처리 카운트
	public int selectBestEmployeePayTotalCount(String searchCondition, String searchValue);

	// 이달의 우수사원 급여 전체 조회
	public List<BestEmployeePayAndEmployeeDTO> bestEmployeePaySearch(SelectCriteria selectCriteria);
	

	
	// 내 급여----------------------------------------------------
	// 관리자 내 급여 카운트
	public int selectMyPayForAdminTotalCount(int adminNo);

	public List<AdminPayAndAdminDTO> myPayForAdmin(int adminNo, SelectCriteria selectCriteria);
	
	// 아이디로 관리자 찾기
	public AdminDTO findAdminByAdminId(String adminId);
	
	
	// 아이디로 직원 찾기
	public EmployeeDTO findEmployeeByEmployeeId(String employeeId);
	
	// 직원 내 급여 카운트
	public int selectMyPayForEmployeeTotalCount(int employeeNo);

	// 직원 내 급여 페이징
	public List<EmployeePayAndApplyEmployeeDTO> myPayForEmployee(int employeeNo, SelectCriteria selectCriteria);
	
	


	


	
	
}







	



