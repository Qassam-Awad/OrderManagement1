package com.example.OrderManagement1.repository;

import com.example.OrderManagement1.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The OrderRepository interface is responsible for performing database operations related to the Order entity.
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByOrderAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByCustomerId(int customerId);
    List<Order> findByOrderAt(LocalDateTime dateTime);
    List<Order> findByCustomerIdAndOrderAtBetween(int customerId, LocalDateTime startDate, LocalDateTime endDate);

    List<Order> findByCustomerName(String customerName, String lastName);

    List<Order> findByPriceLessThan(double price);

    List<Order> findByOrderByPriceDesc();
}

