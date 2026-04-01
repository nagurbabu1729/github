package com.Productbasedcompany.employeeapi.exceptionhandling;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandling {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?>ResourceNotFoundException(ResourceNotFoundException rx, HttpServletRequest request) {
		
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),rx.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);	
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<?>ConflictException(ConflictException ce,HttpServletRequest request) {
		
		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(),HttpStatus.CONFLICT.getReasonPhrase(),ce.getMessage(),request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?>handleinternalServerError(Exception e, HttpServletRequest request) {
//		
//		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),e.getMessage(),request.getRequestURI());
//
//		
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//		
//	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?>handleBadRequestException(BadRequestException bre, HttpServletRequest request) {
		
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(), bre.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<?>handleNoHandler(NoHandlerFoundException Nhe, HttpServletRequest request) {
		
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(), Nhe.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValid(
	            MethodArgumentNotValidException ex) {

	        Map<String, String> errors = new HashMap<>();

	        ex.getBindingResult().getFieldErrors()
	          .forEach(error ->
	              errors.put(error.getField(), error.getDefaultMessage()));

	        return ResponseEntity.badRequest().body(errors);
	    }
}