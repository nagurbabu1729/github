package com.ms.Service;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.Dto.OrderRequest;
import com.ms.Dto.PaymentRequest;
import com.ms.Entity.Orders;
import com.ms.Repository.OrderRepository;
import com.ms.external.PaymentClient;
import com.ms.external.ProductClient;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private ProductClient pc;
	
	@Autowired
	private PaymentClient paymentClient;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public long placeOrder(OrderRequest orderRequest) {
		
		
		// reduce quantity
		pc.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
		
		// orders
		Orders orders = new Orders();
		
		orders.setProductId(orderRequest.getProductId());
		orders.setAmount(orderRequest.getTotalAmount());
		orders.setQuantity(orderRequest.getQuantity());
		orders.setOrderStatus("Created");
		orders.setOrderDate(Instant.now());
		
		orders = orderRepository.save(orders);
		
		
		//payment status
		PaymentRequest paymentRequest = new PaymentRequest();
	    paymentRequest.setOrderId(orders.getId());
	    paymentRequest.setProductId(orderRequest.getProductId());
	    paymentRequest.setAmount(orderRequest.getTotalAmount());
	    paymentRequest.setQuantity(orderRequest.getQuantity());
	    paymentRequest.setPaymentMode(orderRequest.getPaymentMode());
	    
	    
	    // calling payment service
	    try {
	        paymentClient.processPayment(paymentRequest);

	        orders.setOrderStatus("COMPLETED");

	    } catch (Exception e) {

	        orders.setOrderStatus("FAILED");
	    }

	    
	    orderRepository.save(orders);

		
		return orders.getId();
	}
	
}