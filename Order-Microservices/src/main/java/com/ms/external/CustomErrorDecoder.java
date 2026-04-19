package com.ms.external;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.exception.ErrorResponse;
import com.ms.exception.OrderServiceException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {
		
		ObjectMapper objectmapper = new ObjectMapper();
		objectmapper.findAndRegisterModules();
		
		try {
			ErrorResponse errorResponse = objectmapper.readValue(response.body().asInputStream(),ErrorResponse.class);
			
			return new OrderServiceException(errorResponse.getMessage());
			
		} catch (IOException e) {
			
			throw new OrderServiceException("Internal Server Error");
		}
		
	}

}
