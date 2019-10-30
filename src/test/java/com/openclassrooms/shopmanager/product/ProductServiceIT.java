package com.openclassrooms.shopmanager.product;

import com.openclassrooms.shopmanager.order.Cart;
import com.openclassrooms.shopmanager.order.CartLine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceIT {

    private ProductRepository productRepository;

    private ProductService productService;

    public ProductServiceIT(ProductRepository productRepository/*, ProductService productService*/) {

        this.productRepository = productRepository;
        /*this.productService = productService;*/
    }

    @Test
    public void createProduct_TwoProductsCreated_ProductsNamesObtained() {

        ProductModel productModel = new ProductModel();

        productModel.setDescription("Description");
        productModel.setDetails("Detail");
        productModel.setName("Name");
        productModel.setPrice("1.00");
        productModel.setQuantity("50");
        productModel.setId(1L);

        productService.createProduct(productModel);

        assertEquals("Name", productService.getByProductId(1L).getName());
    }
}