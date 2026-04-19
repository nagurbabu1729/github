package com.ms.exception;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
	
	private LocalDateTime timeStamp;
	
	private String message;
	
	private int status;
	
	private String error;
	
	private String path;
	
	public ErrorResponse(int status, String error, String message, String path) {
		//super();
		this.timeStamp = LocalDateTime.now();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
}