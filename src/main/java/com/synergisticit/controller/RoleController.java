package com.synergisticit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Role;
import com.synergisticit.domain.RoleTypes;
import com.synergisticit.service.RoleService;


@Controller
public class RoleController {
	
	@Autowired RoleService roleService;
	
	@GetMapping("/api/role/roleForm")
	public ModelAndView getRoleForm(@ModelAttribute Role role) {
		ModelAndView mv = new ModelAndView("roleForm");
		mv.addObject("roles", roleService.getAllRolls() );
		mv.addObject("roleTypes", RoleTypes.values());
		return mv;
	}
	
	@PostMapping("/api/role/createRole")
	public ModelAndView createRole(@Validated @ModelAttribute Role role, BindingResult br) {
		
		ModelAndView mv = new ModelAndView("roleForm");
		
		if(!br.hasErrors()) {
			roleService.createRole(role);
		}
		mv.addObject("roles", roleService.getAllRolls() );
		mv.addObject("roleTypes", RoleTypes.values());
		return mv;
	}
	
	@GetMapping("/api/role/updateRole/{roleId}")
	public ModelAndView updateRole(@ModelAttribute Role role, @PathVariable Long roleId) {
		
		ModelAndView mv = new ModelAndView("roleForm");
		Role r = roleService.getRoleById(roleId);
		mv.addObject("r", r);
		mv.addObject("roles", roleService.getAllRolls() );
		mv.addObject("roleTypes", RoleTypes.values());
		return mv;
	}

}
