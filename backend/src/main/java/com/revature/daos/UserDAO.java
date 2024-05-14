package com.revature.daos;


import com.revature.models.Order;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    List<Order> findOrdersByUserIdOrderByOrderIdDesc(int userId);
}
