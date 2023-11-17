package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Product;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductCartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductCartServiceImpl implements ProductCartService {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private ModelMapper modelMapper;

    @Override
    public String addToCart(int cartId, int productId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() ->
                new ResourceNotFoundException("Cart","ID",cartId));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product","ID",productId));
        if (product.getCart() == null) {
            product.setCart(cart);
            productRepository.save(product);
            return "Product added to cart successfully!";
        } else if (product.getCart().getId() == cartId) {
            return "Product already exists in this cart!";
        } else {
            return "Product exists in another cart!";
        }
    }

    @Override
    public ProductDto getProductById(int cartId, int productId) {
        cartRepository.findById(cartId).orElseThrow(() ->
                new ResourceNotFoundException("Cart","ID",cartId));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product","ID",productId));
        if(cartId != product.getCart().getId()){
            throw new ApiException("Product does not belong to cart");
        }
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts(int cartId) {
        List<Product> products = productRepository.findByCartId(cartId);
        return products.stream().map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());

    }
    @Override
    public void deleteProductById(int cartId, int productId) {
        cartRepository.findById(cartId).orElseThrow(() ->
                new ResourceNotFoundException("Cart","ID",cartId));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("Product","ID",productId));
        if(cartId != product.getCart().getId()){
            throw new ApiException("Product does not belong to cart");
        }
        productRepository.deleteById(productId);
    }
}
