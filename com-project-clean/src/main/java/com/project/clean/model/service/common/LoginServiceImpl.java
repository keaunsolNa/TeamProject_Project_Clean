package com.project.clean.model.service.common;

import java.net.InetAddress;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		System.out.println(userId);

		if(!userId.contains("cleanup")) {
			
			System.out.println("EMPLOYEE 조회 시작");
			/* employee select*/
			EmployeeAndAdminMemberAuthority employee = commonEmployeeLoginRepository.findByEmployeeIdAndEmployeeRetireYn(userId, "N");
			
			System.out.println("조회 해 온 멤버 객체 : " + employee);
			/* authorities 빈 객체 생성 */
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			/* 조회 실패 시 */
			if(employee == null) {
				employee = new EmployeeAndAdminMemberAuthority();
			}
			
			Date lastLoginTime = new java.sql.Date(System.currentTimeMillis());
			
			employee.setEmployeeLastLoginDate(lastLoginTime);
			
			if(employee.getEmployeeMemberRoleeeAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = employee.getEmployeeMemberRoleeeAndAuthority();
				
				for(int i = 0; i < roleList.size(); i++) {
					Authority list = roleList.get(i).getAuthority();
					System.out.println(list.getName());
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			EmployeeImpl user = new EmployeeImpl(employee.getEmployeeId(), employee.getEmployeePwd(), authorities);
			user.SetDetailEmployee(modelMapper.map(employee, EmployeeAndAdminMemberAuthorityDTO.class));
			return user;
			
		} else {
			
			/* admin select*/
			AdminAndAdminMemberAuthority admin = commonAdminLoginRepository.findByAdminIdAndAdminRetireYn(userId, "N");
			System.out.println("조회 해 온 멤버 객체 : " + admin);
			
			/* authorities 빈 객체 등록 */
			List<GrantedAuthority> authorities = new ArrayList<>();

			/* select 실패 시 */
			if(admin == null) {
				admin = new AdminAndAdminMemberAuthority();
			} 
			
			Date lastLoginTime = new java.sql.Date(System.currentTimeMillis());
			
			
			if(admin.getAdminMemberRoleAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = admin.getAdminMemberRoleAndAuthority();
				
				for(int i = 0; i< roleList.size(); i++) {
					Authority list = roleList.get(i).getAuthority();
					System.out.println(list.getName());
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			AdminDTO adminDTO = modelMapper.map(admin, AdminDTO.class);
			AdminIpAddressDTO adminIp = new AdminIpAddressDTO();
			
			System.out.println("조회해온 멤버 변환 객체 : " + adminDTO);
			try {
	            InetAddress inetAddress = InetAddress.getLocalHost();
	            String strIpAdress = inetAddress.getHostAddress();
	            String IpAdressNo = admin.getAdminId();
	            
	            System.out.println("조회해온 IP 주소 : " + strIpAdress);
	            
	            if(null != strIpAdress) {
	            	if(admin.getAdminIpAddress().isEmpty()) {
	            		adminIp.setAdminNo(adminDTO.getAdminNo());
	            		adminIp.setIpAddressValue(strIpAdress);
	            		
	            		adminIpRepositroy.save(modelMapper.map(adminIp, AdminIpAddress.class));
	            	}
	            } else if(admin.getAdminIpAddress().size() < 4) {
	            	if(!(admin.getAdminIpAddress().contains(strIpAdress))) {
	            		
	            		adminIp.setAdminNo(adminDTO.getAdminNo());
	            		adminIp.setIpAddressValue(strIpAdress);
	            		
	            		adminIpRepositroy.save(modelMapper.map(adminIp, AdminIpAddress.class));
	            	}
	            } else {
	            	
	            }
	            
	        } catch (java.net.UnknownHostException e) {
	            e.printStackTrace();
	        }
			
			admin.setAdminLastLoginDate(lastLoginTime);

			
			AdminImpl user = new AdminImpl(admin.getAdminId(), admin.getAdminPwd(), authorities);
			
			System.out.println("재정의 전 AdminImpl 객체 :" + user);
			user.SetDetailsAdmin(modelMapper.map(admin, AdminAndAdminMemberAuthorityDTO.class));
			System.out.println("재정의 후 AdminImpl 객체 :" + user);
			return user;
			
		}
		
	}

}
