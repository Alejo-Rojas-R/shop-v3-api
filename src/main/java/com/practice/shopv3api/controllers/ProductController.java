package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ProductDTO;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public List<Product> readProducts(){
        return service.readProducts();
    }

    @GetMapping("search/{query}")
    public List<Product> readProductsBySearch(@PathVariable String query, Pageable pageable){
        return service.readProductsBySearch(query, pageable);
    }

    @GetMapping("{id}")
    public Product readProductById(@PathVariable Long id){
        return service.readProductById(id);
    }

    @GetMapping("category/{categoryId}")
    public List<Product> readProductsByCategory(@PathVariable Integer categoryId, Pageable pageable){
        return service.readProductsByCategory(categoryId, pageable);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(this.service.createProduct(dto));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO dto){
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteProduct(@PathVariable("id") Long id){
        service.deleteProduct(id);
    }
}
