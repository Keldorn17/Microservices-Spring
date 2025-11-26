package com.keldorn.orderservice.service;

import com.keldorn.orderservice.dto.OrderRequest;
import com.keldorn.orderservice.dto.OrderResponse;
import com.keldorn.orderservice.mapper.OrderMapper;
import com.keldorn.orderservice.model.Order;
import com.keldorn.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse placeOrder(OrderRequest request) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .price(request.price())
                .skuCode(request.skuCode())
                .quantity(request.quantity())
                .build();
        return orderMapper.toResponse(orderRepository.save(order));
    }
}
