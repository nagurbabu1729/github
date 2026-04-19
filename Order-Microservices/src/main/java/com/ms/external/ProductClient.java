package com.ms.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="product")
public interface ProductClient {
	
	@PutMapping("/product/reducequantity/{ProductId}")
	public void reduceQuantity(@PathVariable("ProductId") Long Productid, @RequestParam("quantity") Long quantity);

}
