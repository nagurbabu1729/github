package com.Productbasedcompany.employeeapi.Aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Before("execution(* com.Productbasedcompany.employeeapi.controller.EmployeeController.addEmployee(..))")
	public void addEmployee() {
		
		log.info("api start:{addEmployee}");
		
	}
	
}