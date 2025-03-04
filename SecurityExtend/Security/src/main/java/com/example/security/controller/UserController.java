package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String userIndex() {
        return "Trang user - USER hoặc ADMIN vào được";
    }
}
