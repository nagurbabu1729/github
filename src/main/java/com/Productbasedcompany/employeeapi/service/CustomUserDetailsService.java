package com.Productbasedcompany.employeeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Productbasedcompany.employeeapi.dao.EmployeeDao;
import com.Productbasedcompany.employeeapi.entity.Employee;
import com.Productbasedcompany.employeeapi.exceptionhandling.ResourceNotFoundException;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeDao empdao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee emp = empdao.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("employee not found"));
		
		return new CustomUserDetails(emp);
	}
		
}