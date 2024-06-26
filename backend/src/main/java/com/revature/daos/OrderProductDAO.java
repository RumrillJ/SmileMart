package com.revature.daos;

import com.revature.models.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDAO extends JpaRepository<OrderProduct, Integer> {
    public OrderProduct findByOrderProductId(int orderProductId);

    public List<OrderProduct> findAllByOrderOrderId(int orderId);
}
