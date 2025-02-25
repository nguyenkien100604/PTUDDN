package com.example.security.service;

import com.example.security.entity.Role;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Hàm load user từ CSDL bằng username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Lấy các role (VD: "ROLE_ADMIN", "ROLE_USER") -> authorities
        Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(Role::getName) // "ROLE_ADMIN", "ROLE_USER"
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());

        // Trả về đối tượng org.springframework.security.core.userdetails.User
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
