package com.revature.services;

import com.revature.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder){
        this.userDAO = userDAO;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Object register() {

        String hashedPassword = passwordEncoder.encode("raw password");

        return "";
    }

    public Object login() {

        if (passwordEncoder.matches("raw password", "hashed password from database")) {

            return ""; // return user info + JWT
        }

        return ""; // Invalid credentials
    }
}
