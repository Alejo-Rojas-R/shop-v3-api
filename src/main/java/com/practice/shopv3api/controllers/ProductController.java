package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ProductDTO;
import com.practice.shopv3api.entities.ProductEntity;
import com.practice.shopv3api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void createProduct(@RequestBody ProductDTO dto) {
        this.service.createProduct(dto);
    }

    @GetMapping()
    public List<ProductEntity> readProducts(){
        return service.readProducts();
    }

    @GetMapping("{id}")
    public ProductEntity readProductById(@PathVariable Long id){
        return service.readProductById(id);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ProductEntity updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO dto){
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable("id") Long id){
        service.deleteProduct(id);
    }
}
