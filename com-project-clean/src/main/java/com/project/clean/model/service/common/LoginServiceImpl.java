package com.project.clean.model.service.common;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.clean.model.domain.commonEntity.Authority;
import com.project.clean.model.domain.joinEntity.AdminAndAdminMemberAuthority;
import com.project.clean.model.domain.joinEntity.AdminMemberRoleAndAuthority;
import com.project.clean.model.domain.joinEntity.EmployeeAndAdminMemberAuthority;
import com.project.clean.model.dto.joinDTO.AdminImpl;
import com.project.clean.model.dto.joinDTO.EmployeeImpl;
import com.project.clean.model.repository.Common.CommonAdminLoginRepository;
import com.project.clean.model.repository.Common.CommonEmployeeLoginRepository;

@Service
public class LoginServiceImpl implements LoginService{

	private CommonAdminLoginRepository commonAdminLoginRepository;
	private CommonEmployeeLoginRepository commonEmployeeLoginRepository;
	private ModelMapper modelMapper;
	
	
	
	@Autowired
	public LoginServiceImpl(CommonAdminLoginRepository commonLoginRepository, CommonEmployeeLoginRepository commonEmployeeLoginRepository, ModelMapper modelMapper) {
		this.commonAdminLoginRepository = commonLoginRepository;
		this.commonEmployeeLoginRepository = commonEmployeeLoginRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		System.out.println(userId);
		
		
		if(!userId.contains("cleanup")) {
			/* employee */
			EmployeeAndAdminMemberAuthority employee = commonEmployeeLoginRepository.findByEmployeeId(userId);
			
			if(employee == null) {
				employee = new EmployeeAndAdminMemberAuthority();
				
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("ROLE_FAIL"));
			}
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			if(employee.getEmployeeMemberRoleeeAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = employee.getEmployeeMemberRoleeeAndAuthority();
				
				for(int i = 0; i < roleList.size(); i++) {
					Authority list = roleList.get(i).getAuthority();
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			EmployeeImpl user = new EmployeeImpl(employee.getEmployeeId(), employee.getPwd(), authorities);
			
			return new User(employee.getEmployeeId(), employee.getPwd(), authorities);
			
		} else {
			/* admin */
			AdminAndAdminMemberAuthority admin = commonAdminLoginRepository.findByAdminId(userId);

			if(admin == null) {
				admin = new AdminAndAdminMemberAuthority();
			}
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			if(admin.getAdminMemberRoleAndAuthority() != null) {
				List<AdminMemberRoleAndAuthority> roleList = admin.getAdminMemberRoleAndAuthority();
				
				for(int i = 0; i< roleList.size(); i++) {
					Authority list = roleList.get(i).getAuthority();
					authorities.add(new SimpleGrantedAuthority(list.getName()));
				}
			}
			
			AdminImpl user = new AdminImpl(admin.getAdminId(), admin.getAdminPwd(), authorities);
			
			return new User(admin.getAdminId(), admin.getAdminPwd(), authorities);
			
		}
		
	}

}
