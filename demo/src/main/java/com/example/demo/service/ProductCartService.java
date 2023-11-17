package com.example.demo.service;

import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductCartService {
    String addToCart(int cartId, int productId);

    ProductDto getProductById(int cartId, int productId);

    List<ProductDto> getAllProducts(int cartId);

    void deleteProductById(int cartId, int productId);

}
