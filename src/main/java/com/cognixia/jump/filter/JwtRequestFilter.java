package com.cognixia.jump.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		// check that header contains something that looks like a jwt with standard "Bearer <token>" syntax
		if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			// grab only token (remove text "Bearer ")
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extractUsername(jwt); // null if token is invalid
		}
		
		// if we found the user and not already in the security context (more info below on this)...
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			// ...load in their details
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			// now check to see that the token is valid based on given user as long as the jwt has not expired
			if( jwtUtil.validateToken(jwt, userDetails) ) {
				
				// all the code below is what is usually done by default by spring security, but since we set up our own
				// token based authentication, need to create an authentication token and placing it in the security context
				
				// security context -> stores info on the currently authenticated user (user who has generated a jwt and is
				// 					   using it to access APIs)
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request) );
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}
			
		}

		// pass on to next step in filter chain
		filterChain.doFilter(request, response);
	}

}