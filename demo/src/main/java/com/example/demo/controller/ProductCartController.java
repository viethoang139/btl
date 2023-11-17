package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.service.ProductCartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ProductCartController {
    private ProductCartService productCartService;

    @PostMapping("/carts/{cartId}/products/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable int cartId,
                                            @PathVariable int productId){
        return ResponseEntity.ok(productCartService.addToCart(cartId, productId));
    }

    @GetMapping("/carts/{cartId}/products/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int cartId,
                                                     @PathVariable int productId){
        return ResponseEntity.ok(productCartService.getProductById(cartId, productId));
    }

    @GetMapping("/carts/{cartId}/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(@PathVariable int cartId){
        return ResponseEntity.ok(productCartService.getAllProducts(cartId));
    }

    @DeleteMapping("/carts/{cartId}/products/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable int cartId,
                                                    @PathVariable int productId){
        productCartService.deleteProductById(cartId, productId);
        return ResponseEntity.ok("Delete product successfully");
    }

}
