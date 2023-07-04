package com.example.OrderManagement1.service.impl;

import com.example.OrderManagement1.entity.ProductOrder;
import com.example.OrderManagement1.entity.ProductOrderId;
import com.example.OrderManagement1.exception.ResourceNotFoundException;
import com.example.OrderManagement1.payload.ProductOrderDto;
import com.example.OrderManagement1.repository.ProductOrderRepository;
import com.example.OrderManagement1.service.ProductOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private final ProductOrderRepository productOrderRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository, ModelMapper modelMapper) {
        this.productOrderRepository = productOrderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductOrderDto> getAllProductOrders() {
        List<ProductOrder> productOrders = productOrderRepository.findAll();
        return productOrders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductOrderDto getProductOrderById(ProductOrderId productOrderId) {
        ProductOrder productOrder = getProductOrder(productOrderId);
        return convertToDto(productOrder);
    }

    @Override
    public ProductOrderDto createProductOrder(ProductOrderDto productOrderDto) {
        ProductOrder productOrder = convertToEntity(productOrderDto);
        ProductOrder savedProductOrder = productOrderRepository.save(productOrder);
        return convertToDto(savedProductOrder);
    }

    @Override
    public ProductOrderDto updateProductOrder(ProductOrderId productOrderId, ProductOrderDto productOrderDto) {
        ProductOrder existingProductOrder = getProductOrder(productOrderId);
        updateProductOrderData(existingProductOrder, productOrderDto);
        ProductOrder updatedProductOrder = productOrderRepository.save(existingProductOrder);
        return convertToDto(updatedProductOrder);
    }

    @Override
    public void deleteProductOrder(ProductOrderId productOrderId) {
        ProductOrder productOrder = getProductOrder(productOrderId);
        productOrderRepository.delete(productOrder);
    }

    @Override
    public List<ProductOrderDto> getProductOrdersByProductAndOrderId(ProductOrderId productOrderId) {
        List<ProductOrder> productOrders = productOrderRepository.findByProductIdAndOrderId(productOrderId);
        return productOrders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductOrder getProductOrder(ProductOrderId productOrderId) {
        return productOrderRepository.findById(productOrderId)
                .orElseThrow(() -> new ResourceNotFoundException("Product Order", "id", productOrderId));
    }

    private ProductOrderDto convertToDto(ProductOrder productOrder) {
        return modelMapper.map(productOrder, ProductOrderDto.class);
    }

    private ProductOrder convertToEntity(ProductOrderDto productOrderDto) {
        return modelMapper.map(productOrderDto, ProductOrder.class);
    }

    @Override
    public void updateProductOrderData(ProductOrder existingProductOrder, ProductOrderDto productOrderDto) {
        existingProductOrder.setQuantity(productOrderDto.getQuantity());
        existingProductOrder.setPrice(productOrderDto.getPrice());
        existingProductOrder.setVat(productOrderDto.getVat());
    }
}
