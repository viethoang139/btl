package com.example.demo.service.impl;

import com.example.demo.dto.CartDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.exception.CartException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CartService {
    private CartRepository cartRepository;
    private ModelMapper modelMapper;
    private static int countCart = 0;
    @Override
    public CartDto createCart(CartDto cartDto) {
        countCart++;
        if(countCart > 1){
            throw new CartException("Cart is already created");
        }
        Cart cart = modelMapper.map(cartDto,Cart.class);
        Cart savedCart = cartRepository.save(cart);
        return modelMapper.map(savedCart,CartDto.class);
    }

    @Override
    public CartDto getCartById(int id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart","ID",id));
        return modelMapper.map(cart,CartDto.class);
    }

    @Override
    public List<CartDto> gatAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream().map(cart -> modelMapper.map(cart,CartDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartDto updateCartById(int id, CartDto cartDto) {
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart","ID",id));
        cart.setQuanlity(cartDto.getQuanlity());
        cart.setPrice(cartDto.getPrice());
        Cart updateCart = cartRepository.save(cart);
        return modelMapper.map(updateCart,CartDto.class);
    }

    @Override
    public void deleteCartById(int id) {
        countCart = 0;
        Cart cart = cartRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cart", "ID", id));
        List<Product> products = cart.getProducts();
        for(Product product : products){
            product.setCart(null);
        }
        cart.setProducts(null);
        cartRepository.deleteById(id);
    }
}
