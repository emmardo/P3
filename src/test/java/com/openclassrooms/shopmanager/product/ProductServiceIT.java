package com.openclassrooms.shopmanager.product;

import com.openclassrooms.shopmanager.order.Cart;
import com.openclassrooms.shopmanager.order.CartLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void createProduct_ProductSaved_NameReturned(){
        //Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Product");

        //Act
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> products = productService.getAllProducts();

        //Assert
        assertEquals(1, productRepository.findAll().size());
        assertEquals("Product", productRepository.findAll().get(0).getName());
    }


    @Test
    public void deleteProductFromRepository_addTwoProductsToRepository_RepositoryUpdated(){

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


        when(productRepository.findAll()).thenReturn(list);

        productRepository.findAll().remove(0);

        String deletedProductsName = productRepository.findAll().get(0).getName();

        int repositorySize = productRepository.findAll().size();


        assertEquals(expectedName, deletedProductsName);
        assertEquals(expectedRepositorySize, repositorySize);

    }



}
