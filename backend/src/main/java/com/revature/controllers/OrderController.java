package com.revature.controllers;

import com.revature.daos.OrderDAO;
import com.revature.daos.UserDAO;
import com.revature.daos.StatusDAO;
import com.revature.models.Order;
//import com.revature.services.OrderService;

import java.util.List;
import java.util.Optional;

import com.revature.models.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderDAO orderDAO;
    private final UserDAO userDAO;
    private final StatusDAO statusDAO;

    @Autowired
    public OrderController(UserDAO userDAO, OrderDAO orderDAO, StatusDAO statusDAO) {
        this.userDAO = userDAO;
        this.orderDAO = orderDAO;
        this.statusDAO = statusDAO;
    }

    // @DeleteMapping("/{orderId}")
    // public ResponseEntity<Object> deleteOrder (@PathVariable int orderId) {
    //     Optional<Order> b = orderDAO.findById(orderId);
    //     if (b.isEmpty()) {
    //         return ResponseEntity.status(404).body("No order at ID " + orderId + "found");
    //     }

    //     Order order = b.get();

    //     orderDAO.deleteById(orderId);
    //     return ResponseEntity.ok().body(order.getOrderId() + " deleted from Orders");
    // } maybe?

    @GetMapping
    ResponseEntity<List<Order>> getAllOrders() {
        System.out.println(orderDAO.findAll());
        return ResponseEntity.ok().body(orderDAO.findAll());
    }

    @PatchMapping("/{orderId}/{orderStatus}")
    public ResponseEntity<Object> completeOrder(@RequestBody Order order, @PathVariable int orderId, @PathVariable String orderStatus) {
        Optional<Status> e = statusDAO.findByStatus(orderStatus);
        Optional<Order> b = orderDAO.findById(orderId);
        if (b.isEmpty()) {
            return ResponseEntity.badRequest().body("Order does not exist.");
        }
        if (e.isEmpty()) {
            return ResponseEntity.badRequest().body("Bad status on order.");
        }
        Status s = e.get();
        Order r = b.get();
        r.setStatus(s);
        r.setDate(order.getDate());
        orderDAO.save(r);
        return ResponseEntity.ok().body(r);
    }
}
