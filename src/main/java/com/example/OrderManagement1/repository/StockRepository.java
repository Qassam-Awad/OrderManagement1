package com.example.OrderManagement1.repository;

import com.example.OrderManagement1.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The StockRepository interface is responsible for performing database operations related to the Stock entity.
 */
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByProductId(int productId);
    List<Stock> findByQuantityGreaterThan(int quantity);
    List<Stock> findByUpdateAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Stock> findByProductIdAndQuantityGreaterThan(int productId, int quantity);
    List<Stock> findByProductIdAndUpdateAtBetween(int productId, LocalDateTime startDate, LocalDateTime endDate);

    List<Stock> findByQuality(int quality);

    List<Stock> findByUpdateDateBetween(LocalDate startDate, LocalDate endDate);

    List<Stock> findByProductIdAndQuality(int productId, int quality);
}

