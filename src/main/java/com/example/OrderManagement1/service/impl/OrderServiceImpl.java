package com.example.OrderManagement1.service.impl;

import com.example.OrderManagement1.entity.Order;
import com.example.OrderManagement1.exception.ResourceNotFoundException;
import com.example.OrderManagement1.payload.OrderDto;
import com.example.OrderManagement1.repository.OrderRepository;
import com.example.OrderManagement1.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The OrderServiceImpl class implements the OrderService interface
 * to provide CRUD operations and custom queries for managing orders.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new OrderServiceImpl with the given dependencies.
     *
     * @param orderRepository The order repository to interact with the database.
     * @param modelMapper     The model mapper for converting between entities and DTOs.
     */
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(int orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
        return convertToDto(order);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        return convertToDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(int orderId, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));

        existingOrder.setId(orderDto.getCustomerId());
        existingOrder.setOrderAt(orderDto.getOrderAt());

        Order updatedOrder = orderRepository.save(existingOrder);
        return convertToDto(updatedOrder);
    }

    @Override
    public void deleteOrder(int orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("Order", "id", orderId);
        }
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(int customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByOrderDate(LocalDateTime orderDate) {
        List<Order> orders = orderRepository.findByOrderAt(orderDate);
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByOrderDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByOrderAtBetween(startDate, endDate);
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerName(String firstName, String lastName) {
        List<Order> orders = orderRepository.findByCustomerName(firstName, lastName);
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> findByCustomerIdAndOrderAtBetween(int customerId, LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByCustomerIdAndOrderAtBetween(customerId, startDate, endDate);
        return orders.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByPriceLessThan(double price) {
        List<Order> orders = orderRepository.findByPriceLessThan(price);
        return mapOrdersToDto(orders);
    }

    @Override
    public List<OrderDto> getOrdersOrderByPriceDesc() {
        List<Order> orders = orderRepository.findByOrderByPriceDesc();
        return mapOrdersToDto(orders);
    }

    /**
     * Converts an Order entity to an OrderDto object.
     *
     * @param order The Order entity to convert.
     * @return The converted OrderDto object.
     */
    private OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    /**
     * Converts an OrderDto object to an Order entity.
     *
     * @param orderDto The OrderDto object to convert.
     * @return The converted Order entity.
     */
    private Order convertToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }

    /**
     * Maps a list of Order entities to a list of OrderDto objects.
     *
     * @param orders The list of Order entities to map.
     * @return The mapped list of OrderDto objects.
     */
    private List<OrderDto> mapOrdersToDto(List<Order> orders) {
        return orders.stream()
                .map(this::mapOrderToDto)
                .collect(Collectors.toList());
    }

    /**
     * Maps an Order entity to an OrderDto object.
     *
     * @param order The Order entity to map.
     * @return The mapped OrderDto object.
     */
    private OrderDto mapOrderToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }
}
