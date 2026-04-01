package com.Productbasedcompany.employeeapi.runners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Productbasedcompany.employeeapi.dao.EmployeeDao;
import com.Productbasedcompany.employeeapi.entity.Employee;


@Component
public class DataLoader implements CommandLineRunner{
	
	@Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // Check if default users already exist
        if (employeeDao.count() == 0) {

            Employee admin = new Employee();
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            admin.setLocation("Hyderabad");
            admin.setSalary(955452.20);
            admin.setProjectname("Default Project");

            Employee user = new Employee();
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setRole("ROLE_USER");
            user.setLocation("Chennai");
            user.setSalary(214525.25);
            user.setProjectname("Default Project");

            employeeDao.save(admin);
            employeeDao.save(user);

            System.out.println("🔥 Default users inserted!");
        } 
        else {
            System.out.println("⚡ Users already exist — skipping default insert.");
        }
    }

}