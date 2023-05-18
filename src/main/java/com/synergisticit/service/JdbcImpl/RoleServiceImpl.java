package com.synergisticit.service.JdbcImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Role;
import com.synergisticit.repository.jdbcTemplate.RoleJdbcRepository;
import com.synergisticit.service.RoleService;


public class RoleServiceImpl implements RoleService{
	
	@Autowired RoleJdbcRepository roleRepo;

	@Override
	public String createRole(Role role) {
		
		return roleRepo.createRole(role);
	}

	@Override
	public Role getRoleById(Long roldId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRolls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRole(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
