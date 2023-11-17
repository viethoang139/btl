package com.example.demo.controller;

import com.example.demo.dto.CartDto;
import com.example.demo.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@AllArgsConstructor
public class CartController {
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto){
        return new ResponseEntity<>(cartService.createCart(cartDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartById(@PathVariable("id") int cartId){
        return ResponseEntity.ok(cartService.getCartById(cartId));
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCarts(){
        return ResponseEntity.ok(cartService.gatAllCarts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCartById(@PathVariable("id") int cartId,
                                                  @RequestBody CartDto cartDto){
        return ResponseEntity.ok(cartService.updateCartById(cartId, cartDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCartById(@PathVariable("id") int cartId){
        cartService.deleteCartById(cartId);
        return ResponseEntity.ok("Delete cart successfully");
    }



}
