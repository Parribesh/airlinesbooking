package com.synergisticit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;


@RestController
public class RoleRestController {
	@Autowired RoleService roleService;
	
	@PostMapping("restapi/role/createRole")
	public String createRole(@Validated @RequestBody Role r, BindingResult br) {
		return roleService.createRole(r);
	}

	@GetMapping("restapi/role/getRole/{roleId}")
	public ResponseEntity<Role> getRole(@PathVariable Long roleId) {
		Role aquiredRole = roleService.getRoleById(roleId);
		return new ResponseEntity<Role>(aquiredRole, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("restapi/role/deleteRole/{roleId}")
	public String deleteRole(Role role, @PathVariable Long roleId) {
		Role acquiredRole = roleService.getRoleById(roleId);
		return roleService.deleteRole(roleId);
	}
	
	@GetMapping("restapi/role/updateRole/{roleId}")
	public Role updateRole(@PathVariable Long roleId) {
		Role acquiredRole = roleService.getRoleById(roleId);
		return acquiredRole;
	}
	
	@GetMapping("restapi/role/findAll")
	public List<Role> findAll() {
		return roleService.getAllRolls();
	}
	
}
