package com.ms.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.Entity.Companies;
import com.ms.dao.CompanyDao;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyDao cd;
	
	public Companies get(Integer id) {
		return cd.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
	}
	
	
	public List<Companies> getAllEmployees() {
		return cd.findAll();
	}
	
	public Companies updateemployeebyid(String address, Companies comss) {
		
		Optional<Companies> com = cd.findByAddress(address);
		
		if(com.isPresent()) {
			Companies com1 = com.get();
			com1.setAddress(comss.getAddress());
			cd.save(com1);
			return com1;
		}
		return null;

	}

	
}