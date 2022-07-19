
public class pagingController {
	   @GetMapping("list/criteria")
	   @ResponseBody
	   public Map<String, Object> findByCriteria(Integer deptCode, int jobGradeCode, @PageableDefault(sort="employeeNo") Pageable pageable) {
	      
	      Map<String,Object> map = new HashMap<>();
	      
	      map = empService.findByCriteria(deptCode, jobGradeCode, pageable);
	      
	      return map;
	   }
}
