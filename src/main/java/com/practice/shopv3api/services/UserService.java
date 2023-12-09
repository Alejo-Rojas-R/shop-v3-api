package com.practice.shopv3api.services;

import com.practice.shopv3api.dtos.AuthenticationDTO;
import com.practice.shopv3api.dtos.SignUpDTO;
import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.exceptions.ShopApiException;
import com.practice.shopv3api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(SignUpDTO dto) {
        boolean exists = this.userRepository.findByEmail(dto.getEmail()).isPresent();
        if (exists) {
            throw new ShopApiException("This email is already being used");
        }
        User newUser = new User(dto.getName(),dto.getLastName(),dto.getEmail(), dto.getPassword(), dto.getPhone(), dto.getAddress(), false);
        this.userRepository.save(newUser);
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

    public User updateUser(Long userId, SignUpDTO userBody) {
        User user = readUserById(userId);

        user.setName(userBody.getName());
        user.setLastName(userBody.getLastName());
        user.setPassword(userBody.getPassword());
        user.setPhone(userBody.getPhone());
        user.setAddress(userBody.getAddress());

        return this.userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        readUserById(userId);
        this.userRepository.deleteById(userId);
    }
}
