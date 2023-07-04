/**
 * The `StockController` class handles HTTP requests related to stocks.
 * It provides endpoints for retrieving, creating, updating, and deleting stocks.
 */
package com.example.OrderManagement1.controller;

import com.example.OrderManagement1.payload.StockDto;
import com.example.OrderManagement1.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/stocks")
@Api(tags = "Stock Management")
public class StockController {
    private final StockService stockService;

    /**
     * Constructs a new `StockController` with the specified `StockService`.
     *
     * @param stockService the StockService used to perform stock-related operations.
     */
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Retrieves all stocks.
     *
     * @return a list of `StockDto` objects representing all stocks.
     */
    @GetMapping
    @ApiOperation("Retrieve all stocks")
    public ResponseEntity<List<StockDto>> getAllStocks() {
        List<StockDto> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }

    /**
     * Retrieves a stock by its stock ID.
     *
     * @param stockId the ID of the stock.
     * @return the `StockDto` object representing the stock.
     */
    @GetMapping("/{stockId}")
    @ApiOperation("Retrieve a stock by its stock ID")
    public ResponseEntity<StockDto> getStockById(@PathVariable int stockId) {
        StockDto stock = stockService.getStockById(stockId);
        return ResponseEntity.ok(stock);
    }

    /**
     * Creates a new stock.
     *
     * @param stockDto the `StockDto` object containing the stock details.
     * @return the created `StockDto` object.
     */
    @PostMapping
    @ApiOperation("Create a new stock")
    public ResponseEntity<StockDto> createStock(@RequestBody StockDto stockDto) {
        StockDto createdStock = stockService.createStock(stockDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    /**
     * Updates a stock with the specified stock ID.
     *
     * @param stockId   the ID of the stock.
     * @param stockDto  the `StockDto` object containing the updated stock details.
     * @return the updated `StockDto` object.
     */
    @PutMapping("/{stockId}")
    @ApiOperation("Update a stock with the specified stock ID")
    public ResponseEntity<StockDto> updateStock(@PathVariable int stockId, @RequestBody StockDto stockDto) {
        StockDto updatedStock = stockService.updateStock(stockId, stockDto);
        return ResponseEntity.ok(updatedStock);
    }

    /**
     * Deletes a stock with the specified stock ID.
     *
     * @param stockId the ID of the stock.
     * @return a ResponseEntity with no content.
     */
    @DeleteMapping("/{stockId}")
    @ApiOperation("Delete a stock with the specified stock ID")
    public ResponseEntity<Void> deleteStock(@PathVariable int stockId) {
        stockService.deleteStock(stockId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves stocks by the specified product ID.
     *
     * @param productId the ID of the product.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/product/{productId}")
    @ApiOperation("Retrieve stocks by the specified product ID")
    public ResponseEntity<List<StockDto>> getStocksByProductId(@PathVariable int productId) {
        List<StockDto> stocks = stockService.getStocksByProductId(productId);
        return ResponseEntity.ok(stocks);
    }

    /**
     * Retrieves stocks by the specified quality.
     *
     * @param quality the quality of the stocks.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/quality/{quality}")
    @ApiOperation("Retrieve stocks by the specified quality")
    public ResponseEntity<List<StockDto>> getStocksByQuality(@PathVariable int quality) {
        List<StockDto> stocks = stockService.getStocksByQuality(quality);
        return ResponseEntity.ok(stocks);
    }

    /**
     * Retrieves stocks by the specified update date range.
     *
     * @param startDate the start date of the update date range.
     * @param endDate   the end date of the update date range.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/update-date-range")
    @ApiOperation("Retrieve stocks by the specified update date range")
    public ResponseEntity<List<StockDto>> getStocksByUpdateDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<StockDto> stocks = stockService.getStocksByUpdateDateRange(startDate, endDate);
        return ResponseEntity.ok(stocks);
    }

    /**
     * Retrieves stocks by the specified product ID and quality.
     *
     * @param productId the ID of the product.
     * @param quality   the quality of the stocks.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/product/{productId}/quality/{quality}")
    @ApiOperation("Retrieve stocks by the specified product ID and quality")
    public ResponseEntity<List<StockDto>> getStocksByProductIdAndQuality(
            @PathVariable int productId,
            @PathVariable int quality) {
        List<StockDto> stocks = stockService.getStocksByProductIdAndQuality(productId, quality);
        return ResponseEntity.ok(stocks);
    }

    /**
     * Retrieves stocks with a quantity greater than the specified quantity.
     *
     * @param quantity the minimum quantity of the stocks.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/quantityGreaterThan/{quantity}")
    @ApiOperation("Retrieve stocks with a quantity greater than the specified quantity")
    public List<StockDto> getByQuantityGreaterThan(@PathVariable int quantity) {
        return stockService.getByQuantityGreaterThan(quantity);
    }

    /**
     * Retrieves stocks with an update date between the specified start and end dates.
     *
     * @param startDate the start date of the update date range.
     * @param endDate   the end date of the update date range.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/updateAtBetween")
    @ApiOperation("Retrieve stocks with an update date between the specified start and end dates")
    public List<StockDto> getByUpdateAtBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return stockService.getByUpdateAtBetween(startDate, endDate);
    }

    /**
     * Retrieves stocks with the specified product ID and a quantity greater than the specified quantity.
     *
     * @param productId the ID of the product.
     * @param quantity  the minimum quantity of the stocks.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/productIdAndQuantityGreaterThan")
    @ApiOperation("Retrieve stocks with the specified product ID and a quantity greater than the specified quantity")
    public List<StockDto> getByProductIdAndQuantityGreaterThan(
            @RequestParam int productId, @RequestParam int quantity) {
        return stockService.getByProductIdAndQuantityGreaterThan(productId, quantity);
    }

    /**
     * Retrieves stocks with the specified product ID and an update date between the specified start and end dates.
     *
     * @param productId the ID of the product.
     * @param startDate the start date of the update date range.
     * @param endDate   the end date of the update date range.
     * @return a list of `StockDto` objects representing the stocks.
     */
    @GetMapping("/productIdAndUpdateAtBetween")
    @ApiOperation("Retrieve stocks with the specified product ID and an update date between the specified start and end dates")
    public List<StockDto> getByProductIdAndUpdateAtBetween(
            @RequestParam int productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return stockService.getByProductIdAndUpdateAtBetween(productId, startDate, endDate);
    }
}
