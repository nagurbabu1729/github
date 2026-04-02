package com.ms.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ms.Entity.Companies;

@Repository
public interface CompanyDao extends JpaRepository<Companies,Integer>{

	Optional<Companies> findByAddress(String address);

	

	
}