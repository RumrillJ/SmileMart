package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.models.dtos.UserRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    private UserDAO userDAO;


    // Dependency Injection
    @Autowired
    public AuthenticationService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // Register a user
    public String registerUser(UserRegistrationDTO userRegistrationDTO) throws IllegalArgumentException{

        // Checks if name is empty
        if (userRegistrationDTO.getName() == null || userRegistrationDTO.getName().isBlank()) {
            log.warn("Name does not meet the requirements");
            throw new IllegalArgumentException("Name cannot be blank!");
        }

        // Ensures password is:
        //    - At least one digit (0-9)
        //    - At least one lowercase letter (a-z)
        //    - At least one uppercase letter (A-Z)
        //    - At least one special character (*.!@#$%^&(){}[]:;<>,.?/~_+-=|\])
        //    - At least 8 characters long, but no more than 32
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

        if (userRegistrationDTO.getPassword() == null || !(userRegistrationDTO.getPassword()).matches(passwordRegex)) {
            log.warn("Password did not meet the requirements");
            throw new IllegalArgumentException("Invalid password!");
        }

        if (userRegistrationDTO.getEmail() == null || userRegistrationDTO.getEmail().isBlank()) {
            log.warn("Email did not meet the requirements");
            throw new IllegalArgumentException("Email cannot be blank!");
        }

        // New user object
        User user = new User();

        // Set user details from DTO using setters
        user.setName(userRegistrationDTO.getName());
        user.setPassword(userRegistrationDTO.getPassword());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(User.ROLE.USER);

        User newUser = userDAO.save(user);
        log.info("user with name {} was created!", newUser.getName());

        return "User " + newUser.getName() + " was registered successfully!";


    }
}