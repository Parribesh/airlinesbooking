package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;


public interface RoleService {
	
	String createRole(Role role);
	Role getRoleById(Long roldId);
	List<Role> getAllRolls();
	String deleteRole(Long roleId);
	
	
}
