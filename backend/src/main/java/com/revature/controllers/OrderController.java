package com.revature.controllers;

import com.revature.daos.OrderDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Order;
//import com.revature.services.OrderService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderDAO orderDAO;

    @Autowired
    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        return ResponseEntity.ok().body("");
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

    @PatchMapping("/{orderId}")
    public ResponseEntity<Object> completeOrder(@RequestBody Order order, @PathVariable int orderId) {
        Optional<Order> b = orderDAO.findById(orderId);
        if (b.isEmpty()) {
            return ResponseEntity.badRequest().body("Order does not exist.");
        }
        Order r = b.get();
        r.setStatus(order.getStatus());
        r.setDate(order.getDate());
        orderDAO.save(r);
        return ResponseEntity.ok().body(r);
    }
}
