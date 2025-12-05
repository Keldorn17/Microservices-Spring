package com.keldorn.orderservice.client;

import com.keldorn.orderservice.dto.BooleanResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    @GetExchange("/api/inventory")
    BooleanResponse isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
