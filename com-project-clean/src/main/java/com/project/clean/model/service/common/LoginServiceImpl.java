package com.project.clean.model.service.common;

import java.net.InetAddress;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.AdminIpAddress;
import com.project.clean.model.domain.commonEntity.Authority;
import com.project.clean.model.domain.joinEntity.AdminAndAdminMemberAuthority;
import com.project.clean.model.domain.joinEntity.AdminMemberRoleAndAuthority;
import com.project.clean.model.domain.joinEntity.EmployeeAndAdminMemberAuthority;
import com.project.clean.model.dto.commonDTO.AdminDTO;
import com.project.clean.model.dto.commonDTO.AdminIpAddressDTO;
import com.project.clean.model.dto.joinDTO.AdminAndAdminMemberAuthorityDTO;
import com.project.clean.model.dto.joinDTO.AdminImpl;
import com.project.clean.model.dto.joinDTO.EmployeeAndAdminMemberAuthorityDTO;
import com.project.clean.model.dto.joinDTO.EmployeeImpl;
import com.project.clean.model.repository.admin.AdminIpRepository;
import com.project.clean.model.repository.common.CommonAdminLoginRepository;
import com.project.clean.model.repository.common.CommonEmployeeLoginRepository;


@Service
public class LoginServiceImpl implements LoginService{

	private CommonAdminLoginRepository commonAdminLoginRepository;
	private CommonEmployeeLoginRepository commonEmployeeLoginRepository;
	private AdminIpRepository adminIpRepositroy;
	private ModelMapper modelMapper;
	

	/* Bean DI */
	@Autowired
	public LoginServiceImpl(CommonAdminLoginRepository commonLoginRepository, CommonEmployeeLoginRepository commonEmployeeLoginRepository, 
							ModelMapper modelMapper, AdminIpRepository adminIpRepositroy) {
		this.commonAdminLoginRepository = commonLoginRepository;
		this.commonEmployeeLoginRepository = commonEmployeeLoginRepository;
		this.adminIpRepositroy = adminIpRepositroy;
		this.modelMapper = modelMapper;
	}
	
	
	/* KS. 로그인 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		/* ID값으로 관리자 여부 확인 */
		if(!userId.contains("cleanup")) {
			
			/* employee select*/
			EmployeeAndAdminMemberAuthority employee = commonEmployeeLoginRepository.findByEmployeeIdAndEmployeeRetireYn(userId, "N");
			
			/* authorities 빈 객체 생성 */
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			Date lastLoginTime = new java.sql.Date(System.currentTimeMillis());
			
			/* 마지막 로그인 시간 Update */
			employee.setEmployeeLastLoginDate(lastLoginTime);
			
			/* 권한 설정 */
			if(employee.getEmployeeMemberRoleeeAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = employee.getEmployeeMemberRoleeeAndAuthority();
				
				for(int i = 0; i < roleList.size(); i++) {
					/* 권한 주입 */
					Authority list = roleList.get(i).getAuthority();
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			/* user 객체 재정의 */
			EmployeeImpl user = new EmployeeImpl(employee.getEmployeeId(), employee.getEmployeePwd(), authorities);
			user.SetDetailEmployee(modelMapper.map(employee, EmployeeAndAdminMemberAuthorityDTO.class));
			
			/* 블랙리스트 회원 처리 */
			if(user.getEmployeeBlackListYn().equals("Y")) {
				System.out.println("블랙리스트");
				throw new DisabledException(user.getEmployeeId());
			}
			
			/* 값 반환 */
			return user;
			
		} else {
			
			/* admin select*/
			AdminAndAdminMemberAuthority admin = commonAdminLoginRepository.findByAdminIdAndAdminRetireYn(userId, "N");
			
			/* authorities 빈 객체 등록 */
			List<GrantedAuthority> authorities = new ArrayList<>();

			/* select 실패 시 */
			if(admin == null) {
				admin = new AdminAndAdminMemberAuthority();
			} 
			
			/* 현재 시간 구하기 */
			Date lastLoginTime = new java.sql.Date(System.currentTimeMillis());
			
			/* 권한 설정 */
			if(admin.getAdminMemberRoleAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = admin.getAdminMemberRoleAndAuthority();
				
				/* 권한 주입 */
				for(int i = 0; i< roleList.size(); i++) {
					Authority list = roleList.get(i).getAuthority();
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			/* Mapping */
			AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
			
			/* 접속 IP 관련 설정 */
			AdminIpAddressDTO adminIp = new AdminIpAddressDTO();
			
			try {
				/* 접속 Session IP 주소 가져오기 */
	            InetAddress inetAddress = InetAddress.getLocalHost();
	            String strIpAdress = inetAddress.getHostAddress();
	            
	            /* 관리자 IP 주소 가져오기 */
	            String IpAdressNo = admin.getAdminId();
	            
	            /* 관리자가 접속 IP가 없을 때 신규 IP 등록*/
	            if(admin.getAdminIpAddress().isEmpty()) {
	            	
	            	if(admin.getAdminIpAddress().isEmpty()) {
	            		adminIp.setAdminNo(adminDTO.getAdminNo());
	            		adminIp.setIpAddressValue(strIpAdress);
	            		System.out.println(strIpAdress);
	            		adminIpRepositroy.save(modelMapper.map(adminIp, AdminIpAddress.class));
	            	}
	            	
	            	/* 관리자 접속 IP가 4개 이하일 때 */
	            } else if(admin.getAdminIpAddress().size() < 5) {
	            	
	            	/* 접속 IP가 기존 IP가 아닐 때 */
	            	if(!(admin.getAdminIpAddress().contains(strIpAdress))) {
	            		
	            		/* 신규 IP 등록 */
	            		adminIp.setAdminNo(adminDTO.getAdminNo());
	            		adminIp.setIpAddressValue(strIpAdress);
	            		System.out.println("TEST" + adminIp);
	            		System.out.println("TEST" + adminIp);
	            		System.out.println("TEST" + adminIp);
	            	}
	            
	            	/* 관리자 접속 IP가 4개 이상일 때 */
	            } else if(admin.getAdminIpAddress().size() > 4) {
	            	System.out.println("4개 이상 IP 확인");
	            	throw new DisabledException(IpAdressNo);
	            }
	            
	        } catch (java.net.UnknownHostException e) {
	        	
	            e.printStackTrace();
	            
	        }
			
			/* 마지막 로그인 시간 Update */
			admin.setAdminLastLoginDate(lastLoginTime);

			/* User 객체 재정의 */
			AdminImpl user = new AdminImpl(admin.getAdminId(), admin.getAdminPwd(), authorities);
			user.SetDetailsAdmin(modelMapper.map(admin, AdminAndAdminMemberAuthorityDTO.class));
			
			/* 값 반환 */
			return user;
			
		}
		
	}

}

