package com.openclassrooms.shopmanager.product;

import javax.validation.constraints.*;

public class ProductModel {

    private Long id;

    @NotBlank(message = "MissingName")
    private String name;            // Required

    private String description;
    private String details;

    @NotBlank(message = "MissingQuantity")
    @Min(1)
    private String  quantity;       // Required, Integer, Greater than zero

    @NotBlank(message = "MissingPrice")
    @DecimalMin(value = "1.00")
    private String  price;          // Required, Numeric, Greater than zero

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
