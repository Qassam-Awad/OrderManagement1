package com.example.OrderManagement1.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

/**
 * The ProductOrderDto class represents the data transfer object for Product Order information.
 * It contains the properties related to the product order and includes validation annotations.
 */
@ApiModel(description = "Product order model information")
@Data
public class ProductOrderDto {
    @ApiModelProperty(value = "Product ID")
    @NotNull(message = "Product ID must not be null")
    private Integer productId;

    @ApiModelProperty(value = "Order ID")
    @NotNull(message = "Order ID must not be null")
    private Integer orderId;

    @ApiModelProperty(value = "Quantity")
    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must be a positive number")
    private Integer quantity;

    @ApiModelProperty(value = "Price")
    @NotNull(message = "Price must not be null")
    @PositiveOrZero(message = "Price must be a non-negative number")
    private Double price;

    @ApiModelProperty(value = "VAT")
    @NotNull(message = "VAT must not be null")
    @PositiveOrZero(message = "VAT must be a non-negative number")
    private Double vat;
}

