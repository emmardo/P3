package com.openclassrooms.shopmanager.product;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProductServiceIT {

    private final ProductRepository productRepository;

    private ProductService productService;

    private ProductServiceIT(ProductService productService, ProductRepository productRepository){

        this.productRepository = productRepository;
        this.productService = productService;
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

        productService
                .createProduct(productModel);

        assertEquals("Name", productService.getByProductId(1L).getName());
    }

}
