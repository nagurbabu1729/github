package com.Productbasedcompany.employeeapi.exceptionhandling;

@SuppressWarnings("serial")
public class ConflictException extends RuntimeException{
	
	public ConflictException(String message) {
		super(message);
	}

}