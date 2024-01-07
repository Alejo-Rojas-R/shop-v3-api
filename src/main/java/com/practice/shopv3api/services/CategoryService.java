package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.CategoryDTO;
import com.practice.shopv3api.entities.Category;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryDTO dto) {
        boolean exists = this.categoryRepository.findByName(dto.getName()).isPresent();
        if (exists) {
            throw new ShopApiException("This category aleady exists in the database", HttpStatus.UNAUTHORIZED);
        }
        Category newCategory = new Category(dto.getName());
        return this.categoryRepository.save(newCategory);
    }

    public List<Category> readCategories() {
        return StreamSupport.stream(this.categoryRepository.findAll().spliterator(), false).toList();
    }

    public Category readCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ShopApiException("This category couldn't be found in the database", HttpStatus.NOT_FOUND));
    }

    public Category updateCategory(Long categoryId, CategoryDTO categoryBody) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ShopApiException("This category couldn't be found in the database", HttpStatus.NOT_FOUND));

        category.setName(categoryBody.getName());

        return this.categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        this.readCategoryById(id);
        this.categoryRepository.deleteById(id);
    }
}
