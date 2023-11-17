package com.example.demo.service;

import com.example.demo.dto.CartDto;

import java.util.List;

public interface CartService {
    CartDto createCart(CartDto cartDto);

    CartDto getCartById(int id);

    List<CartDto> gatAllCarts();

    CartDto updateCartById(int id, CartDto cartDto);

    void deleteCartById(int id);




}
