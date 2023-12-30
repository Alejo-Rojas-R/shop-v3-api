package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.CreateUserDTO;
import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    @PostMapping()
    public void createUser(@RequestBody CreateUserDTO dto) {
        this.service.createUser(dto);
    }

    @GetMapping("{id}")
    public User readUserById(@PathVariable Long id){
        return service.readUserById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable("id") Long id, SignUpDTO dto){
        return service.updateUser(id, dto);
    }

    @GetMapping("login")
    public UserDetails readUserByCredentials(@RequestBody String email){
        return userDetailsService.loadUserByUsername(email);
    }
    */
    @PostMapping("signup")
    public User createUser(@RequestBody CreateUserDTO dto) {
        return userService.createUser(dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
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
