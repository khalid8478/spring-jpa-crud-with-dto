package com.pathshala.controller;

import com.pathshala.entity.Product;
import com.pathshala.payload.request.ProductRequestDto;
import com.pathshala.payload.response.ProductResponseDto;
import com.pathshala.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
        }

    //Create Product
    @PostMapping("/save")
    public ResponseEntity <ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productDto) {
        ProductResponseDto response = productService.saveProduct(productDto);
        return ResponseEntity.ok(response);
    }

    //Update Product
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductRequestDto productDto,
                                                            @PathVariable Long id) {
        ProductResponseDto response = productService.updateProduct(productDto, id);
        return ResponseEntity.ok(response);
    }

    //GET single product
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        ProductResponseDto response = productService.getProduct(id);
        return ResponseEntity.ok(response);
    }

    //GET All products
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    //GET Product by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getProductsByCategory(@PathVariable String category) {
        List<ProductResponseDto> products = productService.getAllProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    //GET AllProductWithPagination
    @GetMapping("/page/{offset}/{limit}")
    public ResponseEntity<Page<Product>> getAllProductsWithPagination(@PathVariable Integer offset,
                                                                      @PathVariable Integer limit) {
        Page<Product> products = productService.getAllProductWithPagination(offset, limit);
        return ResponseEntity.ok(products);
    }

    //GET AllProductWithNameSort
    @GetMapping("/sort/{fieldName}")
    public ResponseEntity<List<Product>> getAllProductsWithSort(@PathVariable String fieldName) {
        List<Product> products = productService.getAllProductsWithNameSort(fieldName);
        return ResponseEntity.ok(products);
    }

    //GET AllProductWithSort&Pagination
    @GetMapping("/sort/{offset}/{limit}/{fieldName}")
    public ResponseEntity<Page<Product>> getAllProductsWithPaginationAndSort(@PathVariable int offset,
                                                                             @PathVariable int limit,
                                                                             @PathVariable String fieldName) {
        Page<Product> products = productService.getAllProductsWithPaginationAndSort(offset, limit, fieldName);
        return ResponseEntity.ok(products);
    }

    //Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String message = productService.deleteProduct(id);
        return ResponseEntity.ok(message);
    }
}
