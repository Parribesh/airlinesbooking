package com.synergisticit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.User;
import com.synergisticit.repository.UserRepository;
import com.synergisticit.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired UserRepository userRepo;

	@Override
	public String createUser(User user) {
		if(user == null) return "failure";
		userRepo.save(user);
		return "success";
	}

	@Override
	public User getUserById(Long userId) {
		return userRepo.findById(userId).orElse(null);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public String deleteUser(User user) {
		if(user == null) return "failure";
		userRepo.delete(user);
		return "success";
	}

	@Override
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
