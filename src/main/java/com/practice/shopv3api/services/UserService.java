package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.CreateUserDTO;
import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserDTO dto) {
        boolean exists = this.userRepository.findByEmail(dto.getEmail()).isPresent();
        if (exists) {
            throw new ShopApiException("This email is already being used");
        }

        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

        User newUser = new User(
                dto.getName(),
                dto.getLastName(),
                dto.getEmail(),
                encryptedPassword,
                dto.getPhone(),
                dto.getAddress(),
                false);
        return this.userRepository.save(newUser);
    }

    public List<User> readProducts() {
        return StreamSupport.stream(this.userRepository.findAll().spliterator(), false).toList();
    }

    public User readUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ShopApiException("This product couldn't be found in the database"));
    }

    public User readUserByEmail(String userEmail) {
        return this.userRepository.findByEmail(userEmail).orElseThrow(() -> new ShopApiException("This user couldn't be found in the database"));
    }

    public void deleteUser(Long userId) {
        readUserById(userId);
        this.userRepository.deleteById(userId);
    }
}
