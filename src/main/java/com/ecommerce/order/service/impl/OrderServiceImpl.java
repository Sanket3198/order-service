package com.ecommerce.order.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.order.client.ProductClient;
import com.ecommerce.order.dto.ProductResponseDto;
import com.ecommerce.order.model.Order;
import com.ecommerce.order.repository.OrderServiceRepository;
import com.ecommerce.order.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderServiceRepository repository;
	
	@Autowired
    private ProductClient productClient;
	
	public Order placeOrder(Long productId, int quantity) {
        ProductResponseDto product = productClient.getProduct(productId);

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(
                product.getPrice().multiply(BigDecimal.valueOf(quantity))
        );

        return repository.save(order);
    }
}
