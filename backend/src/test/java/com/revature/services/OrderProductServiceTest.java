package com.revature.services;

import com.revature.daos.OrderProductDAO;
import com.revature.daos.ProductDAO;
import com.revature.daos.OrderDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.dtos.OrderProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderProductServiceTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private OrderProductDAO orderProductDAO;

    @InjectMocks
    private OrderProductService orderProductService;

    @Test
    public void testAddOrderProduct_ValidInputs() {
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setOrderId(1);
        orderProductDTO.setProductId(1);
        orderProductDTO.setQuantity(1);

        Order order = new Order();
        order.setOrderId(1);

        Product product = new Product();
        product.setProductId(1);

        OrderProduct expectedOrderProduct = new OrderProduct(order, product, 1);

        when(orderDAO.findById(orderProductDTO.getOrderId())).thenReturn(Optional.of(order));
        when(productDAO.findById(orderProductDTO.getProductId())).thenReturn(Optional.of(product));
        when(orderProductDAO.save(any(OrderProduct.class))).thenReturn(expectedOrderProduct);

        OrderProduct actualOrderProduct = orderProductService.addOrderProduct(orderProductDTO);

        assertEquals(expectedOrderProduct, actualOrderProduct);
        verify(orderDAO, times(1)).findById(orderProductDTO.getOrderId());
        verify(productDAO, times(1)).findById(orderProductDTO.getProductId());
        verify(orderProductDAO, times(1)).save(any(OrderProduct.class));
    }

    @Test
    public void testAddOrderProduct_InvalidInputs() {
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setOrderId(1);
        orderProductDTO.setProductId(999);
        orderProductDTO.setQuantity(0);

        when(productDAO.findById(orderProductDTO.getProductId())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> orderProductService.addOrderProduct(orderProductDTO));
        verify(productDAO, times(1)).findById(orderProductDTO.getProductId());
    }

    @Test
    public void testEditOrderProductAmount_ValidInputs() {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setQuantity(1);

        OrderProduct expectedOrderProduct = new OrderProduct();
        expectedOrderProduct.setQuantity(2);

        when(orderProductDAO.save(any(OrderProduct.class))).thenReturn(expectedOrderProduct);

        OrderProduct actualOrderProduct = orderProductService.editOrderProductAmount(orderProduct);

        assertEquals(expectedOrderProduct, actualOrderProduct);
        verify(orderProductDAO, times(1)).save(any(OrderProduct.class));
    }

    @Test
    public void testEditOrderProductAmount_InvalidInputs() {
        assertThrows(IllegalArgumentException.class, () -> orderProductService.editOrderProductAmount(null));
    }
}
