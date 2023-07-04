package com.example.OrderManagement1.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * The OrderDto class represents the data transfer object for Order information.
 * It contains the properties related to the order and includes validation annotations.
 */
@ApiModel(description = "Order model information")
@Data
public class OrderDto {
    @ApiModelProperty(value = "Order ID")
    private int id;

    @ApiModelProperty(value = "Customer ID")
    @NotNull(message = "Customer ID must not be null")
    private Integer customerId;

    @ApiModelProperty(value = "Order date and time")
    @NotNull(message = "Order at must not be null")
    private LocalDateTime orderAt;
}

