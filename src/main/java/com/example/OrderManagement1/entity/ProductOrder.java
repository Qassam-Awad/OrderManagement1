package com.example.OrderManagement1.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * The ProductOrder entity represents the relationship between a product and an order.
 * It contains additional information such as quantity, price, and VAT for a specific product in an order.
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_order")
public class ProductOrder {
    @EmbeddedId
    private ProductOrderId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;

    private int quantity;
    private double price;
    private double vat;
}

