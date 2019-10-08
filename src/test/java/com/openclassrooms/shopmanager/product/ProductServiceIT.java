package com.openclassrooms.shopmanager.product;

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
public class ProductServiceIT {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;


    @Test
    public void createProduct_TwoProductsCreated_ProductsNamesObtained(){
        //Arrange
        Product product1 = new Product();
        product1.setName("Product1");

        Product product2 = new Product();
        product2.setName("Product2");

        List<Product> list = new ArrayList<>();

        list.add(product1);
        list.add(product2);

        //Act
        when(productRepository.findAll()).thenReturn(list);

        List<Product> products = productService.getAllProducts();

        //Assert
        assertEquals(2, products.size());
        assertEquals("Product1", products.get(0).getName());
        assertEquals("Product2", products.get(1).getName());

    }


    @Test
    public void deleteProduct_TwoProductsInRepository_RepositoryUpdated(){
        //Arrange
        String expectedName = "Product2";
        int expectedRepositorySize = 1;

        Product product1 = new Product();
        product1.setName("Product1");
        product1.setPrice(1.50);
        product1.setDetails("Detail1");
        product1.setDescription("Description1");
        product1.setQuantity(1);

        Product product2 = new Product();
        product2.setName("Product2");
        product2.setPrice(1.50);
        product2.setDetails("Detail2");
        product2.setDescription("Description2");
        product2.setQuantity(1);

        List<Product> list = new ArrayList<>();

        list.add(product1);
        list.add(product2);

        //Act
        when(productRepository.findAll()).thenReturn(list);

        List<Product> products = productService.getAllProducts();

        products.remove(0);

        String deletedProductsName = products.get(0).getName();

        int repositorySize = products.size();

        //Assert
        assertEquals(expectedName, deletedProductsName);
        assertEquals(expectedRepositorySize, repositorySize);

    }



}
