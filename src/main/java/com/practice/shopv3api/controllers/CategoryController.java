package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.CategoryDTO;
import com.practice.shopv3api.entities.Category;
import com.practice.shopv3api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shop-v3/categories")
public class CategoryController {
    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping()
    public void createCategory(@RequestBody CategoryDTO dto) {
        this.service.createCategory(dto);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
        this.service.deleteCategory(id);
    }

    @GetMapping()
    public List<Category> readProducts(){
        return service.readCategories();
    }

    @GetMapping("/product/{id}")
    public Category readProductById(@PathVariable Long id){
        return service.readCategoryById(id);
    }

    @PutMapping("/product/{id}")
    public Category updateProduct(@PathVariable("id") Long id, CategoryDTO dto){
        return service.updateCategory(id, dto);
    }
}
