package com.ms.Service;

import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ms.Dto.OrderRequest;
import com.ms.Entity.Orders;
import com.ms.Repository.OrderRepository;
import com.ms.external.ProductClient;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private ProductClient pc;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	public long placeOrder(OrderRequest orderRequest) {
		
		pc.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		
		Orders orders = new Orders();
		
		orders.setProductId(orderRequest.getProductId());
		orders.setAmount(orderRequest.getTotalAmount());
		orders.setQuantity(orderRequest.getQuantity());
		orders.setOrderStatus("Created");
		orders.setOrderDate(Instant.now());
		
		orders = orderRepository.save(orders);
		return orders.getId();
	}
	
}