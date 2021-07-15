package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.InvalidDataInput;
import com.cognixia.jump.exception.ResouceNotFoundException;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetailsService;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	UserRepository repo;
	
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//		} catch (Exception e) {
//			throw new Exception("Username or password is incorrect.");
//		}
//		
//		return ResponseEntity.ok(null);
//	}
	
	@GetMapping("/user")
	public User getCurrentUser() throws ResouceNotFoundException {
		try {
			return repo.findById(MyUserDetailsService.getCurrentUserId()).get();
		} catch(Exception e) {
			throw new ResouceNotFoundException(e.getMessage());
		}
	}
	
	@PostMapping("/user")
	public User createUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	@DeleteMapping("/user")
	public boolean deleteUser(@RequestBody User user) {
		repo.deleteById(MyUserDetailsService.getCurrentUserId());
		return false;
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) {
		return repo.save(user);
	}
}
