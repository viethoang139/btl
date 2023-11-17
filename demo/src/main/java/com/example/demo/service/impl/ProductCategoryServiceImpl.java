package com.example.demo.service.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.ApiException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductCategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    @Override
    public ProductDto createProduct(ProductDto productDto, int categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","ID",categoryId));
        Product product = modelMapper.map(productDto,Product.class);
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public ProductDto getProductById(int categoryId, int productId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","ID",categoryId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ID",productId));
        if(categoryId != product.getCategory().getId()){
            throw new ApiException("Product does not belong to category");
        }
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProductByCategoryId(int categoryId) {
         categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","ID",categoryId));
        List<Product> products = productRepository.findByCategoryId(categoryId);
        return products.stream().map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProductById(int categoryId, int productId, ProductDto productDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","ID",categoryId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ID",productId));
        if(categoryId != product.getCategory().getId()){
            throw new ApiException("Product does not belong to category");
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setQuanlity(productDto.getQuanlity());
        product.setPrice(productDto.getPrice());
        product.setTexture(productDto.getTexture());
        product.setWeight(productDto.getWeight());
        product.setSize(productDto.getSize());
        product.setTrending(productDto.getTrending());
        Product updateProduct = productRepository.save(product);
        return modelMapper.map(updateProduct,ProductDto.class);

    }

    @Override
    public void deleteProductById(int categoryId, int productId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category","ID",categoryId));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product","ID",productId));
        if(categoryId != product.getCategory().getId()){
            throw new ApiException("Product does not belong to category");
        }
        productRepository.deleteById(productId);
    }
}
