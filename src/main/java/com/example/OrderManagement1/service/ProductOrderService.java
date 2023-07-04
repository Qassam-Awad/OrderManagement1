package com.example.OrderManagement1.service;

import com.example.OrderManagement1.entity.ProductOrder;
import com.example.OrderManagement1.entity.ProductOrderId;
import com.example.OrderManagement1.payload.ProductOrderDto;

import java.util.List;

/**
 * The ProductOrderService interface provides methods for managing product orders.
 */
public interface ProductOrderService {

    /**
     * Retrieves a list of all product orders.
     *
     * @return A list of ProductOrderDto objects representing all product orders.
     */
    List<ProductOrderDto> getAllProductOrders();

    /**
     * Retrieves a product order by its ID.
     *
     * @param productOrderId The ID of the product order to retrieve.
     * @return The ProductOrderDto object representing the product order.
     */
    ProductOrderDto getProductOrderById(ProductOrderId productOrderId);

    /**
     * Creates a new product order.
     *
     * @param productOrderDto The ProductOrderDto object representing the product order to create.
     * @return The ProductOrderDto object representing the created product order.
     */
    ProductOrderDto createProductOrder(ProductOrderDto productOrderDto);

    /**
     * Updates an existing product order.
     *
     * @param productOrderId  The ID of the product order to update.
     * @param productOrderDto The ProductOrderDto object representing the updated product order data.
     * @return The ProductOrderDto object representing the updated product order.
     */
    ProductOrderDto updateProductOrder(ProductOrderId productOrderId, ProductOrderDto productOrderDto);

    /**
     * Deletes a product order.
     *
     * @param productOrderId The ID of the product order to delete.
     */
    void deleteProductOrder(ProductOrderId productOrderId);

    /**
     * Retrieves a list of product orders for a specific product and order.
     *
     * @param productOrderId The ID of the product order.
     * @return A list of ProductOrderDto objects representing the product orders.
     */
    List<ProductOrderDto> getProductOrdersByProductAndOrderId(ProductOrderId productOrderId);

    /**
     * Retrieves the product order entity for the specified product order ID.
     *
     * @param productOrderId The ID of the product order.
     * @return The ProductOrder entity.
     */
    ProductOrder getProductOrder(ProductOrderId productOrderId);

    /**
     * Updates the data of an existing product order entity with the data from the specified DTO.
     *
     * @param existingProductOrder The existing ProductOrder entity.
     * @param productOrderDto      The ProductOrderDto containing the updated data.
     */
    void updateProductOrderData(ProductOrder existingProductOrder, ProductOrderDto productOrderDto);
}
