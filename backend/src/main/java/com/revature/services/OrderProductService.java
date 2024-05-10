package com.revature.services;

import com.revature.daos.OrderDAO;
import com.revature.daos.OrderProductsDAO;
import com.revature.daos.ProductDAO;
import com.revature.models.OrderProduct;
import com.revature.models.Product;
import com.revature.models.dtos.OutgoingOrderProductDTO;
import com.revature.models.dtos.OutgoingProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductService {


    //private final OrderDAO orderDAO;
    private final OrderProductsDAO orderProductDAO;

    @Autowired
    public OrderProductService(OrderProductsDAO orderProductDAO) {
        this.orderProductDAO = orderProductDAO;
    }

    public List<OutgoingOrderProductDTO> findAllOrdProductByOrderId(int  orderId) {

        List<OrderProduct> allordPrd = orderProductDAO.findAllByOrderOrderId(orderId);



        List<OutgoingOrderProductDTO> outordprd = new ArrayList<>();

        for(OrderProduct op : allordPrd)
        {

            OutgoingProductDTO outP= new OutgoingProductDTO(
                    op.getProduct().getName(),
                    op.getProduct().getCost(),
                    op.getProduct().getDescription(),
                    op.getProduct().getCategory());

            OutgoingOrderProductDTO outR= new OutgoingOrderProductDTO(
                    op.getQuantity(),
                    outP,
                    op.getOrderProductId()
                    );


            outordprd.add(outR);
        }

        return outordprd;
    }
}
