package com.pathshala.mapper;

import com.pathshala.entity.Product;
import com.pathshala.payload.request.ProductRequestDto;
import com.pathshala.payload.response.ProductResponseDto;

public class ProductMapper {

    // Request DTO -> Entity
    public static Product toEntity(ProductRequestDto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setUnit(dto.getUnit());
        product.setProductCategory(dto.getProductCategory());
        return product;
    }

    //Entity -> ResponseDTO

    public static ProductResponseDto toResponseDto(Product product) {
        ProductResponseDto response = new ProductResponseDto();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setUnit(product.getUnit());
        return response;
    }
}
