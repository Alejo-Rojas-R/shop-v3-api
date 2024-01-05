package com.practice.shopv3api.controllers;

import com.practice.shopv3api.dtos.AuthenticationRequestDTO;
import com.practice.shopv3api.dtos.AuthenticationResponseDTO;
import com.practice.shopv3api.dtos.RegisterRequestDTO;
import com.practice.shopv3api.security.AuthService;
import com.practice.shopv3api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
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
    */

    @PostMapping("signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponseDTO> register (@RequestBody RegisterRequestDTO registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDTO> authenticate (@RequestBody AuthenticationRequestDTO authenticationRequest) {
        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
