package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String adminIndex() {
        return "Trang admin - chỉ ADMIN vào được";
    }

    @GetMapping("/admin/secret")
    public String adminSecret() {
        return "Secret admin info!";
    }
}
