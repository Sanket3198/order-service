package com.ecommerce.order.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.client.InventoryClient;
import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.ProductResponseDto;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderServiceRepository;
import com.ecommerce.order.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderServiceRepository orderRepository;
	
	@Autowired
    ProductClient productClient;
	
	@Autowired
	InventoryClient inventoryClient;
	
	public Order placeOrder(OrderRequest request) {
        ProductResponseDto product = productClient.getProduct(request.getProductId());
        
        boolean inStock = inventoryClient.checkStock(
                request.getProductId(),
                request.getQuantity()
        );
        
        if (!inStock) {
            throw new RuntimeException("Product out of stock");
        }
        
        inventoryClient.reduceStock(
                request.getProductId(),
                request.getQuantity()
        );

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(
                product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity()))
        );

        return orderRepository.save(order);
    }
}
