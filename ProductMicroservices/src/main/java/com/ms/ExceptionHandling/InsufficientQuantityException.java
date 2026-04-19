package com.ms.ExceptionHandling;


@SuppressWarnings("serial")
public class InsufficientQuantityException extends RuntimeException{
	
	public InsufficientQuantityException(String msg) {
        super(msg);
    }

}