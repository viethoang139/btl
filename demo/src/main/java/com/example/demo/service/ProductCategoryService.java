package com.example.demo.service;

import com.example.demo.dto.ProductDto;

import java.util.List;

public interface ProductCategoryService {

    ProductDto createProduct(ProductDto productDto, int categoryId);

    ProductDto getProductById(int categoryId, int productId);

    List<ProductDto> getAllProductByCategoryId(int categoryId);

    ProductDto updateProductById(int categoryId, int productId, ProductDto productDto);

    void deleteProductById(int categoryId, int productId);

}
