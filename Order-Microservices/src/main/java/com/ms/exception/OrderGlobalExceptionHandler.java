package com.ms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class OrderGlobalExceptionHandler {

	@ExceptionHandler(OrderServiceException.class)
	public ResponseEntity<ErrorResponse>HanldleProductNotFoundExcpetion(OrderServiceException rx, HttpServletRequest request) {
		
		ErrorResponse error = new ErrorResponse(
	            HttpStatus.BAD_REQUEST.value(),
	            HttpStatus.BAD_REQUEST.getReasonPhrase(),
	            rx.getMessage(),
	            request.getRequestURI()
	    );

	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}

}
