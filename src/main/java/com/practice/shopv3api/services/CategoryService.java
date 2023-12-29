package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.CategoryDTO;
import com.practice.shopv3api.entities.CategoryEntity;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public CategoryEntity createCategory(CategoryDTO dto) {
        boolean exists = this.categoryRepository.findByName(dto.getName()).isPresent();
        if (exists) {
            throw new ShopApiException("This category aleady exists in the database", HttpStatusCode.valueOf(400));
        }
        CategoryEntity newCategoryEntity = new CategoryEntity(dto.getName());
        return this.categoryRepository.save(newCategoryEntity);
    }

    public List<CategoryEntity> readCategories() {
        return StreamSupport.stream(this.categoryRepository.findAll().spliterator(), false).toList();
    }

    public CategoryEntity readCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(() -> new ShopApiException("This category couldn't be found in the database"));
    }

    public CategoryEntity updateCategory(Long categoryId, CategoryDTO categoryBody) {
        CategoryEntity categoryEntity = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ShopApiException("This category couldn't be found in the database"));

        categoryEntity.setName(categoryBody.getName());

        return this.categoryRepository.save(categoryEntity);
    }

    public void deleteCategory(Long id) {
        this.categoryRepository.findById(id).orElseThrow(()->new ShopApiException("This category id doesn't exist in the database"));
        this.categoryRepository.deleteById(id);
    }
}
