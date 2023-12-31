package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.CategoryDTO;
import com.practice.shopv3api.entities.Category;
import com.practice.shopv3api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<List<Category>> readCategories(){
        return ResponseEntity.ok(service.readCategories());
    }

    @GetMapping("{id}")
    public Category readCategoryById(@PathVariable Long id){
        return service.readCategoryById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void createCategory(@RequestBody CategoryDTO dto) {
        this.service.createCategory(dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteCategory(@PathVariable("id") Long id) {
        this.service.deleteCategory(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Category updateCategory(@PathVariable("id") Long id, CategoryDTO dto){
        return service.updateCategory(id, dto);
    }
}
