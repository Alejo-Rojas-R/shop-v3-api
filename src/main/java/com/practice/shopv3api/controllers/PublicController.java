package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.CreateUserDTO;
import com.practice.shopv3api.entities.ProductEntity;
import com.practice.shopv3api.entities.UserEntity;
import com.practice.shopv3api.security.CustomUserDetailsService;
import com.practice.shopv3api.services.ProductService;
import com.practice.shopv3api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public")
public class PublicController {
    private final ProductService service;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    @Autowired
    public PublicController(ProductService service, CustomUserDetailsService userDetailsService, UserService userService) {
        this.service = service;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @GetMapping()
    public List<ProductEntity> readProducts(){
        return service.readProducts();
    }
    /*
    @GetMapping("login")
    public UserDetails readUserByCredentials(@RequestBody String email){
        return userDetailsService.loadUserByUsername(email);
    }
    */
    @PostMapping("signup")
    public UserEntity createUser(@RequestBody CreateUserDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping("validate")
    public String validateAuthentication(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "You are logged in.";
        } else {
            return "You are not logged in!";
        }
    }
}
