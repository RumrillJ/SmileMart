package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.Order;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Order userCheckoutWithOrder(int userId) {
        Optional<User> optionalUser = userDAO.findById(userId);
        if(optionalUser.isEmpty()){
            throw new IllegalArgumentException("can't find user");
        }
        User user = optionalUser.get();
        return userDAO.findOrdersByUserIdOrderByOrderIdDesc(userId).get(0);
    }

}
