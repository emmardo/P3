package com.openclassrooms.shopmanager.product;

import com.openclassrooms.shopmanager.order.Cart;
import com.openclassrooms.shopmanager.order.CartLine;
import com.openclassrooms.shopmanager.order.OrderRepository;
import com.openclassrooms.shopmanager.order.OrderService;
import org.hibernate.validator.constraints.URL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    ProductService productService;

    @InjectMocks
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @InjectMocks
    Product product;

    @Test
    public void addProductToCart_jfnfh_dfdjdj() {

        boolean value;

        Long id= 2L;

        Product product1 = new Product();

        product1 = product;

        /*Product product2 = new Product();*/
        product1.setId(id);

        List<Product> list = new ArrayList<>();

        list.add(product1);

        when(productService.getAllProducts()).thenReturn(list);

        /*product1 = productService.getAllProducts().get(0);*/

        when(productService.getByProductId(id)).thenReturn(product1);

        if(product1 != null){
            value = true;
        }else{
            value = false;
        }
        assertEquals(value,true);
    }

    @Test
    public void saverOrder_jfjf_jfjnf() {


    }
}
