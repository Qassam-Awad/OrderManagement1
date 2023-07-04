package com.example.OrderManagement1.payload;

import lombok.Data;

/**
 * The LoginDto class represents the data transfer object for login information.
 * It contains the username or email and password properties.
 */
@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}

