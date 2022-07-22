
public class pagingService {
	   public Map<String, Object> findByCriteria(Integer deptCode, int jobGradeCode, Pageable pageable) {
		      Page<Employee> paging;
		      List<EmployeeDTO> empDTOList= new ArrayList<>();
		      Map<String,Object> map = new HashMap<>();
		      if(deptCode==0&&jobGradeCode!=0) {
		         paging = employeeRepository.findByJobGradeCode(jobGradeCode, pageable);
		      } else if(jobGradeCode==0&&deptCode!=0) {
		         paging = employeeRepository.findByDeptCode(deptCode, pageable);
		      } else if(jobGradeCode!=0&&deptCode!=0) {
		         paging=employeeRepository.findByDeptCodeAndJobGradeCode(deptCode, jobGradeCode, pageable);
		      } else {
		         paging= employeeRepository.findAll(pageable);
		      }
		      List<Employee> employeeList = paging.getContent();
		      
		      for (Employee employee : employeeList) {
		         EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		         employeeDTO.setEmpPwd("비밀번호접근은불가능합니다.");
		         if(employeeDTO.getEmpGender()=='M') {
		            employeeDTO.setEmpGender('남');
		         } else if(employeeDTO.getEmpGender()=='F') {
		            employeeDTO.setEmpGender('여');
		         }
		      }
		      int currentPage = paging.getNumber();
		      int maxPage = paging.getTotalPages();
		      int startPage = (int)(currentPage/5)*5;
		      int endPage = (int)(currentPage/5)*5+5;
		      
		      while(endPage>maxPage) {
		         endPage-=1;
		      }
		      
		      map.put("empList", empDTOList);
		      map.put("maxPage", maxPage);
		      map.put("startPage", startPage);
		      map.put("endPage", endPage);
		      map.put("currentPage", currentPage);
		      map.put("deptCode", deptCode);
		      map.put("jobGradeCode", jobGradeCode);
		      
		      
		      return map;
		   }
}
