package com.practice.shopv3api.security;

import com.practice.shopv3api.entities.User;
import com.practice.shopv3api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByEmail(username);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("This user is not registered in the application");
        }

        UserDetails loggedUser = org.springframework.security.core.userdetails.User.withUsername(user.get().getEmail())
                //.password(passwordEncoder.encode(user.get().getPassword()))
                .password(user.get().getPassword())
                //.passwordEncoder(passwordEncoder::encode)
                .roles(user.get().getAdmin() ? "ADMIN" : "USER")
                .build();

        return loggedUser;
    }
}