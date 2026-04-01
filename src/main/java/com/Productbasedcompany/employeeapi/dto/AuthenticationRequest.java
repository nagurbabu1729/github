package com.Productbasedcompany.employeeapi.dto;


public class AuthenticationRequest {
	
	
	private String email;
	
	private String Password;

	public String getEmail() {
		return email;
	}

	public void setUsername(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
}