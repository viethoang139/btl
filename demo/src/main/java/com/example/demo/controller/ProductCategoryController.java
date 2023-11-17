package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductCategoryController {

    private ProductCategoryService productCategoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/categories/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(@PathVariable int categoryId, @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productCategoryService.createProduct(productDto, categoryId), HttpStatus.CREATED);
    }

    @GetMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int categoryId, @PathVariable int productId){
        return ResponseEntity.ok(productCategoryService.getProductById(categoryId, productId));
    }

    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategoryId(@PathVariable int categoryId){
        return ResponseEntity.ok(productCategoryService.getAllProductByCategoryId(categoryId));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable int categoryId, @PathVariable int productId,
                                                        @RequestBody ProductDto productDto){
        return ResponseEntity.ok(productCategoryService.updateProductById(categoryId, productId, productDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/categories/{categoryId}/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable int categoryId, @PathVariable int productId){
        productCategoryService.deleteProductById(categoryId, productId);
        return ResponseEntity.ok("Delete product successfully");
    }






}
