package com.example.day14proj1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.day14proj1.dto.UserRequestDTO;
import com.example.day14proj1.dto.UserResponseDTO;
import com.example.day14proj1.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<UserResponseDTO>login(@RequestBody UserRequestDTO user){
        return ResponseEntity.ok(userService.login(user));
    }
}
