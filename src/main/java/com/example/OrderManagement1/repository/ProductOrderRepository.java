package com.example.OrderManagement1.repository;

import com.example.OrderManagement1.entity.ProductOrder;
import com.example.OrderManagement1.entity.ProductOrderId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The ProductOrderRepository interface is responsible for performing database operations related to the ProductOrder entity.
 */
public interface ProductOrderRepository extends JpaRepository<ProductOrder, ProductOrderId> {
    List<ProductOrder> findByProductIdAndOrderId(ProductOrderId productOrderId);
    List<ProductOrder> findByQuantityGreaterThan(int quantity);
    List<ProductOrder> findByProductId(int productId);
    List<ProductOrder> findByOrderId(int orderId);
    List<ProductOrder> findByQuantityBetween(int minQuantity, int maxQuantity);
    List<ProductOrder> findByPriceGreaterThan(double price);
}

