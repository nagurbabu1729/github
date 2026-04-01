package com.Productbasedcompany.employeeapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.Productbasedcompany.employeeapi.dto.AuthenticationRequest;
import com.Productbasedcompany.employeeapi.util.JwtUtil;

@RestController
public class AuthController {
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateuser(@RequestBody AuthenticationRequest request) {
		
		Authentication authentication =
		        authmanager.authenticate(
		            new UsernamePasswordAuthenticationToken(
		                request.getEmail(),
		                request.getPassword()
		            ));
		
		String token = jwtutil.generateToken(request.getEmail());
		
		return ResponseEntity.ok(token);
	}
	

}