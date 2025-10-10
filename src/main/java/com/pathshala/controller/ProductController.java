package com.pathshala.controller;

import com.pathshala.payload.request.ProductRequestDto;
import com.pathshala.payload.response.ProductResponseDto;
import com.pathshala.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ProductResponseDto>> getProductsByVategory(@PathVariable String category) {
        List<ProductResponseDto> products = productService.getAllProductsByCategory(category);
        return ResponseEntity.ok(products);
    }

    //Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String message = productService.deleteProduct(id);
        return ResponseEntity.ok(message);
    }
}
