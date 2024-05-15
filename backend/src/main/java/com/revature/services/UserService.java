package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.Order;
import com.revature.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Slf4j
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    // Update User Information
    public String updateUser(int userId, User user){
        // Checks if user exists in database
        Optional<User> u = userDAO.findById(userId);

        if(u.isEmpty()){
            // Fail log
            log.warn("The user does not exist!");
            throw new NoSuchElementException("Please register!");
        }

        // Determines which field(s) to update
        if(user.getFirstName() != null && !user.getFirstName().isEmpty()){
            u.get().setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null && !user.getLastName().isEmpty()){
            u.get().setLastName(user.getLastName());
        }
        if(user.getUsername() != null && !user.getUsername().isEmpty()){
            u.get().setUsername(user.getUsername());
        }
        if(user.getPassword() != null && !user.getPassword().isEmpty()){
            u.get().setPassword(user.getPassword());
        }
        if(user.getEmail() != null && !user.getEmail().isEmpty()) {
            u.get().setEmail(user.getEmail());
        }
        if(user.getAddress() != null && !user.getAddress().isEmpty()){
            u.get().setAddress(user.getAddress());
        }
        if(user.getCity() != null && !user.getCity().isEmpty()){
            u.get().setCity(user.getCity());
        }
        if(user.getState() != null && !user.getState().isEmpty()){
            u.get().setState(user.getState());
        }
        if(user.getZip() != 0 && (Long)user.getZip() != null){
            u.get().setZip(user.getZip());
        }
        if(user.getCountry() != null && !user.getCountry().isEmpty()){
            u.get().setCountry(user.getCountry());
        }
        if(user.getPhoneNumber() != 0 && (Long)user.getPhoneNumber() != null){
            u.get().setPhoneNumber(user.getPhoneNumber());
        }

        User updatedUser = userDAO.save(u.get());

        // Success log
        log.info("user with the id #{}'s profile was updated successfully!", updatedUser.getUserId());
        return "User " + updatedUser.getFirstName() + " " + updatedUser.getLastName() + "'s profile was updated successfully!";
    }


}
