package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.CategoryDTO;
import com.practice.shopv3api.entities.CategoryEntity;
import com.practice.shopv3api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void createCategory(@RequestBody CategoryDTO dto) {
        this.service.createCategory(dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable("id") Long id) {
        this.service.deleteCategory(id);
    }

    @GetMapping()
    public List<CategoryEntity> readCategories(){
        return service.readCategories();
    }

    @GetMapping("{id}")
    public CategoryEntity readCategoryById(@PathVariable Long id){
        return service.readCategoryById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryEntity updateCategory(@PathVariable("id") Long id, CategoryDTO dto){
        return service.updateCategory(id, dto);
    }
}
