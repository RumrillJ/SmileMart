package com.revature.daos;


import com.revature.models.Order;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
     Optional<User> findByEmail(String email);
     Optional<User> findByUsername(String username);

     @Query("SELECT o FROM Order o WHERE o.user.userId = :userId")
     List<Order> findOrdersByUserId(int userId);

}
