package com.example.OrderManagement1.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * The CustomerDto class represents the data transfer object for Customer information.
 * It contains the properties related to the customer and includes validation annotations.
 */
@ApiModel(description = "Customer model information")
@Data
public class CustomerDto {
    @ApiModelProperty(value = "Customer ID")
    private int id;

    @ApiModelProperty(value = "Customer email")
    @NotEmpty(message = "Email name must not be empty")
    private String email;

    @ApiModelProperty(value = "Customer password")
    @NotEmpty(message = "Password name must not be empty")
    private String password;

    @ApiModelProperty(value = "Customer first name")
    @NotEmpty(message = "First name must not be empty")
    private String firstName;

    @ApiModelProperty(value = "Customer last name")
    @NotEmpty(message = "Last name must not be empty")
    private String lastName;

    @ApiModelProperty(value = "Customer date of birth")
    @NotNull(message = "Born at must not be null")
    private LocalDate bornAt;
}

