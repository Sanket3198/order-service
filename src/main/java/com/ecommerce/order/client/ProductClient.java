package com.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.order.dto.ProductResponseDto;

@FeignClient(name = "product-services", url = "http://localhost:8081")
public interface ProductClient {
	
	@GetMapping("/api/products/{id}")	
    ProductResponseDto getProduct(@PathVariable Long id);
}
