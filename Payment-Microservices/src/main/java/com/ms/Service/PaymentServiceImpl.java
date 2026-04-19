package com.ms.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.Entity.Payment;
import com.ms.Exception.PaymentException;
import com.ms.Model.PaymentRequest;
import com.ms.repo.PaymentRepository;


@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
    private PaymentRepository paymentRepository;

	@Override
	public void processPayment(PaymentRequest request) {
		
		if (request.getAmount() <= 0) {
		    throw new PaymentException("Invalid payment amount");
		}
		
		
        Payment payment = new Payment();

        payment.setOrderId(request.getOrderId());
        payment.setProductId(request.getProductId());
        payment.setAmount(request.getAmount());
        payment.setQuantity(request.getQuantity());
        payment.setPaymentMode(request.getPaymentMode()); 
        payment.setPaymentStatus("SUCCESS");

   
        paymentRepository.save(payment);

	}

}
