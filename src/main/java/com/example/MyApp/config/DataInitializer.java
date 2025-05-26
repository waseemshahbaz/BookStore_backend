package com.example.MyApp.config;

import com.example.MyApp.Entities.Role;
import com.example.MyApp.Entities.User;
import com.example.MyApp.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Create admin user if it doesn't exist
        if (userRepository.findByEmail("admin@bookstore.com").isEmpty()) {
            var admin = User.builder()
                    .firstName("Admin")
                    .lastName("User")
                    .email("admin@bookstore.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }
} 