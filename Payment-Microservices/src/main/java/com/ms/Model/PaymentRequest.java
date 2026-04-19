package com.ms.Model;
import lombok.Data;

@Data
public class PaymentRequest {
	
	private Long orderId;
    private Long productId;
    private Long amount;
    private Long quantity;
 
    private PaymentMode paymentMode;

}
