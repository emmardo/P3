package com.openclassrooms.shopmanager.product;

import com.openclassrooms.shopmanager.order.Cart;
import com.openclassrooms.shopmanager.order.CartLine;
import com.openclassrooms.shopmanager.order.OrderRepository;
import com.openclassrooms.shopmanager.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    OrderRepository orderRepository;

    /*@Mock*/
    Cart cart;

    /*@Mock*/
    OrderService orderService;

    @Test
    public void addProductToCart_jfnfh_dfdjdj() {

        Product productInstance = new Product();

        productInstance.setId(2L);

        when(productService.getByProductId(productInstance.getId())).thenReturn(productInstance);

        assertEquals(true,orderService.addToCart(2L));

    }

    @Test
    public void saverOrder_jfjf_jfjnf() {


    }
}
