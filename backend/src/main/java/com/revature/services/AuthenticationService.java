package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.models.dtos.OutgoingUserDTO;
import com.revature.models.dtos.UserLoginDTO;
import com.revature.models.dtos.UserRegistrationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class AuthenticationService {

    private final UserDAO userDAO;
    // Handles password hashing
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtService jwtService;


    // Dependency Injection
    @Autowired
    public AuthenticationService(UserDAO userDAO, JwtService jwtService) {
        this.userDAO = userDAO;
        this.jwtService = jwtService;
    }

    // Register Service
    public String registerUser(UserRegistrationDTO userRegistrationDTO) throws IllegalArgumentException{

        // Checks if name is empty
        if (userRegistrationDTO.getFirstName() == null || userRegistrationDTO.getFirstName().isBlank()
            || userRegistrationDTO.getLastName() == null || userRegistrationDTO.getLastName().isBlank()) {
            // Fail log
            log.warn("First name, last name, or both do not meet the requirements");


            throw new IllegalArgumentException("First name, last name, or both do not meet the requirements");
        }

        if (userRegistrationDTO.getAddress().isBlank() || userRegistrationDTO.getCity().isBlank() || userRegistrationDTO.getCountry().isBlank() || userRegistrationDTO.getState().isBlank() || userRegistrationDTO.getZip() == 0) {
            log.warn("All address fields must be filled");
            throw new IllegalArgumentException("All address fields must be filled");
        }

        // Ensures password is:
        //    - At least one digit (0-9)
        //    - At least one lowercase letter (a-z)
        //    - At least one uppercase letter (A-Z)
        //    - At least one special character (*.!@#$%^&(){}[]:;<>,.?/~_+-=|\])
        //    - At least 8 characters long, but no more than 32
        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

        // Checks password meets regex
        if (userRegistrationDTO.getPassword() == null || !(userRegistrationDTO.getPassword()).matches(passwordRegex)) {
            // Fail log
            //log.warn("Password did not meet the requirements");

            throw new IllegalArgumentException("Invalid password!");
        }

        // Checks if email is empty & is a valid email (something@email.***)
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (userRegistrationDTO.getEmail() == null || userRegistrationDTO.getEmail().isBlank() || !userRegistrationDTO.getEmail().matches(emailRegex)) {
            // Fail log
            //log.warn("Email did not meet the requirements");
            throw new IllegalArgumentException("Invalid Email!");
        }

        // Checks if Email already exists
        if (userDAO.findByEmail(userRegistrationDTO.getEmail()).isPresent() || userDAO.findByUsername(userRegistrationDTO.getUsername()).isPresent()) {

            // Fail log
            log.warn("Email, username, or both are already taken");

            
            throw new IllegalArgumentException(userRegistrationDTO.getEmail() + " or" + userRegistrationDTO.getUsername() +" already taken!");
        }

        // New user object
        User user = new User();

        // Set user details from DTO using setters
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user.setEmail(userRegistrationDTO.getEmail());
        user.setRole(User.ROLE.USER);
        user.setAddress(userRegistrationDTO.getAddress());
        user.setCity(userRegistrationDTO.getCity());
        user.setCountry(userRegistrationDTO.getCountry());
        user.setState(userRegistrationDTO.getState());
        user.setZip(userRegistrationDTO.getZip());
        user.setUsername(userRegistrationDTO.getUsername());
        if (userRegistrationDTO.getPhoneNumber() != 0) {
            user.setPhoneNumber(userRegistrationDTO.getPhoneNumber());
        }

        User newUser = userDAO.save(user);

        // Success log
        log.info("user with name {} {} was created!", newUser.getFirstName(), newUser.getLastName());

        return "User " + newUser.getFirstName() + " " + newUser.getLastName() + " was registered successfully!";
    }

    // Login Service
    public Object login (UserLoginDTO userLoginDTO) throws NoSuchElementException {

        // Custom DAO Method to find the user by username and password.
        Optional<User> optionalUser = userDAO.findByUsername(userLoginDTO.getUsername());


        // Generate JWT token from JWT Service
        if (optionalUser.isPresent()) {
            if ( passwordEncoder.matches(userLoginDTO.getPassword(), optionalUser.get().getPassword()) ) {
                // Success login log.
                log.info("{} logged in successfully!", userLoginDTO.getUsername());
                User u = optionalUser.get();
                String token = jwtService.generateToken(u);
                OutgoingUserDTO outgoingUserDTO = new OutgoingUserDTO(
                        u.getUserId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getRole(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getAddress(),
                        u.getCity(),
                        u.getState(),
                        u.getZip(),
                        u.getCountry(),
                        u.getPhoneNumber(),
                        token
                );
                outgoingUserDTO.setToken(token);

                return outgoingUserDTO;
            } else {
                // Incorrect password log
                log.warn("Invalid password");

                throw new NoSuchElementException("Incorrect Password!");
            }
        }

        // Failed login log.
        //log.warn("No such user found");

        // If optional user is not available throw an error.
        throw new NoSuchElementException("User was not found.");
    }
}
