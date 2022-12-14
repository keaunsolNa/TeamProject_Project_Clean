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
	
	
	/* KS. ????????? */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		/* ID????????? ????????? ?????? ?????? */
		if(!userId.contains("cleanup")) {
			
			/* employee select*/
			EmployeeAndAdminMemberAuthority employee = commonEmployeeLoginRepository.findByEmployeeIdAndEmployeeRetireYn(userId, "N");
			
			/* authorities ??? ?????? ?????? */
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			Date lastLoginTime = new java.sql.Date(System.currentTimeMillis());
			
			/* ????????? ????????? ?????? Update */
			employee.setEmployeeLastLoginDate(lastLoginTime);
			
			/* ?????? ?????? */
			if(employee.getEmployeeMemberRoleeeAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = employee.getEmployeeMemberRoleeeAndAuthority();
				
				for(int i = 0; i < roleList.size(); i++) {
					/* ?????? ?????? */
					Authority list = roleList.get(i).getAuthority();
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			/* user ?????? ????????? */
			EmployeeImpl user = new EmployeeImpl(employee.getEmployeeId(), employee.getEmployeePwd(), authorities);
			user.SetDetailEmployee(modelMapper.map(employee, EmployeeAndAdminMemberAuthorityDTO.class));
			
			/* ??????????????? ?????? ?????? */
			if(user.getEmployeeBlackListYn().equals("Y")) {
				System.out.println("???????????????");
				throw new DisabledException(user.getEmployeeId());
			} else if(null == user.getEmployeeHireDate()) {
				System.out.println("?????? ?????? ??????");
				throw new DisabledException(user.getEmployeeId());
			}
			
			/* ??? ?????? */
			return user;
			
		} else {
			
			/* admin select*/
			AdminAndAdminMemberAuthority admin = commonAdminLoginRepository.findByAdminIdAndAdminRetireYn(userId, "N");
			
			/* authorities ??? ?????? ?????? */
			List<GrantedAuthority> authorities = new ArrayList<>();

			/* select ?????? ??? */
			if(admin == null) {
				admin = new AdminAndAdminMemberAuthority();
			} 
			
			/* ?????? ?????? ????????? */
			Date lastLoginTime = new java.sql.Date(System.currentTimeMillis());
			
			/* ?????? ?????? */
			if(admin.getAdminMemberRoleAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = admin.getAdminMemberRoleAndAuthority();
				
				/* ?????? ?????? */
				for(int i = 0; i< roleList.size(); i++) {
					Authority list = roleList.get(i).getAuthority();
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			/* Mapping */
			AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
			
			/* ?????? IP ?????? ?????? */
			AdminIpAddressDTO adminIp = new AdminIpAddressDTO();
			
			try {
				/* ?????? Session IP ?????? ???????????? */
	            InetAddress inetAddress = InetAddress.getLocalHost();
	            String strIpAdress = inetAddress.getHostAddress();
	            
	            /* ????????? IP ?????? ???????????? */
	            String IpAdressNo = admin.getAdminId();
	            
	            /* ???????????? ?????? IP??? ?????? ??? ?????? IP ??????*/
	            if(admin.getAdminIpAddress().isEmpty()) {
	            	
	            	if(admin.getAdminIpAddress().isEmpty()) {
	            		adminIp.setAdminNo(adminDTO.getAdminNo());
	            		adminIp.setIpAddressValue(strIpAdress);
	            		System.out.println(strIpAdress);
	            		adminIpRepositroy.save(modelMapper.map(adminIp, AdminIpAddress.class));
	            	}
	            	
	            	/* ????????? ?????? IP??? 4??? ????????? ??? */
	            } else if(admin.getAdminIpAddress().size() < 5) {
	            	
	            	/* ?????? IP??? ?????? IP??? ?????? ??? */
	            	if(!(admin.getAdminIpAddress().contains(strIpAdress))) {
	            		
	            		/* ?????? IP ?????? */
	            		adminIp.setAdminNo(adminDTO.getAdminNo());
	            		adminIp.setIpAddressValue(strIpAdress);
	            	}
	            
	            	/* ????????? ?????? IP??? 4??? ????????? ??? */
	            } else if(admin.getAdminIpAddress().size() > 4) {
	            	System.out.println("4??? ?????? IP ??????");
	            	throw new DisabledException(IpAdressNo);
	            }
	            
	        } catch (java.net.UnknownHostException e) {
	        	
	            e.printStackTrace();
	            
	        }
			
			/* ????????? ????????? ?????? Update */
			admin.setAdminLastLoginDate(lastLoginTime);

			/* User ?????? ????????? */
			AdminImpl user = new AdminImpl(admin.getAdminId(), admin.getAdminPwd(), authorities);
			user.SetDetailsAdmin(modelMapper.map(admin, AdminAndAdminMemberAuthorityDTO.class));
			
			/* ??? ?????? */
			return user;
			
		}
		
	}

}

