package com.cognixia.jump.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

import com.cognixia.jump.exception.InvalidDataInputException;
import com.cognixia.jump.exception.ResouceNotFoundException;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Username or password is incorrect.");
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateTokens(userDetails);		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/user")
	public ResponseEntity<?> getCurrentUser() throws Exception {
		User user = repo.findById(MyUserDetailsService.getCurrentUserId()).get();
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) throws InvalidDataInputException {
		try {
			return ResponseEntity.ok(repo.save(user));
		} catch(Exception e) {
			throw new InvalidDataInputException("Account was not created.", e);
		}
	}
	
	@DeleteMapping("/user")
	public ResponseEntity<?> deleteUser(@RequestBody User user) throws Exception {
		repo.deleteById(MyUserDetailsService.getCurrentUserId());
		return ResponseEntity.ok(null);
	}
	
	@PutMapping("/user")
	public User updateUser(@RequestBody User user) throws Exception {
		return repo.save(user);
	}
}
