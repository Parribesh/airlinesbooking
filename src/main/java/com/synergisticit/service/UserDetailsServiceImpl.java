package com.synergisticit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.getUserByUsername(username);
		List<Role> roles = user.getUserRoles();
		 List<GrantedAuthority> authorities = new ArrayList<>();	 
		 for(Role r: roles) {
			 authorities.add(new SimpleGrantedAuthority(r.getRoleName().name()));
		 }
		 
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getUserPassword(), authorities);
	}

}
