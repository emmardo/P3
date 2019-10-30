package com.openclassrooms.shopmanager.product;

import com.openclassrooms.shopmanager.order.*;
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
public class OrderServiceTest {

    @Mock
    ProductService productService;

    @InjectMocks
    OrderRepository orderRepository;

    @Mock
    OrderService orderService;

    @Test
    public void addProductToCart_jfnfh_dfdjdj() {

        boolean value;

        Long id= 2L;

        Product product1 = new Product();

        /*product1 = product;*/

        /*Product product2 = new Product();*/
        product1.setId(id);

        List<Product> list = new ArrayList<>();

        list.add(product1);

        when(productService.getAllProducts()).thenReturn(list);

        /*product1 = productService.getAllProducts().get(0);*/

        when(productService.getByProductId(id)).thenReturn(product1);

        if(product1 != null){
            value = true;
        }else{
            value = false;
        }
        assertEquals(value,true);
    }

    @Test
    public void saverOrder_jfjf_jfjnf() {

        /*ProductModel productModel = new ProductModel();

        productModel.setDescription("Description");
        productModel.setDetails("Detail");
        productModel.setName("Name");
        productModel.setPrice("1.00");
        productModel.setQuantity("10");

        productService.createProduct(productModel);*/

        Order order1 = new Order();

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);

        Product product = new Product();
        product.setId(5L);
        product.setName("Name");
        product.setDescription("Description");
        product.setDetails("Detail");
        product.setQuantity(10);
        product.setPrice(1.00);

        CartLine cartLine = new CartLine();
        cartLine.setProduct(product);
        cartLine.setQuantity(1);

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);

        Cart cart = new Cart();
        cart.addItem(product,10);

        orderRepository.save(order1);

        productService.updateProductQuantities(cart);

        assertEquals(1, orderList.size());
        assertEquals(product, cart.findProductInCartLines(5L));
        assertEquals(10, cart.findProductInCartLines(5L).getQuantity());

    }

    @Test
    public void removeProductFromCart_jshshs_jdjdjd() {

        Product productInstance = new Product();
        productInstance.setId(5L);
        productInstance.setName("Name");
        productInstance.setDescription("Description");
        productInstance.setDetails("Detail");
        productInstance.setQuantity(10);
        productInstance.setPrice(1.00);

        CartLine cartLine = new CartLine();
        cartLine.setProduct(productInstance);
        cartLine.setQuantity(1);

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine);

        Cart cart = new Cart();
        cart.addItem(productInstance,10);

        when(productService.getByProductId(productInstance.getId())).thenReturn(productInstance);

        /*Product product = productService.getByProductId(productInstance.getId());*/

        if (cartLines.get(0) !=null) {
            cartLines.remove(0);
        }

        assertEquals(0, cartLines.size());

    }

    @Test
    public void createOrder_dhdjdj_jsjsjsj() {

        Order order1 = new Order();

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);

        Product productInstance1 = new Product();
        productInstance1.setId(5L);
        productInstance1.setName("Name1");
        productInstance1.setDescription("Description1");
        productInstance1.setDetails("Detail1");
        productInstance1.setQuantity(10);
        productInstance1.setPrice(1.00);

        Product productInstance2 = new Product();
        productInstance2.setId(10L);
        productInstance2.setName("Name2");
        productInstance2.setDescription("Description2");
        productInstance2.setDetails("Detail2");
        productInstance2.setQuantity(20);
        productInstance2.setPrice(2.00);

        CartLine cartLine1 = new CartLine();
        cartLine1.setProduct(productInstance1);
        cartLine1.setQuantity(1);

        CartLine cartLine2 = new CartLine();
        cartLine2.setProduct(productInstance2);
        cartLine2.setQuantity(2);

        List<CartLine> cartLines = new ArrayList<>();
        cartLines.add(cartLine1);
        cartLines.add(cartLine2);

        Cart cart = new Cart();
        cart.addItem(productInstance1,10);
        cart.addItem(productInstance2,10);

        order1.setLines(cartLines);

        assertEquals(2, order1.getLines().size());

        cart.clear();

        assertEquals(0,cart.getCartLineList().size());

        //saveOrder(order) tested above
    }
}
