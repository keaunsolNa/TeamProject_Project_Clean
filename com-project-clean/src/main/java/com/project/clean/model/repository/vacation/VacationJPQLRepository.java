package com.project.clean.model.repository.vacation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.project.clean.model.domain.commonEntity.Vacation;

@Repository
public class VacationJPQLRepository {
	
	@PersistenceContext	// 어노테이션이면 스프링부트가 영속성 컨텍스트를 관리하는 엔티티 매니저를 가져온다.
	private EntityManager em;

	/* 휴가 신청자명 검색 */
	public List<Vacation> findByRequestAdminName(String searchValue) {
		
		
		String jpql = "select "
				   + "	     v "
				   + "from VACATION v \r\n"
				   + "join ADMIN a ON (a.adminNo = v.adminNo)\r\n"
				   + "where v.vacationFirstConfirmYn = : vacationFirstConfirmYn\r\n"
				   + "  and v.vacationReturnYn = :vacationReturnYn\r\n"
				   + "	and a.adminRetireYn = :adminRetireYn\r\n"
				   + "	and v.requestAdmin LIKE requestAdmin \r\n"
				   + "order by v.vacationNo desc";
		
		return em.createQuery(jpql, Vacation.class)
				.setParameter("requestAdmin", searchValue)
				.setParameter("vacationFirstConfirmYn", "N")
				.setParameter("vacationReturnYn", "N")
				.setParameter("adminRetireYn", "N")
				.setFirstResult(0)
				.setMaxResults(3)
				.getResultList();
	}

	/* 휴가 시작일 검색 */
	public List<Vacation> findByVacationStartDate(String searchValue) {
		
		String jpql = "SELECT "
				+ "      v "
				+ "FROM VACATION v \r\n"
				+ "JOIN ADMIN a ON (a.adminNo = v.adminNo)\r\n"
				+ "WHERE v.vacationFirstConfirmYn = : vacationFirstConfirmYn \r\n"
				+ "  AND v.vacationReturnYn = :vacationReturnYn \r\n"
				+ "	AND a.adminRetireYn = :adminRetireYn \r\n"
				+ "	AND v.vacationStartDate = :vacationStartDate \r\n"
				+ "ORDER BY v.vacationNo DESC";
			
		
		return em.createQuery(jpql, Vacation.class) 
			    .setParameter("vacationStartDate", searchValue)
			    .setParameter("vacationFirstConfirmYn", "N")
				.setParameter("vacationReturnYn", "N")
				.setParameter("adminRetireYn", "N")
			    .setFirstResult(0)
				.setMaxResults(3)
				.getResultList();
	}

	/* 휴가 종료일 검색 */
	public List<Vacation> findByVacationEndDate(String searchValue) {
	
		String jpql = "SELECT "
				+ "      v "
				+ "FROM VACATION v \r\n"
				+ "JOIN ADMIN a ON (a.adminNo = v.adminNo)\r\n"
				+ "WHERE v.vacationFirstConfirmYn = : vacationFirstConfirmYn \r\n"
				+ "  AND v.vacationReturnYn = :vacationReturnYn \r\n"
				+ "	AND a.adminRetireYn = :adminRetireYn \r\n"
				+ "	AND v.vacationEndDate = :vacationEndDate \r\n"
				+ "ORDER BY v.vacationNo DESC";
		
		return em.createQuery(jpql, Vacation.class)
				 .setParameter("vacationEndDate", searchValue)
				 .setParameter("vacationFirstConfirmYn", "N")
				 .setParameter("vacationReturnYn", "N")
				 .setParameter("adminRetireYn", "N")
				 .setFirstResult(0)
				 .setMaxResults(3)
				 .getResultList();
	}

	/* 휴가명 검색 */
	public List<Vacation> findByVacationName(String searchValue) {
		
		 String jpql = "SELECT "
					+ "      v "
					+ "FROM VACATION v \r\n"
					+ "JOIN ADMIN a ON (a.adminNo = v.adminNo)\r\n"
					+ "WHERE v.vacationFirstConfirmYn = : vacationFirstConfirmYn \r\n"
					+ "  AND v.vacationReturnYn = :vacationReturnYn \r\n"
					+ "	AND a.adminRetireYn = :adminRetireYn \r\n"
					+ "	AND v.vacationName = :vacationName \r\n"
					+ "ORDER BY v.vacationNo DESC";
				
		return em.createQuery(jpql, Vacation.class)
				 .setParameter("vacationName", searchValue)
				 .setParameter("vacationFirstConfirmYn", "N")
				 .setParameter("vacationReturnYn", "N")
				 .setParameter("adminRetireYn", "N")
				 .setFirstResult(0)
				 .setMaxResults(3)
				 .getResultList();
	}

	

//	public List<Vacation> countByRequestAdminContaining(String searchValue) {
//		
//		String jpql = "SELECT \r\n"
//				   + "	   count(*)\r\n"
//				   + "FROM TBL_VACATION v \r\n"
//				   + "JOIN TBL_ADMIN a ON (a.ADMIN_NO = v.ADMIN_NO)\r\n"
//				   + "WHERE v.VACATION_FIRST_CONFIRM_YN = 'Y'\r\n"
//				   + "  AND v.VACATION_RETURN_YN = 'N'\r\n"
//				   + "	AND a.ADMIN_RETIRE_YN = 'N'\r\n"
//				   + "	AND v.REQUEST_ADMIN = :requestAdmin\r\n"
//				   + "ORDER BY v.VACATION_NO DESC";
//		
//		return em.createQuery(jpql, Vacation.class)
//				.setParameter("requestAdmin", searchValue)
//				.setFirstResult(3)
//				.setMaxResults(3)
//				.getResultList();
//	}

	
	
	
	

}
