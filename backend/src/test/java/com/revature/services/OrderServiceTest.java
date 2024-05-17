package com.revature.services;

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
public class OrderServiceTest {

    @Mock
    private OrderDAO orderDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ProductDAO productDAO;

    @Mock
    private OrderProductService orderProductService;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testAddOrder_ValidUser() {
        int userId = 1;
        User user = new User();
        Order expectedOrder = new Order();

        when(userDAO.findById(userId)).thenReturn(Optional.of(user));
        when(orderDAO.save(any(Order.class))).thenReturn(expectedOrder);

        Order actualOrder = orderService.addOrder(userId);

        assertEquals(expectedOrder, actualOrder);
        verify(userDAO, times(1)).findById(userId);
        verify(orderDAO, times(1)).save(any(Order.class));
    }

    @Test
    public void testAddOrder_InvalidUser() {
        int userId = 1;
        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.addOrder(userId);
        });
    }

    @Test
    public void testAddToOrder_ValidUser() {
        String username = "test";
        Product product = new Product();
        User user = new User();
        user.setUsername(username);
        Order expectedOrder = new Order();
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        OrderProduct orderProduct = new OrderProduct();

        when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));
        when(productDAO.findById(orderProductDTO.getProductId())).thenReturn(Optional.of(product));
        when(orderDAO.findByUserUserIdAndStatusStatusId(user.getUserId(), "SHOPPING")).thenReturn(expectedOrder);
        when(orderProductService.addOrderProduct(orderProductDTO)).thenReturn(orderProduct);

        Order actualOrder = orderService.addToOrder(orderProductDTO, user.getUsername());

        assertEquals(expectedOrder, actualOrder);
        verify(userDAO, times(1)).findByUsername(user.getUsername());
        verify(orderDAO, times(1)).findByUserUserIdAndStatusStatusId(user.getUserId(), "SHOPPING");
        verify(orderProductService, times(1)).addOrderProduct(orderProductDTO);
    }

    @Test
    public void testAddToOrder_InvalidUser() {
        int userId = 1;
        when(userDAO.findById(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.addOrder(userId);
        });
    }

    @Test
    public void testAddToOrder_InvalidProduct() {
        String username = "test";
        Product product = new Product();
        User user = new User();
        user.setUsername(username);
        Order expectedOrder = new Order();
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        OrderProduct orderProduct = new OrderProduct();

        when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));
        when(productDAO.findById(orderProductDTO.getProductId())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.addToOrder(orderProductDTO, user.getUsername());
        });
    }
}