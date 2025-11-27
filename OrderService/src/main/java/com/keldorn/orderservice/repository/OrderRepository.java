package com.keldorn.orderservice.repository;

import com.keldorn.orderservice.model.Order;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<@NonNull Order, @NonNull Long> {
}
