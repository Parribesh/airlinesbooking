package com.synergisticit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.RoleRepository;
import com.synergisticit.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired RoleRepository roleRepo;
	
	@Override
	public String createRole(Role role) {
		roleRepo.save(role);
		return "success";
	}

	@Override
	public Role getRoleById(Long roldId) {	
		return roleRepo.findById(roldId).orElse(null);
	}

	@Override
	public List<Role> getAllRolls() {
		return roleRepo.findAll();
	}

	@Override
	public String deleteRole(Long roleId) {
		Role r = roleRepo.findById(roleId).orElse(null);
		if(r != null) {
			roleRepo.delete(r);
		}
		return null;
	}

}
