package com.revature.daos;

import com.revature.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {
    public Order findByUserUserIdAndStatusStatusId(int userId, String statusId);
    public List<Order> findByUserUserId(int userId);

}
