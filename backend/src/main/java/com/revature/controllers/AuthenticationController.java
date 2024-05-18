package com.revature.controllers;

import com.revature.models.dtos.UserLoginDTO;
import com.revature.models.dtos.UserRegistrationDTO;
import com.revature.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    // Dependency Injection
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Register user controller
    @PostMapping("/register")
    public ResponseEntity<String> registerUser (@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            String message = authenticationService.registerUser(userRegistrationDTO);
            return ResponseEntity.status(201).body(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // User login controller
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        try {
            // Returns the JWT token on success
            return ResponseEntity.ok(authenticationService.login(userLoginDTO));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
