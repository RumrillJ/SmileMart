package com.revature.services;

import com.revature.daos.OrderDAO;
import com.revature.daos.ProductDAO;
import com.revature.models.Product;
import com.revature.models.dtos.OutgoingProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {


    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;

    @Autowired
    public OrderService(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    /*public List<OutgoingProductDTO> findAllProductByOrderId(int  orderId) {

        List<Product> allPrd = productDAO.findById(orderId);

        List<OutgoingProductDTO> outprd = new ArrayList<>();

        for(Product p : allPrd)
        {
            OutgoingProductDTO outR= new OutgoingProductDTO(
                    p.getName(),
                    p.getCost(),
                    p.getDescription(),
                    p.getCategory());

            outprd.add(outR);
        }

        return outprd;
    }*/
}


