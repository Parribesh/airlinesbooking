package com.synergisticit.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired UserService userService;
	@Autowired BCryptPasswordEncoder bcryptEncoder;
	
	@PostMapping("restapi/user/createUser")
	public String createUser(@RequestBody User user) {
		user.setUserPassword(bcryptEncoder.encode(user.getUserPassword()));
		return userService.createUser(user);
	}
	
	@GetMapping("restapi/user/getUserById/{userId}")
	public User getUserById(@PathVariable Long userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("restapi/user/getAllUsers")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("restapi/user/deleteUserById/{userId}")
	public String deleteUserById(@PathVariable Long userId){
		return userService.deleteUser(userService.getUserById(userId));
	}
}
