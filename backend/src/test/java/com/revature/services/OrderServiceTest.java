package com.revature.services;

import com.revature.daos.ProductDAO;
import com.revature.daos.OrderDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Order;
import com.revature.models.OrderProduct;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.models.dtos.OrderProductDTO;
import com.revature.models.dtos.OutgoingOrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    public void testGetOrdersByUser_UserDoesNotExist() {
        String username = "test";
        when(userDAO.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrdersByUser(username);
        });
    }

    @Test
    public void testGetOrdersByUser_UserIsUser() {
        String username = "test";
        User user = new User();
        user.setRole(User.ROLE.USER);
        user.setUsername(username);

        when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));
        when(orderService.getOrdersByUser(username)).thenReturn(new ArrayList<>());

        List<OutgoingOrderDTO> orders = orderService.getOrdersByUser(username);
        assertEquals(new ArrayList<OutgoingOrderDTO>(), orders);
    }

    @Test
    public void testSaveOrderAndOrderProducts_InvalidUser() {
        String username = "test";
        when(userDAO.findByUsername(username)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            orderService.saveOrderAndOrderProducts(username, new ArrayList<>());
        });
    }

    @Test
    public void testSaveOrderAndOrderProducts_ValidUser() {
        String username = "test";
        User user = new User();
        user.setUsername(username);
        user.setUserId(1);

        Order order = new Order();
        order.setOrderId(1);
        List<OrderProductDTO> orderProducts = new ArrayList<>();
        OrderProductDTO orderProductDTO = new OrderProductDTO(1, 3);
        orderProductDTO.setOrderId(1);
        orderProducts.add(orderProductDTO);

        Product product = new Product();
        product.setProductId(1);

        OrderProduct orderProduct = new OrderProduct(order, product, 3);
        orderProduct.setOrderProductId(1);

        when(userDAO.findByUsername(username)).thenReturn(Optional.of(user));
        when(orderDAO.save(any(Order.class))).thenReturn(order);
        when(orderProductService.addOrderProductsWithOrderId(orderProductDTO, order.getOrderId())).thenReturn(orderProduct);

        int actualOrder = orderService.saveOrderAndOrderProducts(username, orderProducts);

        assertEquals(order.getOrderId(), actualOrder);
        verify(userDAO, times(1)).findByUsername(username);
        verify(orderDAO, times(1)).save(any(Order.class));
        verify(orderProductService, times(orderProducts.size())).addOrderProductsWithOrderId(orderProductDTO, orderProductDTO.getOrderId());
    }
}