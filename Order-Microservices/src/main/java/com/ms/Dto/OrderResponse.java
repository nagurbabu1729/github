package com.ms.Dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	
	 	private long orderId;
	    private Instant orderDate;
	    private String orderStatus;
	    private long amount;
}