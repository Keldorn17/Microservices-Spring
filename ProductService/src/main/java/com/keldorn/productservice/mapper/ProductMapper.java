package com.keldorn.productservice.mapper;

import com.keldorn.productservice.dto.ProductRequest;
import com.keldorn.productservice.dto.ProductResponse;
import com.keldorn.productservice.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toEntity(ProductRequest request);
    ProductResponse toResponse(Product product);
}
