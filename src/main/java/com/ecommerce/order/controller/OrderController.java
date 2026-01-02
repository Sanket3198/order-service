package com.ecommerce.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	OrderService service;
	
	@PostMapping
	public Order placeOrder(@RequestBody OrderRequest request) {
		return service.placeOrder(request);
	}
}
