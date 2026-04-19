package com.ms.Entity;


import com.ms.Model.PaymentMode;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    private Long orderId;

	    private Long productId;

	    private Long amount;

	    private Long quantity;

	    private String paymentStatus;

	    @Enumerated(EnumType.STRING)
	    private PaymentMode paymentMode;

}
