package com.example.security.config;

import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.RoleRepository;
import com.example.security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(UserRepository userRepository,
                               RoleRepository roleRepository,
                               PasswordEncoder passwordEncoder) {
        return args -> {
            // Tạo role ADMIN
            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            if(roleAdmin == null) {
                roleAdmin = new Role("ROLE_ADMIN");
                roleRepository.save(roleAdmin);
            }

            // Tạo role USER
            Role roleUser = roleRepository.findByName("ROLE_USER");
            if(roleUser == null) {
                roleUser = new Role("ROLE_USER");
                roleRepository.save(roleUser);
            }

            // Tạo user admin
            if(userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setEmail("admin@example.com");
                admin.setRoles(new HashSet<>(Collections.singletonList(roleAdmin)));
                userRepository.save(admin);
            }

            // Tạo user thường
            if(userRepository.findByUsername("user") == null) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setEmail("user@example.com");
                user.setRoles(new HashSet<>(Collections.singletonList(roleUser)));
                userRepository.save(user);
            }
        };
    }
}
