package com.ms.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ms.Dto.PaymentRequest;

@FeignClient(name = "Payment-Microservices")
public interface PaymentClient {
	
	@PostMapping("/process")
    String processPayment(@RequestBody PaymentRequest request);

}