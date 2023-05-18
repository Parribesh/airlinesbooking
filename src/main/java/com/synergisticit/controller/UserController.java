package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.RoleTypes;
import com.synergisticit.domain.User;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;

@Controller
public class UserController {
	
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	@Autowired PasswordEncoder passwordEncoder;
	
	@GetMapping("api/user/userForm")
	public ModelAndView getUserForm(@ModelAttribute User user) {
		ModelAndView mv = new ModelAndView("userForm");
		mv.addObject("RoleTypes", RoleTypes.values());
		mv.addObject("users", userService.getAllUsers());
		mv.addObject("roles", roleService.getAllRolls());
		mv.addObject("user", user);
		return mv;
	}
	
	@PostMapping("api/user/createUser")
	public ModelAndView createUser(@ModelAttribute User user, BindingResult br) {
		ModelAndView mv = new ModelAndView("userForm");
		if(!br.hasErrors()) {
			user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
			userService.createUser(user);
		}
		mv.addObject("RoleTypes", RoleTypes.values());
		mv.addObject("users", userService.getAllUsers());
		mv.addObject("roles", roleService.getAllRolls());
		mv.addObject("user", user);
		return mv;
	}
	
	@GetMapping("api/user/updateUser/{userId}")
	public ModelAndView updateUser(@ModelAttribute User user, @PathVariable Long userId) {
		ModelAndView mv = new ModelAndView("userForm");
		User u = userService.getUserById(userId);
		mv.addObject("u", u);
		mv.addObject("RoleTypes", RoleTypes.values());
		mv.addObject("users", userService.getAllUsers());
		mv.addObject("roles", roleService.getAllRolls());
		mv.addObject("user", user);
		return mv;
	}
	
	@GetMapping("api/user/deleteUser/{userId}")
	public ModelAndView deleteUser(@ModelAttribute User user, @PathVariable Long userId) {
		ModelAndView mv = new ModelAndView("userForm");
		User u = userService.getUserById(userId);
		if(u != null) {
			userService.deleteUser(u);
		}
		mv.addObject("RoleTypes", RoleTypes.values());
		mv.addObject("users", userService.getAllUsers());
		mv.addObject("roles", roleService.getAllRolls());
		mv.addObject("user", user);
		return mv;
	}



}
