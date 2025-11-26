package com.keldorn.orderservice.controller;

import com.keldorn.orderservice.dto.OrderRequest;
import com.keldorn.orderservice.dto.OrderResponse;
import com.keldorn.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }
}
