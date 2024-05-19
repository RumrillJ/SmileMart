package com.revature.services;

import com.revature.daos.OrderProductDAO;
import com.revature.daos.ProductDAO;
import com.revature.daos.OrderDAO;
import com.revature.daos.UserDAO;
import com.revature.models.*;
import com.revature.models.dtos.OrderProductDTO;
import com.revature.models.dtos.OutgoingOrderProductDTO;
import com.revature.models.dtos.OutgoingProductDTO;
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

    @Test
    public void testFindAllOrdProductByOrderId() {

        Optional<Order> order1 = Optional.of(new Order());
        order1.get().setOrderId(1);

        Optional<Product> product1 = Optional.of(new Product());
        product1.get().setProductId(1);
        product1.get().setName("nike");
        product1.get().setDescription("shoes");
        product1.get().setCost(100);

        Optional<OrderProduct> orderproduct1 = Optional.of(new OrderProduct());
        orderproduct1.get().setProduct(product1.get());
        orderproduct1.get().setOrder(order1.get());
        orderproduct1.get().setOrderProductId(1);
        orderproduct1.get().setQuantity(3);



      /*  Optional<Product> product2 = Optional.of(new Product());

        product2.get().setName("nike");
        product2.get().setDescription("tshirt");
        product2.get().setCost(200);
        product2.get().setCategory(category1.get());*/

        List<OrderProduct> expectedPrd = new ArrayList<>();
        expectedPrd.add(orderproduct1.get());
        //expectedPrd.add(product2.get());

        List<OutgoingOrderProductDTO> expectedProducts = new ArrayList<>();



        for(OrderProduct op : expectedPrd) {

            OutgoingProductDTO outP = new OutgoingProductDTO(
                    op.getProduct().getName(),
                    op.getProduct().getCost(),
                    op.getProduct().getDescription(),
                    op.getProduct().getCategory());

            OutgoingOrderProductDTO outR = new OutgoingOrderProductDTO(
                    op.getQuantity(),
                    outP,
                    op.getOrderProductId()
            );
            expectedProducts.add(outR);


        }

        when(orderProductDAO.findAllByOrderOrderId(1)).thenReturn(expectedPrd);

        List<OutgoingOrderProductDTO> actualProducts = orderProductService.findAllOrdProductByOrderId(1);

        assertEquals(expectedProducts.size() , actualProducts.size());

        //assertEquals(expectedProducts.get(0).getName(),actualProducts.);
        for(int i=0;i<actualProducts.size(); i++) {

            //assertEquals(expectedProducts, actualProducts);
            assertEquals(expectedProducts.get(i).getOrderProductId() , actualProducts.get(i).getOrderProductId());
            assertEquals(expectedProducts.get(i).getProduct().getName() , actualProducts.get(i).getProduct().getName());
            assertEquals(expectedProducts.get(i).getQuantity() , actualProducts.get(i).getQuantity());

        }

        verify(orderProductDAO, times(1)).findAllByOrderOrderId(1);
    }

}
