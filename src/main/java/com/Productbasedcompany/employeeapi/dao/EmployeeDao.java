package com.Productbasedcompany.employeeapi.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Productbasedcompany.employeeapi.entity.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{

	Optional<Employee> findByEmail(String email);

	boolean existsByEmail(String email);
		

}