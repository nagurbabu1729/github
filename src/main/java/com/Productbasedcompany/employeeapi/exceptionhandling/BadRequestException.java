package com.Productbasedcompany.employeeapi.exceptionhandling;

@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String message) {
		super(message);
	}

}
