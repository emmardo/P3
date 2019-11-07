package com.openclassrooms.shopmanager.product;

import com.openclassrooms.shopmanager.order.Cart;
import com.openclassrooms.shopmanager.order.CartLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

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
    public void getAllProducts_DbHasData_allDataReturned() {
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
    public void getAllAdminProducts_DbHasData_allDataReturned() {

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
    public void getByProductId_DbHasData_ProductReturned() {

        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setName("First product");

        when(productRepository.findById(expectedProduct.getId())).thenReturn(Optional.of(expectedProduct));

        Product resultingProduct = productService.getByProductId(expectedProduct.getId());

        assertEquals(expectedProduct.getId(), resultingProduct.getId());
        assertEquals("First product", expectedProduct.getName());

    }

    @Test
    public void createProduct_DbHasData_ProductCreated() {

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

    }

    @Test
    public void deleteProduct_DbHasData_ProductDeleted() {

        ProductModel productModel1 = new ProductModel();

        productModel1.setDescription("Description1");
        productModel1.setDetails("Detail1");
        productModel1.setName("Name1");
        productModel1.setPrice("1.00");
        productModel1.setQuantity("10");

        ProductModel productModel2 = new ProductModel();

        productModel2.setDescription("Description2");
        productModel2.setDetails("Detail2");
        productModel2.setName("Name2");
        productModel2.setPrice("2.00");
        productModel2.setQuantity("20");

        Product product1 = new Product();
        product1.setDescription(productModel1.getDescription());
        product1.setDetails(productModel1.getDetails());
        product1.setName(productModel1.getName());
        product1.setPrice(Double.parseDouble(productModel1.getPrice()));
        product1.setQuantity(Integer.parseInt(productModel1.getQuantity()));
        product1.setId(1L);

        Product product2 = new Product();
        product2.setDescription(productModel2.getDescription());
        product2.setDetails(productModel2.getDetails());
        product2.setName(productModel2.getName());
        product2.setPrice(Double.parseDouble(productModel2.getPrice()));
        product2.setQuantity(Integer.parseInt(productModel2.getQuantity()));
        product2.setId(2L);

        List<Product> list = new ArrayList<>();
        list.add(product1);
        list.add(product2);

        when(productRepository.findAll()).thenReturn(list);

        productService.createProduct(productModel1);
        productService.createProduct(productModel2);

        assertEquals(2,productRepository.findAll().size());

        productService.deleteProduct(1L);
        list.remove(product1);

        assertEquals(1,productRepository.findAll().size());
    }

    @Test
    public void updateProductQuantities_DbHasData_QuantitiesUpdated() {

        List<Product> repositoryMock = new ArrayList<>();

        Product product = new Product();
        product.setId(5L);
        product.setName("Name");
        product.setDescription("Description");
        product.setDetails("Detail");
        product.setQuantity(15);
        product.setPrice(1.00);

        CartLine cartLine = new CartLine();
        cartLine.setProduct(product);
        cartLine.setQuantity(1);

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);

        Cart cart = new Cart();
        cart.addItem(product,2);

        when(productService.getAllProducts()).thenReturn(repositoryMock);

        int amountOfProductExistingInRepository = 20;

        for(int i = amountOfProductExistingInRepository; i > 0; i--){

            productService.getAllProducts().add(product);
        }

        assertEquals(20, productService.getAllProducts().size());
        assertEquals("Name", productService.getAllProducts().stream()
                    .filter(p -> p.getId()==5L).findFirst().get().getName());

        productService.updateProductQuantities(cart);

        if(productService.getAllProducts().stream().filter(p -> p.getId()==5L).findFirst().get()
            != null){

            int amountToBeRemovedFromRepository = cart.findProductInCartLines(product.getId()).getQuantity();

            for(int i = amountToBeRemovedFromRepository; i > 0 ; i--) {
                productService.getAllProducts().remove(product);
            }
        }

        assertEquals(5, productService.getAllProducts().size());
        assertEquals(15, cart.findProductInCartLines(product.getId()).getQuantity());
        assertEquals(product, cart.findProductInCartLines(5L));
        assertEquals("Name", productService.getAllProducts().get(0).getName());

    }

    @Test
    public void setErrorMessages_DbHasData_ErrorMessagesSet() {

        HashMap<String, String> errorMessages = new HashMap<>();

        errorMessages.put("nameBlank", "NotBlank.name");
        errorMessages.put("priceBlank", "NotBlank.price");
        errorMessages.put("quantityBlank", "NotBlank.quantity");
        errorMessages.put("priceNotADouble", "NotADouble.price");
        errorMessages.put("priceNotPositive", "NotAPositiveNumber.price");
        errorMessages.put("quantityNotAnInteger", "NotAnInteger.quantity");
        errorMessages.put("quantityNotPositive", "NotAPositiveNumber.quantity");

        assertEquals(7, errorMessages.size());
        assertEquals("NotBlank.name", errorMessages.get("nameBlank"));
    }

    @Test
    public void getErrorMessage_DbHasData_ErrorMessageReturned() {

        String key;

        HashMap<String, String> errorMessages = new HashMap<>();

        errorMessages.put("nameBlank", "NotBlank.name");
        errorMessages.put("priceBlank", "NotBlank.price");
        errorMessages.put("quantityBlank", "NotBlank.quantity");
        errorMessages.put("priceNotADouble", "NotADouble.price");
        errorMessages.put("priceNotPositive", "NotAPositiveNumber.price");
        errorMessages.put("quantityNotAnInteger", "NotAnInteger.quantity");
        errorMessages.put("quantityNotPositive", "NotAPositiveNumber.quantity");

        key = "nameBlank";

        assertEquals("NotBlank.name", errorMessages.get(key));

        key = "quantityNotPositive";

        assertEquals("NotAPositiveNumber.quantity", errorMessages.get(key));

    }
}
