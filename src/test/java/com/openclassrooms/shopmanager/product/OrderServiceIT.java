package com.openclassrooms.shopmanager.product;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceIT {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;




}
