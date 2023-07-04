package com.example.OrderManagement1.service;

import com.example.OrderManagement1.payload.OrderDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The OrderService interface provides methods for managing orders.
 */
public interface OrderService {

    /**
     * Retrieves a list of all orders.
     *
     * @return A list of OrderDto objects representing all orders.
     */
    List<OrderDto> getAllOrders();

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The OrderDto object representing the order.
     */
    OrderDto getOrderById(int orderId);

    /**
     * Creates a new order.
     *
     * @param orderDto The OrderDto object representing the order to create.
     * @return The OrderDto object representing the created order.
     */
    OrderDto createOrder(OrderDto orderDto);

    /**
     * Updates an existing order.
     *
     * @param orderId  The ID of the order to update.
     * @param orderDto The OrderDto object representing the updated order data.
     * @return The OrderDto object representing the updated order.
     */
    OrderDto updateOrder(int orderId, OrderDto orderDto);

    /**
     * Deletes an order.
     *
     * @param orderId The ID of the order to delete.
     */
    void deleteOrder(int orderId);

    /**
     * Retrieves a list of orders for a specific customer.
     *
     * @param customerId The ID of the customer.
     * @return A list of OrderDto objects representing the orders.
     */
    List<OrderDto> getOrdersByCustomerId(int customerId);

    /**
     * Retrieves a list of orders placed on a specific date.
     *
     * @param orderDate The date to search for orders.
     * @return A list of OrderDto objects representing the orders.
     */
    List<OrderDto> getOrdersByOrderDate(LocalDateTime orderDate);

    /**
     * Retrieves a list of orders placed within a date range.
     *
     * @param startDate The start date of the range.
     * @param endDate   The end date of the range.
     * @return A list of OrderDto objects representing the orders.
     */
    List<OrderDto> getOrdersByOrderDateRange(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Retrieves a list of orders placed by a customer with a specific first and last name.
     *
     * @param firstName The first name of the customer.
     * @param lastName  The last name of the customer.
     * @return A list of OrderDto objects representing the orders.
     */
    List<OrderDto> getOrdersByCustomerName(String firstName, String lastName);

    /**
     * Retrieves a list of orders for a specific customer placed within a date range.
     *
     * @param customerId The ID of the customer.
     * @param startDate  The start date of the range.
     * @param endDate    The end date of the range.
     * @return A list of OrderDto objects representing the orders.
     */
    List<OrderDto> findByCustomerIdAndOrderAtBetween(int customerId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Retrieves a list of orders with a price less than the specified value.
     *
     * @param price The maximum price.
     * @return A list of OrderDto objects representing the orders.
     */
    List<OrderDto> getOrdersByPriceLessThan(double price);

    /**
     * Retrieves a list of orders ordered by price in descending order.
     *
     * @return A list of OrderDto objects representing the orders.
     */
    List<OrderDto> getOrdersOrderByPriceDesc();
}
