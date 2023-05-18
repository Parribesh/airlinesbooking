package com.synergisticit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergisticit.domain.User;

@Service
public interface UserService {
	
	String createUser(User user);
	User getUserById(Long userId);
	List<User> getAllUsers();
	String deleteUser(User user);
	User getUserByUsername(String username);
}
