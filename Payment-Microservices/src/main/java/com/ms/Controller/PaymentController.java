package com.ms.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ms.Model.PaymentRequest;
import com.ms.Service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
    private PaymentService paymentService;
	
	@PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequest request) {

        paymentService.processPayment(request);

        return ResponseEntity.status(HttpStatus.OK).body("Payment processed successfully");
    }

}