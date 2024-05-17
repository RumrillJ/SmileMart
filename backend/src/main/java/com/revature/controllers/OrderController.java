package com.revature.controllers;

import com.revature.daos.OrderDAO;
import com.revature.daos.StatusDAO;
import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Status;

import java.util.List;
import java.util.Optional;

import com.revature.models.User;
import com.revature.models.dtos.OrderProductDTO;
import com.revature.models.dtos.OutgoingOrderProductDTO;
import com.revature.services.OrderProductService;
import com.revature.services.OrderService;
import com.revature.services.UserService;
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
    private final OrderProductService ordProductService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, OrderDAO orderDAO, StatusDAO statusDAO, OrderProductService ordProductService, UserService userService) {
        this.orderService = orderService;
        this.orderDAO = orderDAO;
        this.statusDAO = statusDAO;
        this.ordProductService = ordProductService;
        this.userService = userService;
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
    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrdersByUserId(@PathVariable int userId){
        // SecurityConfig's
        // .requestMatchers("/orders/**").authenticated()
        // is requiring endpoints under /orders to require authentication
        // Accessing this endpoint without valid token is automatic 403

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(user.getUserId() != userId && !user.getRole().name().equals("Manager")) {
            return ResponseEntity.status(401).body("Not authorized");
        }

        System.out.println("in getOrdersByUserId, user = " + user.toString());

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
    public ResponseEntity<Object> addToOrder(@RequestBody OrderProductDTO orderProductDTO, HttpSession session) {
        /*
        // Check if logged in
        if (session.getAttribute("userId") == null) {
            return ResponseEntity.status(401).body("You must be logged in to do this");
        }*/

        // Try to catch errors
        try {
            Order o = orderService.addToOrder(orderProductDTO, (int)session.getAttribute("userId"));
            return ResponseEntity.ok(o);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }

    }

    //user checkout and return an orderId
    @PostMapping("/checkout")
    public ResponseEntity<?> GetOrderIdCheckout(int userId, Order order, List<OrderProduct> orderProducts){
        //saveOrderAndOrderProducts(int userId, Order order, List<OrderProduct> orderProducts)
        try{
            int orderId = orderService.saveOrderAndOrderProducts(userId, order, orderProducts);
            return ResponseEntity.ok(orderId);
        }catch(Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/order/{orderId}")

    public ResponseEntity<Object> viewAllProductByOrderId(@PathVariable int orderId) {
        System.out.println("    Inside viewAllProductByOrderId");
        System.out.println(orderId);

        Optional<Order>  ord = orderDAO.findById(orderId);



        if (ord.isEmpty()) {
            return ResponseEntity.badRequest().body("Order does not exist.");
        }
        Order r = ord.get();
        System.out.println("OrderID:");
        System.out.println(r.getOrderId());

        List<OutgoingOrderProductDTO> outOrdPrdDTO  = ordProductService.findAllOrdProductByOrderId(orderId);

                return ResponseEntity.ok().body(outOrdPrdDTO);

    }
}
