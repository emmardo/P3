package com.openclassrooms.shopmanager.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceIT {

    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Test
    public void createProduct_sdgsdg_sadfsdf() throws Exception {

        mockMvc.perform(post("/admin/product").param("name", "name1")
                .param("price", "1.00").param("quantity", "10"))
                .andExpect(model().errorCount(0)).andExpect(status().is3xxRedirection());

        /*ProductModel productModel = new ProductModel();

        productModel.setDescription("Description");
        productModel.setDetails("Detail");
        productModel.setName("Name");
        productModel.setPrice("1.00");
        productModel.setQuantity("100");

        Product product = new Product();
        product.setDescription(productModel.getDescription());
        product.setDetails(productModel.getDetails());
        product.setName(productModel.getName());
        product.setPrice(Double.parseDouble(productModel.getPrice()));
        product.setQuantity(Integer.parseInt(productModel.getQuantity()));

        List<Product> list = new ArrayList<>();
        list.add(product);

        when(productRepository.findAll()).thenReturn(list);

        productService.createProduct(productModel);

        assertEquals( productService.getAllProducts().stream().filter(productInstance ->
                productInstance.getName()=="Name").findFirst().get()
                .getName(), "Name");
        assertEquals( productService.getAllProducts().stream().filter(productInstance ->
                productInstance.getQuantity()==10).findFirst().get()
                .getQuantity(), 100);*/

        /*ProductModel productModel = new ProductModel();

        productModel.setDescription("Description");
        productModel.setDetails("Detail");
        productModel.setName("Name");
        productModel.setPrice("1.00");
        productModel.setQuantity("50");
        productModel.setId(1L);

        Product product = new Product();
        product.setDescription(productModel.getDescription());
        product.setDetails(productModel.getDetails());
        product.setName(productModel.getName());
        product.setPrice(Double.parseDouble(productModel.getPrice()));
        product.setQuantity(Integer.parseInt(productModel.getQuantity()));
        product.setId(productModel.getId());

        productRepository.save(product);

        when(productRepository.save(product)).thenReturn(product);
        *//*productService.createProduct(productModel);*//*

        when(productService.getByProductId(1L)).thenReturn(product);

        assertEquals("Name", productService.getByProductId(1L).getName());*/


    }

    @Test
    public void createProductWithEmptySpaces_mffnfn_djdjdjd() throws Exception {

        mockMvc.perform(post("/admin/product").param("name", " ")
                .param("price", " ").param("quantity", " "))
                .andExpect(model().errorCount(3)).andExpect(status().isOk());
    }
}
