package com.openclassrooms.shopmanager.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Take this test method as a template to write your test methods for ProductService and OrderService.
 * A test method must check if a definite method does its job:
 *
 * Naming follows this popular convention : http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
 */


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepository productRepository;

    @Test
    public void getAllProducts_DbHasData_allDataReturned(){
        //Arrange
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("First product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("First product");

        //Act
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        //Assert
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId() , 0);
        assertEquals(2L, products.get(1).getId() , 0);
    }

    @Test
    public void getAllAdminProducts_HasData_allDataReturned(){

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("First product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Second product");

        when(productRepository.findAllByOrderByIdDesc()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllAdminProducts();

        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId() , 0);
        assertEquals(2L, products.get(1).getId() , 0);
    }

    @Test
    public void getProductById_HasData_FindProductById() {

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("First product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Second product");

        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        assertEquals(2, list.size());
        assertEquals(1L, list.get(0).getId(),0);
        assertEquals(2L, list.get(1).getId(),0);


    }

    @Test
    public void setErrorMessage_HasData_StoreErrorMessage(){
        String expectedMessage1 = "NotBlank.name";
        String expectedMessage2 = "NotBlank.price";

        productService.setErrorMessages();

        String gotMessage1 = productService.getErrorMessages("nameBlank");
        String gotMessage2 = productService.getErrorMessages("priceBlank");

        assertEquals(expectedMessage1, gotMessage1);
        assertEquals(expectedMessage2, gotMessage2);
    }

    @Test
    public void getMissingNameErrorMessage_HasData_ReturnsErrorMessage(){

        String expectedMessage = "NotBlank.name";

        String key = "nameBlank";

        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getMissingPriceErrorMessage_HasData_ReturnsErrorMessage(){

        String expectedMessage = "NotBlank.price";

        String key = "priceBlank";

        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getMissingQuantityErrorMessage_HasData_ReturnsErrorMessage(){

        String expectedMessage = "NotBlank.quantity";

        String key = "quantityBlank";

        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotADoubleErrorMessage_HasData_ReturnsErrorMessage(){

        String expectedMessage = "NotADouble.price";

        String key = "priceNotADouble";

        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotAPositiveNumberErrorMessage_HasData_ReturnsErrorMessage(){

        String expectedMessage = "NotAPositiveNumber.price";

        String key = "priceNotPositive";

        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotAnIntegerErrorMessage_HasData_ReturnsErrorMessage(){

        String expectedMessage = "NotAnInteger.quantity";

        String key = "quantityNotAnInteger";

        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotAPositiveErrorMessage_HasData_ReturnsErrorMessage(){

        String expectedMessage = "NotAPositiveNumber.quantity";

        String key = "quantityNotPositive";

        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        assertEquals(expectedMessage,returnedMessage);
    }

}
