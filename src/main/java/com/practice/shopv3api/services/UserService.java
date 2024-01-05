package com.practice.shopv3api.services;

import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User readUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new ShopApiException("This product couldn't be found in the database"));
    }

    public void deleteUser(Long userId) {
        readUserById(userId);
        this.userRepository.deleteById(userId);
    }
}
