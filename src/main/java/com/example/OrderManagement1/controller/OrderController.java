package com.example.OrderManagement1.controller;

import com.example.OrderManagement1.payload.OrderDto;
import com.example.OrderManagement1.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "Order Management")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves all orders.
     *
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping
    @ApiOperation("Retrieve all orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return ResponseEntity containing the OrderDto object
     */
    @GetMapping("/{orderId}")
    @ApiOperation("Retrieve an order by its ID")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable int orderId) {
        OrderDto order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    /**
     * Creates a new order.
     *
     * @param orderDto the OrderDto object containing the order details
     * @return ResponseEntity containing the created OrderDto object
     */
    @PostMapping
    @ApiOperation("Create a new order")
    public ResponseEntity<OrderDto> createOrder(@RequestBody @Valid OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    /**
     * Updates an existing order.
     *
     * @param orderId   the ID of the order to update
     * @param orderDto  the OrderDto object containing the updated order details
     * @return ResponseEntity containing the updated OrderDto object
     */
    @PutMapping("/{orderId}")
    @ApiOperation("Update an existing order")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable int orderId, @RequestBody @Valid OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(orderId, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    /**
     * Deletes an order.
     *
     * @param orderId the ID of the order to delete
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{orderId}")
    @ApiOperation("Delete an order")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves orders by customer ID.
     *
     * @param customerId the ID of the customer
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping("/customer/{customerId}")
    @ApiOperation("Retrieve orders by customer ID")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(@PathVariable int customerId) {
        List<OrderDto> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves orders by order date.
     *
     * @param orderDate the order date
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping("/order-date/{orderDate}")
    @ApiOperation("Retrieve orders by order date")
    public ResponseEntity<List<OrderDto>> getOrdersByOrderDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime orderDate) {
        List<OrderDto> orders = orderService.getOrdersByOrderDate(orderDate);
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves orders by order date range.
     *
     * @param startDate the start date of the order range
     * @param endDate   the end date of the order range
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping("/order-date-range")
    @ApiOperation("Retrieve orders by order date range")
    public ResponseEntity<List<OrderDto>> getOrdersByOrderDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime endDate) {
        List<OrderDto> orders = orderService.getOrdersByOrderDateRange(startDate, endDate);
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves orders by customer name.
     *
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping("/customer-name")
    @ApiOperation("Retrieve orders by customer name")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerName(@RequestParam String firstName, @RequestParam String lastName) {
        List<OrderDto> orders = orderService.getOrdersByCustomerName(firstName, lastName);
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves orders with a price less than the specified value.
     *
     * @param price the maximum price of the orders
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping("/price-less-than/{price}")
    @ApiOperation("Retrieve orders with a price less than the specified value")
    public ResponseEntity<List<OrderDto>> getOrdersByPriceLessThan(@PathVariable double price) {
        List<OrderDto> orders = orderService.getOrdersByPriceLessThan(price);
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves orders sorted by price in descending order.
     *
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping("/order-by-price-desc")
    @ApiOperation("Retrieve orders sorted by price in descending order")
    public ResponseEntity<List<OrderDto>> getOrdersOrderByPriceDesc() {
        List<OrderDto> orders = orderService.getOrdersOrderByPriceDesc();
        return ResponseEntity.ok(orders);
    }

    /**
     * Retrieves orders by customer ID and order date range.
     *
     * @param customerId the ID of the customer
     * @param startDate  the start date of the order range
     * @param endDate    the end date of the order range
     * @return ResponseEntity containing a list of OrderDto objects
     */
    @GetMapping("/customer/{customerId}/date/{startDate}/{endDate}")
    @ApiOperation("Retrieve orders by customer ID and order date range")
    public ResponseEntity<List<OrderDto>> findByCustomerIdAndOrderAtBetween(
            @PathVariable int customerId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime endDate) {
        List<OrderDto> orders = orderService.findByCustomerIdAndOrderAtBetween(customerId, startDate, endDate);
        return ResponseEntity.ok(orders);
    }

}
