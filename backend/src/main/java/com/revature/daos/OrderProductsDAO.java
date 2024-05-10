package com.revature.daos;


import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductsDAO extends JpaRepository<OrderProduct, Integer> {

    public List<OrderProduct> findAllByOrderOrderId(int orderId);

}
