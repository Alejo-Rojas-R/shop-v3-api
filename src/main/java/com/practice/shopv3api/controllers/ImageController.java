package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ImageDTO;
import com.practice.shopv3api.entities.Image;
import com.practice.shopv3api.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("images")
public class ImageController {
    private ImageService service;

    @Autowired
    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping("product/{id}")
    public List<Image> readImagesByProductId(@PathVariable("id") Long id) {
        return this.service.readImagesByProductId(id);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void createImage(@RequestBody ImageDTO dto) {
        this.service.create(dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteImage(@PathVariable("id") Long id) {
        this.service.delete(id);
    }
}
