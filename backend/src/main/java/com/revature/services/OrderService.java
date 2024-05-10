package com.revature.services;

import com.revature.daos.OrderDAO;
import com.revature.models.Order;
import com.revature.models.dtos.OutgoingOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private final OrderDAO orderDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    //get orders by user id
    public List<OutgoingOrderDTO> getOrdersByUserId(int userId){

        //list to hold return
        List<OutgoingOrderDTO> outgoingOrderDTOList = new ArrayList<>();

        //list to hold orders from DB
        List<Order> allOrdersByUser = orderDAO.findByUserUserId(userId).get();

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
        return outgoingOrderDTOList;
    }
}
