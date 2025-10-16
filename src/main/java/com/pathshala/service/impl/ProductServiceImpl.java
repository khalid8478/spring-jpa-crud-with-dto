package com.pathshala.service.impl;

import com.pathshala.entity.Product;
import com.pathshala.mapper.ProductMapper;
import com.pathshala.payload.request.ProductRequestDto;
import com.pathshala.payload.response.ProductResponseDto;
import com.pathshala.repository.ProductRepository;
import com.pathshala.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto saveProduct(ProductRequestDto productDto) {
        Product product = ProductMapper.toEntity(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto updateProduct(ProductRequestDto productDto, Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setUnit(productDto.getUnit());
        product.setProductCategory(productDto.getProductCategory());

        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResponseDto(updatedProduct);
    }

    @Override
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        return ProductMapper.toResponseDto(product);
    }

    @Override
       public List<ProductResponseDto> getAllProducts() {
            return productRepository.findAll()
                    .stream()
                    .map(ProductMapper::toResponseDto)
                    .collect(Collectors.toList());
        }

    @Override
    public Page<Product> getAllProductWithPagination(int offset, int limit) {
        return productRepository.findAll(PageRequest.of(offset, limit));
    }


    @Override
    public List<ProductResponseDto> getAllProductsByCategory(String category) {
        return productRepository.findByProductCategory(category)
                .stream()
                .map(ProductMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id) );
        productRepository.delete(product);
        return "Product deleted successfully!";
    }

    @Override
    public List<Product> getAllProductsWithNameSort(String fieldName) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, fieldName));
    }

    @Override
    public Page<Product> getAllProductsWithPaginationAndSort(int offset, int limit, String fieldName) {
        return productRepository.findAll(PageRequest.of(offset, limit)
                .withSort(Sort.by(Sort.Direction.ASC, fieldName)));
    }
}
