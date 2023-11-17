package com.example.demo.service;

import com.example.demo.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(int id);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategoryById(int id, CategoryDto categoryDto);

    void deleteCategoryById(int id);

}
