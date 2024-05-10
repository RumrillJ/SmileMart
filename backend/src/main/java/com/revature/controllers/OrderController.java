package com.revature.controllers;

import ch.qos.logback.core.CoreConstants;
import com.revature.daos.OrderDAO;
import com.revature.daos.OrderProductsDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Order;
//import com.revature.services.OrderService;

import java.util.List;
import java.util.Optional;

import com.revature.models.OrderProduct;
import com.revature.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private final OrderDAO orderDAO;
    private final OrderProductsDAO ordPrdDAO;

    @Autowired
    public OrderController(OrderDAO orderDAO, OrderProductsDAO ordPrdDAO) {
        this.orderDAO = orderDAO;
        this.ordPrdDAO = ordPrdDAO;
    }




    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<Order> allOrders = orderDAO.findAll();

        return ResponseEntity.ok().body(allOrders);
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

   /* @PatchMapping("/{orderId}")
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
    }*/

    @GetMapping("/{orderId}")

    public ResponseEntity<Object> viewAllProductByOrderId(@PathVariable int orderId) {
        System.out.println("Inside viewAllProductByOrderId");
        System.out.println(orderId);
       Optional<Order>  ord = orderDAO.findById(orderId);
        if (ord.isEmpty()) {
            return ResponseEntity.badRequest().body("Order does not exist.");
        }
        Order r = ord.get();
        System.out.println("OrderID:");
        System.out.println(r.getOrderId());

        List<OrderProduct> allOrdPrd = ordPrdDAO.findAllByOrderOrderId(r.getOrderId());
        System.out.println("Size:");
        System.out.println(allOrdPrd.size());
             for(int i=0;i<allOrdPrd.size();i++) {
                 System.out.println(i+"Item:");
                  //System.out.println(allOrdPrd.get(i));
                 }

        return ResponseEntity.ok().body(allOrdPrd);

    }


}
