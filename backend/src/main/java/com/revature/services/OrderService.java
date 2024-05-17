package com.revature.services;

import com.revature.daos.OrderDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Order;
import com.revature.models.dtos.OutgoingOrderDTO;
import com.revature.daos.ProductDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.models.dtos.OrderProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class OrderService {

    private final OrderDAO orderDAO;
    private final UserDAO userDAO;
    private final ProductDAO productDAO;
    private final OrderProductService orderProductService;

    @Autowired
    public OrderService(OrderDAO orderDAO, UserDAO userDAO, ProductDAO productDAO, OrderProductService orderProductService) {
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
        this.productDAO = productDAO;
        this.orderProductService = orderProductService;
    }

    //get orders by user id
    public List<OutgoingOrderDTO> getOrdersByUserId(int userId) throws IllegalArgumentException{

        //check if user exists
        if (userDAO.findById(userId).isEmpty()){
            log.warn("User does not exist");
            throw new IllegalArgumentException("User does not exist");
        }

        //list to hold return
        List<OutgoingOrderDTO> outgoingOrderDTOList = new ArrayList<>();

        //list to hold orders from DB
        List<Order> allOrdersByUser = orderDAO.findByUserUserId(userId);

        for(Order o : allOrdersByUser){
            OutgoingOrderDTO order = new OutgoingOrderDTO(
                    o.getOrderId(),
                    o.getUser().getUserId(),
                    o.getProducts(),
                    o.getStatus(),
                    o.getDate()
            );
            outgoingOrderDTOList.add(order);
        }
        log.info("{} orders found for user {}", outgoingOrderDTOList.size(), userId);
        return outgoingOrderDTOList;
    }

    // Create an order, Requires only a user id
    public Order addOrder(int userId) {
        // Check if valid user
        Optional<User> optUser = userDAO.findById(userId);
        if (optUser.isEmpty()) {
            log.warn("Invalid user.");
            throw new IllegalArgumentException("Invalid user.");
        }
        User u = optUser.get();

        log.info("Creating new order for user {}", u.getUserId());
        return orderDAO.save(new Order (
                u,
                // TODO : Fix this >>
                new Status("SHOPPING"),
                new Date()

        ));
    }

    /*
        Takes [orderProductDTO] and [userId]
        [orderProductDTO] contains the orderProductId, product, quantity
        [userId] is the user's id

        Add the passed product to the order
        Assuming we don't get passed the order, so we find it through DAO
        If the user doesn't have an order, we create one
        If the user has an order, we add the product to the order
        If the product is already in the order, we adjust the quantity
     */
    public Order addToOrder(OrderProductDTO orderProductDTO, int userId) throws IllegalArgumentException {

        // Check if valid user -- should never hit since we grab from session
        Optional<User> optUser = userDAO.findById(userId);
        if (optUser.isEmpty()) {
            log.warn("Invalid user.");
            throw new IllegalArgumentException("Invalid user.");
        }

        if(productDAO.findById(orderProductDTO.getProductId()).isEmpty()) {
            log.warn("Product does not exist in the inventory: {}", orderProductDTO.getProductId());
            throw new IllegalArgumentException("Product does not exist in the inventory: " + orderProductDTO.getProductId());
        }

        // Save user as u
        User u = optUser.get();

        // Check if user has an open order TODO: Fix this >>>
        Order userOrder = orderDAO.findByUserUserIdAndStatusStatusId(userId, "SHOPPING");
        if (userOrder == null) {
            // instead of throwing an error, we make a new order
            userOrder = addOrder(userId);
        }

        // We should now have an Order called userOrder
        // Set the order id of the DTO we have to the order id of the userOrder
        orderProductDTO.setOrderId(userOrder.getOrderId());

        // Check if order is filled? - Not filled
        if (userOrder.getProducts() == null || userOrder.getProducts().isEmpty()) {
            // Order list is empty, create a list and add the item
            userOrder.setProducts(new HashSet<>());
            userOrder.getProducts().add(orderProductService.addOrderProduct(orderProductDTO));
        }
        else {
            // If the order is NOT empty, check for the item
            // Search db for the product

            int found = -1;
            for (OrderProduct op : userOrder.getProducts()) {

                // If the item is found, adjust the quantity
                if (orderProductDTO.getProductId() == op.getProduct().getProductId()) {
                    found = 1;
                    log.info("Updating quantity of product {} in order {}", op.getProduct().getProductId(), userOrder.getOrderId());
                    op.setQuantity(op.getQuantity() + orderProductDTO.getQuantity());
                    orderProductService.editOrderProductAmount(op);
                    break;
                }
            }

            // Item isn't found, add it
            if (found == -1) {
                OrderProduct newOrderProduct = orderProductService.addOrderProduct(orderProductDTO);
                newOrderProduct.setOrder(userOrder);
                userOrder.getProducts().add(newOrderProduct);
            }
        }
        //return orderDAO.save(userOrder);
        orderDAO.save(userOrder);
        log.info("Product {} was added to order {} successfully", orderProductDTO.getProductId(), userOrder.getOrderId());
        return userOrder;
    }

    //add an order and its order-products
    public int saveOrderAndOrderProducts(int userId, Order order, List<OrderProduct> orderProducts) {
        Optional<User> optionalUser = userDAO.findById(userId);
        if (optionalUser.isEmpty()) {
            log.warn("Invalid user");
            throw new IllegalArgumentException("Invalid user.");
        }
        order.setUser(optionalUser.get());
        Order o = orderDAO.save(order);
        for(OrderProduct op : orderProducts) {
            log.info("Adding product {} to order {}", op.getProduct().getProductId(), o.getOrderId());
            orderProductService.addOrderProductsWithOrderId(op, o.getOrderId());
        }
        return o.getOrderId();
    }

}
