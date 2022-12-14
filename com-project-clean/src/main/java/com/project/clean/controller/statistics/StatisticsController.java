package com.project.clean.controller.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.project.clean.model.domain.adminEntity.AdminEmployee;
import com.project.clean.model.domain.joinEntity.AdminAndAdminPay;
import com.project.clean.model.dto.commonDTO.StatisticsDTO;
import com.project.clean.model.service.statistics.StatisticsService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

private final StatisticsService statisticsService;
private static final Random RANDOM = new Random(System.currentTimeMillis());

	@Autowired
	public StatisticsController(StatisticsService statisticsService) {
		System.out.print(1);
		this.statisticsService = statisticsService;
	}
	
	/* 관리자 총 평균 급여, 직급별 평균 급여 조회*/
	@GetMapping("/adminSalary")
	public ModelAndView adminAvgSalary(ModelAndView mv) {
			
		int totalAvgSalary = statisticsService.findTotalAvgSalaryWithQuery();
		List<StatisticsDTO> adminSalaryList = statisticsService.findAvgSalaryWithQuery();
			
		mv.addObject("totalAvgSalary", totalAvgSalary);
		mv.addObject("adminSalaryList", adminSalaryList);
			
		return mv;
	}
	
	/* 관리자 직급별 상세정보 조회 */
	@GetMapping("adminSalary/{jobCode}")
	public ModelAndView adminSalaryDetail(ModelAndView mv, @PathVariable int jobCode) {
		String job = "";
		switch (jobCode) {
			case 2 :	job = "총관리자";
						break;
			case 1 :	job = "일반관리자";
						break;
			case 3 :	job = "보안담당자";
						break;
			case 4 :	job = "인사관리자";
						break;
			case 5 :	job = "재정담당자";
						break;
		}
		System.out.println(job);
		
		List<AdminAndAdminPay> adminList = statisticsService.findByAdminJob(job);
		
		mv.addObject("adminList", adminList);
		mv.setViewName("/statistics/adminSalaryDetail");
		
		return mv;
	}
	
//	@GetMapping("ChartsAdminSalary")
//	public ModelAndView chartsAdminSalary (ModelAndView mv) {
//		string chartsAdminSalary = 
//		return null;
//	}
	
	/* 총 매출, 순 수익 조회 */
	@GetMapping("/totalSales")
	public ModelAndView totalSales(ModelAndView mv) {
			
		int totalSales = statisticsService.findTotalSalesyWithQuery();
		int netProfit = statisticsService.findNetProfitWithQuery();
			
		mv.addObject("totalSales", totalSales);
		mv.addObject("netProfit", netProfit);
			
		return mv;
	}
	
	/* 직원 누적근무 조회 */
	@GetMapping("employeeTotalWorkhours")
	public ModelAndView employeeHours(ModelAndView mv) {
		
		int employeeAvgHours = statisticsService.findAvgHoursWithQuery();
		int employeeTotalHours = statisticsService.findTotalHoursWithQuery();
		
		mv.addObject("employeeAvgHours", employeeAvgHours);
		mv.addObject("employeeTotalHours", employeeTotalHours);
		
		return mv;
	}
	
	/* 직원 개별근무시간 조회 */
	@GetMapping("employeeTotalWorkhoursDetail")
	public ModelAndView detail(ModelAndView mv) {
		
		List<AdminEmployee> employeeList = statisticsService.findByEmployeeRetireYn("N");
		mv.addObject("employeeList", employeeList);
		mv.setViewName("/statistics/employeeTotalWorkhoursDetail");
		
		return mv;
	}
	
	/* 이달의 우수직원 조회 */
	@GetMapping("bestEmployee")
	public ModelAndView bestEmployee(ModelAndView mv) {
//		List<EmployeeStatisticsDTO> employeeList = statisticsService.findBestEmployeeWithQuery();
		try {
		List<AdminEmployee> employeeList = statisticsService.findByEmployeeRetireYnOrderByEmployeeSumTimeDesc("N");
		List<AdminEmployee> bestEmployeeList = new ArrayList<AdminEmployee>();
		
		for(int i=0; i<5; i++) {
			bestEmployeeList.add(employeeList.get(i));
		}
				
		mv.addObject("bestEmployeeList", bestEmployeeList);
		mv.addObject("bestEmployee", bestEmployeeList.get(0));
		mv.setViewName("statistics/bestEmployee");
		
		return mv;
		
		}catch(java.util.NoSuchElementException e) {
			
			return mv;
		}
	}
	
	@GetMapping
    public String index(Model model) { 
        model.addAttribute("chartData", getChartData()); 
        return "index";
    }

    private List<List<Object>> getChartData() { 
        return List.of(
               List.of("총 매출금액", RANDOM.nextInt(2)),
               List.of("순 수익 금액", RANDOM.nextInt(2))     
        );
    }

}
