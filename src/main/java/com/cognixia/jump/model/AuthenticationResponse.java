package com.cognixia.jump.model;

// models object to send in response object.
	// doesn't represent a database table
public class AuthenticationResponse {

	private final String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
