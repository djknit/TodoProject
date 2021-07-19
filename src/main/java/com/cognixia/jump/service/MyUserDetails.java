package com.cognixia.jump.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognixia.jump.model.User;

public class MyUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	// attributes from User that are needed to do our authorization/authentication
	private Long id;
	private String username;
	private String password;
	private List<GrantedAuthority> authorities;
	
	public MyUserDetails() { }
	public MyUserDetails(String username) {
		this.username = username;
	}
	public MyUserDetails(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities = Arrays.asList( new SimpleGrantedAuthority( user.getRole().name() ) );
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public Long getId() {
		return id;
	}
	

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
