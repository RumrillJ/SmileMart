package com.revature.controllers;

import com.revature.models.Order;
import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body("");
    }

    // Update user information
    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody User userInfo){
        try{
            String message = userService.updateUser(userId, userInfo);
            return ResponseEntity.status(201).body(message);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    //return an order when user checkout
    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrderByUser(@PathVariable int userId) {
        try {
            Order order = userService.userCheckoutWithOrder(userId);
            return ResponseEntity.ok(order);
        }catch(RuntimeException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



}
