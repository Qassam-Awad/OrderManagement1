package com.example.OrderManagement1.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * The Product entity represents a product available in the system.
 */
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String slug;
    private String name;
    private String reference;
    private double price;
    private double vat;
    private boolean stockable;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOrder> productOrders;
}

