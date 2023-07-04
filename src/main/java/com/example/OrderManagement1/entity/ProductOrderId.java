package com.example.OrderManagement1.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * The ProductOrderId represents the composite primary key for the ProductOrder entity.
 * It consists of the productId and orderId to uniquely identify the relationship between a product and an order.
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductOrderId implements Serializable {
    private int productId;
    private int orderId;
}

