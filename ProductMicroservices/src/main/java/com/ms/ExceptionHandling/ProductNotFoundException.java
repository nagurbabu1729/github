package com.ms.ExceptionHandling;



@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException {
	
	public ProductNotFoundException(String msg) {
		super(msg);
	}

}