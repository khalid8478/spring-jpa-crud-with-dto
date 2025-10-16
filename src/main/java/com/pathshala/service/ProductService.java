package com.pathshala.service;

import com.pathshala.entity.Product;
import com.pathshala.payload.request.ProductRequestDto;
import com.pathshala.payload.response.ProductResponseDto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
    ProductResponseDto saveProduct(ProductRequestDto productDto);
    ProductResponseDto updateProduct(ProductRequestDto productDto, Long id);
    ProductResponseDto getProduct(Long id);
    List<ProductResponseDto> getAllProducts();
    Page<Product> getAllProductWithPagination(int offset, int limit);
    List<ProductResponseDto> getAllProductsByCategory(String category);
    String deleteProduct(Long id);
    List<Product> getAllProductsWithNameSort(String fieldName);

    Page<Product> getAllProductsWithPaginationAndSort(int offset, int limit, String fieldName);

}
