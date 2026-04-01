package com.Productbasedcompany.employeeapi.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.Productbasedcompany.employeeapi.dao.EmployeeDao;
import com.Productbasedcompany.employeeapi.dto.EmployeeRequest;
import com.Productbasedcompany.employeeapi.dto.EmployeeResponse;
import com.Productbasedcompany.employeeapi.entity.Employee;
import com.Productbasedcompany.employeeapi.exceptionhandling.BadRequestException;
import com.Productbasedcompany.employeeapi.exceptionhandling.ConflictException;
import com.Productbasedcompany.employeeapi.exceptionhandling.ResourceNotFoundException;

@Validated
@Service
public class EmployeeService {
	
	private PasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	@Autowired
	private EmployeeDao empdao;
	
	// reusable         // encapsulation
	private EmployeeResponse mapToResponse(Employee emp) {
        EmployeeResponse res = new EmployeeResponse();
        res.setEmail(emp.getEmail());
        res.setSalary(emp.getSalary());
        res.setLocation(emp.getLocation());
        res.setProjectname(emp.getProjectname());
        res.setRole(emp.getRole());
        return res;
    }
	
	@SuppressWarnings("unused")
	private void validateEmployeeRequest(EmployeeRequest er) {
	    if (er.getEmail() == null || !er.getEmail().contains("@")) {
	    	logger.error("Validation failed for email: {}", er.getEmail());
	        throw new BadRequestException("Invalid email format");
	    }
	    if (er.getLocation() == null || er.getLocation().isBlank()) {
	    	logger.error("Validation failed for location: {}", er.getLocation());
	        throw new BadRequestException("Location is required");
	    }
	    if (er.getProjectname() == null || er.getProjectname().isBlank()) {
	    	logger.error("Validation failed for project: {}", er.getProjectname());
	        throw new BadRequestException("Project is required");
	    }
	    if (er.getSalary() <= 0) {
	        throw new BadRequestException("Salary must be greater than 0");
	    }
	}

	
	public EmployeeResponse insertEmployee(EmployeeRequest er)  {

		logger.info("api entered into service class addEmployee");
		
		validateEmployeeRequest(er);
		
		if(!empdao.existsByEmail(er.getEmail())) {
			
			Employee emp = new Employee();
			emp.setEmail(er.getEmail());
			emp.setPassword(encoder.encode(er.getPassword()));
			emp.setSalary(er.getSalary());
			emp.setLocation(er.getLocation());
			emp.setProjectname(er.getProjectname());
			emp.setRole("ROLE_"+er.getRole());
			
			Employee saved = empdao.save(emp);
			
			logger.info("employee added in db successfully");
			
			return mapToResponse(saved);
		}
		
		else {
			logger.warn("Employee already exists with this email: {}", er.getEmail());
			throw new ConflictException("employee already exists with this email: "+ er.getEmail());
		}
		
	}
	
	public EmployeeResponse getEmployeeByid(Integer employeeid)  {
		
		
		Employee employee = empdao.findById(employeeid).orElseThrow(()->
			new ResourceNotFoundException("employee not found with this employeeid :"+ employeeid)
		);
		
		return mapToResponse(employee);
		
	}
	
	
	public EmployeeResponse updateLocationByEmail(String email, String new_location) {
		
		Employee employee = empdao.findByEmail(email).orElseThrow(()->
							new ResourceNotFoundException("employee not found with this email :"+ email));
		
		 if (new_location == null || new_location.isBlank()) {
	            throw new BadRequestException("Location cannot be empty");
	        }
			
		 employee.setLocation(new_location);
		 Employee updated = empdao.save(employee);
		 
		 return mapToResponse(updated);
	}
	
	public EmployeeResponse updateAllDetailsOfEmployee(String email, EmployeeRequest er) {
	
		validateEmployeeRequest(er);
		
		Employee employee = empdao.findByEmail(email).orElseThrow(()->
		new ResourceNotFoundException("employee not found with this email :"+ email));
		
		if (!employee.getEmail().equals(er.getEmail())) {
		    throw new BadRequestException("Email cannot be updated");
		}

			employee.setLocation(er.getLocation());
			employee.setProjectname(er.getProjectname());
			employee.setSalary(er.getSalary());
			Employee updated = empdao.save(employee);
			
			return mapToResponse(updated);
		}
	
	public List<Employee> getAllEmployees() {
		
		return empdao.findAll();
		
	}
	
	public String removeEmployeeByEmail(String email) {
		
		
		Employee employee = empdao.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("employee not found with email: "+email));
		empdao.delete(employee);
		return "deleted employee with: "+email;
	}

	
	public int removeAllEmployees() {
		
		List<Employee> AllEmployees = empdao.findAll();
		
		if (AllEmployees.isEmpty()) {
		   throw new ResourceNotFoundException("No employees found in table");
		}

		int count = AllEmployees.size();
		empdao.deleteAll();
		return count;

	}
	
}