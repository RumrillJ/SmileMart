package com.revature.controllers;

import com.revature.models.dtos.UserRegistrationDTO;
import com.revature.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<String> registerUser (UserRegistrationDTO userRegistrationDTO) {
        try {
            String message = authenticationService.registerUser(userRegistrationDTO);
            return ResponseEntity.status(201).body(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
