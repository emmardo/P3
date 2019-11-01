package com.openclassrooms.shopmanager.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceIT {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void createProduct_TwoProductsCreated_ProductsNamesObtained() {

        ProductModel productModel = new ProductModel();

        productModel.setDescription("Description");
        productModel.setDetails("Detail");
        productModel.setName("Name");
        productModel.setPrice("1.00");
        productModel.setQuantity("10");

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

        assertEquals(productService.getAllProducts().stream().filter(productInstance ->
                productInstance.getName()=="Name").findFirst().get()
                .getName(),"Name");
        assertEquals(productService.getAllProducts().stream().filter(productInstance ->
                productInstance.getQuantity()==10).findFirst().get()
                .getQuantity(),10);

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

}
