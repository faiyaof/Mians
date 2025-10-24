package com.example.springbootsecuritydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestParam String username,
            @RequestParam String password) {
        
        Map<String, Object> response = new HashMap<>();
        
        // 简单的用户名密码验证
        if ("test".equals(username) && "123456".equals(password)) {
            response.put("success", true);
            response.put("message", "登录成功");
            response.put("username", username);
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getCurrentUser(Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        
        if (authentication != null && authentication.isAuthenticated()) {
            response.put("authenticated", true);
            response.put("username", authentication.getName());
            response.put("authorities", authentication.getAuthorities());
        } else {
            response.put("authenticated", false);
        }
        
        return ResponseEntity.ok(response);
    }
}

