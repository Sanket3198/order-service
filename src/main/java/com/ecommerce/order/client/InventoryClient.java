package com.ecommerce.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "http://localhost:8083")
public interface InventoryClient {

	@GetMapping("/inventory/check")
    boolean checkStock(@RequestParam Long productId,
                       @RequestParam int quantity);
	
	@PostMapping("/inventory/reduce")
    void reduceStock(@RequestParam Long productId,
                     @RequestParam int quantity);
}
