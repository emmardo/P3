package com.openclassrooms.shopmanager.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping( value = {"/products" , "/"})
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/admin/products")
    public String getAdminProducts(Model model) {
        model.addAttribute("products", productService.getAllAdminProducts());
        return "productsAdmin";
    }


    @GetMapping("/admin/product")
    public String productForm(Model model) {
        model.addAttribute("product",new ProductModel());
        return "product";
    }



    @PostMapping("/admin/product")
    public String createProduct(@Valid @ModelAttribute("product") ProductModel productModel, BindingResult result)
    {
        productService.setErrorMessages();

        if (productModel.getName().isEmpty()) {
            result.reject(productService.getErrorMessages("nameBlank"));
        }

        if (productModel.getPrice().isEmpty()) {
            result.reject(productService.getErrorMessages("priceBlank"));

        }

        if (productModel.getQuantity().isEmpty()) {
            result.reject(productService.getErrorMessages("quantityBlank"));

        }

        if (!productModel.getPrice().isEmpty()) {

            try {

                Double.parseDouble(productModel.getPrice());

                if (productModel.getPrice() == null) {
                    result.reject(productService.getErrorMessages("priceNotADouble"));
                }

                if (Double.parseDouble(productModel.getPrice()) < 0.01) {
                    result.reject(productService.getErrorMessages("priceNotPositive"));
                }

            } catch (NumberFormatException e) {
                result.reject(productService.getErrorMessages("priceNotADouble"));
            }
        }

        if (!productModel.getQuantity().isEmpty()){

            try {

                Integer.parseInt(productModel.getQuantity());

                if (productModel.getQuantity() == null) {
                    result.reject(productService.getErrorMessages("quantityNotAnInteger"));
                }

                if (Integer.parseInt(productModel.getQuantity()) < 1) {
                    result.reject(productService.getErrorMessages("quantityNotPositive"));
                }

            } catch (NumberFormatException e) {
                result.reject(productService.getErrorMessages("quantityNotAnInteger"));
            }

        }

        //TODO implement form fields validation using the standard annotations in ProductModel class
        // Business constraints for each field is commented against it
        // Add proper error messages for each error and show all of them at the top of the page

        if (!result.hasErrors()) {
            productService.createProduct(productModel);
            return "redirect:/admin/products";
        } else {
            return "product";
        }
    }

    @PostMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestParam("delProductId") Long delProductId,Model model)
    {
        productService.deleteProduct(delProductId);
        model.addAttribute("products", productService.getAllAdminProducts());

        return "productsAdmin";
    }
}
