package com.keldorn.orderservice.client;

import com.keldorn.orderservice.dto.BooleanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory", url = "${inventory.url}")
public interface InventoryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/inventory")
    BooleanResponse isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
