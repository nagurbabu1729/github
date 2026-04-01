package com.Productbasedcompany.employeeapi.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService userdetailsservice;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String email = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) {
			
			token = authHeader.substring(7);
			email = jwtUtil.extractEmail(token);
		}
		
		if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userdetails = userdetailsservice.loadUserByUsername(email);
			
			if (jwtUtil.validatetoken(token, userdetails)) {
			    UsernamePasswordAuthenticationToken authToken =
			            new UsernamePasswordAuthenticationToken(
			                    userdetails,
			                    null,
			                    userdetails.getAuthorities()
			            );

			    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			    SecurityContextHolder.getContext().setAuthentication(authToken);
			}

		}
		filterChain.doFilter(request, response);	
	}

}
