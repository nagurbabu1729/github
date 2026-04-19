package com.ms.Model;

import lombok.Data;

@Data
public class PaymentResponse {
	
	 private Long paymentId;
	 private String status;
	 private String message;

}