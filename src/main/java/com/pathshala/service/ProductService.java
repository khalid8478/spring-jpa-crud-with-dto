package com.pathshala.service;

import com.pathshala.payload.request.ProductRequestDto;
import com.pathshala.payload.response.ProductResponseDto;

import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(ProductRequestDto productDto);
    ProductResponseDto updateProduct(ProductRequestDto productDto, Long id);
    ProductResponseDto getProduct(Long id);
    List<ProductResponseDto> getAllProducts();
    List<ProductResponseDto> getAllProductsByCategory(String category);
    String deleteProduct(Long id);

}
