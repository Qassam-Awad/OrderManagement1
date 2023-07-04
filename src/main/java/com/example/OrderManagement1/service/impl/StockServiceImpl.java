package com.example.OrderManagement1.service.impl;

import com.example.OrderManagement1.entity.Stock;
import com.example.OrderManagement1.exception.ResourceNotFoundException;
import com.example.OrderManagement1.payload.StockDto;
import com.example.OrderManagement1.repository.StockRepository;
import com.example.OrderManagement1.service.StockService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, ModelMapper modelMapper) {
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(int stockId) {
        Stock stock = getStock(stockId);
        return convertToDto(stock);
    }

    @Override
    public StockDto createStock(StockDto stockDto) {
        Stock stock = convertToEntity(stockDto);
        Stock savedStock = stockRepository.save(stock);
        return convertToDto(savedStock);
    }

    @Override
    public StockDto updateStock(int stockId, StockDto stockDto) {
        Stock existingStock = getStock(stockId);
        existingStock.setId(stockDto.getProductId());
        existingStock.setQuantity(stockDto.getQuantity());
        existingStock.setUpdatedAt(stockDto.getUpdatedAt());
        Stock updatedStock = stockRepository.save(existingStock);
        return convertToDto(updatedStock);
    }

    @Override
    public void deleteStock(int stockId) {
        Stock stock = getStock(stockId);
        stockRepository.delete(stock);
    }

    @Override
    public List<StockDto> getStocksByProductId(int productId) {
        List<Stock> stocks = stockRepository.findByProductId(productId);
        return stocks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getStocksByQuality(int quality) {
        List<Stock> stocks = stockRepository.findByQuality(quality);
        return stocks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getStocksByUpdateDateRange(LocalDate startDate, LocalDate endDate) {
        List<Stock> stocks = stockRepository.findByUpdateDateBetween(startDate, endDate);
        return stocks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDto> getStocksByProductIdAndQuality(int productId, int quality) {
        List<Stock> stocks = stockRepository.findByProductIdAndQuality(productId, quality);
        return stocks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<StockDto> getByQuantityGreaterThan(int quantity) {
        List<Stock> stocks = stockRepository.findByQuantityGreaterThan(quantity);
        return mapStocksToDto(stocks);
    }

    @Override
    public List<StockDto> getByUpdateAtBetween(LocalDateTime startDate, LocalDateTime endDate) {
        List<Stock> stocks = stockRepository.findByUpdateAtBetween(startDate, endDate);
        return mapStocksToDto(stocks);
    }

    @Override
    public List<StockDto> getByProductIdAndQuantityGreaterThan(int productId, int quantity) {
        List<Stock> stocks = stockRepository.findByProductIdAndQuantityGreaterThan(productId, quantity);
        return mapStocksToDto(stocks);
    }

    @Override
    public List<StockDto> getByProductIdAndUpdateAtBetween(int productId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Stock> stocks = stockRepository.findByProductIdAndUpdateAtBetween(productId, startDate, endDate);
        return mapStocksToDto(stocks);
    }


    private Stock getStock(int stockId) {
        return stockRepository.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock", "id", stockId));
    }
    private List<StockDto> mapStocksToDto(List<Stock> stocks) {
        return stocks.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StockDto convertToDto(Stock stock) {
        return modelMapper.map(stock, StockDto.class);
    }

    private Stock convertToEntity(StockDto stockDto) {
        return modelMapper.map(stockDto, Stock.class);
    }
}
