package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ImageDTO;
import com.practice.shopv3api.entities.ImageEntity;
import com.practice.shopv3api.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private/images")
public class ImageController {
    private ImageService service;

    @Autowired
    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping("product/{id}")
    public List<ImageEntity> readImagesByProductId(@PathVariable("id") Long id) {
        return this.service.readImagesByProductId(id);
    }

    @PostMapping()
    public void createImage(@RequestBody ImageDTO dto) {
        this.service.create(dto);
    }

    @DeleteMapping("{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        this.service.delete(id);
    }
}
