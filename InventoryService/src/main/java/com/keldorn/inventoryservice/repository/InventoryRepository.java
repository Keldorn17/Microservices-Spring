package com.keldorn.inventoryservice.repository;

import com.keldorn.inventoryservice.model.Inventory;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<@NonNull Inventory, @NonNull Long> {
    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode, Integer quantity);
}
