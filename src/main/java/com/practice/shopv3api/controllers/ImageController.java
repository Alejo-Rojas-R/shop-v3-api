package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.ImageDTO;
import com.practice.shopv3api.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shop-v3/images")
public class ImageController {
    private ImageService service;

    @Autowired
    public ImageController(ImageService service) {
        this.service = service;
    }

    @PostMapping()
    public void createImage(@RequestBody ImageDTO dto) {
        this.service.crear(dto);
    }

    @DeleteMapping("/image/{id}")
    public void deleteImage(@PathVariable("id") Long id) {
        this.service.delete(id);
    }
}
