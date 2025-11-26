package com.keldorn.orderservice.mapper;

import com.keldorn.orderservice.dto.OrderRequest;
import com.keldorn.orderservice.dto.OrderResponse;
import com.keldorn.orderservice.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequest request);
    OrderResponse toResponse(Order order);
}
