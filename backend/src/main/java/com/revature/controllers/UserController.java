package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.JwtService;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin(origins = {"http://localhost:3000", "http://smilemarts3.s3-website.us-east-2.amazonaws.com/"}, allowCredentials = "true")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body("");
    }

    // Update user information
    @PutMapping
    public ResponseEntity<String> updateUser(@RequestHeader("Authorization") String token, @RequestBody User userInfo){
        // Extracting username from token
        String username = jwtService.extractUsername(token.substring(7));

        try{
            String message = userService.updateUser(username, userInfo);
            return ResponseEntity.status(201).body(message);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

}
