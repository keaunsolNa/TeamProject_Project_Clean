package com.project.clean.model.repository.employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.clean.model.domain.adminEntity.AdminEmployeeAddress;

public interface EmployeeAddressRepository extends JpaRepository<AdminEmployeeAddress, Integer>{

	@Query(value = "   SELECT * \r\n"
				    + "  FROM TBL_EMPLOYEE_ADDRESS A\r\n"
					+ "  JOIN TBL_EMPLOYEE B ON (A.EMPLOYEE_NO = B.EMPLOYEE_NO)\r\n"
					+ " WHERE B.EMPLOYEE_RETIRE_YN = 'Y'\r\n"
					+ "   AND B.EMPLOYEE_LAST_CONFIRM_YN = 'Y'", nativeQuery = true)
	List<AdminEmployeeAddress> selectRetireYEmployeeAddressList();

	@Query(value = "   SELECT * \r\n"
					+ "  FROM TBL_EMPLOYEE_ADDRESS A\r\n"
					+ "  JOIN TBL_EMPLOYEE B ON (A.EMPLOYEE_NO = B.EMPLOYEE_NO)\r\n"
					+ " WHERE B.EMPLOYEE_RETIRE_YN = 'N'\r\n"
					+ "   AND B.EMPLOYEE_LAST_CONFIRM_YN = 'Y'", nativeQuery = true)
	List<AdminEmployeeAddress> selectRetireNEmployeeAddressList();

	
	@Query(value = " SELECT\r\n"
				 + "        A.EMPLOYEE_NO\r\n"
				 + "      , A.EMPLOYEE_ADDRESS_NO\r\n"
				 + "      , A.EMPLOYEE_ADDRESS\r\n"
				 + "      , A.EMPLOYEE_DETAIL_ADDRESS\r\n"
				 + "   FROM TBL_EMPLOYEE_ADDRESS A\r\n"
				 + "   JOIN TBL_EMPLOYEE B ON (A.EMPLOYEE_NO = B.EMPLOYEE_NO)\r\n"
				 + "  WHERE B.EMPLOYEE_FIRST_CONFIRM_YN = 'N'\r\n"
				 + "    AND B.EMPLOYEE_SECOND_CONFIRM_YN = 'N'"
				 + "    AND B.EMPLOYEE_REGIST_RETURN_YN = 'N'"
				 + "    AND B.EMPLOYEE_LAST_CONFIRM_YN = 'N'", nativeQuery = true)
	List<AdminEmployeeAddress> findByFirstConfirmN();

	@Query(value = " SELECT\r\n"
				 + "        A.EMPLOYEE_NO\r\n"
				 + "      , A.EMPLOYEE_ADDRESS_NO\r\n"
				 + "      , A.EMPLOYEE_ADDRESS\r\n"
				 + "      , A.EMPLOYEE_DETAIL_ADDRESS\r\n"
				 + "   FROM TBL_EMPLOYEE_ADDRESS A\r\n"
				 + "   JOIN TBL_EMPLOYEE B ON (A.EMPLOYEE_NO = B.EMPLOYEE_NO)\r\n"
				 + "    AND B.EMPLOYEE_REGIST_RETURN_YN = 'Y'", nativeQuery = true)
	List<AdminEmployeeAddress> findByRegistReturnN();

	@Query(value = "SELECT\r\n"
				 + "       A.EMPLOYEE_NO\r\n"
				 + "     , A.EMPLOYEE_ADDRESS_NO\r\n"
				 + "     , A.EMPLOYEE_ADDRESS\r\n"
				 + "     , A.EMPLOYEE_DETAIL_ADDRESS\r\n"
				 + "  FROM TBL_EMPLOYEE_ADDRESS A\r\n"
				 + "  JOIN TBL_EMPLOYEE B ON (A.EMPLOYEE_NO = B.EMPLOYEE_NO)\r\n"
				 + " WHERE B.EMPLOYEE_FIRST_CONFIRM_YN = 'Y'\r\n"
				 + "   AND B.EMPLOYEE_SECOND_CONFIRM_YN = 'N'\r\n"
				 + "   AND B.EMPLOYEE_LAST_CONFIRM_YN = 'N'\r\n"
				 + "   AND B.EMPLOYEE_REGIST_RETURN_YN = 'N'", nativeQuery = true)
	List<AdminEmployeeAddress> secondConfirmN();


}


