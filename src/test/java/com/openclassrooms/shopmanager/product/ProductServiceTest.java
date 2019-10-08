package com.openclassrooms.shopmanager.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
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
    public void getMissingNameErrorMessage_HasData_ReturnsErrorMessage(){
        //Arrange
        String expectedMessage = "NotBlank.name";

        String key = "nameBlank";

        //Act
        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        //Assert
        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getMissingPriceErrorMessage_HasData_ReturnsErrorMessage(){
        //Arrange
        String expectedMessage = "NotBlank.price";

        String key = "priceBlank";

        //Act
        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        //Assert
        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getMissingQuantityErrorMessage_HasData_ReturnsErrorMessage(){
        //Arrange
        String expectedMessage = "NotBlank.quantity";

        String key = "quantityBlank";

        //Act
        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        //Assert
        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotADoubleErrorMessage_HasData_ReturnsErrorMessage(){
        //Arrange
        String expectedMessage = "NotADouble.price";

        String key = "priceNotADouble";

        //Act
        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        //Assert
        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotAPositiveNumberErrorMessage_HasData_ReturnsErrorMessage(){
        //Arrange
        String expectedMessage = "NotAPositiveNumber.price";

        String key = "priceNotPositive";

        //Act
        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        //Assert
        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotAnIntegerErrorMessage_HasData_ReturnsErrorMessage(){
        //Arrange
        String expectedMessage = "NotAnInteger.quantity";

        String key = "quantityNotAnInteger";

        //Act
        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        //Assert
        assertEquals(expectedMessage,returnedMessage);
    }

    @Test
    public void getNotAPositiveErrorMessage_HasData_ReturnsErrorMessage(){
        //Arrange
        String expectedMessage = "NotAPositiveNumber.quantity";

        String key = "quantityNotPositive";

        //Act
        productService.setErrorMessages();

        String returnedMessage = productService.getErrorMessages(key);

        //Assert
        assertEquals(expectedMessage,returnedMessage);
    }

}
