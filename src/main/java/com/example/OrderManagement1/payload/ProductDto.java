package com.example.OrderManagement1.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * The ProductDto class represents the data transfer object for Product information.
 * It contains the properties related to the product and includes validation annotations.
 */
@ApiModel(description = "Product model information")
@Data
public class ProductDto {
    @ApiModelProperty(value = "Product ID")
    private int id;

    @ApiModelProperty(value = "Product slug")
    @NotEmpty(message = "Slug must not be empty")
    private String slug;

    @ApiModelProperty(value = "Product name")
    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Reference must not be empty")
    private String reference;

    @ApiModelProperty(value = "Product price")
    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.0")
    private Double price;

    @ApiModelProperty(value = "Product VAT")
    @NotNull(message = "VAT must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "VAT must be greater than 0.0")
    private Double vat;

    @ApiModelProperty(value = "Is product stockable")
    private boolean stockable;
}

