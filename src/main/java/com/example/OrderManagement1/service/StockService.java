package com.example.OrderManagement1.service;

import com.example.OrderManagement1.payload.StockDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The StockService interface provides methods for managing stocks.
 */
public interface StockService {

    /**
     * Retrieves a list of all stocks.
     *
     * @return A list of StockDto objects representing all stocks.
     */
    List<StockDto> getAllStocks();

    /**
     * Retrieves a stock by its ID.
     *
     * @param stockId The ID of the stock to retrieve.
     * @return The StockDto object representing the stock.
     */
    StockDto getStockById(int stockId);

    /**
     * Creates a new stock.
     *
     * @param stockDto The StockDto object representing the stock to create.
     * @return The StockDto object representing the created stock.
     */
    StockDto createStock(StockDto stockDto);

    /**
     * Updates an existing stock.
     *
     * @param stockId   The ID of the stock to update.
     * @param stockDto  The StockDto object representing the updated stock data.
     * @return The StockDto object representing the updated stock.
     */
    StockDto updateStock(int stockId, StockDto stockDto);

    /**
     * Deletes a stock.
     *
     * @param stockId The ID of the stock to delete.
     */
    void deleteStock(int stockId);

    /**
     * Retrieves a list of stocks for a specific product.
     *
     * @param productId The ID of the product.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getStocksByProductId(int productId);

    /**
     * Retrieves a list of stocks with the specified quality.
     *
     * @param quality The quality of the stocks to retrieve.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getStocksByQuality(int quality);

    /**
     * Retrieves a list of stocks updated within the specified date range.
     *
     * @param startDate The start date of the date range.
     * @param endDate   The end date of the date range.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getStocksByUpdateDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Retrieves a list of stocks for a specific product and quality.
     *
     * @param productId The ID of the product.
     * @param quality   The quality of the stocks.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getStocksByProductIdAndQuality(int productId, int quality);

    /**
     * Retrieves a list of stocks with a quantity greater than the specified value.
     *
     * @param quantity The minimum quantity of the stocks to retrieve.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getByQuantityGreaterThan(int quantity);

    /**
     * Retrieves a list of stocks updated between the specified start and end date times.
     *
     * @param startDate The start date and time of the date range.
     * @param endDate   The end date and time of the date range.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getByUpdateAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Retrieves a list of stocks for a specific product with a quantity greater than the specified value.
     *
     * @param productId The ID of the product.
     * @param quantity  The minimum quantity of the stocks.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getByProductIdAndQuantityGreaterThan(int productId, int quantity);

    /**
     * Retrieves a list of stocks for a specific product updated between the specified start and end date times.
     *
     * @param productId The ID of the product.
     * @param startDate The start date and time of the date range.
     * @param endDate   The end date and time of the date range.
     * @return A list of StockDto objects representing the stocks.
     */
    List<StockDto> getByProductIdAndUpdateAtBetween(int productId, LocalDateTime startDate, LocalDateTime endDate);
}
