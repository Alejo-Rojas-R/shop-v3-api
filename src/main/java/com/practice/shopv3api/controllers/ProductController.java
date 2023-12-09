package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ProductDTO;
import com.practice.shopv3api.entities.Product;
import com.practice.shopv3api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shop-v3/products")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping()
    public void createProduct(@RequestBody ProductDTO dto) {
        this.service.createProduct(dto);
    }

    @GetMapping()
    public List<Product> readProducts(){
        return service.readProducts();
    }

    @GetMapping("/product/{id}")
    public Product readProductById(@PathVariable Long id){
        return service.readProductById(id);
    }

    @PutMapping("/product/{id}")
    public Product updateProduct(@PathVariable("id") Long id, ProductDTO dto){
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        service.deleteProduct(id);
    }
}
