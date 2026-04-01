package com.Productbasedcompany.employeeapi.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Productbasedcompany.employeeapi.dto.EmployeeRequest;
import com.Productbasedcompany.employeeapi.dto.EmployeeResponse;
import com.Productbasedcompany.employeeapi.entity.Employee;
import com.Productbasedcompany.employeeapi.service.EmployeeService;

import jakarta.validation.Valid;

import com.Productbasedcompany.employeeapi.controller.EmployeeController;


@Validated
@RestController
//@RequestMapping("/public")
public class EmployeeController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	
	@Autowired
	private EmployeeService empservice;
	
	@PostMapping("/employee-add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>addEmployee(@Valid @RequestBody EmployeeRequest emp) {
		
			logger.info("api entered to addEmployee");
			
			logger.info("role"+emp.getRole());
		
			EmployeeResponse res = empservice.insertEmployee(emp);
			
			logger.info("inserted successfully in table");
			return ResponseEntity.status(HttpStatus.CREATED).body(res);

	}
	
	@GetMapping("/employee-get/{employeeid}")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?>getemployeebyid(@PathVariable Integer employeeid)  {
		
		EmployeeResponse res = empservice.getEmployeeByid(employeeid);
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	
	}
	
	@PatchMapping("update-emp/{email}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>updatelocationByEmail(@PathVariable String email, @RequestBody EmployeeRequest er) {
		
		EmployeeResponse res = empservice.updateLocationByEmail(email, er.getLocation());
		
		return ResponseEntity.status(HttpStatus.OK).body("updated employee newlocation"+er.getLocation());

	}
	
	@PutMapping("update-all/{email}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>updateAllDetailsByEmployee(@PathVariable String email, @RequestBody EmployeeRequest er) {
		
		EmployeeResponse response = empservice.updateAllDetailsOfEmployee(email,er);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/getall")
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<?>getAllEmployees() {
		
		List<Employee>AllEmployees = empservice.getAllEmployees();
		
		return ResponseEntity.status(HttpStatus.OK).body(AllEmployees);
	}
	
	@DeleteMapping("/remove-emp/{email}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>removeEmployeeByEmail(@PathVariable String email) {
		
		empservice.removeEmployeeByEmail(email);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/remove-all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>removeAllEmployees() {
		
		int count  = empservice.removeAllEmployees();
		
		return ResponseEntity.status(HttpStatus.OK).body(count +":employee deleted");

	}

}