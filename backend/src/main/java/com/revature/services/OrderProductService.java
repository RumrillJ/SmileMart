package com.revature.services;

import com.revature.daos.OrderDAO;
import com.revature.daos.OrderProductDAO;
import com.revature.daos.ProductDAO;
import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Product;
import com.revature.models.dtos.OrderProductDTO;
import com.revature.models.dtos.OutgoingOrderProductDTO;
import com.revature.models.dtos.OutgoingProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
            log.warn("Product {} does not exist in the inventory", orderProductDTO.getProductId());
            throw new IllegalArgumentException("Product does not exist in the inventory: " + orderProductDTO.getProductId());
        }

        // Valid Quantity
        if (orderProductDTO.getQuantity() < 1) {
            log.warn("Not enough of Product {} in stock", orderProductDTO.getProductId());
            throw new IllegalArgumentException("Invalid quantity entered: " + orderProductDTO.getQuantity());
        }

        // Valid Order
        Optional<Order> o = orderDAO.findById(orderProductDTO.getOrderId());
        if (o.isEmpty()) {
            log.warn("Order {} does not exist", orderProductDTO.getOrderId());
            throw new IllegalArgumentException("Order does not exist: " + orderProductDTO.getOrderId());
        }

        // Valid Order
        log.info("Adding Product {} to Order {}", p.get().getProductId(), o.get().getOrderId());
        return orderProductDAO.save(new OrderProduct(
                o.get(), //orderId
                p.get(),
                orderProductDTO.getQuantity()
        ));
    }

    public OrderProduct editOrderProductAmount(OrderProduct op) {
        if (op == null) {
            log.warn("OrderProduct is null.");
            throw new IllegalArgumentException("OrderProduct is null.");
        }
        log.info("Editing OrderProduct {} with new quantity {}", op.getOrderProductId(), op.getQuantity());
        return orderProductDAO.save(op);
    }

    //create an orderProduct with orderId
    public OrderProduct addOrderProductsWithOrderId(OrderProductDTO orderProduct, int orderId){
        Optional<Order> optionalOrder = orderDAO.findById(orderId);
        if(optionalOrder.isEmpty()) {
            log.warn("Order with ID {} does not exist", orderId);
            throw new IllegalArgumentException("Order with id: " +orderId + " does not exist");
        }

        Optional<Product> optionalProduct = productDAO.findById(orderProduct.getProductId());
        if(optionalProduct.isEmpty()) {
            log.warn("Product {} does not exist", orderProduct.getProductId());
            throw new IllegalArgumentException("Product with id: " +orderProduct.getProductId() + " does not exist");
        }

        OrderProduct op = new OrderProduct();
        log.info("Creating new OrderProduct for Order {}", orderId);
        op.setOrder(optionalOrder.get());
        op.setProduct(optionalProduct.get());
        op.setQuantity(orderProduct.getQuantity());

        log.info("Adding Product {} to Order {}", op.getProduct().getProductId(), op.getOrder().getOrderId());

        return orderProductDAO.save(op);
      
        //orderProduct.setOrder(optionalOrder.get());
        //log.info("Adding Product {} to Order {}", orderProduct.getProduct().getProductId(), orderProduct.getOrder().getOrderId());
        //return orderProductDAO.save(orderProduct);
    }

    public List<OutgoingOrderProductDTO> findAllOrdProductByOrderId(int  orderId) {
        List<OrderProduct> allordPrd = orderProductDAO.findAllByOrderOrderId(orderId);
        List<OutgoingOrderProductDTO> outordprd = new ArrayList<>();

        for(OrderProduct op : allordPrd) {

            OutgoingProductDTO outP= new OutgoingProductDTO(
                    op.getProduct().getName(),
                    op.getProduct().getCost(),
                    op.getProduct().getDescription(),
                    op.getProduct().getCategory(),
                    op.getProduct().getImage());

            OutgoingOrderProductDTO outR= new OutgoingOrderProductDTO(
                    op.getQuantity(),
                    outP,
                    op.getOrderProductId()
            );

            log.info("Extracting OrderProduct {} from Order {}", op.getOrderProductId(), orderId);
            outordprd.add(outR);
        }

        log.info("Returning all Products for Order {}", orderId);
        return outordprd;
    }
}
