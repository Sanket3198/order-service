package com.ecommerce.order.service;

import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.model.Order;

public interface OrderService {
	
	Order placeOrder(OrderRequest request);
}
