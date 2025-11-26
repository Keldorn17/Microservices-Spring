package com.keldorn.inventoryservice.service;

import com.keldorn.inventoryservice.dto.BooleanResponse;
import com.keldorn.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public BooleanResponse isInStock(String skuCode, Integer quantity) {
        return new BooleanResponse(inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity));
    }
}
