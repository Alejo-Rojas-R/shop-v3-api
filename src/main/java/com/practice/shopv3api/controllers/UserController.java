package com.practice.shopv3api.controllers;

import com.practice.shopv3api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("private/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    /*
    @PostMapping()
    public void createUser(@RequestBody CreateUserDTO dto) {
        this.service.createUser(dto);
    }

    @GetMapping("{id}")
    public User readUserById(@PathVariable Long id){
        return rvice.readUserById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") Long id, SignUpDTO dto){
        return service.updateUser(id, dto);
    }
    */

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable("id") Long id){
        service.deleteUser(id);
    }
}
