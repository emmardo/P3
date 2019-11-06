package com.openclassrooms.shopmanager.product;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceIT {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void createProduct() {

        product = new Product();
        product.setName("Name");
        product.setDescription("Description");
        product.setDetails("Details");
        product.setId(1L);
        product.setQuantity(1);
        product.setPrice(1.00);

        product = productRepository.save(product);
    }

    @After
    public void deleteProduct() {

        productRepository.delete(product);
    }

    @Before
    public void MockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addToCart() throws Exception {

        mockMvc.perform(post("/order/addToCart").param("productId", product.getId().toString()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void removeFromCart() throws Exception {

        mockMvc.perform(post("/order/removeFromCart").param("productId", product.getId().toString()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void addToCartError() throws Exception {

        mockMvc.perform(post("/order/addToCart").param("productId", "l"))
                .andExpect(status().is5xxServerError());
    }


    @Test
    public void createOrder() throws Exception {

        mockMvc.perform(post("/order/addToCart").param("productId", product.getId().toString()))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/order").param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createOrderError() throws Exception {

        mockMvc.perform(post("/order/removeFromCart").param("productId", product.getId().toString()))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(post("/order").param("id", "1"))
                .andExpect(status().isOk());
    }
}
