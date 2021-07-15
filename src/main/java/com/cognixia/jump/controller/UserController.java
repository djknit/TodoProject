package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetailsService;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@GetMapping("/user")
	public UserDetails getCurrentUser() {
		return MyUserDetailsService.getCurrentUserDetails();
	}
	
//	 NEED:
//		update username/password/names
//		add new user
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return repo.save(user);
	}
	
//		delete ? ? ?
	@DeleteMapping("/user")
	public boolean deleteUser(@RequestBody User user) {
		repo.deleteById(MyUserDetailsService.getCurrentUserId());
		return false;
	}
}
