package com.revature.controllers;

import com.revature.models.Order;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
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

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrderByUser(@PathVariable int userId) { //return an order when user checkout
        try {
            Order order = userService.userCheckoutWithOrder(userId);
            return ResponseEntity.ok(order);
        }catch(RuntimeException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }



}
