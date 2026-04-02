package com.ms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ms.Entity.Companies;
import com.ms.service.CompanyService;

@RestController
@CrossOrigin(origins = "http://localhost:4200") 
public class CompanyController {
	
	@Autowired
	private CompanyService cs;
	
	@GetMapping("/get/{id}")
	public Companies getEmployeeCompany(@PathVariable Integer id) {
		return cs.get(id);
	}
	
	@GetMapping("/getall")
	public List<Companies> getAllEmployees() {
		return cs.getAllEmployees();
	}
	
	
	@PatchMapping("/update/{address}")
	public Companies updateemployeebyid(@PathVariable String address, @RequestBody Companies updatedCompany) {
		return cs.updateemployeebyid(address,updatedCompany);
		
	}
}