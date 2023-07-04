package com.example.OrderManagement1.repository;

import com.example.OrderManagement1.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The ProductRepository interface is responsible for performing database operations related to the Product entity.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStockableTrue(boolean isTrue);
    List<Product> findByPriceLessThan(double price);
    List<Product> findByVatGreaterThan(double vat);
    List<Product> findByOrderByPriceDesc();
    List<Product> findBySlug(String slug);
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByReference(String reference);
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByVatBetween(double minVat, double maxVat);

    List<Product> findByVat(double vat);
}

