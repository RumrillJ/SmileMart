package com.revature.services;

import com.revature.daos.OrderDAO;
import com.revature.daos.OrderProductDAO;
import com.revature.daos.ProductDAO;
import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Product;
import com.revature.models.dtos.OrderProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {
    private final OrderProductDAO orderProductDAO;
    private final ProductDAO productDAO;
    private final OrderDAO orderDAO;

    @Autowired
    public OrderProductService(OrderProductDAO orderProductDAO, ProductDAO productDAO, OrderDAO orderDAO) {
        this.orderProductDAO = orderProductDAO;
        this.productDAO = productDAO;
        this.orderDAO = orderDAO;
    }

    public OrderProduct addOrderProduct(OrderProductDTO orderProductDTO) {
        // Check for valid inputs

        // Valid product
        Optional<Product> p = productDAO.findById(orderProductDTO.getProductId());
        if(p.isEmpty()) {
            throw new IllegalArgumentException("Product does not exist in the inventory: " + orderProductDTO.getProductId());
        }

        // Valid Quantity
        if (orderProductDTO.getQuantity() < 1) {
            throw new IllegalArgumentException("Invalid quantity entered: " + orderProductDTO.getQuantity());
        }

        // Valid Order
        Optional<Order> o = orderDAO.findById(orderProductDTO.getOrderId());
        if (o.isEmpty()) {
            throw new IllegalArgumentException("Order does not exist: " + orderProductDTO.getOrderId());
        }

        // Valid Order
        return orderProductDAO.save(new OrderProduct(
                o.get(), //orderId
                p.get(),
                orderProductDTO.getQuantity()
        ));
    }

    public OrderProduct editOrderProductAmount(OrderProduct op) {
        if (op == null) {
            throw new IllegalArgumentException("OrderProduct is null.");
        }
        return orderProductDAO.save(op);
    }

    //create an orderProduct with orderId
    public OrderProduct addOrderProductsWithOrderId(OrderProduct orderProduct, int orderId){
        Optional<Order> optionalOrder = orderDAO.findById(orderId);
        if(optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order with id: " +orderId + " does not exist");
        }
        orderProduct.setOrder(optionalOrder.get());
        return orderProductDAO.save(orderProduct);
    }
}
