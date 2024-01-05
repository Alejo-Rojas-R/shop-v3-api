package com.practice.shopv3api.security;

import com.practice.shopv3api.dtos.AuthenticationRequestDTO;
import com.practice.shopv3api.dtos.AuthenticationResponseDTO;
import com.practice.shopv3api.dtos.RegisterRequestDTO;
import com.practice.shopv3api.entities.Role;
import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDTO register (RegisterRequestDTO request) {
        boolean exists = this.userRepository.findByEmail(request.getEmail()).isPresent();
        if (exists) {
            throw new ShopApiException("This email is already being used");
        }

        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getName(),
                request.getLastName(),
                request.getEmail(),
                encryptedPassword,
                request.getPhone(),
                request.getAddress(),
                Role.USER
        );

        this.userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponseDTO auth = new AuthenticationResponseDTO();
        auth.setToken(jwtToken);

        return auth;
    }

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
        User user = this.userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("This user is not registered in the application"));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user);

        AuthenticationResponseDTO auth = new AuthenticationResponseDTO();
        auth.setToken(jwtToken);

        return auth;
    }
}