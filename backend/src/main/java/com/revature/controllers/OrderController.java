package com.revature.controllers;

import com.revature.auth.JwtFilter;
import com.revature.daos.OrderDAO;
import com.revature.daos.StatusDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Status;

import java.util.List;
import java.util.Optional;

import com.revature.models.User;
import com.revature.models.dtos.OrderProductDTO;
import com.revature.services.OrderService;
import jakarta.servlet.http.HttpSession;
import com.revature.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
@CrossOrigin
public class OrderController {

    private final OrderDAO orderDAO;
    private final StatusDAO statusDAO;
    private OrderService orderService;
    private UserDAO userDAO;
    private JwtFilter jwtFilter;

    @Autowired
    public OrderController(OrderService orderService, OrderDAO orderDAO, StatusDAO statusDAO, UserDAO userDAO, JwtFilter jwtFilter) {
        this.orderService = orderService;
        this.orderDAO = orderDAO;
        this.statusDAO = statusDAO;
        this.userDAO = userDAO;
        this.jwtFilter = jwtFilter;
    }

    @GetMapping
    ResponseEntity<List<Order>> getAllOrders() {
        System.out.println(orderDAO.findAll());
        return ResponseEntity.ok().body(orderDAO.findAll());
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

    @PatchMapping("/{orderId}/{orderStatus}")
    public ResponseEntity<Object> completeOrder(@RequestBody Order order, @PathVariable int orderId, @PathVariable String orderStatus) {
        Optional<Status> e = statusDAO.findByStatusId(orderStatus);
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


    //get orders by User Id
    @GetMapping("/user")
    public ResponseEntity<?> getOrdersByUserId( @RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = authenticatedUser.getUserId();
        Optional<User> optionalUser = userDAO.findById(userId);
        //login check
        if(optionalUser.isEmpty()) {
            return ResponseEntity.status(401).body("User not logged in!");
        }

        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    /*  ================================
        Check if valid inputs for [OrderProduct]
            - user
            - order
            - product
            - quantity
            Check if user has Orders list,
                If not, reate Orders
                Add current item to newly created Orders and RETURN

            If user has existing Orders list, check if current item is in orders list
            If already exists, update quantity (old + new) then RETURN
            If it does not exist, add to list and RETURN

        Takes in a @RequestBody containing a OrderProduct?
            - [OrderProductId] int, auto inc
            - [Order] -> Null for now
            - [Product] -> Entire product object
            - [Quantity] -> int
     */

    // TODO: Endpoint subject to change
    @PostMapping
    public ResponseEntity<Object> addToOrder(@RequestBody OrderProductDTO orderProductDTO, @RequestHeader("Authorization") String token) {
        /*
        // Check if logged in
        if (session.getAttribute("userId") == null) {
            return ResponseEntity.status(401).body("You must be logged in to do this");
        }*/

        // Try to catch errors
        try {
            String jwt = token.substring(7);
            User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            int userId = authenticatedUser.getUserId();
            Order o = orderService.addToOrder(orderProductDTO, userId);
            return ResponseEntity.ok(o);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    //user checkout and return an orderId
    @PostMapping("/checkout")
    public ResponseEntity<?> GetOrderIdCheckout(@RequestBody List<OrderProductDTO> orderProducts, @RequestHeader("Authorization") String token){
        String jwt = token.substring(7);
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = authenticatedUser.getUserId();
        try{
            int orderId = orderService.saveOrderAndOrderProducts(userId, orderProducts);
            return ResponseEntity.ok(orderId);
        }catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
