package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserDAO userDAO;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserDAO userDAO, JwtService jwtService) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
    }

    public Optional<User> getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    // Update User Information
    public String updateUser(String username, User user) {
        // Find user by username
        Optional<User> u = userDAO.findByUsername(username);
        if (u.isEmpty()) {
            // Error log
            log.error("User {} does not exist", username);
            throw new NoSuchElementException("User does not exist: " + username);
        }

        // Determines which field(s) to update
        if (user.getFirstName() != null && !user.getFirstName().isEmpty()) {
            u.get().setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null && !user.getLastName().isEmpty()) {
            u.get().setLastName(user.getLastName());
        }
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            u.get().setUsername(user.getUsername());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            u.get().setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            u.get().setEmail(user.getEmail());
        }
        if (user.getAddress() != null && !user.getAddress().isEmpty()) {
            u.get().setAddress(user.getAddress());
        }
        if (user.getCity() != null && !user.getCity().isEmpty()) {
            u.get().setCity(user.getCity());
        }
        if (user.getState() != null && !user.getState().isEmpty()) {
            u.get().setState(user.getState());
        }
        if (user.getZip() != 0 && (Long) user.getZip() != null) {
            u.get().setZip(user.getZip());
        }
        if (user.getCountry() != null && !user.getCountry().isEmpty()) {
            u.get().setCountry(user.getCountry());
        }
        if (user.getPhoneNumber() != 0 && (Long) user.getPhoneNumber() != null) {
            u.get().setPhoneNumber(user.getPhoneNumber());
        }

        userDAO.save(u.get());

        // Success log
        log.info("user {} {}'s profile was updated successfully!", u.get().getFirstName(), u.get().getLastName());
        return "User " + u.get().getFirstName() + " " + u.get().getLastName() + "'s profile was updated successfully!";
    }
}