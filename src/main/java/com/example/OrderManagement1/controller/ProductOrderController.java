/**
 * The `ProductOrderController` class handles HTTP requests related to product orders.
 * It provides endpoints for retrieving, creating, updating, and deleting product orders.
 */
package com.example.OrderManagement1.controller;

import com.example.OrderManagement1.entity.ProductOrderId;
import com.example.OrderManagement1.payload.ProductOrderDto;
import com.example.OrderManagement1.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-orders")
@Api(tags = "Product Order Management")
public class ProductOrderController {

    private final ProductOrderService productOrderService;

    /**
     * Constructs a new `ProductOrderController` with the specified `ProductOrderService`.
     *
     * @param productOrderService the ProductOrderService used to perform product order-related operations.
     */
    public ProductOrderController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    /**
     * Retrieves all product orders.
     *
     * @return a list of `ProductOrderDto` objects representing all product orders.
     */
    @GetMapping
    @ApiOperation("Retrieve all product orders")
    public List<ProductOrderDto> getAllProductOrders() {
        return productOrderService.getAllProductOrders();
    }

    /**
     * Retrieves a product order by its product ID and order ID.
     *
     * @param productId the ID of the product.
     * @param orderId   the ID of the order.
     * @return the `ProductOrderDto` object representing the product order.
     */
    @GetMapping("/{productId}/{orderId}")
    @ApiOperation("Retrieve a product order by its product ID and order ID")
    public ProductOrderDto getProductOrderById(
            @PathVariable int productId, @PathVariable int orderId) {
        ProductOrderId productOrderId = new ProductOrderId(productId, orderId);
        return productOrderService.getProductOrderById(productOrderId);
    }

    /**
     * Creates a new product order.
     *
     * @param productOrderDto the `ProductOrderDto` object containing the product order details.
     * @return the created `ProductOrderDto` object.
     */
    @PostMapping
    @ApiOperation("Create a new product order")
    public ProductOrderDto createProductOrder(@RequestBody ProductOrderDto productOrderDto) {
        return productOrderService.createProductOrder(productOrderDto);
    }

    /**
     * Updates a product order with the specified product ID and order ID.
     *
     * @param productId       the ID of the product.
     * @param orderId         the ID of the order.
     * @param productOrderDto the `ProductOrderDto` object containing the updated product order details.
     * @return the updated `ProductOrderDto` object.
     */
    @PutMapping("/{productId}/{orderId}")
    @ApiOperation("Update a product order with the specified product ID and order ID")
    public ProductOrderDto updateProductOrder(
            @PathVariable int productId, @PathVariable int orderId, @RequestBody ProductOrderDto productOrderDto) {
        ProductOrderId productOrderId = new ProductOrderId(productId, orderId);
        return productOrderService.updateProductOrder(productOrderId, productOrderDto);
    }

    /**
     * Deletes a product order with the specified product ID and order ID.
     *
     * @param productId the ID of the product.
     * @param orderId   the ID of the order.
     */
    @DeleteMapping("/{productId}/{orderId}")
    @ApiOperation("Delete a product order with the specified product ID and order ID")
    public void deleteProductOrder(@PathVariable int productId, @PathVariable int orderId) {
        ProductOrderId productOrderId = new ProductOrderId(productId, orderId);
        productOrderService.deleteProductOrder(productOrderId);
    }

    /**
     * Retrieves product orders by the specified product ID and order ID.
     *
     * @param productId the ID of the product.
     * @param orderId   the ID of the order.
     * @return a list of `ProductOrderDto` objects representing the product orders.
     */
    @GetMapping("/product-and-order/{productId}/{orderId}")
    @ApiOperation("Retrieve product orders by the specified product ID and order ID")
    public List<ProductOrderDto> getProductOrdersByProductAndOrderId(
            @PathVariable int productId, @PathVariable int

            orderId) {
        ProductOrderId productOrderId = new ProductOrderId(productId, orderId);
        return productOrderService.getProductOrdersByProductAndOrderId(productOrderId);
    }

}