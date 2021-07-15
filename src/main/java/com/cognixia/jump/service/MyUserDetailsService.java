package com.cognixia.jump.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;

public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepo.findByUsername(username);
		
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found with username: \"" + username + "\"");
		}
		
		return new MyUserDetails(user.get());
	}

	public static MyUserDetails getCurrentUserDetails() {
	// source: https://dzone.com/articles/how-to-get-current-logged-in-username-in-spring-se
		try {
			MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return userDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Long getCurrentUserId() {
		MyUserDetails user = getCurrentUserDetails();
		return user == null ? -1L : user.getId();
	}
	
}
