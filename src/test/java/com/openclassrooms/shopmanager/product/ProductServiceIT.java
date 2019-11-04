package com.openclassrooms.shopmanager.product;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void MockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createProduct_ProductCreated() throws Exception {

        mockMvc.perform(post("/admin/product").param("name", "name1")
                .param("price", "1.00").param("quantity", "10"))
                .andExpect(model().errorCount(0)).andExpect(status().is3xxRedirection());
    }

    @Test
    public void createProductWithEmptySpaces_ProductNotCreated() throws Exception {

        mockMvc.perform(post("/admin/product").param("name", " ")
                .param("price", " ").param("quantity", " "))
                .andExpect(model().errorCount(3)).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void createProductWithNonValidValues_ProductNotCreated() throws Exception {

        mockMvc.perform(post("/admin/product").param("name", " ")
                .param("price", "-").param("quantity", "-"))
                .andExpect(model().errorCount(3)).andExpect(status().is2xxSuccessful());
    }
}
