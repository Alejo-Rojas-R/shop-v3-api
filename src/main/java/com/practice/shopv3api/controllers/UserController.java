package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.SignUpDTO;
import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shop-v3/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public void createUser(@RequestBody SignUpDTO dto) {
        this.service.createUser(dto);
    }

    @GetMapping("/user/{id}")
    public User readUserById(@PathVariable Long id){
        return service.readUserById(id);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id, SignUpDTO dto){
        return service.updateUser(id, dto);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
    }
}
