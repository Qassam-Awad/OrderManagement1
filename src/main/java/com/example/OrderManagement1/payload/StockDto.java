package com.example.OrderManagement1.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The StockDto class represents the data transfer object for Stock information.
 * It contains the properties related to the stock and includes validation annotations.
 */
@ApiModel(description = "Stock model information")
@Data
public class StockDto {
    @ApiModelProperty(value = "Stock ID")
    private int id;

    @ApiModelProperty(value = "Product ID")
    @NotNull(message = "Product ID must not be null")
    private Integer productId;

    @ApiModelProperty(value = "Quantity")
    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must be a positive number")
    private Integer quantity;

    @ApiModelProperty(value = "Updated At")
    @NotNull(message = "UpdatedAt must not be null")
    private LocalDateTime updatedAt;
}

