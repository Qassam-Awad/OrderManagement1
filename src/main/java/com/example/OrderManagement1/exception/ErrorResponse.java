package com.example.OrderManagement1.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * The ErrorResponse class represents the response object for error scenarios.
 * It contains the status code and message to be returned in the response.
 */
@Setter
@Getter
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

