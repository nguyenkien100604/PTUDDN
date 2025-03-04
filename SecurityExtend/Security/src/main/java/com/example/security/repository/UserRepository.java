package com.example.security.repository;

import com.example.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Tìm user theo username
    User findByUsername(String username);
}
