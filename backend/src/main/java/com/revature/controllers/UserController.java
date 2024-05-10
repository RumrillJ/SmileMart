package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.dtos.UserLoginDTO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserDAO userDAO;

    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping
    ResponseEntity<List<User>> getAllUsers() {
        System.out.println(userDAO.findAll());
        return ResponseEntity.ok().body(userDAO.findAll());
    }

    @GetMapping("/{userId}")
    ResponseEntity<Object> getUser(@PathVariable int userId) {
        Optional<User> u = userDAO.findById(userId);
        if (u.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist.");
        }
        return ResponseEntity.ok().body(u.get());
    }

    @PostMapping("/login")
    ResponseEntity<Object> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        Optional<User> u = userDAO.findByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (u.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist.");
        }
        return ResponseEntity.ok().body(u.get());
    }

    //create an account (user)
    @PostMapping
    ResponseEntity<User> insertUser(@RequestBody User user) {
        System.out.println(user);
        User u = userDAO.save(user);
        return ResponseEntity.status(201).body(u);
    }

    @PutMapping("/{userId}")
    ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody User user) {
        Optional<User> u = userDAO.findById(userId);
        if (u.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist.");
        }
        u.get().setFirstName(user.getFirstName());
        u.get().setLastName(user.getLastName());
        userDAO.save(u.get());
        return ResponseEntity.ok().body(u.get());
    }


    //delete a user and cascade (manager)
    @DeleteMapping("/{userId}")
    ResponseEntity<Object> deleteUser(@PathVariable int userId) {
        Optional<User> u = userDAO.findById(userId);
        if (u.isEmpty()) {
            return ResponseEntity.status(404).body("User does not exist.");
        }
        userDAO.deleteById(userId);
        return ResponseEntity.ok().body(u.get());
    }


}
