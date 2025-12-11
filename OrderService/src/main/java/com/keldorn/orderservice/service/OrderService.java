package com.keldorn.orderservice.service;

import com.keldorn.orderservice.client.InventoryClient;
import com.keldorn.orderservice.dto.OrderRequest;
import com.keldorn.orderservice.dto.OrderResponse;
import com.keldorn.orderservice.event.OrderPlacedEvent;
import com.keldorn.orderservice.mapper.OrderMapper;
import com.keldorn.orderservice.model.Order;
import com.keldorn.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public OrderResponse placeOrder(OrderRequest request) {
        var isProductInStock = inventoryClient.isInStock(request.skuCode(), request.quantity());

        if (isProductInStock.result()) {
            Order order = Order.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .price(request.price())
                    .skuCode(request.skuCode())
                    .quantity(request.quantity())
                    .build();

            OrderPlacedEvent orderPlacedEvent = OrderPlacedEvent.builder()
                    .orderNumber(order.getOrderNumber())
                    .email(request.userDetails().email())
                    .build();
            log.info("Start - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("End - Sending OrderPlacedEvent {} to Kafka topic order-placed", orderPlacedEvent);

            return orderMapper.toResponse(orderRepository.save(order));
        }
        throw new RuntimeException("Product with SkuCode " + request.skuCode() + " is not in stock");
    }
}
