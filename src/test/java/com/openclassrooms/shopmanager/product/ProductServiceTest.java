package com.openclassrooms.shopmanager.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void getAllAdminProducts_DbHasData_allDataReturned(){

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("First product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("First product");

        when(productRepository.findAllByOrderByIdDesc()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllAdminProducts();

        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId() , 0);
        assertEquals(2L, products.get(1).getId() , 0);

    }

    @Test
    public void getByProductId_DbHasData_allDataReturned(){

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("First product");

        when(productRepository.findById(expectedProduct.getId())).thenReturn(Optional.of(expectedProduct));

        Product resultingProduct = productService.getByProductId(expectedProduct.getId());

        assertEquals(expectedProduct.getId(), resultingProduct.getId());
        assertEquals("First product", expectedProduct.getName());

    }

    @Test
    public void createProduct_DbHasData_allDataReturned(){

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
        assertEquals(productRepository.findAll().stream().filter(productInstance ->
                        productInstance.getQuantity()==10).findFirst().get()
                        .getQuantity(),10);

    }

    @Test
    public void deleteProduct_DbHasData_allDataReturned(){

    }

    @Test
    public void updateProductQuantities_DbHasData_allDataReturned(){

    }

   /* @Test
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
    }*/

}
